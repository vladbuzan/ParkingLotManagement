package controller;

import model.Utils;
import view.View;

import java.util.Calendar;

public class DateUpdaterThread implements Runnable{
    private final View view;

    public DateUpdaterThread(View view) {
        this.view = view;
    }

    @Override
    public void run() {
        view.updateDate(Utils.calendarAsString_seconds(Calendar.getInstance()));
    }
}
