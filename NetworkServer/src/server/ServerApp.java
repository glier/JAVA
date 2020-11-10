package server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import server.chat.MyServer;

import java.io.IOException;

public class ServerApp {

    private static final Logger LOGGER = LogManager.getLogger(ServerApp.class);
    private static final int DEFAULT_PORT = 8189;

    public static void main(String[] args) {
        int port = DEFAULT_PORT;
        if (args.length != 0) {
            port = Integer.parseInt(args[0]);
        }
        try {
            new MyServer(port).start();
        } catch (IOException e) {
            LOGGER.error("Failed to create MyServer", e);
            System.exit(1);
        }
    }

}
