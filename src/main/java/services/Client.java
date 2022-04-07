package services;

import lombok.AllArgsConstructor;
import model.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;


public class Client {
    Socket clientSocket;
    ObjectOutputStream os;
    public Client(Socket clientSocket) throws IOException {
        this.clientSocket=clientSocket;
        os=new ObjectOutputStream(clientSocket.getOutputStream());
    }

    public Socket connect() throws IOException {
        return new Socket(InetAddress.getLocalHost(), 4321);
    }
    public void sendData(String time, String event,Socket socket) throws IOException {
       os.writeObject(new Message(time,event));

        // reading from server
//        BufferedReader in
//                = new BufferedReader(new InputStreamReader(
//                socket.getInputStream()));
//
//            // displaying server reply
//            System.out.println("Server replied "
//                    + in.readLine());
//            socket.close();

        // closing the scanner object
    }
}

