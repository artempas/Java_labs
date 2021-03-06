package com.miet.pin23.pasechnik;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

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
            try {
                i = Integer.parseInt ( buf );
            } catch (NumberFormatException ignored) {
                System.out.println ( ConsoleColors.ANSI_RED + "Введите цифру!" + ConsoleColors.ANSI_RESET );
                return integerInput ( );
            }
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
            System.out.print ( ConsoleColors.ANSI_BLUE + "Password: " + ConsoleColors.ANSI_RESET );
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
            System.out.println ( ConsoleColors.ANSI_WHITE + "----------------------------------------------------------------------" + ConsoleColors.ANSI_RESET );
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
                            System.out.println ( ConsoleColors.ANSI_PURPLE + (cpy - cnt + 1) + " поезд" + ConsoleColors.ANSI_RESET );
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
                    StringBuilder str = new StringBuilder ( );
                    for (Train train :
                            trains) {
                        str.append ( train.ToString ( ) ).append ( '\n' );
                    }
                    try {
                        writer.WriteContents ( str.toString ( ) );
                    } catch (IOException exc) {
                        System.out.println ( ConsoleColors.ANSI_RED + "An error has occurred while writing to file. For more details check log" + ConsoleColors.ANSI_RESET );
                    }
                    edited = false;
                    logger.log ( Level.INFO , "Database saved" );
                    break;

            }
        }
    }


    public static ArrayList<Long> lab4 ( boolean dataOnly ) {

        if ( !dataOnly ) {
            ArrayList<String> results = new ArrayList<String> ( 4 );

            logger.log ( Level.INFO , "Program started\nArrayList" );
            System.out.print ( ConsoleColors.ANSI_BLUE + "Введите размер массивов: " + ConsoleColors.ANSI_RESET );
            int size = integerInput ( );

            ArrayList<Train> trains = new ArrayList<Train> ( size );
            ArrayList<Long> arrayListTimes = new ArrayList<Long> ( size );
            long arrayListInitTime = System.currentTimeMillis ( );
            for (int i = 0; i < size; i++) {
                Train train = new Train ( );
                train.generateRandom ( 3 , 3 );
                trains.add ( train );
                arrayListTimes.add ( System.currentTimeMillis ( ) );
                if ( i == 0 )
                    logger.log ( Level.INFO , String.format ( "add: ID = %d, %d ms " , i , arrayListTimes.get ( i ) - arrayListInitTime ) );
                else
                    logger.log ( Level.INFO , String.format ( "add: ID = %d, %d ms " , i , arrayListTimes.get ( i ) - arrayListTimes.get ( i - 1 ) ) );
            }
            Long arrayListTotalTime = System.currentTimeMillis ( ) - arrayListInitTime;
            long sum = arrayListTimes.get ( 0 ) - arrayListInitTime;
            for (int i = 1; i < size; i++) {
                sum += arrayListTimes.get ( i ) - arrayListTimes.get ( i - 1 );
            }
            String format = String.format ( "ArrayList:\n" +
                    "Total time: %d ms\n" +
                    "Average per element:%f" , arrayListTotalTime , (float) sum / (float) size );
            logger.log ( Level.INFO ,
                    format );
            results.add ( format );


            logger.log ( Level.INFO , "LinkedList" );
            LinkedList<Train> trainLinkedList = new LinkedList<Train> ( );
            ArrayList<Long> lLTimes = new ArrayList<> ( size );
            long lLInitTime = System.currentTimeMillis ( );
            for (int i = 0; i < size; i++) {
                Train train = new Train ( );
                train.generateRandom ( 3 , 3 );
                trainLinkedList.add ( train );
                lLTimes.add ( System.currentTimeMillis ( ) );
                if ( i == 0 )
                    logger.log ( Level.INFO , String.format ( "add: ID = %d, %d ms " , i , arrayListTimes.get ( 0 ) - lLInitTime ) );
                else
                    logger.log ( Level.INFO , String.format ( "add: ID = %d, %d ms " , i , arrayListTimes.get ( i ) - arrayListTimes.get ( i - 1 ) ) );
            }
            Long lLTotalTime = System.currentTimeMillis ( ) - lLInitTime;
            sum = lLTimes.get ( 0 ) - lLInitTime;
            for (int i = 1; i < size; i++) {
                sum += lLTimes.get ( i ) - lLTimes.get ( i - 1 );
            }
            format = String.format ( """
                    LinkedList:
                    Total time: %d ms
                    Average per element:%f""" , lLTotalTime , (float) sum / (float) size );
            logger.log ( Level.INFO ,
                    format );
            results.add ( format );


            logger.log ( Level.INFO , "ArrayList Edit" );
            long aLEditStart = System.currentTimeMillis ( );
            ArrayList<Long> aLEditTimes = new ArrayList<Long> ( size / 10 );
            for (int ignored = 0; ignored < size / 10; ignored++) {
                int i = new Random ( ).nextInt ( size - ignored );
                trains.remove ( i );
                aLEditTimes.add ( System.currentTimeMillis ( ) );
                if ( ignored == 0 )
                    logger.log ( Level.INFO , String.format ( "remove: ID = %d, %d ms " , i , aLEditTimes.get ( 0 ) - aLEditStart ) );
                else
                    logger.log ( Level.INFO , String.format ( "remove: ID = %d, %d ms " , i , aLEditTimes.get ( ignored ) - aLEditTimes.get ( ignored - 1 ) ) );
            }
            long aLEditTotal = System.currentTimeMillis ( ) - aLEditStart;
            sum = aLEditTimes.get ( 0 ) - aLEditStart;
            for (int i = 1; i < size / 10; i++) {
                sum += aLEditTimes.get ( i ) - aLEditTimes.get ( i - 1 );
            }
            format = String.format ( """
                    ArrayList edit:
                    Total time: %d ms
                    Average per element:%f""" , aLEditTotal , (float) sum / (float) (size / 10) );
            logger.log ( Level.INFO ,
                    format );
            results.add ( format );


            logger.log ( Level.INFO , "LinkedList Edit" );
            long lLEditStart = System.currentTimeMillis ( );
            ArrayList<Long> lLEditTimes = new ArrayList<Long> ( size / 10 );
            for (int ignored = 0; ignored < size / 10; ignored++) {
                int i = new Random ( ).nextInt ( size - ignored );
                trainLinkedList.remove ( i );
                lLEditTimes.add ( System.currentTimeMillis ( ) );
                if ( ignored == 0 )
                    logger.log ( Level.INFO , String.format ( "remove: ID = %d, %d ms " , i , lLEditTimes.get ( 0 ) - lLEditStart ) );
                else
                    logger.log ( Level.INFO , String.format ( "remove: ID = %d, %d ms " , i , lLEditTimes.get ( ignored ) - lLEditTimes.get ( ignored - 1 ) ) );
            }
            long lLEditTotal = System.currentTimeMillis ( ) - lLEditStart;
            sum = lLEditTimes.get ( 0 ) - lLEditStart;
            for (int i = 1; i < size / 10; i++) {
                sum += lLEditTimes.get ( i ) - lLEditTimes.get ( i - 1 );
            }
            format = String.format ( """
                    LinkedList edit:
                    Total time: %d ms
                    Average per element:%f""" , lLEditTotal , (float) sum / (float) (size / 10) );
            logger.log ( Level.INFO ,
                    format );
            results.add ( format );


            System.out.print ( ConsoleColors.ANSI_YELLOW );
            for (String res :
                    results) {
                System.out.println ( res );
            }
            System.out.println ( ConsoleColors.ANSI_RESET );


            //----------------------------------------------------------------------------------
            System.out.println ( ConsoleColors.ANSI_GREEN + "ДОП" + ConsoleColors.ANSI_RESET );


            size = (size * 3) / 2 + 1;
            ArrayList<Train> trains_dop = new ArrayList<Train> ( size );
            ArrayList<Long> dopTimes = new ArrayList<> ( size );
            long dopInitTime = System.currentTimeMillis ( );
            for (int i = 0; i < size; i++) {
                Train train = new Train ( );
                train.generateRandom ( 3 , 3 );
                trains_dop.add ( train );
                dopTimes.add ( System.currentTimeMillis ( ) );
                if ( i == 0 )
                    logger.log ( Level.INFO , String.format ( "add: ID = %d, %d ms " , i , dopTimes.get ( 0 ) - lLInitTime ) );
                else
                    logger.log ( Level.INFO , String.format ( "add: ID = %d, %d ms " , i , dopTimes.get ( i ) - dopTimes.get ( i - 1 ) ) );
            }
            Long dopTotalTime = System.currentTimeMillis ( ) - dopInitTime;

            System.out.println ( ConsoleColors.ANSI_CYAN + String.format ( "Разница во времени заполнения между исходным и увеличенным массивом составляет: %d мс" , dopTotalTime - arrayListTotalTime ) + ConsoleColors.ANSI_RESET );
            return null;
        }
        else {
            ArrayList<Long> Results = new ArrayList<Long> ( );
            for (int size = 10; size <= 100_000; size *= 10) {
                ArrayList<Train> trains = new ArrayList<Train> ( size );
                long InitTime = System.currentTimeMillis ( );
                for (int i = 0; i < size; i++) {
                    Train train = new Train ( );
                    train.generateRandom ( 3 , 3 );
                    trains.add ( train );

                }
                Results.add ( System.currentTimeMillis ( ) - InitTime );

                LinkedList<Train> trainLinkedList = new LinkedList<Train> ( );
                InitTime = System.currentTimeMillis ( );
                for (int i = 0; i < size; i++) {
                    Train train = new Train ( );
                    train.generateRandom ( 3 , 3 );
                    trainLinkedList.add ( train );
                }
                Results.add ( System.currentTimeMillis ( ) - InitTime );
                InitTime = System.currentTimeMillis ( );
                for (int ignored = 0; ignored < size /10; ignored++) {
                    int i = new Random ( ).nextInt ( size - ignored );
                    trains.remove ( i );
                }
                Results.add ( System.currentTimeMillis ( ) - InitTime );
                InitTime = System.currentTimeMillis ( );
                for (int ignored = 0; ignored < size / 10; ignored++) {
                    int i = new Random ( ).nextInt ( size - ignored );
                    trainLinkedList.remove ( i );
                }
                Results.add ( System.currentTimeMillis ( ) - InitTime );
            }
            return Results;
        }

    }


    public static void main ( String[] Args ) {

        myProperties.propFileName = SETTINGS_FILENAME;
        Logger.writer = new LogWriter ( propReader.getPropValues ( Props.log_to_file ) );

        Window loading_win = new Window ( windowType.LOADING );
        ArrayList<Long> yAll = lab4 ( true );
        System.out.println ( yAll );
        ArrayList<Integer> xData = new ArrayList<> ( );
        xData.add ( 0 );
        xData.add ( 10 );
        xData.add ( 100 );
        xData.add ( 1_000 );
        xData.add ( 10_000 );
        xData.add ( 100_000 );

        ArrayList<Long> arrayListAdd = new ArrayList<Long> ( );
        arrayListAdd.add ( 0L );
        for (int i = 0; i < (yAll != null ? yAll.size ( ) : 0); i += 4)
            arrayListAdd.add ( yAll.get ( i ) );
        ArrayList<Long> linkedListAdd = new ArrayList<Long> ( );
        linkedListAdd.add ( 0L );
        for (int i = 1; i < (yAll != null ? yAll.size ( ) : 0); i += 4)
            linkedListAdd.add ( yAll.get ( i ) );
        ArrayList<Long> arrayListRemove = new ArrayList<Long> ( );
        arrayListRemove.add ( 0L );
        for (int i = 2; i < (yAll != null ? yAll.size ( ) : 0); i += 4)
            arrayListRemove.add ( yAll.get ( i ) );
        ArrayList<Long> linkedListRemove = new ArrayList<Long> ( );
        linkedListRemove.add ( 0L );
        for (int i = 3; i < (yAll != null ? yAll.size ( ) : 0); i += 4)
            linkedListRemove.add ( yAll.get ( i ) );

        assert (linkedListAdd.size ( ) == xData.size ( ) &&
                xData.size ( ) == arrayListAdd.size ( ) &&
                linkedListRemove.size ( ) == xData.size ( ) &&
                arrayListRemove.size ( ) == xData.size ( ));
        loading_win.dispose ();

        System.out.println ( xData );
        System.out.println ( arrayListRemove );
        System.out.println ( linkedListRemove );
        Window addWin = new Window ( windowType.ADD , xData , arrayListAdd , linkedListAdd );
        Window removeWin = new Window ( windowType.EDIT, xData,arrayListRemove, linkedListRemove );

    }

}

