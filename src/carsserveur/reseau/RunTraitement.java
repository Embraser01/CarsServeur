/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsserveur.reseau;

/**
 *
 * @author Marc-Antoine
 */
public class RunTraitement implements Runnable{
    private Traitement trai;

    public RunTraitement(Traitement trai) {
        this.trai = trai;
    }
    
    
    
    @Override
    public void run() {
        trai.exec();
    }
    
}
