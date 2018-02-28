package serversocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static Socket socket;
    private static ServerSocket serverSocket;
    public static BufferedReader reader;


    public static void main(String[] args) throws IOException {

        serverSocket = new ServerSocket(Constants.PORT);
        System.out.println("Server started");
        reader = new BufferedReader(new InputStreamReader(System.in));


        socket = serverSocket.accept();
        System.out.println("New client connected");

        Input input = new Input();
        Output output = new Output();

        Thread inputThread = new Thread(input);
        inputThread.start();
        Thread outputThread = new Thread(output);
        outputThread.start();

    }
}




