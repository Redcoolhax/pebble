package com.redcoolhax.pebble;

import java.net.http.HttpResponse;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ResponseWindow extends JFrame {
    private JTextArea responseBody;
    private JScrollPane responseBodyScrollPane;

    public ResponseWindow(HttpResponse<String> response) {
        super("HTTP Response");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        responseBody = new JTextArea(response.body());
        
        responseBodyScrollPane = new JScrollPane(
            responseBody,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        responseBodyScrollPane.setBounds(10, 10, 300, 300);
        add(responseBodyScrollPane);

        setVisible(true);
    }
}