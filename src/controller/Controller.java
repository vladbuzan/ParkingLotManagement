package controller;

import model.Model;
import view.View;

import java.io.IOException;
import java.util.Calendar;


public class Controller {
    private final View view;

    public Controller() {
        Model.getInstance().init(Calendar.getInstance());
        view = new View();
        init();
        view.init();
        ThreadControl threadControl = new ThreadControl(view);
        view.addWindowListener(new WindowClosingSerializer(threadControl));
        threadControl.start();
    }

    private void init() {

        view.addCarBtnListener(e -> {
            String plateNumber = view.getPlateNumber();
            try {
                int index = Integer.parseInt(view.getSpotNo());
                Model.getInstance().addCar(plateNumber, index, Calendar.getInstance());
            } catch (IOException ioException) {
                View.displayError(ioException.getMessage());
            } catch (NumberFormatException numberFormatException) {
                View.displayError("Invalid number format");
            }
            view.updateParkingLotList();
        });

        view.addRemoveBtnListener(e -> {
            try {
                Model.getInstance().removeCar(view.getSelectedCar(), Calendar.getInstance());
            } catch (IOException ioException) {
                View.displayError(ioException.getMessage());
            }
            view.updateParkingLotList();
        });

        view.addHistoryBtnListener(e -> {
            // TODO: 9/10/2020
        });

        view.addViewLotListener(e -> {
            view.viewLot();
        });
        view.addParkingLotListener(e -> {
            if(!e.getValueIsAdjusting()) {
                try {
                    view.updateSelectedCarInfo();
                } catch (IOException ioException) {
                    View.displayError(ioException.getMessage());
                }
            }
        });
    }


}
