package Classes;

import java.util.Arrays;
import java.util.LinkedList;

public final class Messager {
    private LinkedList<String> messages = new LinkedList<>();

    public String getMessages() {
        StringBuilder sb = new StringBuilder();
        for(String message : messages.toArray(new String[0])) {
            sb.append(message);
        }
        return sb.toString();
    }

    public void addMessage(String message) {
        messages.add(message);
    }
}
