import java.util.NoSuchElementException;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           ProcessSchedulerTester
// Files:           CustomProcess, ProcessScheduler, WaitingProcessQueue, WaitingQueueADT
// Course:          300, Fall, and 2019
//
// Author:          (Ariel Fu)
// Email:           afu5@wisc.edu
// Lecturer's Name: Mouna Ayari Ben Hadj Kacem
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
 * This is a tester class for method in CustomProcess and WaitingProcessQueue
 * 
 * @author Ariel
 */
public class ProcessSchedulerTester {

  /**
   * checks the correctness of the insert operation implemented in the
   * WaitingProcessQueue class
   * 
   * @return true if method works as expected
   */
  public static boolean testInsertWaitingProcessQueue() {
    // insert at the root
    WaitingProcessQueue test = new WaitingProcessQueue();
    test.insert(new CustomProcess(5));
    if(test.size() != 1) {
      return false;
    }
    if(test.isEmpty()) {
      return false;
    }
    String string = "p1(5) ";
    if(!test.toString().equals(string)) {
      return false;
    }

    // swap the root
    test.insert(new CustomProcess(2));
    if(test.size() != 2) {
      System.out.println("4");
      return false;
    }
    if(test.isEmpty()) {
      System.out.println("5");
      return false;
    }
    string = "p2(2) p1(5) ";
    if(!test.toString().equals(string)) {
      System.out.println("6");
      return false;
    }

    // insert left subtree
    test.insert(new CustomProcess(6));
    if(test.size() != 3) {
      return false;
    }
    if(test.isEmpty()) {
      return false;
    }
    string = "p2(2) p1(5) p3(6) ";
    if(!test.toString().equals(string)) {
      return false;
    }

    // insert right subtree
    test.insert(new CustomProcess(7));
    if(test.size() != 4) {
      return false;
    }
    if(test.isEmpty()) {
      return false;
    }
    string = "p2(2) p1(5) p3(6) p4(7) ";
    if(!test.toString().equals(string)) {
      return false;
    }

    test.insert(new CustomProcess(4));
    string = "p2(2) p5(4) p3(6) p4(7) p1(5) ";
    if(!test.toString().equals(string)) {
      return false;
    }
    return true;
  }

  /**
   * 
   * checks the correctness of the removeBest operation implemented in the
   * WaitingProcessQueue class
   * 
   * @return true if method works as expected
   */
  public static boolean testRemoveBestWaitingProcessQueue() {
    WaitingProcessQueue test = new WaitingProcessQueue();
    // create a tree
    test.insert(new CustomProcess(5));
    test.insert(new CustomProcess(6));
    test.insert(new CustomProcess(7));
    test.insert(new CustomProcess(8));
    test.insert(new CustomProcess(9));
    test.insert(new CustomProcess(10));
    test.insert(new CustomProcess(11));

    // remove the root
    test.removeBest();
    if(test.size() != 6) {
      return false;
    }
    if(test.isEmpty()) {
      return false;
    }
    String string = "p7(6) p9(8) p8(7) p12(11) p10(9) p11(10) ";
    if(!test.toString().equals(string)) {
      System.out.println(test.toString());
      return false;
    }

    // remove an empty
    test = new WaitingProcessQueue();
    try {
      test.removeBest();
      return false;
    } catch (NoSuchElementException e) {

    }
    return true;
  }

  /**
   * Test the correctness of the peekBest operation implemented in the
   * WaitingProcessQueue class
   * 
   * @return true if the method works as expected
   */
  public static boolean testPeekBest() {
    WaitingProcessQueue test = new WaitingProcessQueue();
    // create a tree
    test.insert(new CustomProcess(5));
    test.insert(new CustomProcess(6));
    test.insert(new CustomProcess(7));
    test.insert(new CustomProcess(8));
    test.insert(new CustomProcess(9));
    test.insert(new CustomProcess(10));
    test.insert(new CustomProcess(11));

    // peek the root
    try {
      test.peekBest();
    } catch (NoSuchElementException e) {
      return false;
    }
    if(test.size() != 7) {
      return false;
    }
    String string = "p13(5) p14(6) p15(7) p16(8) p17(9) p18(10) p19(11) ";
    if(!test.toString().equals(string)) {
      return false;
    }
    // peek an empty
    test = new WaitingProcessQueue();
    try {
      test.peekBest();
      return false;
    } catch (NoSuchElementException e) {
    }
    return true;

  }

  /**
   * Tests compareTo method in CustomProcess
   * 
   * @return true if it works as expected
   */
  public static boolean testCompareTo() {
    // test equal burst time
    CustomProcess x = new CustomProcess(10);
    CustomProcess y = new CustomProcess(10);
    if(x.compareTo(y) >= 0) {
      return false;
    }
    // test greater burst time
    x = new CustomProcess(9);
    y = new CustomProcess(12);
    if(x.compareTo(y) >= 0) {
      return false;
    }
    // test smaller burst time
    x = new CustomProcess(12);
    y = new CustomProcess(9);
    if(x.compareTo(y) <= 0) {
      return false;
    }

    return true;
  }

  /**
   * Runs and prints out all the results from the tester method
   * 
   * @param args
   */
  public static void main(String[] args) {
    System.out.println(testInsertWaitingProcessQueue());
    System.out.println(testRemoveBestWaitingProcessQueue());
    System.out.println(testPeekBest());
    System.out.println(testCompareTo());
  }

}
