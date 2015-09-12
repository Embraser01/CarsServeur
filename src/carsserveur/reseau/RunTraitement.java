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
    
    
    private volatile boolean up = false;
    private volatile boolean right = false;
    private volatile boolean down = false;
    private volatile boolean left = false;
    


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
        
        leftMotor.resetEncoder();
        rightMotor.resetEncoder();
        turnMotor.resetEncoder();
        
        
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
    
    @Override
    public void run() {
//        boolean isRight = false;
//        boolean isLeft = false;
        leftMotor.resetEncoder();
        rightMotor.resetEncoder();
        turnMotor.resetEncoder();
        
        
        while (true) {
            try {
                leftMotor.setCommandedOutput(leftMotorSpeed);
                rightMotor.setCommandedOutput(rightMotorSpeed);
                turnMotor.setCommandedOutput(turnMotorSpeed);
                
                brickPi.updateValues();
                Thread.sleep(150);
            } catch (InterruptedException ex) {
            // ignore
            } catch (IOException ex) {
                
            }
        }
    }
}
