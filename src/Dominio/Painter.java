package Dominio;

import Dominio.FigureType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;




/**
 *
 * @author Charlie
 */
public class Painter {

    private static Painter instance;
    private GraphicsContext context;
    
    public static Painter getPainter(){
        if(instance == null){
            instance = new Painter();
        }
        
        return instance;
    
    }
    
    private Painter() {
    
    }
    
    
    
    public void paintTrack(float pXpos, float pWidth,float pHeight){
        Color trackGray = Color.color(0.5, 0.5, 0.5);
        context.setFill(trackGray);
        context.setStroke(Color.BLACK);
        context.setLineWidth(3);
        context.fillRect(pXpos,0, pWidth, pHeight);
        context.strokeRect(pXpos,0, pWidth, pHeight);
    
    }
    
    public void paintFigure(float pXpos, float pYpos,Color pFigureColor,FigureType pFigureType){
        //dibujamos en el color de la figura
        context.setFill(pFigureColor);
        switch(pFigureType){
            case SQUARE:
                //dibujamos un cuadrado
                context.fillRect(pXpos, pYpos, 60, 60);
                break;
            case CIRCLE:
                //dibujamos un circulo
                
                break;
            default:
        }
    }

    public GraphicsContext getContex() {
        return context;
    }

    public void setContex(GraphicsContext contex) {
        this.context = contex;
    }
    
    
}
