package view;

import javax.swing.*;
import java.awt.*;

class SpotInfoPanel extends JPanel {
    private final JTextField currentSpotTF = new JTextField();
    private final JTextField earningsTF = new JTextField();
    private final JTextField feeTF = new JTextField();
    private final JLabel dateLabel = new JLabel();
    private final JButton viewLotBtn = new JButton("View Lot");

     SpotInfoPanel() {
        //parking spot no. panel
        JPanel spotPanel = new JPanel();
        spotPanel.add(new JLabel("Parked at: "));
        currentSpotTF.setColumns(3);
        currentSpotTF.setEditable(false);
        spotPanel.add(currentSpotTF);

        //earnings panel
        JPanel earningsPanel = new JPanel();
        earningsTF.setColumns(10);
        earningsPanel.add(new JLabel("Earnings:"));
        earningsTF.setColumns(5);
        earningsTF.setEditable(false);
        earningsPanel.add(earningsTF);

        //fee panel
        JPanel feePanel = new JPanel();
        feeTF.setColumns(5);
        feeTF.setEditable(false);
        feePanel.add(new JLabel("Current fee:"));
        feePanel.add(feeTF);

        //date panel
        JPanel datePanel = new JPanel();
        datePanel.add(dateLabel);

        //view lot panel
        JPanel viewLotPanel = new JPanel();
        viewLotPanel.add(viewLotBtn);

        //SpotInfo
        setLayout(new GridLayout(5,1));
        add(spotPanel);
        add(earningsPanel);
        add(feePanel);
        add(datePanel);
        add(viewLotPanel);
    }

    JButton getViewLotBtn() {
         return viewLotBtn;
    }
    void updateSpotTF(String text){
        currentSpotTF.setText(text);
    }

    void updateFeeTF(String text) {
         feeTF.setText(text);
    }

    void updateEarningsTF(String text) {
         earningsTF.setText(text);
    }

    void updateDate(String text) {
         dateLabel.setText(text);
    }


}
