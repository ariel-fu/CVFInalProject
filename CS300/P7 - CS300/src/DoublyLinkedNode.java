
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           DoublyLinkedNode
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
 * This class represents a single node within a doubly linked list.
 * 
 * @author Ariel
 *
 */
public class DoublyLinkedNode<T> {
  private DoublyLinkedNode<T> previous; // stores previous reference of current node.
  private T data; // stores data of current node
  private DoublyLinkedNode<T> next; // stores next reference of current node.

  /**
   * Initializes a new node with the specified information
   * 
   * @param previous - node, which comes before this node in a doubly linked list
   * @param data     - to be stored within this node.
   * @param next     - node, which comes after this node in a doubly linked list
   */
  public DoublyLinkedNode(DoublyLinkedNode<T> previous, T data, DoublyLinkedNode<T> next) {
    // if data is a null reference, throw an exception
    if(data == null) {
      throw new IllegalArgumentException("data is null!");
    }
    // otherwise set all values to the inputed values
    this.previous = previous;
    this.data = data;
    this.next = next;
  }
  
  public DoublyLinkedNode(T data) {
    // if data is a null reference, throw an exception
    if(data == null) {
      throw new IllegalArgumentException("data is null!");
    }
    // set data to the inputed data and previous & next to null.
    this.data = data;
    this.previous = null;
    this.next = null;
  }

  /**
   * Accessor method for this node's data
   * 
   * @return the data stored within this node
   */
  public T getData() {
    return data;
  }

  /**
   * Accessor method for this node's next reference
   * 
   * @return the reference to the node that comes after this one in the list, or
   *         null when this is the last node in that list.
   */
  public DoublyLinkedNode<T> getNext() {
    return next;
  }

  /**
   * Mutator method for this node;s next node reference
   * 
   * @param next - node, which comes after this node in a doubly linked list.
   */
  public void setNext(DoublyLinkedNode<T> next) {
    this.next = next;
  }

  /**
   * Accessor method for this node's previous node reference
   * 
   * @return reference to the node that comes before this one in the list or null
   *         when this is the first node in the list.
   */
  public DoublyLinkedNode<T> getPrevious() {
    return previous;
  }

  /**
   * Mutator method for this node's previous node reference
   * 
   * @param previous - node, which comes before this node in a doubly linked list.
   */
  public void setPrevious(DoublyLinkedNode<T> previous) {
    this.previous = previous;
  }

}
