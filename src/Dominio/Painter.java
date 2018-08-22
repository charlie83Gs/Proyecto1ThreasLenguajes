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
    
    public void paintFigure(float pXpos, float pYpos,float size, Color pFigureColor,FigureType pFigureType){
        //dibujamos en el color de la figura
        context.setFill(pFigureColor);
        context.setStroke(pFigureColor);
        float desplazamiento = size/2;
        float xpos = pXpos-desplazamiento;
        float ypos = pYpos - desplazamiento;
        switch(pFigureType){
            case SQUARE:
                //dibujamos un cuadrado
                context.fillRect(xpos, ypos, size, size);
                //context.fillRoundRect(xpos, ypos, size, 50, 20, 20);
                break;
            case CIRCLE:
                //dibujamos un circulo
                context.fillOval(xpos, ypos, size, size);
                break;
            case CLOCK:
                context.strokeOval(xpos, ypos, size, size);
                context.fillOval(xpos, ypos, size*0.5, size*0.5);
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
