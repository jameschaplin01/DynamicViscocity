package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ConnectArduinoSimulator {

    String viscoSetpoint = "";
    String setPoint = "";
    Double temp = 20.00;
    Properties p;
    InputStream is;


    // constructor makes initial connection to com port
    public ConnectArduinoSimulator() throws IOException {

        // add properties file to instance
        // add reader to get data from file
        p = new Properties();
        is = new FileInputStream("C:\\workspace\\DynamicViscosity\\resources\\inks.properties");
        // loads data into properties object
        p.load(is);

    }

    // randomly generate a value between 5 and 50°C
    // send to checkSetPoint() to get a setpoint String and return to ClientSimulator
    public String getTemp() {
     /**   try
        {
            byte[] readBuffer = new byte[100];
            int numRead = sp.readBytes(readBuffer, readBuffer.length);
            //Convert bytes to String
            String S = new String(readBuffer, "UTF-8");
            tempFloat = Double.parseDouble(S);
            setPoint = checkSetPoint(tempFloat);
            System.out.println("Temperature: " +tempFloat);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        } */

        temp = 5 + (Math.random() * 50);
        setPoint = checkSetPoint(temp);
        System.out.println("RandomTempGenerated: " +temp);


// Output:
// A random number between 10.0 (inclusive) and 50.0 (exclusive)


        return setPoint;
    }

    // query inks.properties and return asocicated viscosity setpoint
    public String checkSetPoint(Double inkTemp) {
        // adds a delay of 3 seconds
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        }
        catch (Exception e) {
        }

        if(inkTemp <= 5) {
            viscoSetpoint = p.getProperty("5");
        }
        else if(inkTemp <= 10) {
            viscoSetpoint = p.getProperty("10");
        }
        else if(inkTemp <= 15) {
            viscoSetpoint = p.getProperty("15");
        }
        else if(inkTemp <= 20) {
            viscoSetpoint = p.getProperty("20");
        }
        else if(inkTemp <= 25) {
            viscoSetpoint = p.getProperty("25");
        }
        else if(inkTemp <= 30) {
            viscoSetpoint = p.getProperty("30");
        }
        else if(inkTemp <= 35) {
            viscoSetpoint = p.getProperty("35");
        }
        else if(inkTemp <= 40) {
            viscoSetpoint = p.getProperty("40");
        }
        else if(inkTemp <= 45) {
            viscoSetpoint = p.getProperty("45");
        }
        else if(inkTemp > 45) {
            viscoSetpoint = p.getProperty("50");
        }

        return viscoSetpoint;
    }

    public static void main() throws IOException {
        ConnectArduinoSimulator connectArduino = new ConnectArduinoSimulator();
    }
}
