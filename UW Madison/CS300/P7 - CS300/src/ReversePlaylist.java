import java.util.Iterator;
import java.util.NoSuchElementException;

////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           ReversePlaylist
//Files:           Song, DoubleLinkedNode, SongCollection, Playlist, ReversePlaylist
//Course:          300, Fall, and 2019
//
//Author:          (Ariel Fu)
//Email:           afu5@wisc.edu
//Lecturer's Name: Mouna Ayari Ben Hadj Kacem
//
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
//Students who get help from sources other than their partner must fully 
//acknowledge and credit those sources of help here.  Instructors and TAs do 
//not need to be credited here, but tutors, friends, relatives, room mates, 
//strangers, and others do.  If you received no outside help from either type
//of source, then please explicitly indicate NONE.
//
//Persons:         NONE
//Online Sources:   Google - Iterator --> find methods that needed to be overriden.
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/**
 * This class represents a playlist that will return the songs from the tail to
 * the head.
 * 
 * @author Ariel
 *
 */

public class ReversePlaylist implements Iterator<Song> {
  private DoublyLinkedNode<Song> current; // keeps track of current song/DoublyLinkedNode

  /**
   * Constructor for ReversePlaylist that takes in a DoublyLinkedNode<Song> that
   * is assumed to be the last song/ tail of the playlist.
   * 
   * @param tail - last song/ tail of the playlist.
   */
  public ReversePlaylist(DoublyLinkedNode<Song> tail) {
    current = tail;
  }

  /**
   * Overrides hasNext() in the Iterator interface
   * 
   * @return true if the current pointer doesn't point to null.
   * @Override hasNext() in Iterator<T> interface.
   */
//  @Override 
  public boolean hasNext() {
    return current != null;
  }

  /**
   * Overrides next() in the Iterator interface
   * 
   * @return song from the previous pointer/ song that came before the current
   *         song.
   * @throws NoSuchElementException - if there are no more songs to iterate.
   * @Override next() in Iterator<T> interface.
   */
  @Override
  public Song next() {
    if(!hasNext()) {
      throw new NoSuchElementException("There are no more songs to play.");
    }
    DoublyLinkedNode<Song> currSong = current;
    current = current.getPrevious();
    return currSong.getData();
  }

}
