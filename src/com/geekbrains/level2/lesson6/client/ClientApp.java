package com.geekbrains.level2.lesson6.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class ClientApp {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8989;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
             DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {

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

        } catch (UnknownHostException e) {
            System.err.println("The connection was not established!");
            e.printStackTrace();
        } catch (IOException | InterruptedException e) {
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
                    System.out.println("Server: " + str);
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
