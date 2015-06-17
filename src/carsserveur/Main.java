/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsserveur;

import carsserveur.reseau.Serveur;
import com.ergotech.brickpi.*;
import com.ergotech.brickpi.motion.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marc-Antoine
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ServerSocket socketserver;
        Socket socketduserveur = null;
        Serveur server;
        
        try {
            socketserver = new ServerSocket(42424);
            socketduserveur = socketserver.accept();
            server = new Serveur(socketduserveur);
            //socketduserveur.close();
            //socketserver.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}