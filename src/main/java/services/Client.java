package services;

import lombok.AllArgsConstructor;
import model.Message;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;


public class Client {
    Socket clientSocket;
    ObjectOutputStream os;
    ObjectInputStream is;
    public Client(Socket clientSocket) throws IOException {
        this.clientSocket=clientSocket;
        os=new ObjectOutputStream(clientSocket.getOutputStream());
//        is=new ObjectInputStream(clientSocket.getInputStream());
    }

    public Socket connect() throws IOException {
        return new Socket(InetAddress.getLocalHost(), 4321);
    }
    public void sendData(String time, String event,Socket socket) throws IOException, ClassNotFoundException {
       os.writeObject(new Message(time,event));
    }
    public void waiting() throws IOException, ClassNotFoundException {
            Message message= (Message)is.readObject();
            System.out.println("Alarm rings "
                    + message.toString());

    }
}

