package serversocket.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

// Реализовать регистрацию клиента и присвоение ника и ID, заменить эррейлист на Map
// Класс Server однопоточный, все подключенные клиенты добавляются в коллекцию

public class Server {
    static final int PORT = 1488;
    private ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();

    public Server(){
        Socket clientSocket = null;
        ServerSocket serverSocket = null;

        try{
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server is started");

            while (true){
                clientSocket = serverSocket.accept();

                ClientHandler client = new ClientHandler(clientSocket, this);
                clients.add(client);
                new Thread(client).start();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                clientSocket.close();
                System.out.println("Server stopped");
                serverSocket.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void sendMessageToAll(String msg){
        for (ClientHandler x : clients){
            x.sendMsg(msg);
        }
    }

    public void removeClient(ClientHandler client){
        clients.remove(client);
    }

}
