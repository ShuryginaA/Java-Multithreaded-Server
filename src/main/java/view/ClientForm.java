package view;

import lombok.SneakyThrows;
import services.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientForm extends JFrame{
    private JTextField time;
    private JButton submit;
    private javax.swing.JPanel JPanel;
    private JLabel Comment;
    private JTextField event;
    private JButton connect;
    private JTextArea ring;
    public static Client client;
    public Socket socket;

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
                client=new Client(new Socket(InetAddress.getLocalHost(), 4321));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
    private void clearFields(){
        time.setText("");
        event.setText("");
    }
    public static void main(String[] args) throws IOException {
        ClientForm mf=new ClientForm();
        mf.setContentPane(new ClientForm().JPanel);
        mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mf.setVisible(true);
        mf.pack();


    }
}
