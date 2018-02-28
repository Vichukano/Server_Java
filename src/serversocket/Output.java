package serversocket;

import java.io.DataOutputStream;
import java.io.IOException;

public class Output implements Runnable {
    public String out = "";
    public DataOutputStream dataOutputStream;

    @Override
    public void run() {
        while (Server.socket.isConnected()) {
            try {
                dataOutputStream = new DataOutputStream(Server.socket.getOutputStream());
                out = Server.reader.readLine();
                dataOutputStream.writeUTF(out);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            Server.socket.shutdownOutput();
            Server.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
