package eapli.base.app.agvmanager.server;

import eapli.base.agv.domain.AGV;
import eapli.base.agv.domain.AGVState;
import eapli.base.agv.repositories.AGVRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.packet.Packet;
import eapli.base.product.domain.Product;
import eapli.base.productOrder.domain.OrderState;
import eapli.base.productOrder.domain.ProductOrder;
import eapli.base.productOrder.repositories.OrderRepository;
import org.springframework.security.core.parameters.P;

import javax.persistence.Persistence;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

class TcpServerAGVManager {

     static ServerSocket sock;

     public static void main(String[] args) throws Exception {
         Socket cliSock;

         try {
             sock = new ServerSocket(2020);
         } catch (IOException ex) {
             System.out.println("Failed to open server socket");
             System.exit(1);
         }

         while (true) {
             cliSock = sock.accept();
             new Thread(new TcpServerAGVManagerThread(cliSock)).start();
         }
     }
 }



    class TcpServerAGVManagerThread implements Runnable {
        private final Socket s;
        private ObjectOutputStream sOut;
        private ObjectInputStream sIn;

        private AGVRepository agvRepository= PersistenceContext.repositories().agvs();

        private OrderRepository orderRepository=PersistenceContext.repositories().orders();

        public TcpServerAGVManagerThread(Socket cli_s) {
            s = cli_s;
        }

        public void run() {
            InetAddress clientIP;

            clientIP = s.getInetAddress();
            System.out.println("New client connection from " + clientIP.getHostAddress() +
                    ", port number " + s.getPort());
            try {
                sOut = new ObjectOutputStream(s.getOutputStream());
                sIn = new ObjectInputStream(s.getInputStream());
                while(true) {
                    Packet packet = null;
                    try {
                        packet = (Packet) sIn.readObject();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    Packet packetWrite = new Packet((byte) 0, (byte) 2, "Acknowledged".getBytes(StandardCharsets.UTF_8));

                    switch (packet.getCode()) {
                        case 0:
                            System.out.println("==> Request to test the connection sent by Client received with success");
                            //Dizer ao cliente que entendeu
                            System.out.println("==> Send message to the client saying it understood the request");
                            sOut.writeObject(packetWrite);
                            sOut.flush();
                            break;
                        case 1:
                            try {
                                System.out.println("==> Request to end connection sent by Client received with success");
                                //Dizer ao cliente que entendeu
                                System.out.println("==> Send message to the client saying it understood the request");
                                sOut.writeObject(packetWrite);
                                sOut.flush();
                                System.out.println("==> Client " + clientIP.getHostAddress() + ", port number: " + this.s.getPort() + " disconnected");
                            } catch (IOException e) {
                                System.out.println("==> ERROR: " + e.getMessage());
                            } finally {
                                try {
                                    this.s.close();
                                } catch (IOException e) {
                                    System.out.println("ERROR: Error while closing the socket");
                                }
                                System.out.println("==> INFO: Socket closed with Success\n\n");
                            }
                            break;
                        case 3:
                            System.out.println("==> Request to change the state of the AGV sent by digital twin client received with success");
                            Optional<AGV> agv = agvRepository.findById(idPacketParser(packet));
                            agv.get().setAgvState(statePacketParser(packet));
                            System.out.println("identidade:"+agv.get().identity());
                            System.out.println("state="+statePacketParser(packet).toString());
                            agvRepository.save(agv.get());

                            if (statePacketParser(packet)==AGVState.OCCUPIED_SERVING_A_GIVEN_ORDER){
                                Iterable<ProductOrder> orderList=orderRepository.findByDateAscAndState(OrderState.TO_BE_PREPARED);
                                if (orderList.iterator().hasNext()){
                                    ProductOrder order=orderList.iterator().next();
                                    order.setOrderState(OrderState.BEING_PREPARED);
                                    orderRepository.save(order);
                                }

                            }
                            if (statePacketParser(packet)==AGVState.FREE){
                                Iterable<ProductOrder> orderList=orderRepository.findByDateAscAndState(OrderState.BEING_PREPARED);
                                if (orderList.iterator().hasNext()){
                                    ProductOrder order=orderList.iterator().next();
                                    order.setOrderState(OrderState.READY_FOR_CARRIER);
                                    orderRepository.save(order);

                                }
                            }
                            Packet packet1= new Packet((byte) 0,(byte) 4,"Work".getBytes(StandardCharsets.UTF_8));
                            sOut.writeObject(packet1);
                            sOut.flush();
                            System.out.println("==> Send message to the client saying it has to work");

                            break;

                        case 5:
                            System.out.println("Received request of the state of the agv");
                            Optional<AGV> agvCase5 = agvRepository.findById(Long.parseLong(packet.data()));
                            Packet packetStatus= new Packet((byte) 0,(byte)6,agvCase5.get().agvState().toString().getBytes());
                            sOut.writeObject(packetStatus);
                            sOut.flush();
                            break;
                        default:
                            System.out.println("==> ERROR: Error while sending the packet to the client");
                            break;

                    }

                }
            } catch (IOException ex) {
                System.out.println("IOException");
            }
        }

        public  Long idPacketParser(Packet packet){
        if (packet.getCode()!=3&& packet.getCode()!=5){
            return null;
        }
        String str= packet.data();
        long num=-1;
        for(int i=0;i<str.length();i++){
            if (str.substring(i,i+3).equals("ID:")){
                num =Long.parseLong(str.substring(i+3));
                break;
            }
        }
        return  num;
        }
        public AGVState statePacketParser(Packet packet) {
            if (packet.getCode()!=3 && packet.getCode() != 5){
                return null;
            }
            String str= packet.data();
            if (str.contains(AGVState.FREE.toString())){
                return AGVState.FREE;
            }
            if (str.contains(AGVState.OCCUPIED_SERVING_A_GIVEN_ORDER.toString())){
                return AGVState.OCCUPIED_SERVING_A_GIVEN_ORDER;
            }
            return null;
        }

        //public static void main(String[] args) {//   String data = "STATE:"+ AGVState.FREE.toString()+"ID:22";
           // Packet packet = new Packet((byte) 0, (byte) 3,data.getBytes(StandardCharsets.UTF_8));
           // System.out.println(idPacketParser(packet));
       // }
    }

