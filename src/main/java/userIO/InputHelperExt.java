package userIO;

import java.util.LinkedList;
import java.util.Queue;

public class InputHelperExt {

    public static final Queue<String> messageQueue = new LinkedList<>();

    public static void send(String message) {
        messageQueue.add(message);
    }

    public static String receive() {
        return messageQueue.poll();
    }

    public static boolean isMessagesEmpty() {
        return messageQueue.isEmpty();
    }
    
    public static void clear() {
    	messageQueue.clear();
    }
}
