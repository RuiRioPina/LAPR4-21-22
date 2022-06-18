package eapli.base.dashboard.domain;

import eapli.base.agv.domain.AGVState;
import eapli.base.packet.Packet;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;


public class TcpClient {

    private static InetAddress serverIP;
    private static Socket socket;
    private ObjectOutputStream sOut;
    private ObjectInputStream sIn;

    public void startConnection(String ip) {
        try {
            serverIP = InetAddress.getByName(ip);
        } catch (UnknownHostException ex) {
            System.out.println("Invalid server specified: " + ip);
            System.exit(1);
        }

        try {
            socket = new Socket(serverIP, 2020);
        } catch (IOException ex) {
            System.out.println("Failed to establish TCP connection");
            System.exit(1);
        }

        try {
            sOut = new ObjectOutputStream(socket.getOutputStream());
            sIn = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("Failed to establish ObjectOutputStream or ObjectInputStream");
            System.exit(1);
        }
    }

    public void stopConnection() throws IOException {
        sIn.close();
        sOut.close();
        socket.close();
    }

    public AGVState getAgvState(Long Identity) throws IOException, ClassNotFoundException {
        String conteudo = "";
        byte version = 0, code = 3;
        AGVState agv = null;
        Packet packet = new Packet(version, code, conteudo.getBytes(StandardCharsets.UTF_8));
        Packet packetOccupied = getAvgState(Identity);
        sOut.writeObject(packetOccupied);
        Packet packetReceived = (Packet) sIn.readObject();
        Packet packetFree = getAvgState(Identity);

        if (packetReceived.getCode() == 6) {
            agv = AGVState.valueOf(packetReceived.data());
            sOut.writeObject(packetFree);
        }
        return agv;
    }


    private static Packet getAvgState(Long id) {
        return new Packet((byte) 0,(byte) 5,(""+id).getBytes(StandardCharsets.UTF_8));
    }

}

