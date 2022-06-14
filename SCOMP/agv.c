#include "agv.h"






#define BUF_SIZE 300
#define SERVER_PORT "2020"

#define SERVER_SSL_CERT_FILE "server.pem"
#define SERVER_SSL_KEY_FILE "server.key"
#define AUTH_CLIENTS_SSL_CERTS_FILE "authentic-clients.pem"


#define FREE_POSITION 0
#define STATIONARY_OBSTACLE 1
#define FREE_DOCK 2
#define SPACE_OCCUPIED_BY_AGV 3
#define DOCK_OCCUPIED_BY_AGV 4
#define WAREHOUSE_WIDTH 5
#define WAREHOUSE_LENGTH 5
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
pthread_cond_t can_move;

int movement_direction;
int current_positionX;
int current_positionY;
int product_positionX;
int	product_positionY;
int battery_capacity;
int current_battery;
int obstaculo;

int warehouse[5][5]={
	// 0-Free Position,1-Stationary Obstacle(eg:shelves),2-AGVDock ,3-Space Occupied by AGV ,4- AGV Dock Occupied By AGV
		{2,0,0,0,0},
		{1,0,1,1,0},
		{0,0,1,1,0},
		{0,0,0,0,0},
		{2,0,0,0,4},
		};


int main(){
	current_battery=50;
	battery_capacity=100;
	
	current_positionX=3;
	current_positionY=1;
	
	product_positionX=1;
	product_positionY=4;
	
	pthread_t threadBatteryManagement;
	pthread_t threadRoutePlanner;
	pthread_t threadSimulationEngine;
	pthread_t threadPositioning;
	pthread_t threadControlSystem;
	pthread_t threadSensors[NUMBER_OF_SENSORS];
	pthread_t threadCommunications;
	int sensor_args[NUMBER_OF_SENSORS];
	
	pthread_cond_init(&can_move,NULL);
	
	pthread_mutex_init(&mutex, NULL);
	
	pthread_create(&threadBatteryManagement, NULL, thread_battery_management,NULL);
	pthread_create(&threadRoutePlanner, NULL, thread_route_planner,NULL);
	pthread_create(&threadSimulationEngine, NULL, thread_simulation_engine,NULL);
	pthread_create(&threadPositioning, NULL, thread_positioning,NULL);
	pthread_create(&threadControlSystem, NULL, thread_control_system,NULL);
	for(int i=0;i<NUMBER_OF_SENSORS;i++){
		sensor_args[i]=i;
		pthread_create(&threadSensors[i],NULL,thread_sensors,(void*)&sensor_args[i]);
	}
	pthread_create(&threadCommunications, NULL, thread_communications, NULL);

	
	printf("Wposvalue =%d\n",warehouse[4][2]);
	//sleep(10);
	sleep(5);
	printf("current_position = %d\n",current_positionX);
	current_positionX++;
	//sleep(10);
	//pthread_exit(null);
	
}

void* thread_battery_management(void *arg){
	while(1){
		if(warehouse[current_positionY][current_positionX]==FREE_DOCK || warehouse[current_positionY][current_positionX]==DOCK_OCCUPIED_BY_AGV){
			if(current_battery<battery_capacity){
				sleep(1);
				//printf("Charging\n");
				//printf("Current battery : %d Total Capacity : %d \n",current_battery,battery_capacity);
				pthread_mutex_lock(&mutex);
				current_battery++;
				pthread_mutex_unlock(&mutex);
			}else{
				//printf("Battery Full\n");
				//printf("Current battery : %d Total Capacity : %d \n",current_battery,battery_capacity);
			}
		}else{ // errado, por enquanto, a bateria deveria descer consoante o movimento do AGV e não do tempo, alterar depois
			sleep(1);
			//printf("Battery Dropping\n");
			//printf("Current battery : %d Total Capacity : %d \n",current_battery,battery_capacity);
			pthread_mutex_lock(&mutex);
			current_battery--;
			pthread_mutex_unlock(&mutex);
			}
	}
	pthread_exit(NULL);
}

/*
*
*
*/

void* thread_route_planner(void *arg){
	printf("%d\n",current_positionX);
	printf("%d\n",current_positionY);
	printf("%d\n",product_positionX);
	printf("%d\n",product_positionY);
	int distance_to_end = distance(current_positionX, current_positionY, product_positionX, product_positionY);
	
	printf("Distance to end: %d\n",distance_to_end);
	
	//while(distance_to_end != 0) {
		for(int i = 0; i < 4; i++){  
			switch(i){
				case 1:
					if(distance(current_positionX--, current_positionY, product_positionX, product_positionY) < distance_to_end) {
						distance_to_end = distance(current_positionX - 1, current_positionY, product_positionX, product_positionY);
						//send signal to positioning module current_positionY = current_positionY--;
					}
				break;
			
				case 2:
					if(distance(current_positionX++, current_positionY, product_positionX, product_positionY) < distance_to_end) {
						distance_to_end = distance(current_positionX + 1, current_positionY, product_positionX, product_positionY);
						//send signal to positioning module current_positionY = current_positionY--;
					}
				break;
			
				case 3:
					if(distance(current_positionX, current_positionY - 1, product_positionX, product_positionY) < distance_to_end) {
						distance_to_end = distance(current_positionX, current_positionY - 1, product_positionX, product_positionY);
						//send signal to positioning module current_positionY = current_positionY--;
					}
				break;
			
				case 4:
					if(distance(current_positionX, current_positionY + 1, product_positionX, product_positionY) < distance_to_end) {
						distance_to_end = distance(current_positionX, current_positionY++, product_positionX, product_positionY);
						//send signal to positioning module current_positionY = current_positionY--;
					}
				break;
			
				default:
					//printf("Error in the route planner. Please reference to that module!\n");
				break;
			}
		//}
	}
	
	
pthread_exit(NULL);
}
void* thread_simulation_engine(void *arg){
		movement_direction=UP;
		pthread_cond_signal(&can_move);
	pthread_exit(NULL);
}
void* thread_positioning(void *arg){
	while(1){
		
		pthread_cond_wait(&can_move,&mutex);
		sleep(AGV_SPEED);
		int direction_received;
		direction_received=movement_direction;
		switch(direction_received){
			case 1:
			current_positionX--;
			break;
			
			case 2:
			current_positionX++;
			break;
			
			case 3:
			current_positionY++;
			break;
			
			case 4:
			current_positionY--;
			break;
			
			default:
			printf("error\n");
			break;
		}
	}

pthread_exit(NULL);
}

void* thread_control_system(void *arg){
	
	pthread_exit(NULL);
}


void* thread_sensors(void *arg){
	int num;
	num = *((int*)arg);
	int positionXSnapshot=current_positionX;

	int positionYSnapshot=current_positionY;
	switch(num){
		case 0:
		while (positionXSnapshot>0){
			positionXSnapshot--;
			if (warehouse[positionXSnapshot][positionYSnapshot]!=0){
				//printf("Obstacle %d spaces away \n", current_positionX-positionXSnapshot);
				obstaculo = current_positionX-positionXSnapshot;
			}
		}
		break;
		case 1:
		while(positionYSnapshot<WAREHOUSE_WIDTH-1 && positionXSnapshot >0){
			positionYSnapshot++;
			positionXSnapshot--;
			if(warehouse[positionXSnapshot][positionYSnapshot]!=0){
				//printf("Obstacle %d spaces away \n", positionYSnapshot-current_positionY);
				obstaculo = positionYSnapshot-current_positionY;
			}
		}
		break;  
		case 2:
		while (positionYSnapshot<WAREHOUSE_WIDTH-1){
			positionYSnapshot++;
			if (warehouse[positionXSnapshot][positionYSnapshot]!=0){
				//printf("Obstacle %d spaces away \n", positionYSnapshot-current_positionY);
				obstaculo = positionYSnapshot-current_positionY;
			}
		}
		break;  
		case 3:
		while (positionYSnapshot<WAREHOUSE_WIDTH-1 && positionXSnapshot<WAREHOUSE_LENGTH-1){
			positionYSnapshot++;
			positionXSnapshot++;
			if(warehouse[positionXSnapshot][positionYSnapshot]!=0){
				//printf("Obstacle %d spaces away \n",positionYSnapshot-current_positionY);
				obstaculo = positionYSnapshot-current_positionY;
			}
		}
		break;    
		case 4:
		while (positionXSnapshot<WAREHOUSE_LENGTH-1){
			positionXSnapshot++;
			if (warehouse[positionXSnapshot][positionYSnapshot]!=0){
				//printf("Obstacle %d spaces away \n", positionXSnapshot-current_positionX);
				obstaculo = positionXSnapshot-current_positionX;
			}
		}
		break;  
		case 5:
		while (positionXSnapshot<WAREHOUSE_LENGTH-1 && positionYSnapshot>0){
			positionXSnapshot++;
			positionYSnapshot--;
			if(warehouse[positionXSnapshot][positionYSnapshot]!=0){
				printf("Obstacle %d spaces away \n",current_positionY-positionYSnapshot);
				obstaculo = current_positionY-positionYSnapshot;
			}
		}
		break;  
		case 6:
		while (positionYSnapshot>0){
			
			positionYSnapshot--;
			if (warehouse[positionXSnapshot][positionYSnapshot]!=0){
				//printf("posvalue =%d\n",warehouse[positionXSnapshot][positionYSnapshot]);
				//printf("Obstacle %d spaces away \n", current_positionY-positionYSnapshot);
				obstaculo = 1;
			}
		}
		break;  
		case 7:
		while(positionYSnapshot>0 && positionXSnapshot >0){
			positionYSnapshot--;
			positionXSnapshot--;
			if(warehouse[positionXSnapshot][positionYSnapshot]!=0){
				//printf("Obstacle %d spaces away\n", current_positionY-positionYSnapshot);
				obstaculo = current_positionY-positionYSnapshot;
			}
		}
		break;
		default:  
		printf("error in sensor\n");
		break;    
	}
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
                + pow(endY - endX, 2) * 1.0);
}



