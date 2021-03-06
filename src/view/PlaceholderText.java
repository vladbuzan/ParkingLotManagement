package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
/*
* class used to implement placeholder
* behaviour for JTextFields
* */
public class PlaceholderText implements FocusListener {
    private final String text;
    private final JTextField textField;

    public PlaceholderText(String text, JTextField textField) {
        this.text = text;
        this.textField = textField;
    }


    @Override
    public void focusGained(FocusEvent e) {
        if(textField.getText().equals(text)) {
            textField.setText("");
            textField.setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (textField.getText().isEmpty()) {
            textField.setForeground(Color.GRAY);
            textField.setText(text);
        }
    }
}
