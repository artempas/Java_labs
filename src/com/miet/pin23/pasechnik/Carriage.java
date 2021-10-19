package com.miet.pin23.pasechnik;

import java.io.InvalidClassException;
import java.util.ArrayList;

/**
 * Base abstract class for all carriages
 */
public abstract class Carriage{

    /**
     * type of goods allowed for transporting in a particular carriage (depends on carriage class)
     */
    protected GoodsType type;
    /**
     * ArrayList of all goods in carriage
     */
    protected ArrayList<Goods> goods;

    /**
     * @return type of goods allowed for transporting in a particular carriage (depends on carriage class)
     */
    public GoodsType getType ( ) {
        return type;
    }

    /**
     * @return  ArrayList of all goods in carriage
     */
    public ArrayList<Goods> getGoods(){return goods;}

    /**
     * replaces existing goods with new ones with type check
     * prints inappropriate goods
     * @param goodsValue ArrayList of new goods to be placed in a carriage
     */
    public void setGoods(ArrayList<Goods> goodsValue) {
        ArrayList<Goods> rightGoods = new ArrayList<Goods> (  );
        for (Goods goods:
             goodsValue) {

            try{
                if (goods.type == type)
                    rightGoods.add ( goods );
                else {
                    throw new InvalidClassException ( "Goods type doesn't match carriage type" );
                }
            }catch (InvalidClassException exc){
                System.out.print ( exc );
                System.out.println ( goods );
            }
        }
        this.goods = rightGoods;
    }

    /**
     * adds several new goods to existing ones with type check
     * prints inappropriate goods
     * @param goodsValue ArrayList of new goods to be placed in a carriage
     */
    public void addGoods(ArrayList<Goods> goodsValue) {
        ArrayList<Goods> rightGoods = new ArrayList<Goods> (  );
        for (Goods goods:
             goodsValue) {

            try{
                if (goods.type == type)
                    rightGoods.add ( goods );
                else {
                    throw new InvalidClassException ( "Goods type doesn't match carriage type" );
                }
            }catch (InvalidClassException exc){
                System.out.print ( exc );
                System.out.println ( goods );
            }
        }
        this.goods.addAll ( rightGoods );
    }
    /**
     * adds one new good to existing ones with type check
     * prints inappropriate goods
     * @param goodsValue new good to be placed in a carriage
     */
    public void addGoods(Goods goodsValue) throws InvalidClassException {
        if (goodsValue.type == type)
            goods.add ( goodsValue );
        else {
            throw new InvalidClassException ( "Goods type doesn't match carriage type" );
        }
    }
    public Carriage(){
        goods = new ArrayList<> (  );
    }

    /**
     * Prints carriage to System.out
     */
    public void printCarriage (){
        System.out.print ( "Car:\nType: " );
        System.out.println ( type );
        for (Goods good:
             goods) {
            good.printGoods ();
        }
    }
}
