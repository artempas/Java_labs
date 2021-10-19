package com.miet.pin23.pasechnik;

public class Program {
    public static void main(String[] Args){
        //Создание поезда
        Train train = new Train (  );
        train.addCar ( new CarsCarriage (  ) );
        train.addCar ( new CisternCarriage (  ) );
        train.addCar ( new TankCarriage (  ) );
        train.addCar ( new ContainerCarriage (  ) );

        //Создание склада
        Warehouse warehouse = new Warehouse (  );
        warehouse.addGoods ( new BoxGoods ( "Контейнеры" ) );
        warehouse.addGoods ( new LiquidGoods ( "Нефть" ) );
        warehouse.addGoods ( new LiquidGoods ( "Мазут" ) );
        warehouse.addGoods ( new ShakyGoods ( "Песок" ) );
        warehouse.addGoods ( new BoxGoods ( "Уголь" ) );
        warehouse.addGoods ( new Cars ( "Автомобили" ) );


        System.out.println ( "BEFORE: " );
        warehouse.printWarehouse ();
        train.printTrain ();
        warehouse.loadToTrain ( train );
        System.out.println ( "AFTER:" );
        train.printTrain();
        warehouse.printWarehouse();
    }
}
