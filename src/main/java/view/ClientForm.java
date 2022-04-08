package view;

import com.intellij.uiDesigner.core.GridConstraints;
import lombok.SneakyThrows;
import model.Message;
import services.Client;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientForm extends JFrame{
    private JTextField time;
    private JButton submit;
    private javax.swing.JPanel JPanel;
    private JLabel Comment;
    private JTextField event;
    private JButton connect;
//    private JTextArea ring;
    public static Client client;
    public static Socket socket;
    static ObjectInputStream is;

    public ClientForm() {

        submit.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                client.sendData(time.getText(),event.getText(),socket);
                clearFields();

            }
        });
        connect.addActionListener(e -> {
            try {
                socket=new Socket(InetAddress.getLocalHost(), 4321);
                client=new Client(socket);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
    private void clearFields(){
        time.setText("");
        event.setText("");
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ClientForm mf=new ClientForm();
        mf.setContentPane(new ClientForm().JPanel);
        mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextArea ring=new JTextArea(8,10);
        ring.setVisible(true);
        ring.setFont(ring.getFont().deriveFont(50f));
        mf.add(ring, new GridConstraints());
        mf.setVisible(true);
        mf.pack();

        socket=new Socket(InetAddress.getLocalHost(), 4321);
        client=new Client(socket);
        is=new ObjectInputStream(socket.getInputStream());
        while(true) {
            Message message= (Message)is.readObject();
            ring.setText("Alarm rings!"+ message.toString());
        }

    }
}
