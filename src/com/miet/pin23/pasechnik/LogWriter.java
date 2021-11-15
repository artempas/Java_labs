package com.miet.pin23.pasechnik;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogWriter extends Writer {
    public LogWriter ( String path ) {
        super ( path );
    }

    public LogWriter ( String path , String encoding ) {
        super ( path , encoding );
    }

    public void WriteContents ( String to_write , boolean append ) {
        try {
            if ( file == null ) {
                throw new IllegalArgumentException ( "File shouldn't be null" );
            }
            if ( !file.exists ( ) ) {
                if ( !file.createNewFile ( ) )
                    throw new IOException ( "Unable to create file" );
                System.out.println ( ConsoleColors.ANSI_BLUE + String.format ( "File %s created" , file.getName ( ) ) + ConsoleColors.ANSI_RESET );
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
            exc.printStackTrace ( );
        }
    }

    public void WriteContents ( String to_write ) {
        try {
            if ( file == null ) {
                throw new IllegalArgumentException ( "File shouldn't be null" );
            }
            if ( !file.exists ( ) ) {
                if ( !file.createNewFile ( ) )
                    throw new IOException ( "Unable to create file" );
                System.out.printf ( "%s created" , file.getName ( ) );
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
            exc.printStackTrace ( );
        }
    }
}
