/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsserveur.reseau;

import com.ergotech.brickpi.BrickPi;
import com.ergotech.brickpi.motion.Motor;

import java.io.IOException;

/**
 * @author Marc-Antoine
 */
public class RunTraitement implements Runnable {

    private static final int SPEED_MAX = 256;
    private static final int SPEED_TURN = 121;

    private final BrickPi brickPi;
    private final Motor leftMotor;
    private final Motor rightMotor;
    private final Motor turnMotor;


    private volatile int leftMotorSpeed = 0;
    private volatile int rightMotorSpeed = 0;
    private volatile int turnMotorSpeed = 0;

    private volatile int oldTurnMotorSpeed = 0;


    public RunTraitement() {

        brickPi = BrickPi.getBrickPi();
        leftMotor = new Motor();
        rightMotor = new Motor();
        turnMotor = new Motor();


        leftMotor.setCommandedOutput(0);
        rightMotor.setCommandedOutput(0);
        turnMotor.setCommandedOutput(0);

        leftMotor.setEnabled(true);
        rightMotor.setEnabled(true);
        turnMotor.setEnabled(true);

        brickPi.setMotor(leftMotor, 3);
        brickPi.setMotor(rightMotor, 1);
        brickPi.setMotor(turnMotor, 2);

    }

    public void setLeftMotorSpeed(int leftMotorSpeed) {
        this.leftMotorSpeed = leftMotorSpeed % SPEED_MAX;
    }


    public void setRightMotorSpeed(int rightMotorSpeed) {
        this.rightMotorSpeed = rightMotorSpeed % SPEED_MAX;
    }


    public void setTurnMotorSpeed(int turnMotorSpeed) {
        this.turnMotorSpeed = turnMotorSpeed % SPEED_TURN;
    }


    public void setupTurnMotor() {
        try {

            turnMotor.setCommandedOutput(250);
            brickPi.updateValues();
            Thread.sleep(200);

            turnMotor.setCommandedOutput(-250);
            brickPi.updateValues();
            Thread.sleep(200);


            turnMotor.setCommandedOutput(95);
            brickPi.updateValues();
            Thread.sleep(200);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        leftMotor.resetEncoder();
        rightMotor.resetEncoder();
        turnMotor.resetEncoder();
        setupTurnMotor();


        while (true) {
            try {
                leftMotor.setCommandedOutput(leftMotorSpeed);
                rightMotor.setCommandedOutput(rightMotorSpeed);


                int diff = Math.abs(turnMotorSpeed - oldTurnMotorSpeed);

                if(turnMotorSpeed > oldTurnMotorSpeed) {
                    turnMotor.setCommandedOutput(diff);
                    oldTurnMotorSpeed = turnMotorSpeed;
                } else {
                    turnMotor.setCommandedOutput(-diff);
                    oldTurnMotorSpeed = turnMotorSpeed;
                }


                brickPi.updateValues();
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                // ignore
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
