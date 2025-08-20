package com.redcoolhax.pebble;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainWindow extends JFrame {
    private static final int REQUEST_LINE_INPUT_HEIGHT = 30;

    private JComboBox<String> requestMethodSelection;
    private JTextField uriField;
    private JTextField httpVersionField;
    private JTextArea requestBody;
    private JButton sendRequestButton;

    public MainWindow() {
        super("Pebble");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        Point topLeft = new Point(10, 10);

        String[] requestMethods = {
            "GET",
            "POST",
            "PUT",
            "DELETE",
            "PATCH",
            "HEAD",
            "OPTIONS",
            "CONNECT",
            "TRACE"
        };
        requestMethodSelection = new JComboBox<>(requestMethods);
        requestMethodSelection.setLocation(topLeft);
        requestMethodSelection.setSize(100, REQUEST_LINE_INPUT_HEIGHT);
        add(requestMethodSelection);

        topLeft.x += requestMethodSelection.getWidth() + 10;

        uriField = new JTextField();
        uriField.setLocation(topLeft);
        uriField.setSize(300, REQUEST_LINE_INPUT_HEIGHT);
        add(uriField);

        topLeft.x += uriField.getWidth() + 10;

        httpVersionField = new JTextField();
        httpVersionField.setLocation(topLeft);
        httpVersionField.setSize(100, REQUEST_LINE_INPUT_HEIGHT);
        add(httpVersionField);

        requestBody = new JTextArea();
        requestBody.setBounds(50, 100, 300, 200);
        add(requestBody);

        sendRequestButton = new JButton("Send Request");
        sendRequestButton.setBounds(400, 300, 100, 40);
        sendRequestButton.addActionListener(this::onSendRequestButtonPress);
        add(sendRequestButton);

        setVisible(true);
    }

    private void onSendRequestButtonPress(ActionEvent event) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(uriField.getText()))
            .method(
                requestMethodSelection.getSelectedItem().toString(),
                HttpRequest.BodyPublishers.ofString(requestBody.getText())
            )
            .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            new ResponseWindow(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}