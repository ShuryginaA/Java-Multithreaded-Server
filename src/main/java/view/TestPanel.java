package view;

import lombok.SneakyThrows;
import model.Message;
import services.Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.*;

class TestPanel extends JFrame {

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
    JPanel panel;

    public TestPanel() throws IOException, ClassNotFoundException {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //setLocationRelativeTo(null);  // better to use..
        setLocationByPlatform(true);
        setTitle("Bla Blubb");
        setResizable(false);
        //setLayout(null); // better to use layouts with padding & borders

        // set a flow layout with large hgap and vgap.
        panel = new JPanel(new FlowLayout(SwingConstants.LEADING, 10, 10));
        // panel.setBounds(5, 5, 290, 290); // better to pack()
        add(panel);
        JTextArea ring=new JTextArea(8,10);
        ring.setVisible(true);
        ring.setFont(ring.getFont().deriveFont(50f));
        panel.add(ring);
        submit=new JButton();
        panel.add(submit);
        connect=new JButton();
        panel.add(connect);
        socket=new Socket(InetAddress.getLocalHost(), 4321);
        client=new Client(socket);
        is=new ObjectInputStream(socket.getInputStream());
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

        while(true) {
            Message message= (Message)is.readObject();
            ring.setText(message.toString());
        }
    }
    private void clearFields(){
        time.setText("");
        event.setText("");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // GUIS should be constructed on the EDT.
        JFrame tt = new TestPanel();
    }
}