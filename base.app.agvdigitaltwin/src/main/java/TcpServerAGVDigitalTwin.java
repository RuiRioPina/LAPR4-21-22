import java.io.*;
import java.net.*;

class TcpServerAGVDigitalTwin {
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
            new Thread(new TcpServerAGVDigitalTwinThread(cliSock)).start();
        }
    }
}


class TcpServerAGVDigitalTwinThread implements Runnable {
    private final Socket s;
    private DataOutputStream sOut;
    private DataInputStream sIn;

    public TcpServerAGVDigitalTwinThread(Socket cli_s) {
        s = cli_s;
    }

    public void run() {
        InetAddress clientIP;

        clientIP = s.getInetAddress();
        System.out.println("New client connection from " + clientIP.getHostAddress() +
                ", port number " + s.getPort());
        try {
            sOut = new DataOutputStream(s.getOutputStream());
            sIn = new DataInputStream(s.getInputStream());

            byte version = (byte) sIn.read();
            byte code = (byte) sIn.read();
            byte d_length_1 = (byte) sIn.read();
            byte d_length_2 = (byte) sIn.read();
            byte data = (byte) sIn.read();

            switch (code) {
                case 0:
                    System.out.println("==> Request to test the connection sent by Client received with success");

                    //Dizer ao cliente que entendeu
                    System.out.println("==> Send message to the client saying it understood the request");
                    byte[] serverMessage = {(byte) 0, (byte) 2, (byte) 0, (byte) 0};
                    sOut.write(serverMessage);
                    sOut.flush();
                    break;
                case 1:
                    try {
                        System.out.println("==> Request to end connection sent by Client received with success");
                        //Dizer ao cliente que entendeu
                        System.out.println("==> Send message to the client saying it understood the request");
                        byte[] serverMessageEnd = {(byte) 0, (byte) 2, (byte) 0, (byte) 0};
                        sOut.write(serverMessageEnd);
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
        } catch (IOException ex) {
            System.out.println("IOException");
        }
    }
}


