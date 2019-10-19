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
    // TODO: get more tests?
    MegaBlock test = new MegaBlock(Color.YELLOW, '3');
    String result = "YELLOW 3";
    if(test.toString().equals(result)) {
      return true;
    }
    return false;
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
    String result = otherBlock.toString() + "->END";
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
    result = otherBlock.toString() + "->";
    if(!test.toString().equals(result)) {
      System.out.println("6");  

      System.out.println(otherBlock.toString());  
      System.out.println(test.toString());  
      return false;
    }

    // otherwise return true
    return true;

  }

  public static void main(String[] args) {
    System.out.println("testMegaBlockEquals: " + testMegaBlockEquals());
    System.out.println("testMegaBlockToString: " + testMegaBlockToString());
    System.out.println("testLinkedMegaBlock: " + testLinkedMegaBlock());
  }
}
