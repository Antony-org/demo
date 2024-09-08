package example.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ApplicationScoped
@ServerEndpoint("/websocket")
public class ChatEndpoint {

    private static Set<Session> clients = new CopyOnWriteArraySet<>();
    private static final Set<String> onlineUsernames = new HashSet<>();
    private static Map<Session, String> sessionUsernames = new HashMap<>();

    @OnOpen
    public void onOpen(Session session) throws IOException {
        System.out.println("New client connected: " + session.getId());
        clients.add(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        if (message.startsWith("USERNAME:")) {
            String username = message.substring("USERNAME:".length());
            sessionUsernames.put(session, username);
            onlineUsernames.add(username);
            sendOnlineUsersList();
            broadcast(username + " has joined the chat.");
        }
         else {
            broadcast(message);
        }
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        System.out.println("Client disconnected: " + session.getId());
        clients.remove(session);
        String username = sessionUsernames.remove(session);
        if (username != null) {
            onlineUsernames.remove(username);
            sendOnlineUsersList();
        }
    }

    private void broadcast(String message) throws IOException {
        for (Session client : clients) {
            if (client.isOpen()) {
                try {
                    client.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    client.close();
                    clients.remove(client);
                }
            }
        }
    }

    private void sendOnlineUsersList() throws IOException {
        StringBuilder onlineUsersMessage = new StringBuilder("ONLINE_USERS:");
        for (String username : onlineUsernames) {
            onlineUsersMessage.append(username).append(",");
        }
        if (onlineUsersMessage.length() > 0) {
            onlineUsersMessage.setLength(onlineUsersMessage.length() - 1);
        }

        broadcast(onlineUsersMessage.toString());
    }
}