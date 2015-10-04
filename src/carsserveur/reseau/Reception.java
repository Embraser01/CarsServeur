/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsserveur.reseau;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Marc-Antoine
 */
public class Reception implements Runnable {

    private BufferedReader in;
    private String message = null;
    private Thread threadTrai = null;
    private RunTraitement trai = null;

    public Reception(BufferedReader in) {

        this.in = in;
        this.trai = new RunTraitement();
        this.threadTrai = new Thread(this.trai);
    }

    @Override
    public void run() {

        this.threadTrai.start();
        System.out.println("Now Listening");

        while (true) {
            try {

                message = in.readLine();
                if (message != null) {
                    String speeds[] = message.split("/");

                    try {
                        trai.setLeftMotorSpeed(Integer.parseInt(speeds[0]));
                        trai.setTurnMotorSpeed(Integer.parseInt(speeds[1]));
                        trai.setRightMotorSpeed(Integer.parseInt(speeds[2]));
                        System.out.println(message);

                    } catch (Exception e) {
                        trai.setLeftMotorSpeed(0);
                        trai.setTurnMotorSpeed(0);
                        trai.setRightMotorSpeed(0);
                        System.out.printf(e.getMessage());
                    }
                }

            } catch (IOException ex) {
            }
        }
    }

}
