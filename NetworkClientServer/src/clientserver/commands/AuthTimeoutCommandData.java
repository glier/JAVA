package clientserver.commands;

import java.io.Serializable;

public class AuthTimeoutCommandData implements Serializable {
    private final String timeoutMessage;

    public AuthTimeoutCommandData(String timeoutMessage) {
        this.timeoutMessage = timeoutMessage;
    }

    public String getTimeoutMessage() {
        return timeoutMessage;
    }
}
