package model;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

class ParkingLot implements Serializable {
    private static final double DAY_FEE = 5/60f; //price during day/hour
    private static final double NIGHT_FEE = 8/60f; //price during night/hour
    private static final int NIGHT_START = 20; //the hour when the night fee starts being applied
    private static final int NIGHT_END = 6; //the hour when the night fee stops being applied
    private static final int CAPACITY = 50;
    private Calendar dateOfExit;
    private final HashMap<Integer,Car> cars;

    ParkingLot() {
        cars = new HashMap<>();
    }

    void addCar(Car car, int index) throws IOException {
        if(index <= 0) throw new IOException("Spots start from number 1");
        if(index > CAPACITY ) throw new IOException("Spot no. bigger than actual capacity");
        if(cars.containsKey(index)) throw new IOException("Spot is occupied");
        if(cars.containsValue(car)) throw new IOException("Car already in the parking lot");
        if(cars.size() == CAPACITY) throw new IOException("Lot is full");
        cars.put(index, car);
    }

    Car removeCar(String plateNumber) throws IOException {
        for(Car car : cars.values()) {
            if(plateNumber.equals(car.getPlate())) {
                cars.values().remove(car);
                return car;
            }
        }
        throw new IOException("No such car in the parking lot");
    }

    HashMap<Integer, Car> getCars() {
        return cars;
    }

    Map.Entry<Integer, Car> getCar(String plateNumber) throws IOException {
        for(Map.Entry<Integer, Car> entry : cars.entrySet()) {
            if(plateNumber.equals(entry.getValue().getPlate())) {
                return entry;
            }
        }
        throw new IOException("No such car in the parking lot");
    }

    void elapseOneMinute(Calendar currentTime) {
        int currentHour = currentTime.get(Calendar.HOUR_OF_DAY);
        double increment;
        if(currentHour > NIGHT_START || currentHour < NIGHT_END) {
            increment = NIGHT_FEE;
        } else {
            increment = DAY_FEE;
        }
        for(Car car : cars.values()) {
            car.incrementFee(increment);
        }
    }

    /*
    * Update the currentFee of each
    * car based on the time spent in the
    * parking lost since the last time
    * the app was close i.e. serialized
    * */
    void init(Calendar currentDate) {
        if(dateOfExit == null) return;
        double increment = 0;
        currentDate.add(Calendar.MINUTE, 1);
        while(currentDate.compareTo(dateOfExit) > 0) {
            int hour = dateOfExit.get(Calendar.HOUR_OF_DAY);
            if(hour > NIGHT_START || hour < NIGHT_END) {
                increment += NIGHT_FEE;
            } else {
                increment += DAY_FEE;
            }
            dateOfExit.add(Calendar.MINUTE, 1);
        }
        double finalIncrement = increment; //intellij complains otherwise so
        cars.values().forEach(car -> car.incrementFee(finalIncrement));
    }

    void close(Calendar currentDate) {
        dateOfExit = currentDate;
    }

}
