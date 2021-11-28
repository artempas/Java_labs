package com.miet.pin23.pasechnik;

import java.util.ArrayList;

public class ExceptionHandler {
    private Logger logger;
    private static ArrayList<Throwable> exceptions = new ArrayList<Throwable> ( );

    public ExceptionHandler(String name){
        logger = new Logger ( name );
    }

    /**
     * Add exception to list without logging and printing it
     * @param e thrown exception
     */
    public void addException ( Throwable e ) {
        exceptions.add ( e );
    }

    /**
     * Add exception to list without logging, but print it to System.out
     * @param e thrown exception
     * @param printException true - print exception; false - don't print
     */
    public void addException ( Throwable e , boolean printException ) {
        exceptions.add ( e );
        if ( printException )
            System.out.println ( ConsoleColors.ANSI_RED + e.getMessage ( ) + ConsoleColors.ANSI_RESET );
    }

    /**
     * Add exception to list with logging
     * @param messageForLog message to be written to log
     * @param e throw exception
     */
    public void addException ( String messageForLog , Throwable e ) {
        logger.log ( Level.ERROR , messageForLog , e );
        exceptions.add ( e );
    }

    /**
     * Add exception to list with logging and print it to System.out
     * @param message message to be printed and logged
     * @param e thrown exception
     * @param printException true - print exception to console; false - don't print
     */
    public void addException ( String message , Throwable e , boolean printException ) {
        logger.log ( Level.ERROR , "" , e );
        exceptions.add ( e );
        if ( printException )
            System.out.println ( ConsoleColors.ANSI_RED + message + e.getMessage ( ) + ConsoleColors.ANSI_RESET );
    }

    /**
     * @return amount of handled exceptions
     */
    public int exceptionCount ( ) {
        return exceptions.size ( );
    }

    /**
     * Adds given exception to list of errors and returns it to be thrown without logging
     * @param e exception to be thrown
     * @return same as input
     */
    public Throwable makeErr(Throwable e) {
        addException (e);
        return new Throwable(e);
    }


    /**
     * Adds given exception to list of errors and returns it to be thrown with logging
     * @param messageForLog message to be written to log
     * @param e exception to be thrown
     * @return same as input
     */
    public Throwable makeErr(String messageForLog,Throwable e) {
        addException (messageForLog,e);
        return new Throwable(e);
    }

    /**
     * Adds given exception to list of errors and returns it to be thrown without logging
     * @param e exception to be thrown
     * @param printException true - print exception to System.out; false - don't print
     * @return same as input
     */
    public Throwable makeErr(Throwable e, boolean printException) {
        addException (e,printException);
        return new Throwable(e);
    }

    /**
     * Adds given exception to list of errors and returns it to be thrown with logging with message
     * @param message message to be written to log and console
     * @param e exception to be thrown
     * @param printException true - print exception to System.out; false - don't print
     * @return same as input
     */
    public Throwable makeErr( String message , Throwable e, boolean printException) {
        addException ( message ,e,printException);
        return new Throwable(e);
    }


}
