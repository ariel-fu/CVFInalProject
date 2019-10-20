import java.util.NoSuchElementException;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           LinkedListMegaBlock
// Files:           MegaBlock, LinkedMegaBlock, LinkedListMegaBlock, Color, MegaBlockBuilderTester
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
 * This class models and implements a linked list of MegaBlock objects
 * 
 * @author Ariel
 *
 */
public class LinkedListMegaBlock {
  private LinkedMegaBlock head; // head of this list
  private LinkedMegaBlock tail; // tail of this list
  private int size; // size of this list (total number of megablocks stored in this list)
  private int redCount; // number of RED megablocks stored in this list
  private int yellowCount; // number of YELLOW megablocks stored in this list
  private int blueCount; // number of BLUE megablocks stored in this list

  /**
   * Creates an empty linked list of mega blocks
   */
  public LinkedListMegaBlock() {

    size = 0;
    redCount = 0;
    yellowCount = 0;
    blueCount = 0;
  }

  /**
   * Returns true if this list is empty, and false otherwise.
   * 
   * @return true if this list is empty
   */
  public boolean isEmpty() {
    if(size == 0) {
      return true;
    }
    return false;
  }

  /**
   * Returns the size of this list.
   * 
   * @return size of the list.
   */
  public int size() {
    return size;
  }

  /**
   * Removes all of the elements from this list.
   */
  public void clear() {
    head = null;
    tail = null;
    size = 0;
    redCount = 0;
    blueCount = 0;
    yellowCount = 0;
  }

  /**
   * Adds a new blueBlock at the end of this list.
   * 
   * @param blueBlock - new element to be added to this list.
   * @throws IllegalArgumentException - if the blueBlock is null or if the color
   *                                  of the specific blueBlock is not equal to
   *                                  Color.BLUE
   */
  public void addBlue(MegaBlock blueBlock) {
    if(blueBlock == null) {
      throw new IllegalArgumentException("The block cannot be null!");
    }

    if(!blueBlock.getColor().equals(Color.BLUE)) {
      throw new IllegalArgumentException("The block must be a blue block.");
    }

    LinkedMegaBlock linkedBlueBlock = new LinkedMegaBlock(blueBlock);
    if(head == null) {
      head = linkedBlueBlock;
      tail = linkedBlueBlock;
    } else {
      tail.setNext(linkedBlueBlock);
      tail = linkedBlueBlock;
    }
    // increment size of the list and the number of blue blocks
    blueCount++;
    size++;
  }

  /**
   * Adds a new object at the head of this list
   * 
   * @param redBlock - new element to be added to this list.
   * @throws IllegalArgumentException - if the redBlock is null or if its color
   *                                  does to equal to Color.RED
   */
  public void addRed(MegaBlock redBlock) {

    if(redBlock == null) {
      throw new IllegalArgumentException("Block cannot be null.");
    }

    if(!redBlock.getColor().equals(Color.RED)) {
      throw new IllegalArgumentException("This block must be a red block.");
    }
    LinkedMegaBlock runner = head;
    LinkedMegaBlock newNode = new LinkedMegaBlock(redBlock);
    if(head == null) {
      head = newNode;

    } else {

      newNode.setNext(head);
      head = newNode;
      if(tail == null) {
        tail = newNode;
      }
    }
    // increment the size of the list and the number of red blocks
    redCount++;
    size++;
  }

  /**
   * Adds the provided yellowBLock at the position index in this list.
   * 
   * @param index       - index at which the specified yellow block is to be
   *                    inserted
   * @param yellowBlock - new element to be added to this list
   * @throws IllegalArgumentException  - if the yellowBlock is null or if it does
   *                                   not have a Color.Yellow color
   * @throws IndexOutOfBoundsException - if the index is out of the range reserved
   *                                   for yellow blocks (index < redCount ||
   *                                   index > size - blueCount)
   */
  public void addYellow(int index, MegaBlock yellowBlock) {
    if(yellowBlock == null) {
      throw new IllegalArgumentException("Block cannot be null");
    }

    if(!yellowBlock.getColor().equals(Color.YELLOW)) {
      throw new IllegalArgumentException("This block must be a yellow block");
    }

    if((index < redCount || index > size - blueCount)) {
      throw new IndexOutOfBoundsException("Index is out of bounds!");
    }
    int currIndex = 0;
    LinkedMegaBlock runner = head;
    LinkedMegaBlock setYellow = new LinkedMegaBlock(yellowBlock);
    if(currIndex == index) {
      setYellow.setNext(head);
      head = setYellow;
    } else {

      while (currIndex != index - 1) {
        runner = runner.getNext();
        currIndex++;
      }
      if(head == null) {
        head = setYellow;
        tail = setYellow;
      } else {

        setYellow.setNext(runner.getNext());
        runner.setNext(setYellow);
        // if the tail is null or not a blue block, set it to the current yellow block.
        if(tail == null) {
          tail = setYellow;
        } else if(!tail.getBlock().getColor().equals(Color.BLUE)) {
          // set the current yellow block to the tail only if it is at the end of the
          // list.
          if(setYellow.getNext() == null) {
            tail = setYellow;
          }

        }
      }
    }
    // increment the size of the list and the number of yellow blocks.
    yellowCount++;
    size++;
  }

  /**
   * Returns the element stored at position index of this list without removing it
   * 
   * @param index - position within this list
   * @return the megablock object stored at position index of this list
   * @throws java.lang.IndexOutOfBoundsException - if the index is out of range
   *                                             (index < 0 || index >= size())
   */
  public MegaBlock get(int index) {
    int currIndex = 0;
    LinkedMegaBlock runner = head;

    while (currIndex != index) {
      runner = runner.getNext();
      currIndex++;
    }
    return runner.getBlock();
  }

  /**
   * Replaces the megablock at the specified position in this list with the
   * specified element if they have the same Color
   * 
   * @param index - index of the block to replace
   * @param block - element to be stored at the specified position
   * @throws IllegalArgumentException  - if object is null or is not equal to the
   *                                   megablock already at at index position
   * 
   * @throws IndexOutOfBoundsException - if the index is out of range (index < 0
   *                                   || index >= size())
   */
  public MegaBlock set(int index, MegaBlock block) {
    if(block == null) {
      throw new IllegalArgumentException("Block cannot be null.");
    }
    if((index < 0 || index >= size())) {
      throw new IndexOutOfBoundsException("Index is out of bounds.");
    }

    LinkedMegaBlock runner = head;
    int currIndex = 0;
    while (currIndex != index) {
      runner = runner.getNext();
      currIndex++;
    }

    MegaBlock currBlock = runner.getBlock();
    if(!block.equals(currBlock)) {
      throw new IllegalArgumentException(
              "Blocks are not the same color. Can only replace with the same color.");
    }

    runner.setBlock(block);

    return currBlock;
  }

  /**
   * Removes and returns the mega block at the head of this list if its color is
   * red
   * 
   * @return - a reference to the removed element
   * @throws NoSuchElementException - if this list does not contain any red mega
   *                                block
   */
  public MegaBlock removeRed() {
    if(head.getBlock() == null) {
      throw new NoSuchElementException("This list does not contain a red block.");
    }
    if(!head.getBlock().getColor().equals(Color.RED)) {
      throw new NoSuchElementException("The head of the list is not a red block. ");
    }

    MegaBlock previousHead = head.getBlock();
    // set the head to the block in front of the current head
    head = head.getNext();
    // decrement the number of red blocks and the size of the list
    redCount--;
    size--;
    return previousHead;
  }

  /**
   * Removes and returns the element at the tail of this list if it has a blue
   * color
   * 
   * @return - a reference to the removed element
   * @throws NoSuchElementException - if this list does not contain any blue block
   */
  public MegaBlock removeBlue() {
    if(tail.getBlock() == null) {
      throw new NoSuchElementException("This list does not contain any blue blocks.");
    }
    if(!tail.getBlock().getColor().equals(Color.BLUE)) {
      throw new NoSuchElementException("The tail of the list is not a blue block.");
    }
    int index = 0;
    LinkedMegaBlock previousTail = tail;
    LinkedMegaBlock runner = head;
    if(size == 1) {
      head = null;
      tail = null;
    } else {
      while (index != size - 2) {
        runner = runner.getNext();
        index++;
      }
      runner.setNext(runner.getNext().getNext());
      tail = runner;
    }
    blueCount--;
    size--;
    return previousTail.getBlock();
  }

  /**
   * Removes and returns the element stored at index position in this list
   * 
   * @param index - position of the element to remove in this list.
   * @return a reference to the removed element
   * @throws IndexOutOfBoundsException - if the index is out of range (index <
   *                                   redCount or index >= size - blueCount)
   */
  public MegaBlock removeYellow(int index) {
    if(index < redCount || index >= size - blueCount) {
      throw new IndexOutOfBoundsException("Cannot remove anything out of bounds! Index: " + index);
    }

    int currIndex = 0;
    LinkedMegaBlock runner = head;
    if(size == 1) {
      head = null;
      tail = null;
    } else {
      while (currIndex <= index - 2) {
        runner = runner.getNext();
        currIndex++;
      }
      // skip the block to be removed.
      runner.setNext(runner.getNext().getNext());
    }
    // decrement number of yellow blocks and list size
    yellowCount--;
    size--;

    return runner.getBlock();
  }

  /**
   * Returns the number of red mega bloks stored in this list
   * 
   * @return the redCount
   */
  public int getRedCount() {
    return redCount;
  }

  /**
   * Returns the number of yellow mega bloks stored in this list
   * 
   * @return the yellowCount
   */
  public int getYellowCount() {
    return yellowCount;
  }

  /**
   * Returns the number of blue mega bloks stored in this list
   * 
   * @return the blueCount
   */
  public int getBlueCount() {
    return blueCount;
  }

  /**
   * Returns a String representation of the content of this list. If this list is
   * empty, an empty String ("") will be returned.
   * 
   * @return a String representation of the contents of this list
   */
  public String toString() {
    if(size == 0) {
      return "";
    }
    int index = 0;
    String value = "";
    LinkedMegaBlock runner = head;
    while (index < size) {

      value += runner.toString();

      runner = runner.getNext();
      if(runner == null) {
        break;
      }
      index++;
    }
    return value;

  }
}
