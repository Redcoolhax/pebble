package com.redcoolhax.pebble;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Point;

public class MainWindow extends JFrame {
    private static final int REQUEST_LINE_INPUT_HEIGHT = 30;

    private JComboBox<String> requestMethodSelection;
    private JTextField uriField;
    private JTextField httpVersionField;
    private JTextArea requestBody;

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

        setVisible(true);
    }
}