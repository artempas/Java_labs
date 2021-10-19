package com.miet.pin23.pasechnik.lab2;

import java.io.InvalidClassException;
import java.util.ArrayList;

/**
 * Cistern carriages class
 */
public class CisternCarriage extends Carriage{

    public CisternCarriage( ArrayList<Goods> goodsVal){
        super.setGoods ( goodsVal );type = GoodsType.LIQUID;
    }
    public CisternCarriage(){
        super();
        type = GoodsType.LIQUID;
    }
}
