package controller;

import model.Model;
import view.View;

import java.io.IOException;
import java.util.Calendar;

public class FeeUpdaterThread implements Runnable {
    private final View view;
    public FeeUpdaterThread(View view) {
        this.view = view;
    }

    @Override
    public void run() {
        Model.getInstance().elapseOneMinute(Calendar.getInstance());
        try {
            view.updateSelectedCarInfo();
            view.updateEarnings();
        } catch (IOException ioException) {
            //do nothing
        }
    }
}

