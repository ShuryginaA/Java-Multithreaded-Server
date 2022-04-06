package services;

import model.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

// services.Client class
public class Client {

    // driver code
    public void connect() throws IOException {
        Socket socket = new Socket("localhost", 1234);
    }
    public void sendData(String time, String event) throws IOException {
        Socket socket = new Socket("localhost", 1234);
       ObjectOutputStream os=new ObjectOutputStream(socket.getOutputStream());
       os.writeObject(new Message(time,event));

        // reading from server
//        BufferedReader in
//                = new BufferedReader(new InputStreamReader(
//                socket.getInputStream()));
//
//            // displaying server reply
//            System.out.println("Server replied "
//                    + in.readLine());
            socket.close();

        // closing the scanner object
    }
}

