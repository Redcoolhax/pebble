package com.redcoolhax.pebble;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * The window that the entire program is run on. 
 * Comes with various components for building and sending HTTP requests, 
 * as well as various error-handling measures.
 */
public class MainWindow extends JFrame {
    private static final int FIRST_ROW_COMPONENT_HEIGHT = 30;

    private JComboBox<String> requestMethodSelection;
    private JTextField uriField;
    private JComboBox<String> httpVersionSelection;
    private JTextArea headersArea;
    private JTextArea requestBody;
    private JButton sendRequestButton;

    /**
     * Creates the MainWindow. Should only ever be called once for the duration of the program.
     */
    public MainWindow() {
        super("Pebble");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        Point topLeft = new Point(10, 20);

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
        requestMethodSelection.setSize(100, FIRST_ROW_COMPONENT_HEIGHT);
        add(requestMethodSelection);

        topLeft.x += requestMethodSelection.getWidth() + 10;

        uriField = new JTextField();
        uriField.setLocation(topLeft);
        uriField.setSize(300, FIRST_ROW_COMPONENT_HEIGHT);
        add(uriField);

        topLeft.x += uriField.getWidth() + 10;

        String[] httpVersions = {
            "HTTP/1.1",
            "HTTP/2"
        };
        httpVersionSelection = new JComboBox<>(httpVersions);
        httpVersionSelection.setLocation(topLeft);
        httpVersionSelection.setSize(100, FIRST_ROW_COMPONENT_HEIGHT);
        add(httpVersionSelection);

        labelComponent(requestMethodSelection, "Request Method");
        labelComponent(uriField, "URI");
        labelComponent(httpVersionSelection, "HTTP Version");

        topLeft.x = 10;
        topLeft.y += FIRST_ROW_COMPONENT_HEIGHT + 30;

        headersArea = new JTextArea();
        headersArea.setLocation(topLeft);
        headersArea.setSize(200, 200);
        add(headersArea);

        topLeft.x += headersArea.getWidth() + 30;

        requestBody = new JTextArea();
        requestBody.setLocation(topLeft);
        requestBody.setSize(300, 200);
        add(requestBody);

        labelComponent(headersArea, "HTTP Headers");
        labelComponent(requestBody, "Request Body");

        sendRequestButton = new JButton("Send Request");
        sendRequestButton.setSize(150, 40);
        sendRequestButton.setLocation(
            (this.getWidth() / 2) - (sendRequestButton.getWidth() / 2),
            300
        );
        sendRequestButton.addActionListener(this::onSendRequestButtonPress);
        add(sendRequestButton);

        setVisible(true);
    }

    /**
     * Creates a label for a component that is automatically added just above it.
     * @param component The component to be labeled.
     * @param label The text displayed above the component.
     */
    private void labelComponent(JComponent component, String label) {
        JLabel newLabel = new JLabel(label);
        newLabel.setBounds(component.getX(), component.getY() - 20, component.getWidth(), 20);
        add(newLabel);
    }

    /**
     * The method to be assigned to the sendRequestButton. 
     * Collects all the data from the other components, builds and sends an HTTP request.
     * @param event
     */
    private void onSendRequestButtonPress(ActionEvent event) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
            .uri(URI.create(uriField.getText()))
            .method(
                requestMethodSelection.getSelectedItem().toString(),
                HttpRequest.BodyPublishers.ofString(requestBody.getText())
            )
            .version(httpVersionStringToEnum(httpVersionSelection.getSelectedItem().toString()));
        for (Pair<String, String> headerPair : getHeaderInput())
            requestBuilder.header(headerPair.getKey(), headerPair.getValue());

        HttpRequest request = requestBuilder.build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            new TextWindow("HTTP Response", 400, 400, response.body());
        } catch (IOException e) {
            newTextWindowForResourceAndStackTrace(
                "IOException Occured During Request", 700, 400,
                "send_request_io_exception.txt", e
            );
        } catch (InterruptedException e) {
            
        }
    }

    /**
     * Retrieves the info from the headersArea and returns it as an ArrayList of key-value Pairs.
     * @return The HTTP headers inputted by the user in the headersArea.
     * @throws IllegalStateException If the input in the headersArea is invalid.
     */
    private ArrayList<Pair<String, String>> getHeaderInput() {
        String headerString = headersArea.getText();

        if (headerString.equals(""))
            return new ArrayList<>();
        
        String[] lines = headerString.split("\n");
        ArrayList<Pair<String, String>> headerPairs = new ArrayList<>();
        for (String line : lines) {
            String[] pair = line.split(":");
            if (pair.length != 2)
                throw new IllegalStateException("Error when parsing HTTP headers.");
            headerPairs.add(new Pair<>(pair[0].trim(), pair[1].trim()));
        }
        return headerPairs;
    }

    /**
     * Creates a new TextWindow with a resource text file, with the stack trace of 
     * an Exception appended to the end of it.
     * @param title The title of the window.
     * @param width The width of the window.
     * @param height The height of the window.
     * @param resourcePath The path to the resource file.
     * @param e The Exception to get the stack trace from.
     */
    private static void newTextWindowForResourceAndStackTrace(
        String title, int width, int height, String resourcePath, Exception e
        ) {
        try {
            new TextWindow(title, width, height,
                ResourceReading.readAsText(resourcePath) + stackTraceAsString(e)
            );
        } catch (Exception ex) {
            handleExceptionForResourceReading(ex);
        }
    }

    /**
     * Creates a new TextWindow with a default message for when an Exception occurs 
     * while trying to read a resource.
     * @param e The Exception to get the stack trace from.
     */
    private static void handleExceptionForResourceReading(Exception e) {
        new TextWindow("Resource Reading Failed", 400, 400,
            "A catastrophic error has occured when trying to read an internal resource. " +
            "Please look at the stack trace below and file a bug report:\n" +
            stackTraceAsString(e)
        );
    }

    /**
     * @param e An Exception.
     * @return The Exception's stack trace as a String.
     */
    private static String stackTraceAsString(Exception e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        return stringWriter.toString();
    }

    /**
     * Converts a String representing an HTTP version (as it shows up in an HTTP request 
     * or the httpVersionSelection JComboBox) to its equivalent HttpClient.Version enum value.
     * @param version A String representing an HTTP version as it shows up in an HTTP request.
     * @return The respective HttpClient.Version enum value.
     * @throws IllegalArgumentException If the provided String does not correspond to a valid 
     * HTTP version.
     */
    private static HttpClient.Version httpVersionStringToEnum(String version) {
        switch (version) {
            case "HTTP/1.1" -> {return HttpClient.Version.HTTP_1_1;}
            case "HTTP/2" -> {return HttpClient.Version.HTTP_2;}
            default -> {throw new IllegalArgumentException(
                "Version: '" + version + "'' does not correspond to a valid HTTP version."
            );}
        }
    }
}