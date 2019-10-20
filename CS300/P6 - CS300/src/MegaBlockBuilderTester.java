//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           MegaBlockBuilderTester
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
 * Tests methods made in other classes
 * 
 * @author Ariel
 *
 */
public class MegaBlockBuilderTester {

  /**
   * Tests MegaBlock's .equals(Object otherBlock)
   * 
   * @return true if this MegaBlock is equal to otherBlock, otherwise return
   *         false.
   */
  public static boolean testMegaBlockEquals() {
    MegaBlock testBlock = new MegaBlock(Color.RED, 'w');
    MegaBlock falseBlock = new MegaBlock(Color.BLUE, 'z');
    MegaBlock sameColor = new MegaBlock(Color.RED, 'e');
    // if the testBlock is NOT equal to itself, return false
    if(!testBlock.equals(testBlock)) {
      return false;
    }

    // if the testBlock is NOT equal to another MegaBlock with the same color,
    // return false
    if(!testBlock.equals(sameColor)) {
      return false;
    }

    // if the testBlock is equal to another MegaBlock with different colors, return
    // false
    if(testBlock.equals(falseBlock)) {
      return false;
    }

    // TODO: test non-megablock

    // if the testBlock is equal to null, return false
    if(testBlock.equals(null)) {
      return false;
    }
    return true;
  }

  /**
   * Returns a String representation of this MegaBlock Object
   * 
   * @return true if .toString matches with the result
   */
  public static boolean testMegaBlockToString() {
    //
    MegaBlock test = new MegaBlock(Color.YELLOW, '3');

    String result = "YELLOW 3";
    if(!test.toString().equals(result)) {
      return false;
    }

    result = "BLUE  ";
    test = new MegaBlock(Color.BLUE, ' ');
    if(!test.toString().equals(result)) {
      return false;
    }
    return true;
  }

  /**
   * Tests LinkedMegaBlock class
   * 
   * @return true if none of the tests return false
   */
  public static boolean testLinkedMegaBlock() {
    MegaBlock block = new MegaBlock(Color.RED, '5');
    LinkedMegaBlock test = new LinkedMegaBlock(block);
    LinkedMegaBlock next = new LinkedMegaBlock(block);
    MegaBlock otherBlock = new MegaBlock(Color.BLUE, '2');
    // tests getBlock, it should be equal to block
    if(!test.getBlock().equals(block)) {
      System.out.println("1");
      return false;
    }

    // test setBlock, test's block should be equal to otherBlock
    test.setBlock(otherBlock);
    if(!test.getBlock().equals(otherBlock)) {

      System.out.println("2");
      return false;
    }

    // test getNext, next should be null
    if(test.getNext() != null) {
      System.out.println("3");
      System.out.println(test.getNext());
      return false;
    }

    // toString with ->END
    String result = otherBlock.toString() + " -> END";
    if(!test.toString().equals(result)) {
      System.out.println("4");
      System.out.println(block.toString());
      System.out.println(test.toString());
      return false;
    }

    // test setNext
    test.setNext(next);
    if(!test.getNext().equals(next)) {
      System.out.println("5");
      return false;
    }

    // toString with ->
    result = otherBlock.toString() + " -> ";
    if(!test.toString().equals(result)) {
      System.out.println("6");

      System.out.println(otherBlock.toString());
      System.out.println(test.toString());
      return false;
    }

    // TODO: test set next to a different block when next points to a block
    // TODO: test set next to null when next points to a block
    // TODO: test set block to null
    // TODO: test different constructor

    // otherwise return true
    return true;

  }

  /**
   * Checks for the correctness of the LinkedListMegaBlock.addRed() method
   * 
   * @return true if added a red block to the head of the list
   */
  public static boolean testlinkedMegaBlockListAddRed() {
    MegaBlock block = new MegaBlock(Color.RED, 'e');
    LinkedListMegaBlock test = new LinkedListMegaBlock();
    test.addRed(block);
    if(test.size() != 1) {
      return false;
    }
    if(test.getRedCount() != 1) {
      return false;
    }
    if(test.getBlueCount() != 0) {
      return false;
    }
    if(test.getYellowCount() != 0) {
      return false;
    }
    return true;
  }

  /**
   * Checks for the correctness of the LinkedListMegaBlock.removeBlue() method
   * 
   * @return true if added a blue block to the end of the list.
   */
  public static boolean testLinkedListMegaBlockRemoveBlue() {

    MegaBlock block = new MegaBlock(Color.BLUE, 'e');
    LinkedListMegaBlock test = new LinkedListMegaBlock();
    test.addBlue(block);
    if(test.size() != 1) {
      return false;
    }
    if(test.getRedCount() != 0) {
      return false;
    }
    if(test.getBlueCount() != 1) {
      return false;
    }
    if(test.getYellowCount() != 0) {
      return false;
    }
    // remove the blue block
    test.removeBlue();
    if(test.size() != 0) {
      return false;
    }
    if(test.getRedCount() != 0) {
      return false;
    }
    if(test.getBlueCount() != 0) {
      return false;
    }
    if(test.getYellowCount() != 0) {
      return false;
    }
    return true;
  }

  /**
   * Tests add and remove yellow blocks
   * 
   * @return true if the size, counts, and color matches
   */
  public static boolean testLinkedListMegaBlockAddRemoveYellow() {

    MegaBlock block1 = new MegaBlock(Color.YELLOW, 'e');
    MegaBlock block2 = new MegaBlock(Color.YELLOW, 'e');
    MegaBlock block3 = new MegaBlock(Color.YELLOW, 'e');
    LinkedListMegaBlock test = new LinkedListMegaBlock();
    test.addYellow(0, block1);
    test.addYellow(1, block2); // expect an IndexOutOfBounds

    if(test.size() != 2) {
      return false;
    }
    if(test.getRedCount() != 0) {
      return false;
    }
    if(test.getBlueCount() != 0) {
      return false;
    }
    if(test.getYellowCount() != 2) {
      return false;
    }

    test.removeYellow(1);
    if(test.size() != 1) {
      return false;
    }
    if(test.getRedCount() != 0) {
      return false;
    }
    if(test.getBlueCount() != 0) {
      return false;
    }
    if(test.getYellowCount() != 1) {
      return false;
    }

    test.removeYellow(0);
    if(test.size() != 0) {
      return false;
    }
    if(test.getRedCount() != 0) {
      return false;
    }
    if(test.getBlueCount() != 0) {
      return false;
    }
    if(test.getYellowCount() != 0) {
      return false;
    }

    try {
      test.removeYellow(1);
    } catch (IndexOutOfBoundsException e) {
      return true;
    }

    return false;
  }

  /**
   * Tests .clear()
   * 
   * @return true if the list is empty after .clear() is used
   */
  public static boolean testLinkedListMegaBlockClear() {
    LinkedListMegaBlock list = new LinkedListMegaBlock();
    list.addYellow(0, new MegaBlock(Color.YELLOW, 'A'));
    list.addRed(new MegaBlock(Color.RED, 'B'));
    list.addYellow(list.size(), new MegaBlock(Color.YELLOW, 'C'));
    list.addYellow(2, new MegaBlock(Color.YELLOW, 'D'));
    list.addBlue(new MegaBlock(Color.BLUE, 'E'));

    if(list.size() != 5) {
      return false;
    }

    list.clear();
    return list.isEmpty();
  }

  /**
   * Tests add and remove red blocks
   * 
   * @return true if the size, color, and color counts match up
   */
  public static boolean testAddAndRemoveRed() {
    MegaBlock megaBlock = new MegaBlock(Color.RED, 'e');
    MegaBlock redBlock = new MegaBlock(Color.RED, 'a');
    LinkedListMegaBlock test = new LinkedListMegaBlock();
    test.addRed(megaBlock);
    if(test.size() != 1) {
      return false;
    }
    if(test.getRedCount() != 1) {
      return false;
    }

    test.addRed(redBlock);
    if(test.size() != 2) {
      return false;
    }
    if(test.getRedCount() != 2) {
      return false;
    }
    return true;

  }

  /**
   * Test add and remove blue blocks
   * 
   * @return true if the size, color, and color counts match up
   */
  public static boolean testAddAndRemoveBlue() {
    MegaBlock megaBlock = new MegaBlock(Color.BLUE, 'e');
    MegaBlock blueBlock = new MegaBlock(Color.BLUE, 'a');
    LinkedListMegaBlock test = new LinkedListMegaBlock();
    test.addBlue(megaBlock);
    if(test.size() != 1) {
      return false;
    }
    if(test.getBlueCount() != 1) {
      return false;
    }

    test.addBlue(blueBlock);
    if(test.size() != 2) {
      return false;
    }
    if(test.getBlueCount() != 2) {
      return false;
    }
    return true;

  }

  public static boolean testAddRed() {
    MegaBlock redBlock = new MegaBlock(Color.RED, 't');
    LinkedListMegaBlock test = new LinkedListMegaBlock();
    test.addRed(redBlock);
    if(test.size() != 1) {
      return false;
    }
    if(test.getRedCount() != 1) {
      return false;
    }
    return true;
  }

  /**
   * Tests Gradescope's .addXXX() methods
   * 
   * @return true if the .toString matches with expected result.
   */
  public static boolean testAddXXX() {
    LinkedListMegaBlock list = new LinkedListMegaBlock();
    // add yellow
    // size: 1, Y: 1, R: 0, B: 0
    list.addYellow(0, new MegaBlock(Color.YELLOW, 'A'));

    if(list.size() != 1) {
      return false;
    }
    if(list.getYellowCount() != 1) {
      return false;
    }
    if(list.getRedCount() != 0) {
      return false;
    }
    if(list.getBlueCount() != 0) {
      return false;
    }
    // add Red
    // size: 2, Y: 1, R: 1, B: 0
    list.addRed(new MegaBlock(Color.RED, 'B'));

    if(list.size() != 2) {
      return false;
    }
    if(list.getYellowCount() != 1) {
      return false;
    }
    if(list.getRedCount() != 1) {
      return false;
    }
    if(list.getBlueCount() != 0) {
      return false;
    }
    // add yellow
    // size: 3, Y: 2, R: 1, B: 0
    list.addYellow(list.size(), new MegaBlock(Color.YELLOW, 'C'));

    if(list.size() != 3) {
      return false;
    }
    if(list.getYellowCount() != 2) {
      return false;
    }
    if(list.getRedCount() != 1) {
      return false;
    }
    if(list.getBlueCount() != 0) {
      return false;
    }

    // add another yellow
    // size: 5, Y: 3, R: 2, B: 0
    list.addYellow(2, new MegaBlock(Color.YELLOW, 'D'));

    if(list.size() != 4) {
      return false;
    }
    if(list.getYellowCount() != 3) {
      return false;
    }
    if(list.getRedCount() != 1) {
      return false;
    }
    if(list.getBlueCount() != 0) {
      return false;
    }

    // add a blue block
    // size: 6, Y: 3, R: 2, B: 1
    list.addBlue(new MegaBlock(Color.BLUE, 'E'));

    if(list.size() != 5) {
      return false;
    }
    if(list.getYellowCount() != 3) {
      return false;
    }
    if(list.getRedCount() != 1) {
      return false;
    }
    if(list.getBlueCount() != 1) {
      return false;
    }

    String result = "RED B -> YELLOW A -> YELLOW D -> YELLOW C -> BLUE E -> END";
    if(list.toString().equals(result)) {
      return true;
    }
    System.out.println("result: " + result);
    System.out.println("toString(): " + list.toString());
    return false;
  }

  /**
   * Tests .get(int index)
   * 
   * @return true if .get(int index) returns the block at index.
   */
  public static boolean testGetBlock() {
    MegaBlock yellowBlock1 = new MegaBlock(Color.YELLOW, 'U');
    MegaBlock yellowBlock2 = new MegaBlock(Color.YELLOW, 'Q');
    MegaBlock blueBlock1 = new MegaBlock(Color.BLUE, 'K');
    MegaBlock yellowBlock3 = new MegaBlock(Color.YELLOW, 't');
    MegaBlock redBlock1 = new MegaBlock(Color.RED, 'b');
    MegaBlock redBlock2 = new MegaBlock(Color.RED, 'w');
    MegaBlock blueBlock2 = new MegaBlock(Color.BLUE, 'G');

    LinkedListMegaBlock test = new LinkedListMegaBlock();
    test.addYellow(0, yellowBlock1);
    test.addYellow(test.size(), yellowBlock2);
    test.addRed(redBlock2);
    test.addBlue(blueBlock1);
    test.addYellow(2, yellowBlock3);
    if(!test.get(2).equals(yellowBlock3)) {
      System.out.println(test.get(2));
      return false;
    }
    test.addRed(redBlock1);
    test.addBlue(blueBlock2);

    if(!test.get(0).equals(redBlock1)) {
      return false;
    }
    if(!test.get(test.size() - 1).equals(blueBlock2)) {
      return false;
    }
    if(!test.get(4).equals(yellowBlock2)) {
      System.out.println(test);
      System.out.println(test.get(4));
      return false;
    }

    if(test.getRedCount() != 2) {
      return false;
    }
    if(test.getBlueCount() != 2) {
      return false;
    }
    if(test.getYellowCount() != 3) {
      return false;
    }

    return true;
  }

  /**
   * Tests .set()
   * 
   * @return true if exceptions are thrown when necessary and if the blocks are
   *         replaced when using .set()
   */
  public static boolean testSetBlock() {
    MegaBlock yellowBlock1 = new MegaBlock(Color.YELLOW, 'U');
    MegaBlock yellowBlock2 = new MegaBlock(Color.YELLOW, 'Q');
    MegaBlock blueBlock1 = new MegaBlock(Color.BLUE, 'K');
    MegaBlock yellowBlock3 = new MegaBlock(Color.YELLOW, 't');
    MegaBlock redBlock1 = new MegaBlock(Color.RED, 'b');
    MegaBlock redBlock2 = new MegaBlock(Color.RED, 'w');
    MegaBlock blueBlock2 = new MegaBlock(Color.BLUE, 'G');

    LinkedListMegaBlock test = new LinkedListMegaBlock();

    test.addBlue(blueBlock2);
    test.addYellow(0, yellowBlock1);
    test.addRed(redBlock1);
    test.addYellow(2, yellowBlock3);
    test.set(1, yellowBlock2);

    if(!test.get(1).equals(yellowBlock2)) {
      System.out.println(test.get(1));
      return false;
    }

    try {
      test.set(0, blueBlock2);
      return false;
    } catch (IllegalArgumentException e) {
    }

    try {
      test.set(24, blueBlock2);
      return false;
    } catch (IndexOutOfBoundsException e) {
    }

    try {
      test.set(-1, blueBlock2);
      return false;
    } catch (IndexOutOfBoundsException e) {
    }

    try {
      test.set(2, null);
      return false;
    } catch (IllegalArgumentException e) {
    }

    return true;
  }

  /**
   * Tests the constructor of LinkedListMegaBlock
   * 
   * @return true if all values are set to 0 and the list is empty (size = 0)
   */
  public static boolean testConstructorLinkedList() {
    LinkedListMegaBlock test = new LinkedListMegaBlock();
    if(test.getBlueCount() != 0) {
      return false;
    }
    if(test.getRedCount() != 0) {
      return false;
    }
    if(test.getYellowCount() != 0) {
      return false;
    }
    if(test.size() != 0) {
      return false;
    }
    if(!test.isEmpty()) {
      return false;
    }
    return true;
  }

  /**
   * Tests removeAt method that is now private. SO remove this tester?
   * 
   * @return true if removeAt removes the block at the correct index.
   */
  public static boolean testRemoveAt() {
    LinkedListMegaBlock test;
    MegaBlock blue0 = new MegaBlock(Color.BLUE, '0');
    MegaBlock blue1 = new MegaBlock(Color.BLUE, '1');
    MegaBlock blue2 = new MegaBlock(Color.BLUE, '2');
    LinkedMegaBlock removed;
    test = new LinkedListMegaBlock();
    test.addBlue(blue0);
    removed = test.removeAt(0);
    if(removed.getBlock().getLabel() != blue0.getLabel()) {
      System.out.println("Failed 0,0");
      return false;
    }
    if(!test.toString().equals("")) {
      System.out.println("Failed 0,0");
      return false;
    }
    System.out.println(test.toString());

    test = new LinkedListMegaBlock();
    test.addBlue(blue0);
    test.addBlue(blue1);
    removed = test.removeAt(0);
    if(removed.getBlock().getLabel() != blue0.getLabel()) {
      System.out.println("Failed 1,0");
      return false;
    }
    System.out.println(test.toString());

    test = new LinkedListMegaBlock();
    test.addBlue(blue0);
    test.addBlue(blue1);
    removed = test.removeAt(1);
    if(removed.getBlock().getLabel() != blue1.getLabel()) {
      System.out.println("Failed 1,1");
      return false;
    }
    System.out.println(test.toString());

    test = new LinkedListMegaBlock();
    test.addBlue(blue0);
    test.addBlue(blue1);
    test.addBlue(blue2);

    removed = test.removeAt(0);
    if(removed.getBlock().getLabel() != blue0.getLabel()) {
      System.out.println("Failed 3,0");
      return false;
    }

    System.out.println(test.toString());
    test = new LinkedListMegaBlock();
    test.addBlue(blue0);
    test.addBlue(blue1);
    test.addBlue(blue2);
    removed = test.removeAt(1);
    if(removed.getBlock().getLabel() != blue1.getLabel()) {
      System.out.println("Failed 3,1");
      return false;
    }
    System.out.println(test.toString());

    test = new LinkedListMegaBlock();
    test.addBlue(blue0);
    test.addBlue(blue1);
    test.addBlue(blue2);
    removed = test.removeAt(2);
    if(removed.getBlock().getLabel() != blue2.getLabel()) {
      System.out.println("Failed 3,2");
      return false;
    }
    System.out.println(test.toString());

    // TODO - test 0,1,2

    return true;
  }

  /**
   * Prints out all the results from the test methods.
   * 
   * @param args - ?
   */
  public static void main(String[] args) {
    testRemoveAt();

    System.out.println("testMegaBlockEquals: " + testMegaBlockEquals());
    System.out.println("testMegaBlockToString: " + testMegaBlockToString());
    System.out.println("testLinkedMegaBlock: " + testLinkedMegaBlock());
    System.out.println("testLinkedMegaBlockListAddRed : " + testlinkedMegaBlockListAddRed());
    System.out.println("testLinkedListMegaBlockRemoveBlue: " + testLinkedListMegaBlockRemoveBlue());
    System.out.println(
            "testLinkedListMegaBlockAddRemoveYellow: " + testLinkedListMegaBlockAddRemoveYellow());
    System.out.println("testLinkedListMegaBlockClear: " + testLinkedListMegaBlockClear());
    System.out.println("testAddXXX: " + testAddXXX());
    System.out.println("testAddAndRemoveRed: " + testAddAndRemoveRed());
    System.out.println("testAddAndRemoveBlue " + testAddAndRemoveBlue());
    System.out.println("testGetBlock " + testGetBlock());
    System.out.println("testSetBlock: " + testSetBlock());
    System.out.println("testConstructorLinkedList: " + testConstructorLinkedList());
    System.out.println("testGetRed: " + testAddRed() );

  }
}
