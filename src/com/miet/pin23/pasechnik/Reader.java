package com.miet.pin23.pasechnik;
import java.io.*;
import java.util.ArrayList;

public class Reader {
    private static Logger log = new Logger ( Reader.class.getName () );
    private String _encoding;
    private final File file;
    public Reader(String path){
        _encoding="utf-8";
        file = new File(path);
    }
    public Reader(String path, String encoding){

        this._encoding=encoding;
        file = new File(path);
    }
    public String GetContents(){
        StringBuilder content = new StringBuilder (  );
        try {
            if(file==null){
                throw new IllegalArgumentException ( "File shouldn't be null" );
            }
            if(!file.exists ()){
                if(!file.createNewFile ())
                    throw new IOException ( "Unable to create file" );
                    log.log ( Level.INFO,"File for database is created" );
                return "";
            }
            if(!file.canRead ()){
                throw new IllegalArgumentException ( "Inaccessible file" );
            }
            if(!file.isFile ()){
                throw new IllegalArgumentException ( "Path shouldn't be a directory" );
            }
            FileInputStream inputStream = new FileInputStream ( file );
            InputStreamReader streamReader = new InputStreamReader ( inputStream, _encoding );
            try (BufferedReader bufferedReader = new BufferedReader ( streamReader )) {
                String line = null;
                while ((line = bufferedReader.readLine ( )) != null) {
                    content.append ( line + '\n' );
                }
            }
        }
        catch (IOException exc){
            exc.printStackTrace ();
            log.log(Level.ERROR, "Exception: ", exc);

        }
        log.log ( Level.INFO,"Successfully read from file "+file.getName () );

        return content.toString ();
    }

    public ArrayList<Train> readTrains(){
        String[] inp = GetContents().split ( "\n" );
        ArrayList<Train> trains = new ArrayList<Train> (  );
        int current_train =-1;
        int current_carriage=-1;
        for (String line: inp) {
            if ( line.startsWith ( "Train:" ) ) {
                trains.add ( new Train ( ) );
                current_train += 1;
                current_carriage = -1;
            }
            if ( line.startsWith ( "Carriage:" ) ) {
                switch (line.split ( ":" )[1]) {
                    case ("BOX"):
                        trains.get ( current_train ).addCar ( new ContainerCarriage ( ) );
                        current_carriage += 1;
                        break;
                    case ("LIQUID"):
                        trains.get ( current_train ).addCar ( new CisternCarriage ( ) );
                        current_carriage += 1;
                        break;
                    case ("CAR"):
                        trains.get ( current_train ).addCar ( new CarsCarriage ( ) );
                        current_carriage += 1;
                        break;
                    case ("SHAKY"):
                        trains.get ( current_train ).addCar ( new TankCarriage ( ) );
                        current_carriage += 1;
                        break;
                }
            } else {
                String type = "";
                try {
                    type = line.split ( ":" )[1];
                }
                catch (ArrayIndexOutOfBoundsException exc){
                    log.log (Level.INFO, "HANDLED EXCEPTION: Tried to split "+line+" by ':' and get [1]" );
                }
                try {
                    switch (type) {
                        case ("BOX"):
                            trains.get ( current_train ).getCars ( ).get ( current_carriage ).addGoods ( new BoxGoods ( line.split ( ":" )[0] ) );
                            break;
                        case ("SHAKY"):
                            trains.get ( current_train ).getCars ( ).get ( current_carriage ).addGoods ( new ShakyGoods ( line.split ( ":" )[0] ) );
                            break;
                        case ("LIQUID"):
                            trains.get ( current_train ).getCars ( ).get ( current_carriage ).addGoods ( new LiquidGoods ( line.split ( ":" )[0] ) );
                            break;
                        case ("CAR"):
                            trains.get ( current_train ).getCars ( ).get ( current_carriage ).addGoods ( new Cars ( line.split ( ":" )[0] ) );
                            break;
                    }
                } catch (InvalidClassException e) {
                    log.log(Level.ERROR, "Can't add " + line + " to carriage due to an inappropriate type");
                }
            }
        }
        log.log ( Level.INFO,"Successfully read trains from database" );
        return trains;
    }




}

