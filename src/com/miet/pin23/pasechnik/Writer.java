package com.miet.pin23.pasechnik;

import java.io.*;


public class Writer {
    private String _encoding;
    private File file;

    public Writer ( String path ) {
        file = new File ( path );
        _encoding = "UTF-8";
    }

    public Writer ( String path , String _encoding ) {
        file = new File ( path );
        this._encoding = _encoding;
    }

    public void WriteContents ( String to_write , boolean append ) {
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
