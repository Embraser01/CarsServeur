/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsserveur.reseau;

import carsserveur.Main;
import com.ergotech.brickpi.BrickPi;
import com.ergotech.brickpi.motion.Motor;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marc-Antoine
 */
public class RunTraitement implements Runnable{
    
    private static final int SPEED_TURN = 100;
    private static final int SPEED = 255;
    
    private BrickPi brickPi;
    private Motor leftMotor;
    private Motor rightMotor;
    private Motor turnMotor;
    
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
        
        
        brickPi.setMotor(leftMotor, 2);
        brickPi.setMotor(rightMotor, 1);
        brickPi.setMotor(turnMotor, 3);
        
    }
    
    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }
    
    @Override
    public void run() {
        leftMotor.resetEncoder();
        rightMotor.resetEncoder();
        turnMotor.resetEncoder();
        
        
        while(true){
            try {
                if(up){
                    leftMotor.setCommandedOutput(RunTraitement.SPEED);
                    rightMotor.setCommandedOutput(RunTraitement.SPEED);
                }
                else if(down){
                    leftMotor.setCommandedOutput(-RunTraitement.SPEED);
                    rightMotor.setCommandedOutput(-RunTraitement.SPEED);
                }
                else {
                    leftMotor.setCommandedOutput(0);
                    rightMotor.setCommandedOutput(0);
                }
                
                if(right){
                    
                    if(turnMotor.getCurrentEncoderValue() >= -40)
                        turnMotor.rotate(45, -SPEED_TURN);
                }
                else if(left){
                    
                    if(turnMotor.getCurrentEncoderValue() <= 40)
                        turnMotor.rotate(45, SPEED_TURN);
                }
                else {
                    if(turnMotor.getCurrentEncoderValue() > 0){
                        turnMotor.rotate(turnMotor.getCurrentEncoderValue(), -SPEED_TURN);
                    }
                    if(turnMotor.getCurrentEncoderValue() < 0) {
                        turnMotor.rotate(turnMotor.getCurrentEncoderValue(), SPEED_TURN);
                    }
                    
                }
                
                brickPi.updateValues();
                Thread.sleep(150);
            } catch (InterruptedException ex) {
            // ignore
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
