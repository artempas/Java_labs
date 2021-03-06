package com.miet.pin23.pasechnik;

import java.io.*;


public class Writer {
    protected String encoding;
    protected File file;
    private static Logger log = new Logger ( Writer.class.getName ( ) );
    private static ExceptionHandler exceptionHandler = new ExceptionHandler ( Writer.class.getName ( ) );

    public Writer ( String path ) {
        file = new File ( path );
        encoding = "UTF-8";
    }

    public Writer ( String path , String _encoding ) {
        file = new File ( path );
        this.encoding = _encoding;
    }

    public void WriteContents ( String to_write , boolean append ) throws IOException {
        try {
            if ( file == null ) {
                throw new IllegalArgumentException ( "File shouldn't be null" );
            }
            if ( !file.exists ( ) ) {
                if ( !file.createNewFile ( ) )
                    throw new IOException ( "Unable to create file" );
                log.log ( Level.INFO , String.format ( "File %s created" , file.getName ( ) ) );
            }
            if ( !file.canRead ( ) ) {
                throw new IllegalArgumentException ( "Inaccessible file" );
            }
            if ( !file.isFile ( ) ) {
                throw new IllegalArgumentException ( "Path shouldn't be a directory" );
            }
            try (BufferedWriter bufferedWriter = new BufferedWriter ( new FileWriter ( file , append ) )) {
                bufferedWriter.write ( to_write );
            }
        } catch (IOException exc) {
            exceptionHandler.addException ( exc );
            throw exc;
        }
    }

    public void WriteContents ( String to_write ) throws IOException {
        try {
            if ( file == null ) {
                throw new IllegalArgumentException ( "File shouldn't be null" );
            }
            if ( !file.exists ( ) ) {
                if ( !file.createNewFile ( ) )
                    throw new IOException ( "Unable to create file" );
                log.log ( Level.INFO , String.format ( "File %s created" , file.getName ( ) ) );
            }
            if ( !file.canRead ( ) ) {
                throw new IllegalArgumentException ( "Inaccessible file" );
            }
            if ( !file.isFile ( ) ) {
                throw new IllegalArgumentException ( "Path shouldn't be a directory" );
            }
            try (BufferedWriter bufferedWriter = new BufferedWriter ( new FileWriter ( file ) )) {
                bufferedWriter.write ( to_write );
            }
        } catch (IOException exc) {
            exceptionHandler.addException ( exc );
            throw new IOException ( );
        }
    }

}
