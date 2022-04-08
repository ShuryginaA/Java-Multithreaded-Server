package view;

import services.Server;
import services.TimeChanger;
import static services.Server.events;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.util.Map;

public class MainFrame extends JFrame{

    private JTextField start_time;
    private javax.swing.JPanel JPanel;
    private JButton button;
    private JTextField current_time;
    private JTextArea Events;
    private JButton showEvents;

    public MainFrame() {
        start_time.setText("Start server time: 0:0");
        button.addActionListener(e -> current_time.setText(Server.timer.getTimer().toString()));
        showEvents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder s= new StringBuilder();
                for(Map.Entry<LocalTime,String> map:events.entrySet())
                {
                    s.append(map.getKey()).append(" ").append(map.getValue()).append("\n");
                }
                Events.setText(s.toString());
            }
        });
    }


    public static void main(String[] args) throws IOException {
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
