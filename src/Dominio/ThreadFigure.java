
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/**
 *
 * @author Charlie
 */

public class ThreadFigure {
    Color figurecolor;
    FigureType figureType;
    float xPos;
    float yPos;
    Controller myController;
    TrackQueue track;
    float actualXPos;
    int pos;
    float speed;
    float size = 10;
    private boolean alive;
    
    //relation of size from figure and track width
    private static final float trackRatio = 0.2f;
    

    public ThreadFigure() {
        figurecolor = Color.hsb(Math.random()*360, 1, 1);
        figureType = FigureType.CLOCK;
        alive = true;
    }

    public ThreadFigure(Color figurecolor, FigureType figureType, float xPos, float yPos, Controller myController, TrackQueue track, float actualXPos, int pos) {
        this.figurecolor = figurecolor;
        this.figureType = figureType;
        this.xPos = xPos;
        this.yPos = yPos;
        this.myController = myController;
        this.track = track;
        this.actualXPos = actualXPos;
        this.pos = pos;
        alive = true;
    }
    
    public void paintSelf(){
        
        Painter.getPainter().paintFigure(xPos, yPos,size, figurecolor, figureType);
    }
    
    public void update(float pWidth){
        //float nsize = pWidth*0.9f;
        size = pWidth*trackRatio;
        
    }
    
    public synchronized void updatePosition(){
       if(!track.checkColition(this)){
           if(track.isGoDown()){
            yPos += speed;
        }
           else{
               yPos -= speed;
           }
       }
    }
    
    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getxPos() {
        return xPos;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public TrackQueue getTrack() {
        return track;
    }

    public void setTrack(TrackQueue track) {
        this.track = track;
    }
    
    
    
}
