package services;

import model.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import static services.Server.events;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try {
            ObjectInputStream is = new ObjectInputStream(clientSocket.getInputStream());
            Message message = (Message) is.readObject();
            events.put(message.getTime(), message.getEvent());
            System.out.println(events);
            is.close();


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

