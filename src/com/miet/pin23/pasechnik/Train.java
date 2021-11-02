package com.miet.pin23.pasechnik;

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
            System.out.println (  );
        }
    }
    public String ToString(){
        StringBuilder str= new StringBuilder ( "Train: \n" );
        for (Carriage car:
             cars) {
            str.append ( car.toString ( )+'\n' );
        }
        return str.toString ( );
    }
    public void editTrain(){
        System.out.println ( ConsoleColors.ANSI_YELLOW+"1-Добавить вагон" );
        System.out.println ( "2-удалить вагон"+ConsoleColors.ANSI_RESET );
        int menu = Program.integerInput ();
        while(!(menu==1 || menu==2)){
            System.out.println ( ConsoleColors.ANSI_RED+"Введите цифру от 1 до 2"+ConsoleColors.ANSI_RESET );
            menu=Program.integerInput ();
        }
        switch (menu){
            case (1):
                System.out.println ( ConsoleColors.ANSI_YELLOW+"Выберите тип вагона: ");
                System.out.println ( ConsoleColors.ANSI_CYAN+"1 - Автовоз" );
                System.out.println ( "2 - Цистерна для жидкостей" );
                System.out.println ( "3 - Цистерна для сыпучих грузов" );
                System.out.println ( "4 - Контейнер"+ConsoleColors.ANSI_RESET );
                int type = Program.integerInput ();
                while (type>4 || type<1){
                    type = Program.integerInput ();
                    System.out.println ( ConsoleColors.ANSI_RED+"Введите цифру от 1 до 4"+ConsoleColors.ANSI_RESET );

                }
                switch (type) {
                    case 1 -> this.addCar ( new CarsCarriage ( ) );
                    case 2 -> this.addCar ( new CisternCarriage ( ) );
                    case 3 -> this.addCar ( new TankCarriage ( ) );
                    case 4 -> this.addCar ( new ContainerCarriage ( ) );
                }
                break;
            case 2:
                System.out.println ( ConsoleColors.ANSI_BLUE + String.format ( "Какой вагон вы хотели бы удалить (от 1 до %d)" , cars.size ( ) ) + ConsoleColors.ANSI_RESET );
                int ind = Program.integerInput ( );
                while (ind > cars.size ( ) || ind < 0) {
                    System.out.println ( ConsoleColors.ANSI_RED + String.format ( "Введите цифру от 1 до %d" , cars.size ( ) ) + ConsoleColors.ANSI_RESET );
                    ind = Program.integerInput ( );
                }
                cars.remove ( ind-1 );
                break;
        }

    }
}
