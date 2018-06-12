/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author bryan
 */
public class SongBook {
    private List<Song> songbook;

    public SongBook() {
        songbook = new ArrayList<Song>();
    }

    public void exportTo(String filename) {
        try (
                FileOutputStream fos = new FileOutputStream(filename);
                PrintWriter writer = new PrintWriter(fos);
        ) {
            for (Song song : this.songbook) {
                writer.printf("%s|%s|%s%n", song.getArtist(), song.getTitle(), song.getVideoUrl());
            }
        } catch (IOException ioe) {
            System.out.println("Problem saving.");
            ioe.printStackTrace();
        }
    }

    public void importFrom(String filename) {
        try (
                FileInputStream fis = new FileInputStream(filename);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] args = line.split("\\|");
                addSong(new Song(args[0], args[1], args[2]));
            }
        } catch (IOException ioe) {
            System.out.println("Problem loading.");
            ioe.printStackTrace();
        }
    }

    public void addSong(Song song) {
        songbook.add(song);
    }

    public int getCount() {
        return songbook.size();
    }

    // Function: This is a map of songs by the artist
    // FIX: need to be Cached!
    private Map<String, List<Song>> byArtist() {
        Map<String, List<Song>> byArtist = new TreeMap<String, List<Song>>();
        for (Song song : this.songbook) {
            List<Song> artistSongs = byArtist.get(song.getArtist());
            if (artistSongs == null) {
                artistSongs = new ArrayList<Song>();
                byArtist.put(song.getArtist(), artistSongs);
            }
            artistSongs.add(song);
        }
        return byArtist;
    }

    public Set<String> getArtists() {
        return byArtist().keySet();
    }

    public List<Song> getSongsByArtist(String artistName) {
        List<Song> songs = byArtist().get(artistName);
        songs.sort(new Comparator<Song>() {
            @Override
            public int compare(Song song1, Song song2) {
                if (song1.equals(song2)) {
                    return 0;
                }
                return song1.getTitle().compareTo(song2.getTitle());
            }
        });
        return songs;
    }

}
