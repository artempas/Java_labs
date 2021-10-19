package com.miet.pin23.pasechnik.lab2;

import java.util.ArrayList;

/**
 * Train class
 */
public class Train {
    /**
     * ArrayList of cars
     */
    private ArrayList<Carriage> cars;
    public Train(ArrayList<Carriage> carsVal){
        cars = carsVal;
    }
    public Train(){
        cars = new ArrayList<Carriage> (  );
    }

    public ArrayList<Carriage> getCars ( ) {
        return cars;
    }

    public void setCars ( ArrayList<Carriage> cars ) {
        this.cars = cars;
    }

    /**
     * Adds ArrayList of carriages to the train
     * @param cars ArrayList of carriages
     */
    public void addCars(ArrayList<Carriage> cars){
        this.cars.addAll ( cars );
    }

    /**
     * Adds one carriage to the train
     * @param car carriage
     */
    public void addCar(Carriage car){
        this.cars.add ( car );
    }

    /**
     * Prints train to System.out
     */
    public void printTrain(){
        System.out.println ( "Train: " );
        for (Carriage car:
             cars) {
            car.printCarriage ();
        }
    }
}
