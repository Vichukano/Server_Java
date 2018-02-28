package serversocket;


import java.io.*;
import java.net.Socket;

public class Client {

    private static Socket socket;
    private static DataOutputStream dataOutputStream;
    private static DataInputStream dataInputStream;
    private static String in = "";

    public static void sendBtn_Handler() {
        String msg = Interface.textField.getText();
        if (!msg.isEmpty()) {
            Interface.textField.setText("");
            Interface.textArea.append(msg);
            Interface.textArea.append("\r\n");
            try {
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeUTF(msg);
                dataOutputStream.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            msg = Interface.textField.getText();
        }
    }

    public static void main(String[] args) throws IOException {

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
                Interface.textArea.append("Server: " + in);
                Interface.textArea.append("\r\n");
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
}

