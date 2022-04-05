package view;

import services.Server;
import services.TimeChanger;

import javax.swing.*;

public class MainFrame extends JFrame{

    private JTextField start_time;
    private javax.swing.JPanel JPanel;
    private JButton button;
    private JTextField current_time;

    public MainFrame() {
        start_time.setText("Start server time: 0:0");
        button.addActionListener(e -> current_time.setText(Server.timer.toString()));
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
