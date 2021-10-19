package com.miet.pin23.pasechnik;

import java.io.InvalidClassException;
import java.util.ArrayList;

/**
 * Warehouse class
 */
public class Warehouse {
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
    public Warehouse(ArrayList<Goods> goods){
        this.goods = goods;
    }

    /**
     * Adds ArrayList of goods to warehouse
     * @param goods ArrayList of goods
     */
    public void addGoods(ArrayList<Goods> goods){
        this.goods.addAll ( goods );
    }

    /**
     * Adds one good to warehouse
     * @param good Good
     */
    public void addGoods(Goods good){
        this.goods.add ( good );
    }
    public Warehouse(){
        this.goods = new ArrayList<Goods> (  );
    }

    /**
     * Load goods from warehouse to cars in train with good type check
     * @param train Train obj
     */
    public void loadToTrain(Train train){
        int cnt=0;
        boolean found=false;
        while(!goods.isEmpty ()&&goods.size ()!=cnt){
            found = false;
            for (Carriage car :
                    train.getCars ()) {

                if (car.getType () == goods.get ( cnt ).getType ()){
                    try {
                        car.addGoods ( goods.get ( cnt ) );
                        goods.remove ( cnt );
                        found = true;
                        break;
                    }catch (InvalidClassException exc){
                        System.out.println ( exc );
                    }
                }
            }
            if(!found) cnt+=1;

            }
    }

    /**
     * Prints warehouse to System.out
     */
    void printWarehouse(){
        System.out.println ( "Warehouse: " );
        for (Goods good:
             goods) {
            good.printGoods ();
        }
    }
}
