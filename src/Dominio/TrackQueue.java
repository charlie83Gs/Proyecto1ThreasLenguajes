/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.ArrayList;
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
    ArrayList<ThreadFigure> figuras;

    public TrackQueue(int trackId, float xPos, float width, float height, Controller pmyController) {
        this.trackId = trackId;
        this.xPos = xPos;
        this.width = width;
        this.height = height;
        figuras = new ArrayList<>();
        this.myController = pmyController;
    }
    
    public void update(float pXpos, float pWidth, float pHeigth){
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
        newFigure.yPos = 0;
        
        figuras.add(0,newFigure);
        
        return newFigure; 
    
    }
    
    public void paintSelf(){
        Painter.getPainter().paintTrack(xPos,width,height);
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

    public synchronized boolean checkColition(ThreadFigure thisFigure){
        ThreadFigure nextFigure = this.getNext(thisFigure);
        //caso en el que no hay mas figuras
        if(nextFigure == null) return false;
        
        if(((thisFigure.yPos + (thisFigure.size)) > nextFigure.getyPos()) != myController.isGoDown()){   // no pega con otro
            if((thisFigure.yPos < height) == myController.isGoDown()){   // llega al final
                return false;
            }else{
                //kills threead figure 
                thisFigure.setAlive(false);
                figuras.remove(thisFigure);
            }
        }
        return true;
    }
    
}
