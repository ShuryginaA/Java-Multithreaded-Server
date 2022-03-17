package services;

import model.ServerTimer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static volatile ServerTimer timer= new ServerTimer(0,0);
    List<String> events=new ArrayList<>();

    public void start(){
        ServerSocket server = null;
        try {
            server = new ServerSocket(1234);
            server.setReuseAddress(true);
            while (true) {
                Socket client = server.accept();
                System.out.println("New client connected "
                        + client.getInetAddress()
                        .getHostAddress());
                ClientHandler clientSock
                        = new ClientHandler(client);
                new Thread(clientSock).start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (server != null) {
                try {
                    server.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
