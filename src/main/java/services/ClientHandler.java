package services;

import model.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalTime;
import java.util.Map;

import static services.Server.events;
import static services.Server.timer;

public class ClientHandler extends Thread {
    private final Socket clientSocket;
    private final ObjectInputStream is;
    ObjectOutputStream os;
    Thread t;

    public ClientHandler(Socket socket) throws IOException {
        this.clientSocket = socket;
        is = new ObjectInputStream(clientSocket.getInputStream());
    }

    public void run() {
        try {
            Message message;
            try {
                os=new ObjectOutputStream(clientSocket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            t=new Thread(
                    ()-> { while(true) {
                        for (Map.Entry<LocalTime, String> map : events.entrySet()) {
                            if ((timer.getTimer().equals(map.getKey()))) {
                                try {
                                    os.writeObject(new Message(map.getKey().toString(), map.getValue()));
                                    break;
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }});
            t.start();
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
