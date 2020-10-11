package com.geekbrains.level2.lesson6.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerApp {
    public static final int SERVER_PORT = 8989;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Waiting for new connection...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client has been connected!");

            DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

            StreamMessageReader messageReader = new StreamMessageReader(dataInputStream);
            messageReader.start();

            String str = "";
            while (!messageReader.isStopped()) {
                if (str.equals("exit")) {
                    messageReader.setStop();
                    messageReader.join();
                    break;
                }
                System.out.println("Write message:");
                str = scanner.nextLine();
                dataOutputStream.writeUTF(str);
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Server port is already opened!");
            e.printStackTrace();
        }
    }

    private static class StreamMessageReader extends Thread {
        private boolean stopped = false;
        private final DataInputStream dataInputStream;

        public StreamMessageReader(DataInputStream dataInputStream) {
            this.dataInputStream = dataInputStream;
        }

        public void setStop() {
            stopped = true;
        }

        public boolean isStopped() {
            return stopped;
        }

        @Override
        public void run() {
            try {
                while (!stopped) {
                    String str = dataInputStream.readUTF();
                    System.out.println("Client: " + str);
                    if (str.equals("exit"))
                        setStop();
                }
            } catch (IOException e) {
                System.err.println("Error read stream");
                e.printStackTrace();
            }
        }
    }
}
