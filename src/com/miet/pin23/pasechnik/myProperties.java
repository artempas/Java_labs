package com.miet.pin23.pasechnik;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class myProperties {
    private static Logger log = new Logger ( myProperties.class.getName ( ) );
    private static ExceptionHandler exceptionHandler = new ExceptionHandler(myProperties.class.getName ());
    public static String propFileName;


    public String getPropValues ( Props p ) {
        Properties prop = new Properties ( );
        try {


            try (FileInputStream fis = new FileInputStream ( propFileName )) {
                prop.load ( fis );
            } catch (IOException exc) {
                exceptionHandler.makeErr ("Properties hasn't been read",exc);
            }

        } catch (Exception e) {
            e.printStackTrace ( );
        }
        return prop.getProperty ( p.toString ( ) );
    }
}