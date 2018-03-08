package serversocket.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
// Выделисть никнейм другим цветом.
// Класс реализует подключенние клиентов к серверу.
public class ClientHandler implements Runnable {

    private Server server;

    private DataOutputStream dataOutputStream;

    private DataInputStream dataInputStream;

    private Socket clientSoсket = null;

    private String nickname ="";

    private static int clients_count = 0;

    public ClientHandler(Socket socket, Server server) {
        try {
            clients_count++;
            this.server = server;
            this.clientSoсket = socket;
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            sendMsg("Inter your nickname");//*
            nickname = dataInputStream.readUTF();//*
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                server.sendMessageToAll("New client connected");
                server.sendMessageToAll("Clients in chat: " + clients_count);
                break;
            }

            while (true) {
                String clientMessage = dataInputStream.readUTF();
                System.out.println(clientMessage);
                server.sendMessageToAll(nickname + " : " + clientMessage);
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close();
        }
    }

    public void sendMsg(String msg) {
        try {
            dataOutputStream.writeUTF(msg);
            dataOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        server.removeClient(this);
        clients_count--;
        server.sendMessageToAll("Clients in chat: " + clients_count);
    }
}

