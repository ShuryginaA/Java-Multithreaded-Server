package services;

import model.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.time.LocalTime;

import static services.Server.events;

public class ClientHandler extends Thread {
    private final Socket clientSocket;
    private final ObjectInputStream is;

    public ClientHandler(Socket socket) throws IOException {
        this.clientSocket = socket;
        is = new ObjectInputStream(clientSocket.getInputStream());
    }

    public void run() {
        try {
            Message message;
            while(true) {
                message=(Message) is.readObject();
                events.put(LocalTime.parse(message.getTime()), message.getEvent());
                System.out.println(events);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

