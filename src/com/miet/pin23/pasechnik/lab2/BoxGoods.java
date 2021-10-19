package com.miet.pin23.pasechnik.lab2;

/**
 * Class of goods to be stored in a box
 */
public class BoxGoods extends Goods{
    public BoxGoods(String titleValue){
        setTitle ( titleValue );
        type = GoodsType.BOX;
    }
}
