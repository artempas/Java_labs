package com.miet.pin23.pasechnik;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Random;

public abstract class Goods implements Serializable {
    /**
     * Base abstract class for all categorized goods
     */
    private String title;
    private GoodsType type;

    /**
     * @return Category of a good
     */
    public GoodsType getType ( ) {
        return type;
    }

    public void SetType ( GoodsType t ) {
        type = t;
    }

    public String getTitle ( ) {

        return title;
    }

    /**
     * Sets the name of a good
     *
     * @param titleVal Name of a good
     */
    public void setTitle ( String titleVal ) {

        title = titleVal;
    }

    /**
     * Prints good in System.out
     */
    public void printGoods ( ) {
        System.out.print ( "\t\tTitle: " + title + " | " );
        System.out.print ( "Type: " );
        System.out.println ( type );
    }

    public String toString ( ) {
        return type.toString ( ) + ":" + this.title;
    }

    public void generateRandom ( ) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random ( );
        StringBuilder buffer = new StringBuilder ( targetStringLength );
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat ( ) * (rightLimit - leftLimit + 1));
            buffer.append ( (char) randomLimitedInt );
        }
        title = buffer.toString ( );
    }
}
