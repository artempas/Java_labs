package com.miet.pin23.pasechnik;

import java.io.InvalidClassException;
import java.util.ArrayList;

/**
 * Warehouse class
 */
public class Warehouse {

    private static final ExceptionHandler exceptionHandler = new ExceptionHandler ( Warehouse.class.getName ( ) );
    /**
     * ArrayList of goods stored in a warehouse
     */
    private ArrayList<Goods> goods;

    public ArrayList<Goods> getGoods ( ) {
        return goods;
    }

    public void setGoods ( ArrayList<Goods> goods ) {
        this.goods = goods;
    }

    public Warehouse ( ArrayList<Goods> goods ) {
        this.goods = goods;
    }

    /**
     * Adds ArrayList of goods to warehouse
     *
     * @param goods ArrayList of goods
     */
    public void addGoods ( ArrayList<Goods> goods ) {
        this.goods.addAll ( goods );
    }

    /**
     * Adds one good to warehouse
     *
     * @param good Good
     */
    public void addGoods ( Goods good ) {
        this.goods.add ( good );
    }

    public Warehouse ( ) {
        this.goods = new ArrayList<Goods> ( );
    }

    /**
     * Load goods from warehouse to cars in train with good type check
     *
     * @param train Train obj
     */
    public void loadToTrain ( Train train ) {
        int cnt = 0;
        boolean found = false;
        while (!goods.isEmpty ( ) && goods.size ( ) != cnt) {
            found = false;
            for (Carriage car :
                    train.getCars ( )) {

                if ( car.getType ( ) == goods.get ( cnt ).getType ( ) ) {
                    try {
                        found = car.addGoods ( goods.get ( cnt ) );
                        if ( found ) {
                            goods.remove ( cnt );
                            break;
                        }
                    } catch (InvalidClassException exc) {
                        exceptionHandler.addException ( "" , exc );
                    }
                }
            }
            if ( !found )
                if ( goods.get ( cnt ).getType ( ) == GoodsType.LIQUID ) {
                    train.addCar ( new CisternCarriage ( ) );
                } else
                    cnt += 1;

        }
    }

    /**
     * Prints warehouse to System.out
     */
    void printWarehouse ( ) {
        System.out.println ( "Warehouse: " );
        for (Goods good :
                goods) {
            good.printGoods ( );
        }
    }
}
