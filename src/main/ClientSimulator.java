package main;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Basic client server program
 * To use first start main.Server
 * Then start client
 * Write into client command prompt and see result received in server
 */

public class ClientSimulator {

    // we initialize our socket (tunnel)
    // and our input reader and output stream
    // we will take the input from the user
    // and send it to the socket using output stream
    private Socket socket;
    private DataOutputStream out;
    private ConnectArduinoSimulator connectArduino;

    String currentSetpoint = "^0=SV5400";

    // constructor that takes the ip address and the port
    // also receives an instance of the arduino connector
    public ClientSimulator(String address, int port, ConnectArduinoSimulator connectArduino) {
        this.connectArduino = connectArduino;
        // try to establish a connection
        try {
            // create a socket
            socket = new Socket(address, port);
            // Output reader that (used to send data to server)
            out = new DataOutputStream(socket.getOutputStream());

        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException i) {
            System.out.println(i);
        }

        // string to be used to add temp from Arduino
        String temp = "";
        String viscoSetPoint = "";

        // reads temp from arduino and sends corresponding visco correction to server (printer)
        while (!viscoSetPoint.equals("Stop")) {
            try {
                viscoSetPoint = (String) connectArduino.getTemp();
                // check to see if a new visco setpoint needs to be sent to the server (printer)
                out.writeUTF(viscoSetPoint); // writes to output stream (main.Server)
                System.out.println("sent to printer: " + viscoSetPoint);
            } catch (IOException i) {
                System.out.println(i);
            }
        }

        // close the connection
        try {
            System.out.println("Closing client socket ");
            out.close();
            socket.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) throws IOException {
        ConnectArduinoSimulator connectArduino = new ConnectArduinoSimulator();
          ClientSimulator client = new ClientSimulator("127.0.0.1", 6666, connectArduino);
       // ClientSimulator client = new ClientSimulator("192.168.1.11", 3000, connectArduino);
    }
}
