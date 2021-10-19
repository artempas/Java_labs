package com.miet.pin23.pasechnik.lab2;

import java.io.InvalidClassException;
import java.util.ArrayList;

/**
 * Vehicle carriages class
 */
public class CarsCarriage extends Carriage{
    public CarsCarriage( ArrayList<Goods> goodsVal) {
        super.setGoods ( goodsVal );
                type = GoodsType.CAR;
    }
    public CarsCarriage(){
        super();
                type = GoodsType.CAR;

    }
}
