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
import java.time.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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

        controller = new Controller(11, (float) cnv_Carreras.getWidth(), (float) cnv_Carreras.getHeight());

        //add a listener to center the  label
        anc_container.widthProperty().addListener((obs, oldVal, newVal) -> {
            update(cnv_Carreras.getWidth(), cnv_Carreras.getHeight());
            redraw();

        });
        anc_container.heightProperty().addListener((obs, oldVal, newVal) -> {
            update(cnv_Carreras.getWidth(), cnv_Carreras.getHeight());
            redraw();
        });

        //se hace schedule a dibujar la pantalla every x miliseconds
        Timeline frameScheduler = new Timeline(new KeyFrame(javafx.util.Duration.millis(33), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //System.out.println("this is called every 5 seconds on UI thread");
                redraw();
            }
        }));
        frameScheduler.setCycleCount(Timeline.INDEFINITE);
        frameScheduler.play();
        
    }
    
    public void setupStageListeners(Stage stage){
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                controller.stop();
            }
        });
    }
    
    public void redraw() {
        ThreadFigure figure = new ThreadFigure();
        figure.paintSelf();
        controller.paintTracks();
        controller.updateTracks((float) cnv_Carreras.getWidth(), (float) cnv_Carreras.getHeight());
    }

    public void update(double width, double height) {
        controller.updateTracks((float) width, (float) height);
    }

    //funciones para los botones
    @FXML
    public void createFigures() {
        controller.createFigures(10.0f, 20);

    }

    @FXML
    public void toggleBarriers() {

    }

    @FXML
    public void revert() {
        controller.invertTracks();
    }

    @FXML
    public void simulate() {

    }

    @FXML
    public void interrupt() {
        controller.togglePaused();
    }

}
