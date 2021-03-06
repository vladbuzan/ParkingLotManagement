package model;

import java.io.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/*implemented the Model as a singleton class
and used serialization*/

public class Model implements Serializable {
    private static Model instance;
    private final ParkingLot parkingLot;
    private final History history;

    private Model() {
        parkingLot = new ParkingLot();
        history = new History();
    }

    public static Model getInstance(){
        if(instance != null) return instance;
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("ParkingLot.ser"));
            instance = (Model) inputStream.readObject();
        } catch (Exception e) {
            instance = new Model();
        }
        return instance;
    }

    public void init(Calendar currentDate) {
        parkingLot.init(currentDate);
    }

    public void addCar(String plate, int index, Calendar entryDate) throws IOException {
        Car car = new Car(plate, entryDate);
        parkingLot.addCar(car, index);
    }

    public void removeCar(String plateNumber, Calendar exitDate) throws IOException {
        Car car = parkingLot.removeCar(plateNumber);
        car.setExitDate(exitDate);
        history.addCar(car);
    }

    public int getSpotNo(String plateNumber) throws IOException{
        return parkingLot.getCar(plateNumber).getKey();
    }

    public void elapseOneMinute(Calendar currentDate) {
        parkingLot.elapseOneMinute(currentDate);
    }

    public double getFee(String plateNumber) throws IOException {
        return parkingLot.getCar(plateNumber).getValue().getCurrentFee();
    }

    public double getEarnings() {
        double earnings = 0;
        for(Car car : parkingLot.getCars().values()) {
            earnings += car.getCurrentFee();
        }
        return earnings;
    }

    public String[] getParkedCars() {
        return parkingLot.getCars().values().stream().map(Car::getPlate).toArray(String[]::new);
    }



    public static void close(Calendar currentDate) {
        instance.parkingLot.close(currentDate);
    }

    /*
    * Returns an array of Strings
    * representing info about the cars
    * currently parked as well as the cars
    * that left the parking lot in the past
    * */
    public String[] getHistory() {
        String[] ret;
        Car[] parkedCars = parkingLot.getCars().values().toArray(Car[]::new);
        String[] parkedCarsHistory = Arrays.stream(parkedCars).map(Car::getAsHistory).toArray(String[]::new);
        ret = Stream.concat(Arrays.stream(parkedCarsHistory), Arrays.stream(history.getHistory())).toArray(String[]::new);
        return ret;
    }


}
