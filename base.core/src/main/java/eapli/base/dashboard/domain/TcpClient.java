package eapli.base.dashboard.domain;

import eapli.base.agv.domain.AGVState;
import eapli.base.packet.Packet;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class TcpClient {

    static InetAddress serverIP;
    static Socket sock;
    private static final  int PORT_NUMBER = 2020;


    public static AGVState main(String[] args) throws Exception {

        //System.out.println("Client side: Waiting for you to send a request");

        /*if (args.length != 1) {
            System.out.println("Server IPv4/IPv6 address or DNS name is required as argument");
            System.exit(1);
        }*/

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
            //Packet packet = new Packet(version, code, conteudo.getBytes(StandardCharsets.UTF_8));
            Packet packetOccupied = getAvgState(Long.valueOf(args[1]));
            outputStream.writeObject(packetOccupied);
            //System.out.println("sent packet with data " + packetOccupied.data());
            Packet packetReceived = (Packet) inputStream.readObject();

            //System.out.println("received packet with data " + packetReceived.data());
            Packet packetFree = getAvgState(Long.valueOf(args[1]));

            if (packetReceived.getCode() == 6) {
                AGVState agv = AGVState.valueOf(packetReceived.data());
                outputStream.writeObject(packetFree);
                //sock.close();
                return agv;
            }

        }
        while (true);
        //sock.close();
        //return null;
    }

    private static Packet getAvgState(Long id){
        Packet packet= new Packet((byte) 0,(byte) 5,(""+id).getBytes(StandardCharsets.UTF_8));
        return packet;
    }


}
