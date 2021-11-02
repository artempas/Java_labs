package com.miet.pin23.pasechnik;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class Program {
    public static Logger logger = new Logger ( "Main" );
    public static BufferedReader reader = new BufferedReader (
            new InputStreamReader ( System.in ) );
    public static String SETTINGS_FILENAME = "config/config.properties";
    public static myProperties propReader = new myProperties ( );


    public static int integerInput ( ) {
        int i = 0;
        try {
            String buf = reader.readLine ( );
            while (buf.isBlank ( )) {
                buf = reader.readLine ( );
            }
            i = Integer.parseInt ( buf );
        } catch (IOException ignored) {
        }
        return i;
    }

    public static void lab2 ( ) {
        //Создание поезда
        Train train = new Train ( );
        train.addCar ( new CarsCarriage ( ) );
        train.addCar ( new CisternCarriage ( ) );
        train.addCar ( new TankCarriage ( ) );
        train.addCar ( new ContainerCarriage ( ) );

        //Создание склада
        Warehouse warehouse = new Warehouse ( );
        warehouse.addGoods ( new BoxGoods ( "Контейнеры" ) );
        warehouse.addGoods ( new LiquidGoods ( "Нефть" ) );
        warehouse.addGoods ( new LiquidGoods ( "Нефть2" ) );
        warehouse.addGoods ( new LiquidGoods ( "Дизельное топливо" ) );
        warehouse.addGoods ( new LiquidGoods ( "Дизельное топливо2" ) );
        warehouse.addGoods ( new LiquidGoods ( "Мазут" ) );
        warehouse.addGoods ( new ShakyGoods ( "Песок" ) );
        warehouse.addGoods ( new BoxGoods ( "Уголь" ) );
        warehouse.addGoods ( new Cars ( "Автомобили" ) );


        System.out.println ( "BEFORE: " );
        warehouse.printWarehouse ( );
        train.printTrain ( );
        warehouse.loadToTrain ( train );
        System.out.println ( "AFTER:" );
        train.printTrain ( );
        warehouse.printWarehouse ( );
    }

    private static boolean authentication ( ) {
        String username = "";
        String password = "";
        System.out.print ( ConsoleColors.ANSI_BLUE + "Username: " + ConsoleColors.ANSI_RESET );
        try {
            username = reader.readLine ( );
            System.out.println ( ConsoleColors.ANSI_BLUE + "Password: " + ConsoleColors.ANSI_RESET );
            password = reader.readLine ( );
        } catch (IOException exc) {
            System.out.println ( ConsoleColors.ANSI_RED + "Input error" + ConsoleColors.ANSI_RESET );
            logger.log ( Level.ERROR , "Input error " , exc );
        }
        boolean success = Objects.equals ( propReader.getPropValues ( Props.username ) , username ) && Objects.equals ( propReader.getPropValues ( Props.password ) , password );
        if ( success )
            logger.log ( Level.INFO , String.format ( "%s successfully authorized" , username ) );
        else
            logger.log ( Level.INFO , String.format ( "%s entered wrong password" , username ) );
        return success;

    }

    private static int Menu ( ArrayList<Train> trains ) {
        System.out.println ( ConsoleColors.ANSI_WHITE + "" +
                "Меню\n" +
                "1) Список поездов\n" +
                "2) Добавить поезда\n" +
                "3) Изменение состава поезда\n" +
                "4) Удаление поезда\n" +
                "5) Сохранить\n" +
                "0) Выход" + ConsoleColors.ANSI_RESET );
        return integerInput ( );

    }

    private static Train addTrain ( ) {
        System.out.println ( ConsoleColors.ANSI_YELLOW + "Кол-во вагонов: " + ConsoleColors.ANSI_RESET );
        int cnt = integerInput ( );
        Train train = new Train ( );
        while (cnt != 0) {
            System.out.println ( ConsoleColors.ANSI_YELLOW + "Выберите тип вагона: " );
            System.out.println ( ConsoleColors.ANSI_CYAN + "1 - Автовоз" );
            System.out.println ( "2 - Цистерна для жидкостей" );
            System.out.println ( "3 - Цистерна для сыпучих грузов" );
            System.out.println ( "4 - Контейнер" + ConsoleColors.ANSI_RESET );
            int type = integerInput ( );
            while (type > 4 || type < 1) {
                System.out.println ( ConsoleColors.ANSI_RED + "Введите цифру от 1 до 4" + ConsoleColors.ANSI_RESET );
                type = integerInput ( );

            }
            switch (type) {
                case 1 -> train.addCar ( new CarsCarriage ( ) );
                case 2 -> train.addCar ( new CisternCarriage ( ) );
                case 3 -> train.addCar ( new TankCarriage ( ) );
                case 4 -> train.addCar ( new ContainerCarriage ( ) );
            }
            cnt--;
        }
        return train;
    }

    public static void lab3 ( ) {
        boolean is_auth = false;
        while (!is_auth) {
            is_auth = authentication ( );
            if ( is_auth ) {
                System.out.println ( ConsoleColors.ANSI_GREEN + "Welcome, " + propReader.getPropValues ( Props.username ) + '!' + ConsoleColors.ANSI_RESET );
            } else {
                System.out.println ( ConsoleColors.ANSI_RED + "Wrong password or username, try again" + ConsoleColors.ANSI_RESET );
            }
        }
        String database_path = propReader.getPropValues ( Props.database );
        Reader r = new Reader ( database_path );
        ArrayList<Train> trains = r.readTrains ( );
        int menu = 1;
        boolean edited = false;
        while (menu != 0) {
            menu = Menu ( trains );
            while (menu > 5 || menu < 0) {
                System.out.println ( ConsoleColors.ANSI_RED + "Введите цифру от 1 до 5" + ConsoleColors.ANSI_RESET );
                menu = integerInput ( );
            }
            System.out.println (ConsoleColors.ANSI_WHITE+ "----------------------------------------------------------------------" +ConsoleColors.ANSI_RESET);
            switch (menu) {
                case (1):
                    logger.log ( Level.INFO , "Asked for list of trains" );
                    System.out.print ( "Список поездов " );
                    if ( trains.size ( ) != 0 ) {
                        System.out.println ( ":" );
                        int cnt = 1;
                        for (Train train :
                                trains) {
                            System.out.print ( ConsoleColors.ANSI_PURPLE + cnt++ + ')' + ConsoleColors.ANSI_RESET );
                            train.printTrain ( );
                        }
                    } else
                        System.out.println ( "пуст" );

                    break;
                case (2):
                    System.out.println ( ConsoleColors.ANSI_WHITE + "Сколько поездов хотите добавить?" + ConsoleColors.ANSI_RESET );
                    int cnt = integerInput ( );
                    int cpy = cnt;
                    if ( cnt == 1 ) trains.add ( addTrain ( ) );
                    else
                        while (cnt != 0) {
                            System.out.println ( ConsoleColors.ANSI_PURPLE+(cpy-cnt+1)+" поезд"+ ConsoleColors.ANSI_RESET ) ;
                            trains.add ( addTrain ( ) );
                            cnt--;
                        }
                    logger.log ( Level.INFO , String.format ( "Added %d trains" , cpy ) );
                    edited = true;
                    break;
                case 3:
                    if ( trains.size ( ) == 0 ) {
                        System.out.println ( ConsoleColors.ANSI_RED + "В базе данных нет поездов" + ConsoleColors.ANSI_RESET );
                    } else {
                        cnt = 1;
                        for (Train train :
                                trains) {
                            System.out.print ( ConsoleColors.ANSI_PURPLE + cnt++ + ')' + ConsoleColors.ANSI_RESET );
                            train.printTrain ( );
                        }
                        System.out.println ( ConsoleColors.ANSI_BLUE + String.format ( "Какой поезд вы хотели бы изменить (от 1 до %d)" , trains.size ( ) ) + ConsoleColors.ANSI_RESET );
                        int ind = integerInput ( );
                        while (ind > trains.size ( ) || ind < 0) {
                            System.out.println ( ConsoleColors.ANSI_RED + String.format ( "Введите цифру от 1 до %d" , trains.size ( ) ) + ConsoleColors.ANSI_RESET );
                            ind = integerInput ( );
                        }
                        trains.get ( ind - 1 ).editTrain ( );
                        logger.log ( Level.INFO , String.format ( "Edited %dth train" , ind - 1 ) );
                        edited = true;
                    }
                    break;
                case 4:
                    if ( trains.size ( ) == 0 ) {
                        System.out.println ( ConsoleColors.ANSI_RED + "В базе данных нет поездов" + ConsoleColors.ANSI_RESET );
                    } else {
                        cnt = 1;
                        for (Train train :
                                trains) {
                            System.out.print ( ConsoleColors.ANSI_PURPLE + cnt++ + ')' + ConsoleColors.ANSI_RESET );
                            train.printTrain ( );
                        }
                        System.out.println ( ConsoleColors.ANSI_BLUE + String.format ( "Какой поезд вы хотели бы удалить (от 1 до %d)" , trains.size ( ) ) + ConsoleColors.ANSI_RESET );
                        int ind = integerInput ( );
                        while (ind > trains.size ( ) || ind < 0) {
                            System.out.println ( ConsoleColors.ANSI_RED + String.format ( "Введите цифру от 1 до %d" , trains.size ( ) ) + ConsoleColors.ANSI_RESET );
                            ind = integerInput ( );
                        }
                        trains.remove ( ind - 1 );
                        logger.log ( Level.INFO , String.format ( "Removed %dth train" , ind - 1 ) );
                        edited = true;
                    }
                    break;

                case 0:
                    if ( edited ) {
                        System.out.println ( ConsoleColors.ANSI_RED + "Имеются несохранённые изменения! Хотите сохранить?" );
                        System.out.println ( ConsoleColors.ANSI_GREEN + "1 - да" );
                        System.out.println ( ConsoleColors.ANSI_RED + "2 - нет" + ConsoleColors.ANSI_RESET );
                        int flag = integerInput ( );
                        if ( flag == 0 ) {
                            break;
                        }
                    } else break;
                case 5:
                    Writer writer = new Writer ( database_path );
                    String str = "";
                    for (Train train :
                            trains) {
                        str += train.ToString ( ) + '\n';
                    }
                    writer.WriteContents ( str );
                    edited = false;
                    logger.log ( Level.INFO , "Database saved" );
                    break;

            }
        }
    }


    public static void main ( String[] Args ) {
        myProperties.propFileName = SETTINGS_FILENAME;
        Logger.writer = new Writer ( propReader.getPropValues ( Props.log_to_file ) );
        lab3 ( );

    }
}
