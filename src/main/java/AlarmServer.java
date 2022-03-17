import model.ServerTimer;
import services.ClientHandler;
import services.Server;
import services.TimeChanger;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

// Server class
public class AlarmServer {
    Server serverService=new Server();

    public void startServer()
    {
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
                new TimeChanger();
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

