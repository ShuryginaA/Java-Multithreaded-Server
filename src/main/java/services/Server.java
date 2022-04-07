package services;

import model.ServerTimer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Server {

    int port=4321;
    InetAddress ip=null;
    ServerSocket server = null;
    public static volatile ServerTimer timer= new ServerTimer();
    public static volatile Map<LocalTime,String> events=new HashMap<>();

    private int countClients=0;

    public void start() throws UnknownHostException {
        ip=InetAddress.getLocalHost();
        try {
            server = new ServerSocket(port,0,ip);
            System.out.println("Server started");
            while (true) {
                Socket client = server.accept();
                countClients++;
                System.out.println("New client connected "
                        + client.getLocalPort()
                +" "+ countClients);
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
