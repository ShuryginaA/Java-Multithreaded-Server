package view;

import model.ServerTimer;
import services.Server;
import services.TimeChanger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame{

    private JTextField textField1;
    private javax.swing.JPanel JPanel;
    private JButton button;

    public MainFrame() {
        textField1.setText(Server.timer.toString());
        button.addActionListener(e -> textField1.setText(Server.timer.toString()));
    }


    public static void main(String[] args){
        MainFrame mf=new MainFrame();
        mf.setContentPane(new MainFrame().JPanel);
        mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mf.setVisible(true);
        mf.pack();

        new TimeChanger();
        Server server=new Server();
        server.start();
    }

}
