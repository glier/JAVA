package server.chat.handler;

import clientserver.Command;
import clientserver.CommandType;
import clientserver.commands.AuthCommandData;
import clientserver.commands.PrivateMessageCommandData;
import clientserver.commands.PublicMessageCommandData;
import server.chat.MyServer;

import java.io.*;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class ClientHandler {

    private static final int TIMEOUT = 120 * 1000;
    private final MyServer myServer;
    private final Socket clientSocket;

    private ObjectInputStream in;
    private ObjectOutputStream out;

    private String username;

    public ClientHandler(MyServer myServer, Socket clientSocket) {
        this.myServer = myServer;
        this.clientSocket = clientSocket;
    }

    public void handle() throws IOException {
        in  = new ObjectInputStream(clientSocket.getInputStream());
        out = new ObjectOutputStream(clientSocket.getOutputStream());

        new Thread(() -> {
            try {
                authentication();
                readMessages();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    closeConnection();
                } catch (IOException e) {
                    System.err.println("Failed to close connection!");
                }
            }
        }).start();
    }

    public String getUsername() {
        return username;
    }

    private void readMessages() throws IOException {
        while (true) {
            Command command = readCommand();
            if (command == null) {
                continue;
            }

            switch (command.getType()) {
                case END:
                    return;
                case PRIVATE_MESSAGE: {
                    PrivateMessageCommandData data = (PrivateMessageCommandData) command.getData();
                    String recipient = data.getReceiver();
                    String privateMessage = data.getMessage();
                    myServer.sendPrivateMessage(recipient, Command.messageInfoCommand(privateMessage, username));
                    break;
                }
                case PUBLIC_MESSAGE: {
                    PublicMessageCommandData data = (PublicMessageCommandData) command.getData();
                    String message = data.getMessage();
                    String sender = data.getSender();
                    myServer.broadcastMessage(this, Command.messageInfoCommand(message, username));
                    break;
                }
                default:
                    System.err.println("Unknown type of command: " + command.getType());
            }

        }
    }

    private Command readCommand() throws IOException {
        try {
            return (Command) in.readObject();
        } catch (ClassNotFoundException e) {
            String errorMessage = "Unknown type of object from client";
            System.err.println(errorMessage);
            e.printStackTrace();
            sendMessage(Command.errorCommand(errorMessage));
            return null;
        }
    }

    private void authentication() throws IOException {
        Timer timeoutTimer = new Timer(true);
        timeoutTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        if (username == null) {
                            System.out.println("authentication is terminated caused by timeout expired");
                            sendMessage(Command.authTimeoutCommand("Истекло время ожидания подключения!"));
                            Thread.sleep(100);
                            clientSocket.close();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, TIMEOUT);

        while (true) {
            Command command = readCommand();
            if (command == null) {
                continue;
            }

            if (command.getType() == CommandType.AUTH) {
                boolean isSuccessAuth = processAuthCommand(command);
                if (isSuccessAuth) break;
            }
            else {
                sendMessage(Command.authErrorCommand("Auth command is required!"));
            }
        }
    }

    private boolean processAuthCommand(Command command) throws IOException {
        AuthCommandData cmdData = (AuthCommandData) command.getData();
        String login = cmdData.getLogin();
        String password = cmdData.getPassword();
        this.username = myServer.getAuthService().getUsernameByLoginAndPassword(login, password);
        if (username != null) {
            if (myServer.isNicknameAlreadyBusy(username)) {
                sendMessage(Command.authErrorCommand("Login and password are already used!"));
                return false;
            }
            sendMessage(Command.authOkCommand(username));
            myServer.broadcastMessage( this, Command.messageInfoCommand(username + " joined to chat!", null));
            myServer.subscribe(this);
            return true;
        } else {
            sendMessage(Command.authErrorCommand("Login and/or password are invalid! Please, try again"));
            return false;
        }
    }

    private void closeConnection() throws IOException {
        myServer.unsubscribe(this);
        clientSocket.close();
    }


    public void sendMessage(Command command) throws IOException {
        out.writeObject(command);
    }
}
