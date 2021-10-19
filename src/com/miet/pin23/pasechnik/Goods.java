package com.miet.pin23.pasechnik;

public abstract class Goods {
    /**
     * Base abstract class for all categorized goods
     */
    private String title;
    protected GoodsType type;

    /**
     *
     * @return Category of a good
     */
    public GoodsType getType ( ) {
        return type;
    }

    /**
     *
     * @return Name of a good
     */
    public String getTitle(){

        return title;
    }

    /**
     * Sets the name of a good
     * @param titleVal Name of a good
     */
    public void setTitle(String titleVal){

        title=titleVal;
    }

    /**
     * Prints good in System.out
     */
    public void printGoods(){
        System.out.print( "Title: "+title+" | " );
        System.out.print("Type: ");
        System.out.println ( type );
    }
}
