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
        newFigure.setSpeed(speed);
        newFigure.xPos = xPos + width / 2;
        newFigure.yPos = 0;
        
        figuras.add(newFigure);
        
        return newFigure; 
    
    }
    
    public void paintSelf(){
        Painter.getPainter().paintTrack(xPos,width,height);
        for(ThreadFigure figure: figuras){
            figure.paintSelf();
        }
    }
    
    private ThreadFigure getNext(ThreadFigure thisFigure){
        return figuras.get(figuras.indexOf(thisFigure) + 1);
    }

    public boolean checkColition(ThreadFigure thisFigure){
        ThreadFigure nextFigure = this.getNext(thisFigure);
        if(((thisFigure.yPos + (thisFigure.size/2)) > nextFigure.getyPos()) != myController.isGoDown()){   // no pega con otro
            if((thisFigure.yPos < height) != myController.isGoDown()){   // llega al final
                return false;
            }
        }
        return true;
    }
    
}
