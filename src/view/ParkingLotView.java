package view;

import model.Model;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

class ParkingLotView extends JFrame {
    ParkingLotView() {
        super("Parking Lot");
        setSize(430, 380);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        update();
        setVisible(true);
    }

    void update() {
        String[] parkingSpot = new String[50];
        System.out.println("called too");
        var model = Model.getInstance();
        var cars = model.getParkedCars();
        var mainPanel = new JPanel(new GridLayout(10, 5));

        for (String car : cars) {
            try {
                parkingSpot[model.getSpotNo(car) - 1] = car;
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        for (int i = 0; i < parkingSpot.length; i++) {
            var label = new JLabel(Integer.toString(i + 1) + " " +
                    (parkingSpot[i] == null? " " : parkingSpot[i]));
            var panel = new JPanel();
            panel.add(label);
            if(parkingSpot[i] == null) {
                panel.setBackground(Color.GREEN);
            } else {
                panel.setBackground(Color.RED);
            }
            mainPanel.add(panel);
        }
        setContentPane(mainPanel);
    }
}
