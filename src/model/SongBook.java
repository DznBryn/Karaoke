/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.*;
/**
 *
 * @author bryan
 */
public class SongBook {
    private List<Song> songbook;
    
    public SongBook(){
        songbook = new ArrayList<Song>();
    }
    
    public void addSong(Song song){
        songbook.add(song);
    }
    
    public int getCount(){
        return songbook.size();    
    }
    
  
}
