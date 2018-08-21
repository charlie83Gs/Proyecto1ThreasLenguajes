/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.ArrayList;

/**
 *
 * @author Charlie
 */
public class Controller {
    float width;
    float height;
    ArrayList<TrackQueue> tracks;

    public Controller(int numTracks, float pWidht, float pHeight) {
        tracks = new ArrayList<>();
        for(int i = 0; i < numTracks; i++ ){
            tracks.add(new TrackQueue(i,0,0,0));
        }
        updateTracks(pWidht, pHeight);
        
    }
    
    
    /**
     * in case of screen resizing
     * @param pWidht
     * @param pHeight 
     */
    
    //se recomienda tener un thread pool para asignar threads a las figuras
    public void updateTracks(float pWidht, float pHeight){
        width = pWidht;
        height = pHeight;
        int totalTracks = tracks.size();
        float trackWidth = width / totalTracks;
        for(int track = 0; track < totalTracks;track++){
            tracks.get(track).update(trackWidth*track, trackWidth, height);
        }
    }
   
    public void paintTracks(){
        for(TrackQueue track: tracks){
            track.paintSelf();
        }
    }
    
    
    public void createFigures(float speed, int amount){
        //se crean amount figuras
        while(amount-- > 0){
            int targetTrack  = (int)(Math.floor(Math.random()*tracks.size() -0.001));
            //se recomienda unir la figura y el thread aca en el controlador !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            tracks.get(targetTrack).createFigure(speed);

            
       }
    }
}
