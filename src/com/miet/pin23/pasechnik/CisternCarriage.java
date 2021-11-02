package com.miet.pin23.pasechnik;

import java.io.InvalidClassException;
import java.util.ArrayList;

/**
 * Cistern carriages class
 */
public class CisternCarriage extends Carriage{

    public CisternCarriage( ArrayList<Goods> goodsVal){
        super.setGoods ( new ArrayList<> ( 2 ) );



        type = GoodsType.LIQUID;
    }
    public CisternCarriage(){
        super();
        type = GoodsType.LIQUID;
    }

    @Override
    public boolean addGoods ( Goods goodsValue ) throws InvalidClassException {
        if (getGoods ().size ()==2){
            return false;
        }
        return super.addGoods ( goodsValue );
    }
    @Override
    public boolean addGoods(ArrayList<Goods> goodsValue){
        if (getGoods ().size ()+goodsValue.size ()>2) {return false;}
        return super.addGoods ( goodsValue );
    }
}
