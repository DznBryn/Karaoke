/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 * @author bryan
 */
public class Song {
    private String artist, title, videoUrl;

    public Song(String artist, String title, String videoUrl) {
        this.artist = artist;
        this.title = title;
        this.videoUrl = videoUrl;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getTitle() {
        return this.title;
    }

    public String getVideoUrl() {
        return this.videoUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(artist, song.artist) &&
                Objects.equals(title, song.title) &&
                Objects.equals(videoUrl, song.videoUrl);
    }

    @Override
    public int hashCode() {

        return Objects.hash(artist, title, videoUrl);
    }
}
