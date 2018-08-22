/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DilanHO
 */
public class RunningFigure implements Runnable {
    
    private ThreadFigure figure;
    
    public void run(){
        figure.updatePosition();
        
        try {Thread.currentThread().sleep(33);
        } catch (InterruptedException ex) {}
    }
    
    
}
