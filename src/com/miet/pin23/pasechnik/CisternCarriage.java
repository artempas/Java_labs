package com.miet.pin23.pasechnik;

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
