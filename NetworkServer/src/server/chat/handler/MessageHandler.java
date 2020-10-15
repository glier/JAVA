package server.chat.handler;

public class MessageHandler {
    private static final String MESSAGE_PRIVAT_CMD_PREFIX = "/w";

    public boolean isPrivat(String message) {
        return message.startsWith(MESSAGE_PRIVAT_CMD_PREFIX);
    }

    public String getUserNameFromMessage(String message) {
        return message.split("\\s+", 3)[1];
    }
}
