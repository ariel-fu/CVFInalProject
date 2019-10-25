
import java.util.Iterator;
import java.util.NoSuchElementException;

////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           Playlist
//Files:           Song, DoubleLinkedNode, SongCollection, Playlist, ReversePlaylist
//Course:          300, Fall, and 2019
//
//Author:          (Ariel Fu)
//Email:           afu5@wisc.edu
//Lecturer's Name: Mouna Ayari Ben Hadj Kacem
//
////////////////////PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
//Partner Name:    None
//Partner Email:   None
//Partner Lecturer's Name: None
//
//VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//___ Write-up states that pair programming is allowed for this assignment.
//___ We have both read and understand the course Pair Programming Policy.
//___ We have registered our team prior to the team registration deadline.
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
//Online Sources:  NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/**
 * This class represents an iterator that will return songs from front to tail
 * in order.
 * 
 * @author Ariel
 *
 */

public class Playlist implements Iterator<Song> {

	private DoublyLinkedNode<Song> current;
	private Iterator<DoublyLinkedNode<Song>> iterator;

	/**
	 * Constructor that takes in a DoublyLinkedNode<Song> that is expected to be the
	 * first song in the playlist.
	 * 
	 * @param head
	 */
	public Playlist(DoublyLinkedNode<Song> head) {

		current = head;
	}

	/**
	 * Checks if there are more songs left in the playlist.
	 * 
	 * @return true if there are still songs left.
	 */
	@Override
	public boolean hasNext() {
		if (current.getNext() == null) {
			return false;
		}
		return true;
	}

	/**
	 * Returns a different song each time. Returns from head to tail in order
	 * 
	 * @return next song in playlist.
	 * @throws NoSuchElementException - if no songs are left to be returned by the
	 *                                iterator.
	 */
	@Override
	public Song next() {
		if (!hasNext()) {
			throw new NoSuchElementException("There aren't any more songs to play.");
		}
		current = iterator.next();
		return current.getData();
	}

}
