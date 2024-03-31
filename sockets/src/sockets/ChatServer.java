package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ChatServer {
    private static List<Socket> clients = new ArrayList<>();
    private static Map<Socket, String> clientNames = new HashMap<>();

    public static void main(String[] args) {
        try {
        	int port = 5050;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started at port "+port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                clients.add(clientSocket);

                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;
        private String clientName;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println("Welcome to the chat!");
                clientName = in.readLine();
                clientNames.put(clientSocket, clientName);
                out.println("You are connected as " + clientName);
                broadcast(clientName + " has joined the chat.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println(clientName + ": " + message);
                    broadcast(clientName + ": " + message, clientSocket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void broadcast(String message) {
            for (Socket client : clients) {
                try {
                    PrintWriter clientOut = new PrintWriter(client.getOutputStream(), true);
                    clientOut.println(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Surcharge de m�thode pour prendre en charge la diffusion avec un seul argument
        private void broadcast(String message, Socket senderClientSocket) {
            for (Socket client : clients) {
                if (client != senderClientSocket) {
                    try {
                        PrintWriter clientOut = new PrintWriter(client.getOutputStream(), true);
                        clientOut.println(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}