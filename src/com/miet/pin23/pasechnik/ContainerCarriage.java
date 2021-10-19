package com.miet.pin23.pasechnik;

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
