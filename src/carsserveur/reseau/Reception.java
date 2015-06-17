/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsserveur.reseau;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author Marc-Antoine
 */
public class Reception implements Runnable {

    private BufferedReader in;
    private String message = null;
    private RunTraitement threadTrai = null;

    public Reception(BufferedReader in){

        this.in = in;
        
        this.threadTrai = new RunTraitement();
    }
        
    @Override
    public void run() {
        
        this.threadTrai.run();
        while(true){
            try {
                
                message = in.readLine();
                if(message != null){
                    System.out.println(message);
                    switch(message.substring(0, 2)){
                                                    
                    case "10":
                        threadTrai.setUp(true);
                        threadTrai.setDown(false);
                        break;
                    case "20":
                        threadTrai.setUp(false);
                        break;

                    case "11":
                        threadTrai.setRight(true);
                        threadTrai.setLeft(false);
                        break;
                    case "21":
                        threadTrai.setRight(false);
                        break;

                    case "12":
                        threadTrai.setDown(true);
                        threadTrai.setUp(false);
                        break;
                    case "22":
                        threadTrai.setDown(false);
                        break;

                    case "13":
                        threadTrai.setLeft(true);
                        threadTrai.setRight(false);
                        break;
                    case "23":
                        threadTrai.setRight(false);
                        break;

                    case "01":
                        threadTrai.setUp(false);
                        threadTrai.setRight(false);
                        threadTrai.setDown(false);
                        threadTrai.setLeft(false);
                        break;

                    default:
                        threadTrai.setUp(false);
                        threadTrai.setRight(false);
                        threadTrai.setDown(false);
                        threadTrai.setLeft(false);
                        break;
                    }
                }
                    System.out.println(message);
                // TODO traitement
                
            } catch (IOException ex) {
                //Logger.getLogger(Reception.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
