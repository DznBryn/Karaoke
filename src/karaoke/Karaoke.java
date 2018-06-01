/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karaoke;
import model.Song;
import model.SongBook;

/**
 *
 * @author bryan
 */
public class Karaoke {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SongBook songbook = new SongBook();
        KaraokeMachine machine = new KaraokeMachine(songbook);
        machine.run();
    }
    
}
