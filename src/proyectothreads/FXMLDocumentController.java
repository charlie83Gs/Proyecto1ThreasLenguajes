/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectothreads;

import Dominio.Controller;
import Dominio.ThreadFigure;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import Dominio.Painter;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author Charlie
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private Canvas cnv_Carreras;
    
    @FXML
    private AnchorPane anc_container;
    
    //interactive interface
    //text input
    @FXML
    private TextField tf_Speed;
    @FXML
    private TextField tf_Amount;
    @FXML
    private TextField tf_Barrera;
    
    //buttons
    @FXML
    private Button btn_CrearFiguras;
    
    @FXML
    private Button btn_Barrera;
    
    @FXML
    private Button btn_interrupt;
    
    @FXML
    private Button btn_revert;
    
    @FXML
    private Button btn_simulate;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    private Controller controller;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cnv_Carreras.widthProperty().bind(anc_container.widthProperty().subtract(200));
        cnv_Carreras.heightProperty().bind(anc_container.heightProperty().subtract(30));
        System.out.println(anc_container.widthProperty().toString());
        cnv_Carreras.getGraphicsContext2D().setFill(Color.BROWN);
        //cnv_Carreras.getGraphicsContext2D().fillRect(0, 0, cnv_Carreras.getWidth(), cnv_Carreras.getHeight());
        cnv_Carreras.getGraphicsContext2D().fillArc(0, 0, 30, 30, 45, 240, ArcType.OPEN);
        cnv_Carreras.getGraphicsContext2D().fillRect(10, 10, 60, 60);
        
        Painter.getPainter().setContex(cnv_Carreras.getGraphicsContext2D());
        
        controller = new Controller(11, (float)cnv_Carreras.getWidth(), (float)cnv_Carreras.getHeight());
        
        //add a listener to center the  label
        anc_container.widthProperty().addListener((obs, oldVal, newVal) -> {
            update(cnv_Carreras.getWidth(),cnv_Carreras.getHeight()); 
            redraw();
             
        });
        anc_container.heightProperty().addListener((obs, oldVal, newVal) -> {
            update(cnv_Carreras.getWidth(),cnv_Carreras.getHeight()); 
            redraw();
        });
        
    }

    public void redraw(){
        ThreadFigure figure = new ThreadFigure();
        figure.paintSelf();
        controller.paintTracks();
    }

    public void update(double width, double height){
        controller.updateTracks((float)width, (float)height);
    }
    
    //funciones para los botones
    @FXML
    public void createFigures(){
        
    }
    
    @FXML
    public void toggleBarriers(){
        
    }
    
    @FXML
    public void revert(){
        
    }
    
    @FXML
    public void simulate(){
        
    }
    
    @FXML
    public void interrupt(){
        
    }




}
