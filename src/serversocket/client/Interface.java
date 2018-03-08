package serversocket.client;

import serversocket.client.Client;
import serversocket.client.Constants;

import javax.swing.*;
import java.awt.*;

// Сделать текст ареа неизменяемой.
public class Interface {
    public static JFrame frame;
    public static JTextField textField;
    public static JTextArea textArea;
    public static JButton sendBtn;


    public static void drawFrame() {
        frame = new JFrame();
        textArea = new JTextArea(Constants.FRAME_HEIGHT / 19, 50);
        textField = new JTextField();
        sendBtn = new JButton();
        textArea.setLineWrap(true);
        sendBtn.setText("Send");
        sendBtn.setToolTipText("Click to send message");
        sendBtn.addActionListener(e -> {
            try {
                sendBtn_Click();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle(Constants.TITLE);
        frame.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        frame.setLocation(0, 0);
        frame.getContentPane().add(BorderLayout.NORTH, textArea);
        frame.getContentPane().add(BorderLayout.CENTER, textField);
        frame.getContentPane().add(BorderLayout.EAST, sendBtn);
        textArea.setEditable(false);
        frame.setVisible(true);
    }

    public static void sendBtn_Click(){
        String msg = textField.getText();
        if (!msg.isEmpty()){
            textField.setText("");
            Client.sendMessage(msg);
        }
        else {
            msg = textField.getText();
        }
    }


}
