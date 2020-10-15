package server.chat;

import server.chat.auth.AuthService;
import server.chat.auth.BaseAuthService;
import server.chat.handler.ClientHandler;
import server.chat.handler.MessageHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {

    private final ServerSocket serverSocket;
    private final List<ClientHandler> clients = new ArrayList<>();
//    private final List<ClientHandler> clients = new CopyOnWriteArrayList<>();
//    private final List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());
    private final AuthService authService;
    private final MessageHandler messageHandler;


    public MyServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.authService = new BaseAuthService();
        this.messageHandler = new MessageHandler();
    }

    public void start() throws IOException {
        System.out.println("Сервер был запущен");

        authService.start();
        try {
            while (true) {
                waitAndProcessNewClientConnection();
            }
        } catch (IOException e) {
            System.err.println("Failed to accept new connection");
            e.printStackTrace();
        } finally {
            authService.stop();
            serverSocket.close();
        }
    }

    private void waitAndProcessNewClientConnection() throws IOException {
        System.out.println("Ожидание нового подключения....");
        Socket clientSocket = serverSocket.accept();
        System.out.println("Клиент подключился");// /auth login password
        processClientConnection(clientSocket);
    }

    private void processClientConnection(Socket clientSocket) throws IOException {
        ClientHandler clientHandler = new ClientHandler(this, clientSocket);
        clientHandler.handle();
    }

    public AuthService getAuthService() {
        return authService;
    }

    public synchronized void broadcastMessage(String message, ClientHandler sender) throws IOException {
        for (ClientHandler client : clients) {
            if (client == sender) {
                continue;
            }

            if (messageHandler.isPrivat(message)
                    && messageHandler.getUserNameFromMessage(message).equals(client.getUsername())) {
                client.sendMessage(message);
                return;
            }

            if (!messageHandler.isPrivat(message))
                client.sendMessage(message);
        }
    }

    public synchronized void subscribe(ClientHandler handler) {
        clients.add(handler);
    }

    public synchronized void unsubscribe(ClientHandler handler) {
        clients.remove(handler);
    }

    public synchronized boolean isNicknameAlreadyBusy(String username) {
        for (ClientHandler client : clients) {
            if (client.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

}
