package com.redcoolhax.pebble;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextWindow extends JFrame {
    private JScrollPane textScrollArea;

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