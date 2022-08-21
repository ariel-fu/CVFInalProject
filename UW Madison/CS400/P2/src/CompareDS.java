//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           CompareDS.java
// Files:           BST.java, RBT.java, STADT.java, TestBST.java, TestRBT.java, 
//					CompareDS.java
// Course:          (CS400, Spring, 2020)
//
// Author:          (Ariel Fu)
// Email:           (afu5@wisc.edu)
// Lecture Number: 001
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////

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
// Persons:         (NONE)
// Online Sources:  (NONE)
//               
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This class compares my BST to my RBT implementation
 * 
 * @author Ariel
 *
 */
public class CompareDS {

  /**
   * Runs the BST work method and the RBT work method
   * 
   * @param args
   */
  public static void main (String[] args) {
    long runBST = runBST();
    long runRBT = runRBT();
    String workDone = "The work done by either the BST or the RBT is as follows"
              + "/r/t" + "1. insert 100 Nodes/ key-value pairs" + "/r/t"
              + "2. Get the last element inserted in the BST or RBT" + "/r/t"
              + "3. Call contains with a parameter of a key that is not in the RBT or the BST";
    // compare BST and RBT
    if (runBST > runRBT) {
      System.out.println("BST is faster than RBT by " + (runBST - runRBT));
    } else {
      System.out.println("RBT is faster than BST by  " + (runRBT - runBST));
    }
  }

  /**
   * Runs the BST for: inserting, getting, and contains
   * 
   * @return a long that is how long it took to insert, get, and search the BST
   */
  private static long runBST () {
    BST<Integer, Integer> bst = new BST<Integer, Integer>();
    long startTime = 0;
    long endTime = 0;
    long runTime = 0;
    // try inserting 100 Nodes/ key-value pairs
    try {
      startTime = System.nanoTime();
      for (int i = 0; i < 100; i++) {
        bst.insert(i, i);
      }
      endTime = System.nanoTime();
      runTime += (endTime - startTime);
    } catch (Exception e) {
      System.out.println("Exception thrown from insert in BST");
    }

    // try getting the final number that was inserted
    try {
      startTime = System.nanoTime();
      bst.get(49);

      endTime = System.nanoTime();
      runTime += (endTime - startTime);
    } catch (Exception e) {
      System.out.println("Exception thrown from get in BST");
    }

    // runs through to find the Node with key 400
    try {
      startTime = System.nanoTime();
      bst.contains(400);

      endTime = System.nanoTime();
      runTime += (endTime - startTime);
    } catch (Exception e) {
      System.out.println("Exception thrown from get in BST");
    }
    return runTime;
  }

  /**
   * Runs RBT's work: inserting, getting, and searching the RBT
   * 
   * @return a long that is how long it took to insert, get, and search the RBT
   */
  private static long runRBT () {
    long startTime = 0;
    long runTime = 0;
    long endTime = 0;
    RBT<Integer, Integer> rbt = new RBT<Integer, Integer>();
    // try inserting 100 Nodes/ key-value pairs
    try {
      startTime = System.nanoTime();
      for (int i = 0; i < 100; i++) {
        rbt.insert(i, i);
      }
      endTime = System.nanoTime();
    } catch (Exception e) {
      System.out.println("Exception thrown from insert in RBT");
    }

    runTime += (endTime - startTime);

    // try getting the final number that was inserted
    try {
      startTime = System.nanoTime();
      rbt.get(49);
      endTime = System.nanoTime();
      runTime += (endTime - startTime);
    } catch (Exception e) {
    }

    // runs through the RBT to find the Node with key 400
    try {
      startTime = System.nanoTime();
      rbt.contains(400);
      endTime = System.nanoTime();
      runTime += (endTime - startTime);
    } catch (Exception e) {
    }
    return runTime;
  }

}
