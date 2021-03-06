package model;

import java.io.Serializable;
import java.util.ArrayList;

/*class used to hold information about
cars that have been parked in the parking
lot and left*/

class History implements Serializable {
    private final ArrayList<Car> removedCars;

    History() {
        removedCars = new ArrayList<>();
    }

    void addCar(Car car) {
        removedCars.add(car);
    }

    String[] getHistory() {
        String[] ret = removedCars.stream().map(Car::getAsHistory).toArray(String[]::new);
        return ret;
    }
}
