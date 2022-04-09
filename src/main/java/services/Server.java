package services;

import model.Message;
import model.ServerTimer;

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Server {

    int port=4321;
    InetAddress ip=null;
    ServerSocket server = null;
    public static volatile ServerTimer timer= new ServerTimer();
    public static volatile Map<LocalTime,String> events=new HashMap<>();
    Set<String> set = Collections.newSetFromMap(new ConcurrentHashMap<>());
    Thread t;
    ObjectOutputStream os;

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
//                os=new ObjectOutputStream(client.getOutputStream());
                alarm();
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

    private void alarm(){
//        t=new Thread(
//                ()-> { while(true) {
//                    for (Map.Entry<LocalTime, String> map : events.entrySet()) {
//                        if ((timer.getTimer().equals(map.getKey()))) {
//                            try {
//                                os.writeObject(new Message(map.getKey().toString(), map.getValue()));
//                                break;
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }});
//        t.start();

    }
}
