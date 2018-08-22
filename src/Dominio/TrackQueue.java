/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Charlie
 */

//falta impelentar instanciaciopn con pista invertida
public class TrackQueue {
    
    int trackId;
    float xPos;
    float width;
    float height;
    Controller myController;
    boolean goDown;
    ArrayList<ThreadFigure> figuras;
    boolean paused;
    boolean barrier;

    public TrackQueue(int trackId, float xPos, float width, float height, Controller pmyController) {
        this.trackId = trackId;
        this.xPos = xPos;
        this.width = width;
        this.height = height;
        figuras = new ArrayList<>();
        this.myController = pmyController;
        goDown = true;
        paused = false;
        barrier = false;
    }
    
    public synchronized void update(float pXpos, float pWidth, float pHeigth){
        width = pWidth;
        height = pHeigth;
        xPos = pXpos;
        
        for(ThreadFigure figure: figuras){
            figure.xPos = xPos + width / 2;
            figure.update(width);
        }
        
    }
    
    //falta impelentar instanciaciopn con pista invertida
    public ThreadFigure createFigure(float speed){
        ThreadFigure newFigure = new ThreadFigure();
        newFigure.setSpeed(1.0f+speed*(float)Math.random());
        newFigure.setTrack(this);
        newFigure.xPos = xPos + width / 2;
        if(this.isGoDown()){
            newFigure.yPos = 0;
           
        }
        else{
            newFigure.yPos = height;
        }
        
        figuras.add(0,newFigure);
        
        return newFigure; 
    
    }
    
    public synchronized void paintSelf(){
        Painter.getPainter().paintTrack(xPos,width,height,barrier);
        for(ThreadFigure figure: figuras){
            figure.paintSelf();
        }
    }
    
    private synchronized ThreadFigure getNext(ThreadFigure thisFigure){
        if(figuras.indexOf(thisFigure) + 1 >= figuras.size()){
            return null;
        }
        return figuras.get(figuras.indexOf(thisFigure) + 1);
    }
    
    private synchronized void removeLastFigure(ThreadFigure thisFigure){
        figuras.remove(thisFigure);
    }

    public synchronized boolean checkColition(ThreadFigure thisFigure){
        ThreadFigure nextFigure = this.getNext(thisFigure);
        //caso en el que no hay mas figuras
        //pausa
        if(paused)return true;
        
        //colisiones con barreras
        if(myController.isGoDown()){
            if((thisFigure.yPos + (thisFigure.size) >= height/2+1) && thisFigure.yPos < height / 2 && barrier){
                return true;
            }
        }else{
            if((thisFigure.yPos - (thisFigure.size) <= height/2-1) && thisFigure.yPos > height/2 && barrier){
                return true;
            }
         }
        //ultimo de la cola
        if(nextFigure == null) return false;
        
        
        //kills threead figure 
        if (thisFigure.getyPos() < 0 || thisFigure.getyPos() > height) {
            thisFigure.setAlive(false);
            removeLastFigure(thisFigure);
            return false;
        }

        //colisiones entre figuras
        if(myController.isGoDown()){
            if(thisFigure.yPos + (thisFigure.size) <= nextFigure.getyPos()+1){
                return false;
            }
        }else{
            if( thisFigure.yPos - (thisFigure.size) >= nextFigure.getyPos()-1){
                return false;
            }

        }
        
        
        
        
        /*if(((thisFigure.yPos + (thisFigure.size)) > nextFigure.getyPos()) != myController.isGoDown()){   // no pega con otro
                return false;
        }
        if(((thisFigure.yPos - (thisFigure.size)) < nextFigure.getyPos()) == !myController.isGoDown()){   // no pega con otro
                return false;
        }*/
        
        
        return true;
    }
    
    
    public void invertFigures(){
       Collections.reverse(this.figuras);
       this.goDown = !this.goDown;
    }
    

    public synchronized boolean isGoDown() {
        return goDown;
    }

    public synchronized void setGoDown(boolean goDown) {
        this.goDown = goDown;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public void setBarrier(boolean barrier) {
        this.barrier = barrier;
    }

    public boolean isBarrier() {
        return barrier;
    }
    
    
    
    
}
