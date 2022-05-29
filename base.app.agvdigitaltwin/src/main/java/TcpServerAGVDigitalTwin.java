

import eapli.base.packet.Packet;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

class TcpServerAGVDigitalTwin {
    static ServerSocket sock;
    private static final  int PORT_NUMBER = 2020;

    public static void main(String[] args) throws Exception {
        Socket cliSock;
        System.out.println("Server side: Waiting for requests");

        try {
            sock = new ServerSocket(PORT_NUMBER);
        } catch (IOException ex) {
            System.out.println("Failed to open server socket");
            System.exit(1);
        }

        while (true) {
            cliSock = sock.accept();
            new Thread(new TcpServerAGVDigitalTwinThread(cliSock)).start();
        }
    }
}


class TcpServerAGVDigitalTwinThread implements Runnable {
    private final Socket s;
    private ObjectOutputStream sOut;
    private ObjectInputStream sIn;

    public TcpServerAGVDigitalTwinThread(Socket cli_s) {
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

                    default:
                        System.out.println("==> ERROR: Error while sending the packet to the client");
                        break;

                }
            }
        } catch (IOException ex) {
            System.out.println("IOException");
        }
    }
}


