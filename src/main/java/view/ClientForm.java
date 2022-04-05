package view;

import lombok.SneakyThrows;
import services.Client;
import services.ClientHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClientForm extends JFrame{
    private JTextField time;
    private JButton Submit;
    private javax.swing.JPanel JPanel;
    private JLabel Comment;
    private JTextField event;
    public static Client client;

    public ClientForm() {
        Submit.addActionListener(new ActionListener() {
            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent e) {
                client.sendData(time.getText(),event.getText());


            }
        });
    }
    public static void main(String[] args) throws IOException {
        ClientForm mf=new ClientForm();
        mf.setContentPane(new ClientForm().JPanel);
        mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mf.setVisible(true);
        mf.pack();

        client=new Client();

    }
}
