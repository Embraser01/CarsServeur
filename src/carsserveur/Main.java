/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsserveur;

import com.ergotech.brickpi.*;
import com.ergotech.brickpi.motion.*;

/**
 *
 * @author Marc-Antoine
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BrickPi brickPi = BrickPi.getBrickPi();
        
        
        Motor motor = new Motor();
        motor.setCommandedOutput(0);
        motor.setEnabled(true);
        motor.resetEncoder();
        
        brickPi.setMotor(motor, 1);
        motor.setCommandedOutput(25);
        for (int counter = 0; counter < 50; counter++) {
            try {
                System.out.println("Forward Motors: Speed " + brickPi.getMotor(1).getCurrentSpeed() + " encoder " + brickPi.getMotor(1).getCurrentEncoderValue() + " Time " + System.currentTimeMillis() % 10000);
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                // ignore
            }
        }
        motor.setEnabled(true);
    }

}