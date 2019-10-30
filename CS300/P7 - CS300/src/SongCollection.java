import java.util.Iterator;
import java.lang.Iterable;
import java.util.NoSuchElementException;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           SongCollection
// Files:           Song, DoubleLinkedNode, SongCollection, Playlist, ReversePlaylist
// Course:          300, Fall, and 2019
//
// Author:          (Ariel Fu)
// Email:           afu5@wisc.edu
// Lecturer's Name: Mouna Ayari Ben Hadj Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    None
// Partner Email:   None
// Partner Lecturer's Name: None
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         NONE
// Online Sources:  NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/**
 * This class represents a collection of the Object Song
 * 
 * @author Ariel
 *
 */
public class SongCollection implements Iterable<Song> {
  private DoublyLinkedNode<Song> head; // head of the collection
  private DoublyLinkedNode<Song> tail; // tail of the collection
  private boolean playDirectionForward; // determines which way the song collection plays.

  /**
   * No-argument constructor, which initializes head and tail to null and
   * forwardPlaylist to true.
   */
  public SongCollection() {
    head = null;
    tail = null;
    playDirectionForward = true;

  }

  /**
   * Adds song to the end / tail of this doubly linked list. If the song is null,
   * it throws a NullPointerException
   * 
   * @param song - new song to add to the end of the doubly linked list.
   * @throws NullPointerException - if the song inputed is null.
   */
  public void add(Song song) {
    if(song == null) {
      throw new NullPointerException("song cannot be null.");
    }
    DoublyLinkedNode<Song> currSong = new DoublyLinkedNode<Song>(song);
    // if the list is empty, set the head to the current song.
    if(head == null && tail == null) {
      head = currSong;
    } else {
      // otherwise, reset the tail pointers.
      currSong.setPrevious(tail);
      tail.setNext(currSong);
    }
    // set the tail to the current song.
    tail = currSong;

  }

  /**
   * Removes and returns song from the front/head of this list. When the list is
   * empty, throws a NoSuchElementException.
   * 
   * @throws NoSuchElementException if the head of the list is null and the list
   *                                is empty.
   * @return song from the front / head of this list
   */
  public Song remove() {
    if(head == null) {
      throw new NoSuchElementException("there are no more songs to remove!");
    }
    Song previousHead = head.getData();
    head = head.getNext();
    // if the list is now empty, set the tail back to null, also.
    if(head == null) {
      tail = null;
    }

    return previousHead;
  }

  /**
   * When playDirctionForward is true, a SongCollection's iterator() method should
   * return a Playlist object, otherwise it should return a ReversePlaylist
   * object.
   * 
   * @return a Playlist object or a ReversePlaylist object, depending if
   *         playDirectionForward is true or false.
   */

  @Override 
  public Iterator<Song> iterator() {
    // if playDirectionForward is true, return a Playlist object.
    if(playDirectionForward) {
      return new Playlist(head);
    }
    // if playDirectionForward is false, return a ReversePlaylist object.
    return new ReversePlaylist(tail);
  }

  /**
   * Mutator method for isForward, changes the direction the playlist plays.
   * 
   * @param isForward - if true, a Playlist object will be returned in iterator()
   *                  if false, a ReversePlaylist object will be returned in
   *                  iterator().
   */
  public void setPlayDirection(boolean isForward) {
    playDirectionForward = isForward;
  }
// TODO ~ review big-O time complexityw ith dad about the last one... Aiden said he got O(1)?
///////////////////////////////////////////////////////////////////////////////////
//For each of the following big-O time complexity analyses, consider the problem
//size to be the number of Songs that are stored within the argument songs, when
//the method is first called.
//
//For analysisMethodA, the big-O time complexity is ___O(1)_________.
//
//For analysisMethodB, the big-O time complexity is ___O(n)_________.
//
//For analysisMethodC, the big-O time complexity is ___O(1)_________.
//
///////////////////////////////////////////////////////////////////////////////////

}
