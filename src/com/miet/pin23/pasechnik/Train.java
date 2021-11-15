package com.miet.pin23.pasechnik;

import java.io.InvalidClassException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Train class
 */
public class Train implements Serializable {
    /**
     * ArrayList of cars
     */
    private ArrayList<Carriage> cars;
    private static Logger logger = new Logger ( Train.class.getName ( ) );
    private static ExceptionHandler exceptionHandler = new ExceptionHandler ( Train.class.getName ( ) );

    public Train ( ArrayList<Carriage> carsVal ) {
        cars = carsVal;
    }

    public Train ( ) {
        cars = new ArrayList<Carriage> ( );
    }

    public ArrayList<Carriage> getCars ( ) {
        return cars;
    }

    public void setCars ( ArrayList<Carriage> cars ) {
        this.cars = cars;
    }

    /**
     * Adds ArrayList of carriages to the train
     *
     * @param cars ArrayList of carriages
     */
    public void addCars ( ArrayList<Carriage> cars ) {
        this.cars.addAll ( cars );
    }

    /**
     * Adds one carriage to the train
     *
     * @param car carriage
     */
    public void addCar ( Carriage car ) {
        this.cars.add ( car );
    }

    /**
     * Prints train to System.out
     */
    public void printTrain ( ) {
        System.out.println ( "Train: " );
        for (Carriage car :
                cars) {
            car.printCarriage ( );
            System.out.println ( );
        }
    }

    public String ToString ( ) {
        StringBuilder str = new StringBuilder ( "Train: \n" );
        for (Carriage car :
                cars) {
            str.append ( car.toString ( ) + '\n' );
        }
        return str.toString ( );
    }

    public void editTrain ( ) {
        System.out.println ( ConsoleColors.ANSI_YELLOW + "1-Добавить вагон" );
        System.out.println ( "2-удалить вагон" + ConsoleColors.ANSI_RESET );
        int menu = Program.integerInput ( );
        while (!(menu == 1 || menu == 2)) {
            System.out.println ( ConsoleColors.ANSI_RED + "Введите цифру от 1 до 2" + ConsoleColors.ANSI_RESET );
            menu = Program.integerInput ( );
        }
        switch (menu) {
            case (1) -> {
                System.out.println ( ConsoleColors.ANSI_YELLOW + "Выберите тип вагона: " );
                System.out.println ( ConsoleColors.ANSI_CYAN + "1 - Автовоз" );
                System.out.println ( "2 - Цистерна для жидкостей" );
                System.out.println ( "3 - Цистерна для сыпучих грузов" );
                System.out.println ( "4 - Контейнер" + ConsoleColors.ANSI_RESET );
                int type = Program.integerInput ( );
                while (type > 4 || type < 1) {
                    type = Program.integerInput ( );
                    System.out.println ( ConsoleColors.ANSI_RED + "Введите цифру от 1 до 4" + ConsoleColors.ANSI_RESET );

                }
                switch (type) {
                    case 1 -> this.addCar ( new CarsCarriage ( ) );
                    case 2 -> this.addCar ( new CisternCarriage ( ) );
                    case 3 -> this.addCar ( new TankCarriage ( ) );
                    case 4 -> this.addCar ( new ContainerCarriage ( ) );
                }
            }
            case 2 -> {
                System.out.println ( ConsoleColors.ANSI_BLUE + String.format ( "Какой вагон вы хотели бы удалить (от 1 до %d)" , cars.size ( ) ) + ConsoleColors.ANSI_RESET );
                int ind = Program.integerInput ( );
                while (ind > cars.size ( ) || ind < 0) {
                    System.out.println ( ConsoleColors.ANSI_RED + String.format ( "Введите цифру от 1 до %d" , cars.size ( ) ) + ConsoleColors.ANSI_RESET );
                    ind = Program.integerInput ( );
                }
                cars.remove ( ind - 1 );
            }
        }

    }

    public void generateRandom ( ) {
        int trainsCnt = new Random ( ).nextInt ( 10 );
        cars = new ArrayList<Carriage> ( trainsCnt );
        generating ( trainsCnt );
    }

    public void generateRandom ( int carsToCreate ) {
        cars = new ArrayList<> ( carsToCreate );
        generating ( carsToCreate );
    }

    public void generateRandom ( int carsToCreate, int goodsPerCar ) {
        cars = new ArrayList<> ( carsToCreate );
        generating ( carsToCreate , goodsPerCar );
    }

    private void generating ( int trainsCnt ) {
        for (int i = 0; i < trainsCnt; i++) {
            GoodsType type = GoodsType.values ( )[new Random ( ).nextInt ( GoodsType.values ( ).length )];
            switch (type) {
                case BOX -> this.addCar ( new ContainerCarriage ( ) );
                case CAR -> this.addCar ( new CarsCarriage ( ) );
                case LIQUID -> this.addCar ( new CisternCarriage ( ) );
                case SHAKY -> this.addCar ( new TankCarriage ( ) );
            }
            int goodsAmount = new Random ( ).nextInt ( 5 );
            for (int j = 0; j < goodsAmount; j++) {
                if ( type == GoodsType.BOX ) {
                    BoxGoods box = new BoxGoods ( );
                    box.generateRandom ( );
                    try {
                        cars.get ( i ).addGoods ( box );
                    } catch (InvalidClassException exc) {
                        exceptionHandler.addException ( "Critical error in generating train" , exc );
                    }
                }
                if ( type == GoodsType.CAR ) {
                    Cars car = new Cars ( );
                    car.generateRandom ( );
                    try {
                        cars.get ( i ).addGoods ( car );
                    } catch (InvalidClassException exc) {
                        exceptionHandler.addException ( "Critical error in generating train" , exc );
                    }
                }
                if ( type == GoodsType.LIQUID ) {
                    LiquidGoods liquid = new LiquidGoods ( "" );
                    liquid.generateRandom ( );
                    try {
                        cars.get ( i ).addGoods ( liquid );
                    } catch (InvalidClassException exc) {
                        exceptionHandler.addException ( "Critical error in generating train" , exc );
                    }
                }
                if ( type == GoodsType.SHAKY ) {
                    ShakyGoods shaky = new ShakyGoods ( "" );
                    shaky.generateRandom ( );
                    try {
                        cars.get ( i ).addGoods ( shaky );
                    } catch (InvalidClassException exc) {
                        exceptionHandler.addException ( "Critical error in generating train" , exc );
                    }


                }
            }

        }
    }

    private void generating ( int trainsCnt, int goodsPerCar ) {
        for (int i = 0; i < trainsCnt; i++) {
            GoodsType type = GoodsType.values ( )[new Random ( ).nextInt ( GoodsType.values ( ).length )];
            switch (type) {
                case BOX -> this.addCar ( new ContainerCarriage ( ) );
                case CAR -> this.addCar ( new CarsCarriage ( ) );
                case LIQUID -> this.addCar ( new CisternCarriage ( ) );
                case SHAKY -> this.addCar ( new TankCarriage ( ) );
            }

            for (int j = 0; j < goodsPerCar; j++) {
                if ( type == GoodsType.BOX ) {
                    BoxGoods box = new BoxGoods ( );
                    box.generateRandom ( );
                    try {
                        cars.get ( i ).addGoods ( box );
                    } catch (InvalidClassException exc) {
                        exceptionHandler.addException ( "Critical error in generating train" , exc );
                    }
                }
                if ( type == GoodsType.CAR ) {
                    Cars car = new Cars ( );
                    car.generateRandom ( );
                    try {
                        cars.get ( i ).addGoods ( car );
                    } catch (InvalidClassException exc) {
                        exceptionHandler.addException ( "Critical error in generating train" , exc );
                    }
                }
                if ( type == GoodsType.LIQUID ) {
                    LiquidGoods liquid = new LiquidGoods ( "" );
                    liquid.generateRandom ( );
                    try {
                        cars.get ( i ).addGoods ( liquid );
                    } catch (InvalidClassException exc) {
                        exceptionHandler.addException ( "Critical error in generating train" , exc );
                    }
                }
                if ( type == GoodsType.SHAKY ) {
                    ShakyGoods shaky = new ShakyGoods ( "" );
                    shaky.generateRandom ( );
                    try {
                        cars.get ( i ).addGoods ( shaky );
                    } catch (InvalidClassException exc) {
                        exceptionHandler.addException ( "Critical error in generating train" , exc );
                    }


                }
            }

        }
    }
}
