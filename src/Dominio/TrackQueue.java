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
public class TrackQueue {
    
    int trackId;
    float xPos;
    float width;
    float height;
    ArrayList<ThreadFigure> figuras;

    public TrackQueue(int trackId, float xPos, float width, float height) {
        this.trackId = trackId;
        this.xPos = xPos;
        this.width = width;
        this.height = height;
    }
    
    public void update(float pXpos, float pWidth, float pHeigth){
        width = pWidth;
        height = pHeigth;
        xPos = pXpos;
        
    }
    
    
    public void paintSelf(){
        Painter.getPainter().paintTrack(xPos,width,height);
    }
    
    

    
    
}
