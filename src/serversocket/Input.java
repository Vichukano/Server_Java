package serversocket;

import java.io.DataInputStream;
import java.io.IOException;

public class Input implements Runnable {
    public String in = "";
    public DataInputStream dataInputStream;

    @Override
    public void run() {
        while (Server.socket.isConnected()) {
            try {
                dataInputStream = new DataInputStream(Server.socket.getInputStream());
                in = dataInputStream.readUTF();
                System.out.println(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            Server.socket.shutdownInput();
            Server.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
