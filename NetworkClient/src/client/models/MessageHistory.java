package client.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MessageHistory implements Serializable {
    private final int MAX_HISTORY_MESSAGE = 10;
    private List<Message> messages;

    public MessageHistory(List<Message> messages) {
        this.messages = messages;
    }

    public MessageHistory() {
        this.messages = new ArrayList<>();
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void add(Message message) {
        this.messages.add(message);

        if (messages.size() > MAX_HISTORY_MESSAGE) {
            messages.remove(0);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageHistory that = (MessageHistory) o;
        return Objects.equals(messages, that.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MAX_HISTORY_MESSAGE, messages);
    }
}
