package server.chat.handler;

import clientserver.Command;
import clientserver.CommandType;
import clientserver.commands.AuthCommandData;
import clientserver.commands.PrivateMessageCommandData;
import clientserver.commands.PublicMessageCommandData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import server.chat.MyServer;

import java.io.*;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientHandler {
    private static final Logger LOGGER = LogManager.getLogger(ClientHandler.class);
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

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(() -> {
            try {
                authentication();
                readMessages();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    closeConnection();
                } catch (IOException e) {
                    LOGGER.error("Failed to close connection!");
                }
            }
        });
        executorService.shutdown();
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
                    LOGGER.warn("Unknown type of command: " + command.getType());
            }

        }
    }

    private Command readCommand() throws IOException {
        try {
            return (Command) in.readObject();
        } catch (ClassNotFoundException e) {
            String errorMessage = "Unknown type of object from client";
            LOGGER.error(errorMessage, e);
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
                            LOGGER.warn("authentication is terminated caused by timeout expired");
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
                String message = "Login and password are already used!";
                LOGGER.warn(message);
                sendMessage(Command.authErrorCommand(message));
                return false;
            }
            sendMessage(Command.authOkCommand(username));
            myServer.broadcastMessage( this, Command.messageInfoCommand(username + " joined to chat!", null));
            myServer.subscribe(this);
            return true;
        } else {
            String message = "Login and/or password are invalid! Please, try again";
            LOGGER.warn(message);
            sendMessage(Command.authErrorCommand(message));
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
