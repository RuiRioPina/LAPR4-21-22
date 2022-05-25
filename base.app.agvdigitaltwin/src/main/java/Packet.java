
    import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
    import java.nio.charset.StandardCharsets;
    import java.util.List;

    public class Packet {
        private byte version;
        private byte code;
        private byte d_length1;
        private byte d_length2;
        private byte[] data;

        public Packet(byte version,byte code, byte[] data){
            this.version=version;
            this.code=code;
            this.data=data;
            this.d_length1=(byte)calculateDlength1(data.length);
            this.d_length1=(byte)calculateDlength2(data.length);
        }

        private int calculateDlength1(int size){
            if (size>256){
                return 255;
            }else return size;
        }

        private int calculateDlength2(int size){
            if (size<256){
                return 0;
            }else  return size/256;
        }

        public byte getCode(){
            return this.code;
        }
        public String data() {
            String str = new String(data, StandardCharsets.UTF_8);
            return str;
        }
    }
