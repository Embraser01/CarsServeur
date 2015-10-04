/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsserveur.reseau;


import carsserveur.findprotocole.BroadcastListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Marc-Antoine
 */
public class Serveur {

    public static void main(String[] args) {

        ServerSocket socketserver;
        Socket socketduserveur;
        BufferedReader in = null;
        PrintWriter out;
        Thread t = null;

        BroadcastListener broadcastListener = null;
        Thread threadBroadCastListener = null;

        try {

            broadcastListener = new BroadcastListener(42425);
            threadBroadCastListener = new Thread(broadcastListener);
            threadBroadCastListener.start();


            socketserver = new ServerSocket(42424);

            while (true) {
                try {
                    socketduserveur = socketserver.accept();

                    System.out.println("Nouveau client connecté !");
                    out = new PrintWriter(socketduserveur.getOutputStream());
                    in = new BufferedReader(new InputStreamReader(socketduserveur.getInputStream()));
                    out.println("Vous êtes bien connecté sur le robot du projet Cars | Semaine Spé 2 2015 avec :");
                    out.println("\t - FERNANDES Marc-Antoine");
                    out.println("\t - PHAN Dominique");
                    out.println("\t - LE BOT Axel");
                    out.println("\t - DUCOROY Maxime");
                    out.flush();
                    out.println("Début Transmission");
                    out.flush();

                    if (t != null) {
                        t.interrupt();
                    }
                    t = new Thread(new Reception(in));
                    t.start();

                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
            socketserver.close();
        } catch (IOException ex) {
        }

    }
}
