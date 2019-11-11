import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           DrawingStack
//Files:           DrawingChange, StackADT, LinkedNode, DrawingStack, AsciiArtTester, Canvas, AsciiArtDriver
//Course:          300, Fall, and 2019
//
//Author:          (Ariel Fu)
//Email:           afu5@wisc.edu
//Lecturer's Name: Mouna Ayari Ben Hadj Kacem
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
 * This class represents the stack of DrawingChanges made
 * 
 * @author Ariel
 * 
 *
 **/
public class DrawingStack implements StackADT<DrawingChange>, Iterable<DrawingChange> {
  private LinkedNode<DrawingChange> head;
  private int size;

  /**
   * No argument constructor for DrawingStack class, it sets the size of the stack
   * to 0 and the head to null.
   * 
   */
  public DrawingStack() {
    head = null;
    size = 0; // optional?
  }

  /**
   * Constructor for DrawingStack class
   * 
   * @param top - LinkedNode of type DrawingChange that is at the top of the stack
   */
  public DrawingStack(LinkedNode<DrawingChange> top) {
    if(top == null) {
      return;
    }
    this.head = top;
    size++;
  }

  /**
   * Pushes an object onto the top of the stack
   * 
   * @param object - object to be pushed onto the top of the stack
   * @throws IllegalArgumentException if the input is null.
   */
//TODO: add @Override annotation!
//  @Override
  public void push(DrawingChange element) {
    if(element == null) {
      throw new IllegalArgumentException("The drawing change added to the Stack cannot be null.");
    }
    LinkedNode<DrawingChange> nextNode = new LinkedNode<DrawingChange>(element);
    head = nextNode;
    size++;
  }

  /**
   * Removes the head of the stack and returns the head of the stack
   * 
   * @return previous head of the stack
   * @throws EmptyStackException without error message if the stack is empty.
   */
//TODO: add @Override annotation!
  // @Override
  public DrawingChange pop() {
    if(isEmpty()) {
      throw new EmptyStackException();
    }
    LinkedNode<DrawingChange> previousHead = head;
    head = head.getNext();
    size--; // decrement the size then return the removed head/top of the stack
    return previousHead.getData();
  }

  /**
   * Returns the head of the stack without removing it
   * 
   * @return head of the stack / the top of the stack
   * @throws EmptyStackException - if the stack is empty
   */
  // TODO: add @Override annotation!
  // @Override
  public DrawingChange peek() {
    if(isEmpty()) {
      // throw new NoSuchElementException("The stack is empty.");
      throw new EmptyStackException();

    }
    return head.getData();
  }

  /**
   * Returns whether the stack is empty or not
   * 
   * @return true if the head is equal to null
   */
//TODO: add @Override annotation!
  // @Override
  public boolean isEmpty() {
    return head == null;
  }

  /**
   * Gets the size of the stack
   * 
   * @return size of the stack
   */
//TODO: add @Override annotation!
  // @Override
  public int size() {
    return size;
  }

  /**
   * Returns a new DrawingStackIterator object that starts at the top of the stack
   * 
   * @return DrawingStackIterator object that starts at the head of the stack
   */
//TODO: add @Override annotation!
//  @Override
  public Iterator<DrawingChange> iterator() {

    return new DrawingStackIterator<DrawingChange>(head);
  }

}
