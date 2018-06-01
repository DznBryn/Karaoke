/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author bryan
 */
public class Song {
    private String artist, title, videoUrl;
    
    public Song(String artist,String title,String videoUrl){
        this.artist = artist;
        this.title = title;
        this.videoUrl = videoUrl;
    }
    
    public String getArtist(){
        return this.artist;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public String getVideoUrl(){
        return this.videoUrl;
    }
}
