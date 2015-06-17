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
    private Thread threadTrai = null;
    private RunTraitement trai = null;

    public Reception(BufferedReader in){

        this.in = in;
        this.trai = new RunTraitement();
        this.threadTrai = new Thread(this.trai);
    }
        
    @Override
    public void run() {
        
        this.threadTrai.start();
        while(true){
            try {
                
                message = in.readLine();
                if(message != null){
                    switch(message.substring(0, 2)){
                                                    
                    case "10":
                        trai.setUp(true);
                        trai.setDown(false);
                        break;
                    case "20":
                        trai.setUp(false);
                        break;

                    case "11":
                        trai.setRight(true);
                        trai.setLeft(false);
                        break;
                    case "21":
                        trai.setRight(false);
                        break;

                    case "12":
                        trai.setDown(true);
                        trai.setUp(false);
                        break;
                    case "22":
                        trai.setDown(false);
                        break;

                    case "13":
                        trai.setLeft(true);
                        trai.setRight(false);
                        break;
                    case "23":
                        trai.setLeft(false);
                        break;

                    case "01":
                        trai.setUp(false);
                        trai.setRight(false);
                        trai.setDown(false);
                        trai.setLeft(false);
                        break;

                    default:
                        trai.setUp(false);
                        trai.setRight(false);
                        trai.setDown(false);
                        trai.setLeft(false);
                        break;
                    }
                }
                // TODO traitement
                
            } catch (IOException ex) {
                //Logger.getLogger(Reception.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
