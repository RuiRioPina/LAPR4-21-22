package clientserver;

import eapli.base.agv.domain.AGVState;
import eapli.base.packet.Packet;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;


public class TcpClientAGVDigitalTwin {

    static InetAddress serverIP;
    static Socket sock;
    private static final  int PORT_NUMBER = 8080;

    //args[0]= server ip
    //args[1]= AGV ID
    public static void main(String[] args) throws Exception {
        System.out.println("Client side: Waiting for you to send a request");

        if (args.length != 1) {
            System.out.println("Server IPv4/IPv6 address or DNS name is required as argument");
            System.exit(1);
        }

        try {
            serverIP = InetAddress.getByName(args[0]);
        } catch (UnknownHostException ex) {
            System.out.println("Invalid server specified: " + args[0]);
            System.exit(1);
        }

        try {
            sock = new Socket(serverIP, PORT_NUMBER);
        } catch (IOException ex) {
            System.out.println("Failed to establish TCP connection");
            System.exit(1);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        ObjectOutputStream outputStream = new ObjectOutputStream(sock.getOutputStream());
        ObjectInputStream inputStream = new ObjectInputStream(sock.getInputStream());

        String conteudo = "";

        byte version = 0, code = 3;


        do {
            conteudo = in.readLine();
            //Packet packet = new Packet(version, code, conteudo.getBytes(StandardCharsets.UTF_8));
            Packet packetOccupied = buildStateChangeRequestData(AGVState.OCCUPIED_SERVING_A_GIVEN_ORDER,Long.valueOf(35));
            outputStream.writeObject(packetOccupied);
            System.out.println("sent packet with data " + packetOccupied.data());
            Packet packetReceived = (Packet) inputStream.readObject();
            if (packetReceived.getCode() == 2 && packetReceived.getCode() == 1) {
                break;
            }

            System.out.println("received packet with data " + packetReceived.data());
            Packet packetFree = buildStateChangeRequestData(AGVState.FREE,Long.valueOf(35));

            if (packetReceived.getCode() == 4) {
                Thread.sleep(10000); //simulates work
                System.out.println("Work has finished");
                outputStream.writeObject(packetFree);
            }




        }
        while (!conteudo.equals("-1"));
        sock.close();
    }


    private  static Packet buildStateChangeRequestData(AGVState state,Long id){
        Packet packet= new Packet((byte) 0,(byte) 3,("STATE:"+state.toString()+"ID:"+id).getBytes(StandardCharsets.UTF_8));
        return packet;
    }
}
