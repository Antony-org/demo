package example.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ApplicationScoped
@ServerEndpoint("/websocket")
public class ChatEndpoint {

    private static Set<Session> clients = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("New client connected: " + session.getId());
        clients.add(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("Message received: " + message);
        for (Session client : clients) {
            if (client.isOpen()) {
                try {
                    client.getBasicRemote().sendText(message);
                }
                catch (IOException e) {
                    client.close();
                    clients.remove(client);
                }
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Client disconnected: " + session.getId());
        clients.remove(session);
    }
}