package view;

import javax.swing.*;
import java.awt.*;

class ParkingLotPanel extends JPanel {
    private final JList<String> parkingLotList = new JList<>();

    ParkingLotPanel() {
        setLayout(new BorderLayout());
        parkingLotList.setFixedCellWidth(18);
        parkingLotList.setVisibleRowCount(18);
        parkingLotList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane parkingLotSP = new JScrollPane(parkingLotList);
        JLabel parkingLotLabel = new JLabel("Cars in the parking lot");
        add(parkingLotLabel, BorderLayout.NORTH);
        add(parkingLotSP, BorderLayout.CENTER);
    }

    JList<String> getParkingLotList() {
        return parkingLotList;
    }
}
