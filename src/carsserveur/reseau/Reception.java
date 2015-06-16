/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsserveur.reseau;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marc-Antoine
 */
public class Reception implements Runnable {

    private BufferedReader in;
    private String message = null;

    public Reception(BufferedReader in){

        this.in = in;
    }
        
    @Override
    public void run() {
        
        while(true){
            try {
                
                message = in.readLine();
                System.out.println(message);
                // TODO traitement
                
            } catch (IOException ex) {
                Logger.getLogger(Reception.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
