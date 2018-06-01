/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karaoke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import model.Song;
import model.SongBook;

/**
 *
 * @author bryan
 */
public class KaraokeMachine {
    
    private SongBook songBook;
    private BufferedReader reader;
    private Map<String, String> menu;
    
    public KaraokeMachine(SongBook songBook){
        this.songBook = songBook;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.menu = new HashMap<String,String>();
        this.menu.put("add", "Add a new song to the song book");
        this.menu.put("quit", "Exit the program");
    }
    
    // Function: this will prompt an action. It will load out the options stored in Menu Map (add or quit). 
    //           After, allow the use to answer in their choice
    
    // Note: Prompt is private because with only want this class to have excess to this method for motification 
    private String promptAction() throws IOException{
        System.out.printf("There are %d songs available. Your options are: %n", this.songBook.getCount());
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
    
    private Song promptNewSong() throws IOException{
        System.out.print("What is the artist name: ");
        String artist = this.reader.readLine();
        System.out.print("What is the artist song title: ");
        String title = this.reader.readLine();
        System.out.print("What is the artist video url: ");
        String url = this.reader.readLine();
        
        return new Song(artist,title,url);
    }
}
