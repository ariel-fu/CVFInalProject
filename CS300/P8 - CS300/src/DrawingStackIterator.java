import java.util.Iterator;
import java.util.NoSuchElementException;

////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           DrawingStackIterator
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
 * This class represents an iterator to iterate over the DrawingStack
 * 
 * @author Ariel
 */
public class DrawingStackIterator<DrawingChange> implements Iterator<DrawingChange> {
  private LinkedNode<DrawingChange> current; // assumed to be the head of the stack

  /**
   * Constructor for the DrawingStackIterator class
   * 
   * @param head - first node in the stack (assumed)
   */
  public DrawingStackIterator(LinkedNode<DrawingChange> head) {
    current = head;
  }

  /**
   * Returns whether the stack is empty or not
   * 
   * @return true if current is equal to null
   */
//TODO: add @Override annotation!
  //@Override
  public boolean hasNext() {
    return current != null;
  }

  /**
   * Returns the next element in the stack
   * 
   * @return next element in the stack
   * @throws NoSuchElementException - if there are no more elements inside the
   *                                stack
   */
//TODO: add @Override annotation!
//  @Override
  public DrawingChange next() {
    if(!hasNext()) {
      throw new NoSuchElementException("There are no more elements in the stack.");
    }
    LinkedNode<DrawingChange> currChange = current;
    current = current.getNext();
    return currChange.getData();

  }

}
