package model;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;

public class Car implements Serializable {
    private final String plate;
    private double currentFee;
    private final Calendar entryDate;
    private Calendar exitDate;


    public Car(String plate, Calendar entryDate) throws IOException {
        if (!isValidPlate(plate)) throw new IOException("Invalid plate number");
        this.plate = plate;
        this.entryDate = entryDate;
        this.currentFee = 0;
        this.exitDate = null; //no point knowing the exitDate at the creation of the car
    }

    double getCurrentFee() {
        return this.currentFee;
    }
    String getPlate() {
        return plate;
    }

    public void setExitDate(Calendar exitDate) {
        this.exitDate = exitDate;
    }

    void incrementFee(double fee) {
        currentFee += fee;
    }

    private boolean isValidPlate(String plate) {
        return Utils.isValidPlate(plate);
    }

    String getAsHistory() {
        StringBuilder ret = new StringBuilder();
        ret.append("Car: ").append(plate);
        ret.append(" entry date: ").append(Utils.calendarAsString(entryDate));
        if(exitDate != null) {
            ret.append(" exit date: ").append(Utils.calendarAsString(exitDate));
            ret.append(" paid: ").append(currentFee);
        } else {
            ret.append(" currently parked");
        }
        return ret.toString();
    }

    @Override
    public boolean equals(Object car) {
        if(car instanceof Car)
            return ((Car) car).plate.equals(this.plate);
        else return false;
    }




}

