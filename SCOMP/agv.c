char * ip = "192.168.60.1";
char * cli = "A";
#include "communication.h"
#include "agv.h"
#define FREE_POSITION 0
#define STATIONARY_OBSTACLE 1
#define FREE_DOCK 2
#define SPACE_OCCUPIED_BY_AGV 3
#define DOCK_OCCUPIED_BY_AGV 4
#define WAREHOUSE_WIDTH 18
#define WAREHOUSE_LENGTH 20
#define MAX_STR_SIZE 1000
#define NUMBER_OF_SENSORS 8
#define UP 1
#define DOWN 2
#define RIGHT 3
#define LEFT 4
#define AGV_SPEED 1
#define FREE_POSITION 0
#define STATIONARY_OBSTACLE 1
#define FREE_DOCK 2
#define SPACE_OCCUPIED_BY_AGV 3
#define DOCK_OCCUPIED_BY_AGV 4
#define WAREHOUSE_WIDTH 18
#define WAREHOUSE_LENGTH 20
#define MAX_STR_SIZE 1000
#define NUMBER_OF_SENSORS 8
#define UP 1
#define DOWN 2
#define RIGHT 3
#define LEFT 4
#define AGV_SPEED 1
typedef struct Packet{
    char version;
    char code;
    char d_length1;
    char d_length2;
    char data[MAX_STR_SIZE];
}Packet;
pthread_mutex_t mutex;
pthread_mutex_t battery_mutex;// used for accessing battery values
pthread_mutex_t current_position_mutex; // used for current position values
pthread_mutex_t direction_mutex; //used for movement direction variable
pthread_mutex_t signal_pos_mutex;
pthread_mutex_t signal_has_moved_mutex;
pthread_mutex_t obstacles_mutex;

pthread_cond_t can_move;
pthread_cond_t battery_discharge;
pthread_cond_t has_moved;

int position_flag;
int movement_direction;

int current_positionX;
int current_positionY;

int previous_positionX;
int previous_positionY;

int product_positionX;
int	product_positionY;
int battery_capacity;
int current_battery;
int obstaculos[NUMBER_OF_SENSORS] = {0,0,0,0,0,0,0,0};
int warehouse[WAREHOUSE_WIDTH][WAREHOUSE_LENGTH];

int main(){
    current_battery=50;
    battery_capacity=100;
    getWarehouse(ip,cli,warehouse);
    for(int i = 0;i < WAREHOUSE_WIDTH; i++) {
        for(int j = 0;j < WAREHOUSE_LENGTH; j++) {
			printf("%d ",warehouse[i][j]);
		}
		printf("\n");
    }
    int p [4];
	positions(ip,cli,p);
	printf("%d ",p[0]);
	printf("%d\n",p[1]);
    
    current_positionX=4;
    current_positionY=0;

    product_positionX=p[0];
    product_positionY=p[1];

    previous_positionX = current_positionX;
	previous_positionY = current_positionY;
    pthread_t threadBatteryManagement;
    pthread_t threadRoutePlanner;
    pthread_t threadSimulationEngine;
    pthread_t threadPositioning;
    pthread_t threadControlSystem;
    pthread_t threadSensors[NUMBER_OF_SENSORS];
    pthread_t threadCommunications;
    int sensor_args[NUMBER_OF_SENSORS];

    pthread_cond_init(&can_move,NULL);
    pthread_cond_init(&battery_discharge,NULL);
    pthread_cond_init(&has_moved,NULL);


    pthread_mutex_init(&mutex, NULL);
    pthread_mutex_init(&battery_mutex, NULL);
    pthread_mutex_init(&current_position_mutex, NULL);
    pthread_mutex_init(&direction_mutex,NULL);
    pthread_mutex_init(&signal_pos_mutex,NULL);
    pthread_mutex_init(&signal_has_moved_mutex,NULL);
    pthread_mutex_init(&obstacles_mutex,NULL);

    //pthread_create(&threadBatteryManagement, NULL, thread_battery_management,NULL);
    pthread_create(&threadRoutePlanner, NULL, thread_route_planner,NULL);
    pthread_create(&threadSimulationEngine, NULL, thread_simulation_engine,NULL);
    pthread_create(&threadPositioning, NULL, thread_positioning,NULL);
    pthread_create(&threadControlSystem, NULL, thread_control_system,NULL);
    for(int i=0;i<NUMBER_OF_SENSORS;i++){
        sensor_args[i]=i;
        pthread_create(&threadSensors[i],NULL,thread_sensors,(void*)&sensor_args[i]);
    }
    pthread_create(&threadCommunications, NULL, thread_communications, NULL);
    //sleep(10);

    sleep(30);
    printf("Current positon x = %d\n",current_positionX);
    printf("Current position y = %d\n",current_positionY);

    for(int i = 0;i < WAREHOUSE_WIDTH; i++) {
        for(int j = 0;j < WAREHOUSE_LENGTH; j++) {
			printf("%d ",warehouse[i][j]);
		}
		printf("\n");
    }

    for(int i = 0;i < NUMBER_OF_SENSORS; i++) {
        printf("%d. %d\n", i, obstaculos[i]);
    }
    //sleep(10);
    //pthread_exit(NULL);
    sendWarehouse(ip,cli,warehouse);
   
}
void* thread_battery_management(void *arg){
    while(1){
        pthread_mutex_lock(&current_position_mutex);
        if(warehouse[current_positionY][current_positionX]==FREE_DOCK || warehouse[current_positionY][current_positionX]==DOCK_OCCUPIED_BY_AGV){
            pthread_mutex_unlock(&current_position_mutex);
            if(current_battery<battery_capacity){
                sleep(1);
                //printf("Charging\n");
                //printf("Current battery : %d Total Capacity : %d \n",current_battery,battery_capacity);
                pthread_mutex_lock(&battery_mutex);
                current_battery++;
                pthread_mutex_unlock(&battery_mutex);
            }else{
                //printf("Battery Full\n");
                //printf("Current battery : %d Total Capacity : %d \n",current_battery,battery_capacity);
            }
        }else{
            pthread_mutex_unlock(&current_position_mutex);
            pthread_cond_wait(&battery_discharge,&battery_mutex);
            //printf("Battery Dropping\n");
            //printf("Current battery : %d Total Capacity : %d \n",current_battery,battery_capacity);
            pthread_mutex_lock(&battery_mutex);
            current_battery--;
            pthread_mutex_unlock(&battery_mutex);
        }
    }
    pthread_exit(NULL);
}
/*
*
*
*/
void* thread_route_planner(void *arg){
    printf("Current positon y = %d\n",current_positionX);
    printf("Current position x = %d\n\n",current_positionY);
    printf("Product position x = %d\n",product_positionX);
    printf("Product position y = %d\n\n",product_positionY);
    pthread_mutex_lock(&current_position_mutex);
    while(current_positionX!=product_positionX || current_positionY!=product_positionY){
		//sleep(1);
        printf("chegou\n");
        pthread_mutex_unlock(&current_position_mutex);
        pthread_mutex_lock(&current_position_mutex);
        printf("chegou 2 \n");
        position_flag=0;
        while(current_positionX!=product_positionX){
			sleep(1);
            position_flag=1;
            pthread_mutex_unlock(&current_position_mutex);
            pthread_mutex_lock(&current_position_mutex);
            if(current_positionX>product_positionX){
                if(obstaculos[0]!=1){
                    pthread_mutex_unlock(&current_position_mutex);

                    pthread_mutex_lock(&direction_mutex);
                    movement_direction=UP;
                    pthread_mutex_unlock(&direction_mutex);

                    pthread_cond_signal(&can_move);
                    pthread_cond_broadcast(&has_moved);

                }else if (current_positionY> WAREHOUSE_LENGTH/2 && obstaculos[2] != 1){

                    pthread_mutex_unlock(&current_position_mutex);
                    pthread_mutex_lock(&direction_mutex);
                    movement_direction=RIGHT;
                    pthread_mutex_unlock(&direction_mutex);
                    pthread_cond_signal(&can_move);

                    pthread_cond_broadcast(&has_moved);
                }else if(obstaculos[6] != 1){
                    pthread_mutex_unlock(&current_position_mutex);

                    pthread_mutex_lock(&direction_mutex);
                    movement_direction=LEFT;
                    pthread_mutex_unlock(&direction_mutex);
                    pthread_cond_signal(&can_move);

                    pthread_cond_broadcast(&has_moved);
                }
            }else pthread_mutex_unlock(&current_position_mutex);

            if(current_positionX<product_positionX){
                //printf("chegou 3\n");
                pthread_mutex_unlock(&current_position_mutex);
                if(obstaculos[4] != 1){
                    pthread_mutex_unlock(&current_position_mutex);
                    pthread_mutex_lock(&direction_mutex);
                    movement_direction=DOWN;
                    pthread_mutex_unlock(&direction_mutex);
                    pthread_cond_signal(&can_move);

                    pthread_cond_broadcast(&has_moved);
                }else if (current_positionY < WAREHOUSE_LENGTH/2 && obstaculos[2] != 1){
                    pthread_mutex_unlock(&current_position_mutex);
                    pthread_mutex_lock(&direction_mutex);
                    movement_direction=RIGHT;
                    pthread_mutex_unlock(&direction_mutex);
                    pthread_cond_signal(&can_move);
                    pthread_cond_broadcast(&has_moved);
                }else if (obstaculos[6] != 1){
                    pthread_mutex_unlock(&current_position_mutex);
                    pthread_mutex_lock(&direction_mutex);
                    movement_direction=LEFT;
                    pthread_mutex_unlock(&direction_mutex);
                    pthread_cond_signal(&can_move);

                    pthread_cond_broadcast(&has_moved);
                }
            }else pthread_mutex_unlock(&current_position_mutex);

        }

        if (position_flag==0){
            pthread_mutex_unlock(&current_position_mutex);
        }

        while(current_positionY!=product_positionY){
            sleep(1);
            pthread_mutex_lock(&current_position_mutex);

            if(current_positionY>product_positionY){
                if(obstaculos[6]!=1){
                    pthread_mutex_unlock(&current_position_mutex);
                    pthread_mutex_lock(&direction_mutex);
                    movement_direction=LEFT;
                    pthread_mutex_unlock(&direction_mutex);

                    pthread_cond_signal(&can_move);

                    pthread_cond_broadcast(&has_moved);
                }else if (current_positionX < WAREHOUSE_WIDTH/2 && obstaculos[0] != 1){

                    pthread_mutex_unlock(&current_position_mutex);
                    pthread_mutex_lock(&direction_mutex);
                    movement_direction=UP;
                    pthread_mutex_unlock(&direction_mutex);
                    pthread_cond_signal(&can_move);

                    pthread_cond_broadcast(&has_moved);
                }else if(obstaculos[4] != 1){
                    pthread_mutex_unlock(&current_position_mutex);

                    pthread_mutex_lock(&direction_mutex);
                    movement_direction=DOWN;
                    pthread_mutex_unlock(&direction_mutex);
                    pthread_cond_signal(&can_move);

                    pthread_cond_broadcast(&has_moved);
                }
            }

            if(current_positionY<product_positionY){
                if(obstaculos[2]!=1){
                    pthread_mutex_unlock(&current_position_mutex);
                    pthread_mutex_lock(&direction_mutex);
                    movement_direction=RIGHT;
                    pthread_mutex_unlock(&direction_mutex);

                    pthread_cond_signal(&can_move);

                    pthread_cond_broadcast(&has_moved);
                }else if (current_positionX < WAREHOUSE_WIDTH/2  && obstaculos[0] != 1){

                    pthread_mutex_unlock(&current_position_mutex);
                    pthread_mutex_lock(&direction_mutex);
                    movement_direction=UP;
                    pthread_mutex_unlock(&direction_mutex);
                    pthread_cond_signal(&can_move);

                    pthread_cond_broadcast(&has_moved);
                }else if(obstaculos[4] != 1){
                    pthread_mutex_unlock(&current_position_mutex);

                    pthread_mutex_lock(&direction_mutex);
                    movement_direction=DOWN;
                    pthread_mutex_unlock(&direction_mutex);
                    pthread_cond_signal(&can_move);

                    pthread_cond_broadcast(&has_moved);
                }
            }
        }
    }
    pthread_exit(NULL);
}
void* thread_simulation_engine(void *arg){

    pthread_exit(NULL);
}

void* thread_positioning(void *arg){
    while(1){
        //printf("chegou ao positioning\n");
        pthread_cond_wait(&can_move,&signal_pos_mutex);
        
        //printf("passou ao positioning1\n");
        //sleep(AGV_SPEED);
        //printf("passou ao positioning2\n");
        int direction_received;
        pthread_mutex_lock(&direction_mutex);
        direction_received=movement_direction;
        //printf(" receveu %d\n", movement_direction);
        pthread_mutex_unlock(&direction_mutex);
        //printf("mingou\n");
        
        for(int i = 0;i < NUMBER_OF_SENSORS; i++) {
			printf("%d. %d\n", i, obstaculos[i]);
		}
        switch(direction_received){
            case 1:
                //printf("amansou1\n");
                pthread_mutex_lock(&current_position_mutex);
                //printf("apareceu\n");


                current_positionX--;

				warehouse[previous_positionX][previous_positionY] = 0;

				warehouse[current_positionX][current_positionY] = 3;

				previous_positionX = current_positionX;
				previous_positionY = current_positionY;

                pthread_mutex_unlock(&current_position_mutex);
                //printf("subiu\n");
                pthread_cond_signal(&battery_discharge);

                break;
            case 2:
                //printf("amansou2\n");
                pthread_mutex_lock(&current_position_mutex);
                //printf("mete nojo\n");
                current_positionX++;
                warehouse[previous_positionX][previous_positionY] = 0;

				warehouse[current_positionX][current_positionY] = 3;

				previous_positionX = current_positionX;
				previous_positionY = current_positionY;
                pthread_mutex_unlock(&current_position_mutex);
                //printf("desceu\n");
                pthread_cond_signal(&battery_discharge);
                break;
            case 3:
                //printf("amansou3");
                pthread_mutex_lock(&current_position_mutex);
                current_positionY++;
                warehouse[previous_positionX][previous_positionY] = 0;

				warehouse[current_positionX][current_positionY] = 3;

				previous_positionX = current_positionX;
				previous_positionY = current_positionY;
                pthread_mutex_unlock(&current_position_mutex);
                //printf("direitou\n");
                pthread_cond_signal(&battery_discharge);
                break;
            case 4:
                //printf("amansou4");
                pthread_mutex_lock(&current_position_mutex);
                current_positionY--;
                warehouse[previous_positionX][previous_positionY] = 0;

				warehouse[current_positionX][current_positionY] = 3;

				previous_positionX = current_positionX;
				previous_positionY = current_positionY;
                pthread_mutex_unlock(&current_position_mutex);
                //		printf("esquerdou\n");
                pthread_cond_signal(&battery_discharge);
                break;
            default:
                printf("error\n");
                break;
        }
        printf ("current position x = %d \n current position y = %d\n",current_positionX,current_positionY);
        sendWarehouse(ip,cli,warehouse);
    }
    pthread_exit(NULL);
}
void* thread_control_system(void *arg){
    pthread_exit(NULL);
}
void* thread_sensors(void *arg){

    while(1) {

        int num;
        num = *((int*)arg);

        pthread_mutex_lock(&current_position_mutex);

        int positionXSnapshot=current_positionX;
        int positionYSnapshot=current_positionY;
        int positionX=current_positionX;
        int positionY=current_positionY;


        pthread_mutex_unlock(&current_position_mutex);
        //printf("CHEGOU O SIGNAL NO SENSORRRRR\n");

		for(int i = 0; i < 8; i++){
		int positionXSnapshot=current_positionX;
        int positionYSnapshot=current_positionY;
        switch(i){
            case 0:
                //printf("CASE 0 NO SENSORRRRR\n");
                while (positionXSnapshot>0){
                    positionXSnapshot--;
                    obstaculos[0]=0;
                    //printf("SENSOR 0 Posição Y = %d\n", positionY);
                    //printf("SENSOR 0 Posição X = %d\n", positionX);
                    if (warehouse[positionXSnapshot][positionYSnapshot]!=0){
                        //printf("Obstacle %d spaces away \n",positionX-positionXSnapshot);
                        obstaculos[0]= abs(positionX-positionXSnapshot);
                        break;

                    }
                }
                break;
            case 1:
                while(positionYSnapshot<WAREHOUSE_LENGTH-1 && positionXSnapshot >0){
                    positionYSnapshot++;
                    positionXSnapshot--;
                    obstaculos[1]=0;
                    if(warehouse[positionXSnapshot][positionYSnapshot]!=0){
                        //printf("Obstacle %d spaces away \n", positionYSnapshot-positionY);
                        obstaculos[1] =  abs(positionYSnapshot-positionY);
                        break;
                    }
                }
                break;
            case 2:
                while (positionYSnapshot<WAREHOUSE_LENGTH-1){
                    positionYSnapshot++;
                    obstaculos[2]=0;
                    if (warehouse[positionXSnapshot][positionYSnapshot]!=0){
                        //printf("SENSOR 2 Obstacle %d spaces away \n", positionYSnapshot-positionY);
                        obstaculos[2] =  abs(positionYSnapshot-positionY);
                        break;
                    }
                }
                break;
            case 3:
                while (positionYSnapshot<WAREHOUSE_LENGTH-1 && positionXSnapshot<WAREHOUSE_WIDTH-1){
                    positionYSnapshot++;
                    positionXSnapshot++;
                    obstaculos[3]=0;
                    if(warehouse[positionXSnapshot][positionYSnapshot]!=0){
                        //printf("Obstacle %d spaces away \n",positionYSnapshot-positionY);
                        obstaculos[3] = abs(positionYSnapshot-positionY);
                        break;
                    }
                }
                break;
            case 4:
                while (positionXSnapshot<WAREHOUSE_WIDTH-1){
                    positionXSnapshot++;
                    obstaculos[4]=0;
                    if (warehouse[positionXSnapshot][positionYSnapshot]!=0){
                        //printf("Obstacle %d spaces away \n", positionXSnapshot-positionX);
                        obstaculos[4] = abs(positionXSnapshot-positionX);
                        break;
                    }
                }
                break;
            case 5:
                while (positionXSnapshot<WAREHOUSE_WIDTH-1 && positionYSnapshot>0){
                    positionXSnapshot++;
                    positionYSnapshot--;
                    obstaculos[5]=0;
                    if(warehouse[positionXSnapshot][positionYSnapshot]!=0){
                        //printf("SENSOR 5 Obstacle %d spaces away \n",positionY-positionYSnapshot);
                        obstaculos[5] = abs(positionY-positionYSnapshot);
                        break;
                    }
                }
                break;
            case 6:
                while (positionYSnapshot>0){
                    positionYSnapshot--;
                    obstaculos[6]=0;
                    if (warehouse[positionXSnapshot][positionYSnapshot]!=0){
                        //printf("posvalue =%d\n",warehouse[positionXSnapshot][positionYSnapshot]);
                        //printf("SENSOR 6 Obstacle %d spaces away \n", positionY-positionYSnapshot);
                        obstaculos[6]= abs(positionY-positionYSnapshot);
                        break;
                    }
                }
                break;
            case 7:

                while(positionYSnapshot>0 && positionXSnapshot >0){
                    positionYSnapshot--;
                    positionXSnapshot--;

                    obstaculos[7]=0;
                    //printf("SENSOR 7 Posição Y = %d\n", positionY);
                    //printf("SENSOR 7 Posição X = %d\n", positionX);

                    if(warehouse[positionXSnapshot][positionYSnapshot]!=0){

                        //printf(" SENSOR 7  Obstacle %d spaces away\n", positionY-positionYSnapshot);
                        obstaculos[7] = abs(positionY-positionYSnapshot);

                        break;

                    }
                }
                break;
            default:
                printf("error in sensor\n");
                break;
        }
		}
        pthread_cond_wait(&has_moved, &signal_has_moved_mutex);
    }
    pthread_mutex_unlock(&current_position_mutex);
    pthread_exit(NULL);
}
	void* thread_communications(void *arg) {
		pthread_exit(NULL);
	}
	//Utils Matemática
	//Euclidian Distance
	float distance(int currentX, int currentY, int endX, int endY) {
		// Calculating distance
		return sqrt(pow(endX - currentX, 2)
					+ pow(endY - currentY, 2) * 1.0);
	}
