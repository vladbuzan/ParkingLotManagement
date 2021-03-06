package view;

import javax.swing.*;
import java.awt.*;

class UserInputPanel extends JPanel {
    private final JButton addCarBtn = new JButton("Add Car");
    private final JTextField addCarTF = new JTextField("Registration plate here");
    private final JTextField spotTF = new JTextField("Spot no.");
    private final JButton removeCarBtn = new JButton("Remove Car");
    private final JButton historyBtn = new JButton("History");

    public UserInputPanel() {
        //add car button panel
        JPanel addCarPanel = new JPanel();
        addCarTF.setColumns(15);
        addCarTF.setForeground(Color.GRAY);
        addCarTF.addFocusListener(new PlaceholderText("Registration plate here", addCarTF));
        spotTF.setColumns(5);
        spotTF.setForeground(Color.GRAY);
        spotTF.addFocusListener(new PlaceholderText("Spot no.", spotTF));
        addCarPanel.setLayout(new FlowLayout());
        addCarPanel.add(addCarTF);
        addCarPanel.add(spotTF);
        addCarPanel.add(addCarBtn);

        //history and remove panel
        JPanel history_removePanel = new JPanel();
        history_removePanel.add(removeCarBtn);
        history_removePanel.add(historyBtn);
        history_removePanel.setSize(10, 10);

        //UserInput
        setLayout(new GridLayout(2,1));
        add(addCarPanel);
        add(history_removePanel);
    }

    String getPlateNumber() {
        return addCarTF.getText();
    }

    String getSpotNo() {
        return spotTF.getText();
    }

    public JButton getAddCarBtn() {
        return addCarBtn;
    }

    public JButton getRemoveCarBtn() {
        return removeCarBtn;
    }

    public JButton getHistoryBtn() {
        return historyBtn;
    }
}
