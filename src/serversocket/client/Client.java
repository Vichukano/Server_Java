package serversocket.client;


import java.io.*;
import java.net.Socket;

public class Client {

    private static Socket socket;
    private static DataOutputStream dataOutputStream;
    private static DataInputStream dataInputStream;
    private static String in = "";

    public static void sendMessage(String msg) {
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(msg);
            dataOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Client(){

        Interface.drawFrame();

        try {
            socket = new Socket(Constants.IP_ADRESS, Constants.PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                dataInputStream = new DataInputStream(socket.getInputStream());
                in = dataInputStream.readUTF();
                System.out.println(in);
                Interface.textArea.append(in);
                Interface.textArea.append("\r\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

