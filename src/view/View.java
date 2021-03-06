package view;

import model.Model;
import model.Utils;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class View extends JFrame {
    private final ParkingLotPanel parkingLotPanel = new ParkingLotPanel();
    private final UserInputPanel userInputPanel = new UserInputPanel();
    private final SpotInfoPanel spotInfoPanel = new SpotInfoPanel();
    private ParkingLotView parkingLotView;

    public View() {
        super("Parking Lot Monitor");

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");  // use windows theme
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        setSize(380, 420);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        JPanel listPanel = new JPanel();
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new GridLayout(2, 1));
        listPanel.add(parkingLotPanel);
        userPanel.add(userInputPanel);
        userPanel.add(spotInfoPanel);

        mainPanel.add(listPanel);
        mainPanel.add(userPanel);
        mainPanel.setLayout(new GridLayout(1,2));
        setContentPane(mainPanel);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void init() {
        updateEarnings();
        updateParkingLotList();
    }

    public String getPlateNumber() {
        return userInputPanel.getPlateNumber();
    }

    public String getSelectedCar() throws IOException {
        String ret = parkingLotPanel.getParkingLotList().getSelectedValue();
        if(ret == null) throw new IOException("No car selected");
        return ret;
    }

    public String getSpotNo() {
        return userInputPanel.getSpotNo();
    }

    public void updateParkingLotList() {
        parkingLotPanel.getParkingLotList().setListData(Model.getInstance().getParkedCars());
        if(parkingLotView != null) {
            System.out.println("called");
            parkingLotView.update();
        }
    }

    public void updateSelectedCarInfo() throws IOException {
        String car = parkingLotPanel.getParkingLotList().getSelectedValue();
        if(car == null) return; //occurs when a spot is selected and a new car is added
        int spot = Model.getInstance().getSpotNo(car);
        double currentFee = Model.getInstance().getFee(car);
        spotInfoPanel.updateSpotTF(Integer.toString(spot));
        spotInfoPanel.updateFeeTF(String.format("%.2f", currentFee));
    }

    public void updateEarnings() {
        String earnings = String.format("%.2f",Model.getInstance().getEarnings());
        spotInfoPanel.updateEarningsTF(earnings);
    }

    public void viewLot() {
        parkingLotView = new ParkingLotView();
    }

    public void updateDate(String text) {
        spotInfoPanel.updateDate(text);
    }

    public void addCarBtnListener(ActionListener actionListener) {
        userInputPanel.getAddCarBtn().addActionListener(actionListener);
    }

    public void addRemoveBtnListener(ActionListener actionListener) {
        userInputPanel.getRemoveCarBtn().addActionListener(actionListener);
    }

    public void addHistoryBtnListener(ActionListener actionListener) {
        userInputPanel.getHistoryBtn().addActionListener(actionListener);
    }

    public void addParkingLotListener(ListSelectionListener listSelectionListener) {
        parkingLotPanel.getParkingLotList().addListSelectionListener(listSelectionListener);
    }

    public void addViewLotListener(ActionListener actionListener) {
        spotInfoPanel.getViewLotBtn().addActionListener(actionListener);
    }

    public static void displayError(String message) {
        JOptionPane.showMessageDialog(new JFrame(), message,"Dialog", JOptionPane.ERROR_MESSAGE);
    }
}
