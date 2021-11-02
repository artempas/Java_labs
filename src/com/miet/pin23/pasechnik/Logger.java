package com.miet.pin23.pasechnik;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Timer;

public class Logger {
    private static LocalTime time;
    public static Writer writer;
    private String name;
    private static myProperties propReader=new myProperties ();

    public Logger(String name){
        this.name=name;
    }
    public void log(Level l, String msg, Throwable thrown){
        if( Objects.equals ( propReader.getPropValues ( Props.debug ) , "true" ) ){
            time = LocalTime.now ();
            writer.WriteContents (time.format ( DateTimeFormatter.ISO_LOCAL_TIME )+' '+name+':'+ l.toString () +' '+msg + ' '+thrown.getMessage ()+'\n' );
        }


    }
    public void log(Level l, String msg){
         if( Objects.equals ( propReader.getPropValues ( Props.debug ) , "true" ) ){
            time = LocalTime.now ();
            writer.WriteContents (time.format ( DateTimeFormatter.ISO_LOCAL_TIME )+' '+name+':'+ l.toString () +' '+msg +'\n',true );
        }
    }

}
