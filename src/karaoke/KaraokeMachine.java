/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karaoke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import model.SongRequest;
import model.Song;
import model.SongBook;


/**
 *
 * @author bryan
 */
public class KaraokeMachine {
    
    private SongBook songBook;
    private BufferedReader reader;
    private Queue<SongRequest> songRequestQueue;
    private Map<String, String> menu;
    
    public KaraokeMachine(SongBook songBook){
        this.songBook = songBook;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.songRequestQueue = new ArrayDeque<SongRequest>();
        this.menu = new HashMap<String,String>();
        this.menu.put("add", "Add a new song to the song book");
        this.menu.put("play", "Play queue");
        this.menu.put("choose", "Choose a song.");
        this.menu.put("quit", "Exit the program");
    }
    
    // Function: this will prompt an action. It will load out the options stored in Menu Map (add or quit). 
    //           After, allow the use to answer in their choice
    
    // Note: Prompt is private because with only want this class to have excess to this method for motification 
    private String promptAction() throws IOException{
        System.out.printf("There are %d songs available and %d in queue. Your options are: %n", this.songBook.getCount(),this.songRequestQueue.size());
        for(Map.Entry<String, String> options : this.menu.entrySet()){
            System.out.printf("%s - %s %n",
                              options.getKey(),
                              options.getValue() );
        }
        System.out.print("What do you want to do: ");
        String choice = this.reader.readLine();
        
        //to take the spaces in the begin and end of user choice use .trim
        return choice.trim().toLowerCase();
    }
    
    public void run(){
        String choice = "";
        
        // Do,While loop will run one time, if the statement of While is false it will run again 
        // until the statement of While is true
        do{
            try{
                choice = promptAction();
                switch(choice){
                    case "add":
                        Song song = promptNewSong();
                        this.songBook.addSong(song);
                        System.out.printf("Entry \"%s\" has been added! %n", song.getArtist());
                        break;
                    case "choose":
                        String singerName = promptForSingerName();
                        String artist = promptArtist();
                        Song artistSongs = promptSongForArtist(artist);
                        SongRequest request = new SongRequest(singerName, artistSongs);
                        if (this.songRequestQueue.contains(request)) {
                            System.out.printf("%n%n Whoop %s already requested. %n%n",singerName);
                            break;
                        }
                        this.songRequestQueue.add(request);
                        break;
                    case "play":
                        playNext();
                        break;
                    case "quit":
                        System.out.println("Thanks for playing!");
                        break;
                    default:
                        System.out.println("Error with your input. Please try again.\n\n");
                        break;
                }
            }catch(IOException ioe){
                System.out.println("Problem with input");
                ioe.printStackTrace();
            }
                
        }
        while(!choice.equals("quit"));
    }

    private String promptForSingerName() throws IOException {
        System.out.println("Enter the singer's name: ");
        return reader.readLine();
    }

    private Song promptNewSong() throws IOException{
        System.out.print("What is the artist name: ");
        String artist = this.reader.readLine();
        System.out.print("What is the artist song title: ");
        String title = this.reader.readLine();
        System.out.print("What is the artist video url: ");
        String url = this.reader.readLine();
        
        return new Song(artist,title,url);
    }
    
    private String promptArtist() throws IOException{
        System.out.println("Available artists: ");
        List<String> artists = new ArrayList<String>(this.songBook.getArtists());
        int index = promptForIndex(artists);
        return artists.get(index);
    }
    
    private Song promptSongForArtist(String artist) throws IOException{
        List<Song> songs = this.songBook.getSongsByArtist(artist);
        List<String> songTitles = new ArrayList<String>();
        for(Song songTitle : songs){
            songTitles.add(songTitle.getTitle());
        }
        System.out.printf("Available songs for %s: %n", artist);
        int index = promptForIndex(songTitles);
        return songs.get(index);
    }
    
    private int promptForIndex(List<String> options) throws IOException{
        int counter = 1;
        for(String option : options){
            System.out.printf("%d). %s %n",counter,option);
            counter++;
        }
        System.out.print("Your choice: ");
        String optionAsString = this.reader.readLine();
        int choice = Integer.parseInt(optionAsString.trim()); // Integer.parseInt() converts a String Integer into a Integer
        return choice - 1; //Choice - 1 because choice is zero base, it starts with 0 instead of 1
    }
    
    public void playNext(){
        SongRequest songRequest = this.songRequestQueue.poll();
        if(songRequest == null){
            System.out.println("Sorry there are no songs. Use choose to add some.");
        } else {
            Song song = songRequest.getSong();
            System.out.printf("%n%n%s: Open %s to hear %s by %s%n%n",
                    songRequest.getSingerName(),
                    song.getVideoUrl(),
                    song.getTitle(),
                    song.getArtist());
        }
    }
}
