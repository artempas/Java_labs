package com.miet.pin23.pasechnik;

import java.util.ArrayList;
/**
 * Tank carriages class
 */
public class TankCarriage extends Carriage{

    public TankCarriage( ArrayList<Goods> goodsVal){

        type = GoodsType.SHAKY;

        super.setGoods ( goodsVal );
    }
    public TankCarriage(){
        super();
        type = GoodsType.SHAKY;

    }
}
