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
  //
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
    head = null;
    tail = null;
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
    // set everything back to null/0.
    head = null;
    tail = null;
    size = 0;
    redCount = 0;
    blueCount = 0;
    yellowCount = 0;
  }

  /**
   * Adds a new blueBlock at the end of this list. If the list is empty, insert
   * the blueBlock at the head of the list. Otherwise, insert the newNode at the
   * end of the list
   * 
   * @param blueBlock - new element to be added to this list.
   * @throws IllegalArgumentException - if the blueBlock is null or if the color
   *                                  of the specific blueBlock is not equal to
   *                                  Color.BLUE
   */
  public void addBlue(MegaBlock blueBlock) {
    // if the block is invalid, throw the corresponding exception.
    if(blueBlock == null) {
      throw new IllegalArgumentException("The block cannot be null!");
    }

    if(!blueBlock.getColor().equals(Color.BLUE)) {
      throw new IllegalArgumentException("The block must be a blue block.");
    }

    LinkedMegaBlock linkedBlueBlock = new LinkedMegaBlock(blueBlock);
    // if the list is empty, set the head to the new blue block.
    if(head == null || tail == null) {
      head = linkedBlueBlock;
    } else {
      // otherwise, set the current tail's next to the new blue block
      tail.setNext(linkedBlueBlock);
    }
    // then set the tail to the blue block.
    tail = linkedBlueBlock;
    // increment size of the list and the number of blue blocks
    blueCount++;
    size++;
  }

  /**
   * Adds a new object at the head of this list. If the list is empty, insert the
   * redBlock at the head of the list.
   * 
   * @param redBlock - new element to be added to this list.
   * @throws IllegalArgumentException - if the redBlock is null or if its color
   *                                  does to equal to Color.RED
   */
  public void addRed(MegaBlock redBlock) {
    // if the block is invalid, throw the correct exception
    if(redBlock == null) {
      throw new IllegalArgumentException("Block cannot be null.");
    }

    if(!redBlock.getColor().equals(Color.RED)) {
      throw new IllegalArgumentException("This block must be a red block.");
    }

    LinkedMegaBlock newNode = new LinkedMegaBlock(redBlock);
    // if the list is empty, set both head and tail to the new red block.
    if(head == null) {
      head = newNode;
      tail = newNode;
    } else {
      // else, set the head to the new red block.
      newNode.setNext(head);
      head = newNode;
    }

    // increment the size of the list and the number of red blocks
    redCount++;
    size++;
  }

  /**
   * Adds the provided yellowBLock at the position index in this list. Traverse
   * the list until the currIndex = index-1.
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
    // if the block or the index is invalid, throw the corresponding exception.
    if(yellowBlock == null) {
      throw new IllegalArgumentException("Block cannot be null");
    }

    if(!yellowBlock.getColor().equals(Color.YELLOW)) {
      throw new IllegalArgumentException("This block must be a yellow block");
    }

    if((index < redCount || index > size - blueCount)) {
      throw new IndexOutOfBoundsException("Index is out of bounds!");
    }

    LinkedMegaBlock runner = head;
    LinkedMegaBlock yellowLnkBlk = new LinkedMegaBlock(yellowBlock);
    // if the index is valid at 0, set the new yellow block to head and set head to
    // the new yellow block.
    if(index == 0) {
      yellowLnkBlk.setNext(head);
      head = yellowLnkBlk;

    } else {

      // get the LinkedMegaBlock before the index
      runner = this.getLinkedMegaBlock(index - 1);
      // append yellow block after runner
      yellowLnkBlk.setNext(runner.getNext());
      runner.setNext(yellowLnkBlk);

    }

    // set the current yellow block to the tail only if it is at the end of the
    // list.
    if(yellowLnkBlk.getNext() == null) {
      tail = yellowLnkBlk;
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
    // if the index is out of bounds, throw IndexOutOfBounds.
    if(index < 0 || index > size) {
      throw new IndexOutOfBoundsException("Index is out of bounds!");
    }
    LinkedMegaBlock runner = this.getLinkedMegaBlock(index);
    return runner.getBlock();
  }

  /**
   * Gets the LinkedMegaBlock at the given index
   * 
   * @param index - index of LinkedMegaBlock
   * @return LinkedMegaBlock at the given index
   */
  private LinkedMegaBlock getLinkedMegaBlock(int index) {
    int currIndex = 0;
    LinkedMegaBlock runner = head;
    // run through to find the block at index.
    while (currIndex != index) {
      runner = runner.getNext();
      currIndex++;
    }
    // then return the block at index.
    return runner;
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
    // if the index or the block is invalid, throw the correct exception
    if(block == null) {
      throw new IllegalArgumentException("Block cannot be null.");
    }
    if((index < 0 || index >= size())) {
      throw new IndexOutOfBoundsException("Index is out of bounds.");
    }

    LinkedMegaBlock runner = this.getLinkedMegaBlock(index);
    MegaBlock currBlock = runner.getBlock();
    // if the block to be set and the current block at index are not the same color,
    // do not set the block and throw an exception.
    if(!block.equals(currBlock)) {
      throw new IllegalArgumentException(
              "Blocks are not the same color. Can only replace with the same color.");
    }
    // otherwise set the block and return the block that used to be at index.
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
    // if the head block is invalid, throw the corresponding error.
    if(head == null) {
      throw new NoSuchElementException("This list does not contain a red block.");
    }
    if(head.getBlock() == null) {
      throw new NoSuchElementException("This list does not contain a red block.");
    }
    if(!head.getBlock().getColor().equals(Color.RED)) {
      throw new NoSuchElementException("The head of the list is not a red block. ");
    }

    LinkedMegaBlock previousHead = removeAt(0);
    // decrement the number of red blocks and the size of the list
    redCount--;
    size--;
    return previousHead.getBlock();
  }

  /**
   * Removes and returns the element at the tail of this list if it has a blue
   * color
   * 
   * @return - a reference to the removed element
   * @throws NoSuchElementException - if this list does not contain any blue block
   */
  public MegaBlock removeBlue() {
    // if the tail block is invalid, throw the corresponding error.
    if(tail == null) {
      throw new NoSuchElementException("This list doesn't contain any blocks");
    }
    if(tail.getBlock() == null) {
      throw new NoSuchElementException("This list does not contain any blue blocks.");
    }
    if(!tail.getBlock().getColor().equals(Color.BLUE)) {
      throw new NoSuchElementException("The tail of the list is not a blue block.");
    }

    LinkedMegaBlock previousTail = removeAt(size - 1);
    // decrement blueCount and size
    blueCount--;
    size--;
    return previousTail.getBlock();
  }

  /**
   * Removes the block at the given index.
   * 
   * @param index - index of block to be removed
   * @return block that was removed
   */
  private LinkedMegaBlock removeAt(int index) {
    LinkedMegaBlock removedLinkedMegaBlock;
    LinkedMegaBlock runner = getLinkedMegaBlock(index);
    // if the size is 1, set the removed LinkedMegaBlock to the head and the head
    // and tail to null.
    if(size == 1) {
      removedLinkedMegaBlock = head;
      head = null;
      tail = null;
    } else if(index == 0) {
      // if removing the head, set the removed LinkedMegaBlock to the head and the
      // head to head.getNext
      removedLinkedMegaBlock = head;
      head = head.getNext();
    } else {
      // otherwise, get the linkedMegaBlock at index-1
      runner = this.getLinkedMegaBlock(index - 1);
      // set the removed LinkedMegaBlock to the block at index (note runner.getNext)
      removedLinkedMegaBlock = runner.getNext();
      // set runner to the removed LinkedMegaBlock's next block. (aka
      // runner.getNext().getNext())
      runner.setNext(removedLinkedMegaBlock.getNext());
      // if removed the tail, set runner/current block to the tail.
      if(runner.getNext() == null) {
        tail = runner;
      }
    }
    return removedLinkedMegaBlock;
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
    // throw an IndexOutOfBounds exception if it is not a valid index
    if(index < redCount || index >= size - blueCount) {
      throw new IndexOutOfBoundsException("Cannot remove anything out of bounds! Index: " + index);
    }
    // remove the block at the index
    LinkedMegaBlock runner = removeAt(index);
    // update size and yellow block counts
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
    String value = "";
    if(head == null) {
      return value;
    }
    LinkedMegaBlock runner = head;
    while (runner != tail) {
      value += runner.toString();
      runner = runner.getNext();
    }
    value += tail.toString();
    return value;
  }

//  public static void main(String[] args) {
//    MegaBlock blueBlock1 = new MegaBlock(Color.BLUE, '1');
//    MegaBlock blueBlock2 = new MegaBlock(Color.BLUE, '2');
//    LinkedListMegaBlock test = new LinkedListMegaBlock();
//
//    test.addBlue(blueBlock1);
//    if(tail.getBlock().equals(blueBlock1)) {
//      System.out.println("tail is set correctly");
//    } else {
//      System.out.println("NOT SET CORRECTLY.");
//    }
//
//    if(head.getBlock().equals(blueBlock1)) {
//      System.out.println("head is set correctly");
//    } else {
//      System.out.println("NOT SET CORRECTLY");
//    }
//
//    test.removeBlue();
//    if(tail == null) {
//      System.out.println("Remove is working correctly.");
//    } else {
//      System.out.println("NOT REMOVING CORRECTLY");
//    }
//    if(head == null) {
//      System.out.println("head is also null");
//    } else {
//      System.out.println("NOT REMOVING CORRECTLY");
//    }
//    
//    System.out.println("add yellow ! -----------");
//    
//    MegaBlock yellow1 = new MegaBlock(Color.YELLOW, 'y');
//    
//    test.addYellow(0, yellow1);
//    if(tail.getBlock().equals(yellow1)) {
//      System.out.println("tail is set correctly");
//    } else {
//      System.out.println("NOT SET CORRECTLY.");
//    }
//
//    if(head.getBlock().equals(yellow1)) {
//      System.out.println("head is set correctly");
//    } else {
//      System.out.println("NOT SET CORRECTLY");
//    }    
//    
//    test.removeYellow(0);
//    if(tail == null) {
//      System.out.println("Remove is working correctly.");
//    } else {
//      System.out.println("NOT REMOVING CORRECTLY");
//    }
//    if(head == null) {
//      System.out.println("head is also null");
//    } else {
//      System.out.println("NOT REMOVING CORRECTLY");
//    }
//    
//    System.out.println("add red ! ==================");
//    
//    MegaBlock redBlock = new MegaBlock(Color.RED, 'r');
//    
//    test.addRed(redBlock);
//    if(tail.getBlock().equals(redBlock)) {
//      System.out.println("tail is set correctly");
//    } else {
//      System.out.println("NOT SET CORRECTLY.");
//    }
//
//    if(head.getBlock().equals(redBlock)) {
//      System.out.println("head is set correctly");
//    } else {
//      System.out.println("NOT SET CORRECTLY");
//    }    
//    
//    test.removeRed();
//    if(tail == null) {
//      System.out.println("Remove is working correctly.");
//    } else {
//      System.out.println("NOT REMOVING CORRECTLY");
//    }
//    if(head == null) {
//      System.out.println("head is also null");
//    } else {
//      System.out.println("NOT REMOVING CORRECTLY");
//    }
//    
//    System.out.println("---------Finished testing individual.---------");
//    
//    test = new LinkedListMegaBlock();
//    test.addYellow(0, yellow1);
//    test.addBlue(blueBlock1);
//    
//    if(tail.getBlock().equals(blueBlock1)) {
//      System.out.println("tail is set correctly");
//    } else {
//      System.out.println("NOT SET CORRECTLY.");
//    }
//
//    if(head.getBlock().equals(yellow1)) {
//      System.out.println("head is set correctly");
//    } else {
//      System.out.println("NOT SET CORRECTLY");
//    }    
//    
//    test.removeYellow(0);
//    if(tail.getBlock().equals(blueBlock1)) {
//      System.out.println("Remove is working correctly.");
//    } else {
//      System.out.println("NOT REMOVING CORRECTLY");
//    }
//    if(head.getBlock().equals(blueBlock1)) {
//      System.out.println("head becomes tail when removing the head.");
//    } else {
//      System.out.println("NOT REMOVING CORRECTLY");
//    }
//    
//    System.out.println("--------Finished testing blue+yellow ---------");
//    MegaBlock yellowBlock = new MegaBlock(Color.YELLOW, 'e');
//    MegaBlock yellowBlock1 = new MegaBlock(Color.YELLOW, 'e');
//    test = new LinkedListMegaBlock();
//    test.addRed(redBlock);
//    test.addBlue(blueBlock2);
//    try {
//    test.addYellow(0, yellow1);
//    } catch(IndexOutOfBoundsException e) { System.out.println("Index out of bounds works...still");}
//    
//    try {
//    test.addYellow(test.size(), yellowBlock);
//    } catch(IndexOutOfBoundsException e) {
//      System.out.println("IOBE works at the tail end too...");
//    }
//    try {
//      test.addYellow(1, yellowBlock1);
//    } catch(IndexOutOfBoundsException e) {
//      System.out.println("this was not supposed to happen.");
//    }
//    try {
//      test.addYellow(1, null);
//    } catch (IllegalArgumentException e) {
//      System.out.println("caught the null pointer");
//    }
//    
//    try {
//      test.addYellow(1, redBlock);
//    } catch(IllegalArgumentException e) {
//      System.out.println("caught a red block trying to be added to a yellow block spot...");
//    }
//    
//    System.out.println("--------Test add yellow at different places ---------");
//    test.clear(); 
//    test = new LinkedListMegaBlock();
//    test.addRed(redBlock);
//    if(head.getBlock().equals(redBlock)) {
//      System.out.println("head is set correctly");
//    } else {
//      System.out.println("head is not set");
//    }
//    
//    if(tail.getBlock().equals(redBlock)) {
//      System.out.println("tail is set correctly");
//    } else {
//      System.out.println("tail is not set");
//      System.out.println(tail);
//      System.out.println(tail.getBlock());
//      
//    }
//  }
}
