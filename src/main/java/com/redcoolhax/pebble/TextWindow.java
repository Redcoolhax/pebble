package com.redcoolhax.pebble;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * A general-purpose JFrame that displays a block of text, 
 * providing vertical and horizontal scroll bars if needed.
 */
public class TextWindow extends JFrame {
    private JScrollPane textScrollArea;

    /**
     * @param title The title of the window.
     * @param width The width of the window.
     * @param height The height of the window.
     * @param contents The text that this window will display.
     */
    public TextWindow(String title, int width, int height, String contents) {
        super(title);
        setSize(width, height);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JTextArea textArea = new JTextArea(contents);

        textScrollArea = new JScrollPane(
            textArea,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        add(textScrollArea);

        setVisible(true);
    }
}