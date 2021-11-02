package com.miet.pin23.pasechnik;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;

public class myProperties {
	private static Logger log = new Logger ( myProperties.class.getName () );
	public static String propFileName;

	public String getPropValues(Props p){
		Properties prop = new Properties();
		try {


			try (FileInputStream fis = new FileInputStream(propFileName)) {
				prop.load(fis);
			} catch (IOException exc){
				exc.printStackTrace ();
			}

			Date time = new Date(System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace ();
		}
		return prop.getProperty ( p.toString () );
	}
}