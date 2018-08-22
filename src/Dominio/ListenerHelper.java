/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 *
 * @author Carlos
 */
public class ListenerHelper {
    //obliga a un textfield a solo recibir numeros
    public static void convert_to_number_field(TextField t){
            t.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                if (!newValue.matches("\\d*")) {
                    t.setText(newValue.replaceAll("[^\\d]", ""));
                }
            });
    }
    
    public static void convert_to_float_field(TextField t){
            t.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                if (!newValue.matches("(\\d*,*)*")) {
                    t.setText(newValue.replaceAll("[^\\d]", ""));
                }
            });
    }
    
}