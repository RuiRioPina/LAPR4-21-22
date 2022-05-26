import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;


public class TcpClientAGVDigitalTwin {

    static InetAddress serverIP;
    static Socket sock;
    private static final  int PORT_NUMBER = 2020;


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

        byte version = 0, code = 0;


        do {
            conteudo = in.readLine();
            Packet packet = new Packet(version, code, conteudo.getBytes(StandardCharsets.UTF_8));
            outputStream.writeObject(packet);
            System.out.println("sent packet with data " + packet.data());
            Packet packetReceived = (Packet) inputStream.readObject();
            if (packetReceived.getCode() == 2 && packet.getCode() == 1) {
                break;
            }

            System.out.println("received packet with data " + packetReceived.data());
        }
        while (!conteudo.equals("-1"));
        sock.close();
    }
}
