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
import java.sql.*;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Server {

    int port = 4321;
    InetAddress ip = null;
    ServerSocket server = null;
    public static volatile ServerTimer timer = new ServerTimer();
    public static volatile Map<LocalTime, String> events = new HashMap<>();
    Thread t;
    ObjectOutputStream os;
    static Connection connection;
    private final String url = "jdbc:postgresql://localhost/Alarms";
    private final String user = "postgres";
    private final String password = "123";

    private int countClients = 0;

    public void start() throws UnknownHostException {
        ip = InetAddress.getLocalHost();
        try {
            server = new ServerSocket(port, 0, ip);
            connect();
            System.out.println("Server started");
            while (true) {
                Socket client = server.accept();
                countClients++;
                System.out.println("New client connected "
                        + client.getLocalPort()
                        + " " + countClients);
//                os=new ObjectOutputStream(client.getOutputStream());
                ClientHandler clientSock
                        = new ClientHandler(client);
                new Thread(clientSock).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public Connection connect() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
            System.out.println(getAllMessages());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return connection;
    }

    public static Map<String,String> getAllMessages() throws SQLException {
        Map<String,String> msg = new HashMap<>();
        if (connection != null) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from alarms");
            while (rs.next()) {
                msg.put(rs.getString("time"),rs.getString("event"));
            }
        }
        return msg;
    }

    public static void addEvent(Message message) throws SQLException {
        if (connection != null) {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into alarms(time,event) values(?,?)");
            statement.setString(1,message.getTime());
            statement.setString(2,message.getEvent());
            statement.executeUpdate();

        }
    }
}
