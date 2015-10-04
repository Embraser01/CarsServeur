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
 *
 * @author Marc-Antoine
 */
public class RunTraitement implements Runnable{
    
    //private static final int SPEED_TURN = 170;
    private static final double ANGLE = 0.125;
    
    private static final int SPEED_MAX = 255;
    
    private final BrickPi brickPi;
    private final Motor leftMotor;
    private final Motor rightMotor;
    private final Motor turnMotor;
    
    
    private volatile int leftMotorSpeed = 0;
    private volatile int rightMotorSpeed = 0;
    private volatile int turnMotorSpeed = 0;
    private volatile int oldTurnValue = -1;
    


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
        brickPi.setMotor(rightMotor, 2);
        brickPi.setMotor(turnMotor, 1);
        
    }

    public int getLeftMotorSpeed() {
        return leftMotorSpeed;
    }

    public void setLeftMotorSpeed(int leftMotorSpeed) {
        this.leftMotorSpeed = leftMotorSpeed % SPEED_MAX;
    }

    public int getRightMotorSpeed() {
        return rightMotorSpeed;
    }

    public void setRightMotorSpeed(int rightMotorSpeed) {
        this.rightMotorSpeed = rightMotorSpeed % SPEED_MAX;
    }

    public int getTurnMotorSpeed() {
        return turnMotorSpeed;
    }

    public void setTurnMotorSpeed(int turnMotorSpeed) {
        this.turnMotorSpeed = turnMotorSpeed % SPEED_MAX;
    }


    public void setupTurnMotor(){
        turnMotor.resetEncoder();
        turnMotor.rotate(-180,SPEED_MAX);

        try {

            brickPi.updateValues();
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        turnMotor.rotate(180,SPEED_MAX);

        try {

            brickPi.updateValues();
            Thread.sleep(150);
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
        setupTurnMotor();
        
        
        while (true) {
            try {

                leftMotor.setCommandedOutput(leftMotorSpeed);
                rightMotor.setCommandedOutput(rightMotorSpeed);

                if(oldTurnValue != 0){

                    if(turnMotorSpeed == 0){
                        turnMotor.rotate(1,255);
                    }
                    turnMotor.rotate(turnMotorSpeed % 0.5,SPEED_MAX);
                    oldTurnValue = 0;

                } else {

                }


                brickPi.updateValues();
                Thread.sleep(150);
            } catch (InterruptedException ex) {
                // ignore
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
