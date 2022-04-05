package services;

import java.io.*;
import java.net.*;
import java.util.*;

// services.Client class
public class Client {

    // driver code
    public void connect() throws IOException {
        Socket socket = new Socket("localhost", 1234);
    }
    public void sendData(String time, String event) throws IOException {
        Socket socket = new Socket("localhost", 1234);
        PrintWriter out = new PrintWriter(
                socket.getOutputStream(), true);

        // reading from server
        BufferedReader in
                = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));

            out.println(time+" "+event);
            out.flush();

            // displaying server reply
            System.out.println("Server replied "
                    + in.readLine());

        // closing the scanner object
    }
}

