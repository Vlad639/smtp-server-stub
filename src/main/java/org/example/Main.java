package org.example;


import org.subethamail.smtp.server.SMTPServer;

import javax.swing.*;
import java.nio.charset.StandardCharsets;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

public class Main {

    public static void main(String[] args) {
        SMTPServer server = SMTPServer
                .port(25000)
                .messageHandler((messageContext, from, to, messageBody) -> {
                    JFrame frame = new JFrame("New message!");
                    frame.setSize(800, 600);
                    String mailMessage = new String(messageBody, StandardCharsets.UTF_8);

                    JTextArea textArea = new JTextArea(mailMessage);
                    JScrollPane jScrollPane = new JScrollPane(textArea, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    frame.add(jScrollPane);
                    frame.setVisible(true);
                    System.out.println(mailMessage);
                    System.out.println("---------------------------------------------------------");
                })
                .build();

        server.start();
    }

}
