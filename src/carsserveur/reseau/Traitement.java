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
public class Traitement {
    
    private static final int SPEED_TURN = 200;
    private static final int SPEED = 255;
    
    private BrickPi brickPi;
    private Motor leftMotor;
    private Motor rightMotor;
    
    private boolean up;
    private boolean right;
    private boolean down;
    private boolean left;

    

    public Traitement() {
        brickPi = BrickPi.getBrickPi();
        leftMotor = new Motor();
        rightMotor = new Motor();
        
        
        leftMotor.setCommandedOutput(0);
        rightMotor.setCommandedOutput(0);
        
        leftMotor.setEnabled(true);
        rightMotor.setEnabled(true);
        
        leftMotor.resetEncoder();
        rightMotor.resetEncoder();
        
        brickPi.setMotor(leftMotor, 2);
        brickPi.setMotor(rightMotor, 1);
        
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
    
    
    public boolean exec() {
                
        
        while(true){
            try {
                if(up && left){
                    leftMotor.setCommandedOutput(200);
                    rightMotor.setCommandedOutput(255);
                }
                
                brickPi.updateValues();
                Thread.sleep(200);
            } catch (InterruptedException ex) {
            // ignore
            }   catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
