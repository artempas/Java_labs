package com.miet.pin23.pasechnik.lab2;

import java.io.InvalidClassException;
import java.util.ArrayList;

/**
 * Container carriages class
 */
public class ContainerCarriage extends Carriage{
    public ContainerCarriage( ArrayList<Goods> goodsVal){
        super.setGoods ( goodsVal );type = GoodsType.BOX;
    }
    public ContainerCarriage(){super();
    type = GoodsType.BOX;}
}
