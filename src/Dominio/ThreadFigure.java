
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

    public ThreadFigure() {
        figurecolor = Color.hsb(Math.random()*360, 1, 1);
        figureType = FigureType.SQUARE;
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
    }
    
    public void paintSelf(){
        
        Painter.getPainter().paintFigure(xPos, yPos, figurecolor, figureType);
    }
    
    public void update(){
        
        
    }
}
