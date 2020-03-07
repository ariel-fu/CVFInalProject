import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           TestRBT.java
//Files:           BST.java, RBT.java, STADT.java, TestBST.java, TestRBT.java, CompareDS.java
//Course:          (CS400, Spring, 2020)
//
//Author:          (Ariel Fu)
//Email:           (afu5@wisc.edu)
//Lecture Number: 001
//
////////////////////PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////

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
//Persons:         (NONE)
//Online Sources:  (NONE)
//
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;

// TODO: Add tests to test if a Red-Black tree 
// extension of BST is correct.  Mostly check node color and position

//@SuppressWarnings("rawtypes")
public class TestRBT {

  protected RBT<Integer, String> rbt;

  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    rbt = new RBT<Integer, String>();
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterEach
  void tearDown() throws Exception {
  }

  /**
   * CASE 123 Insert three values in sorted order and then check the root, left,
   * and right keys to see if RBT rebalancing occurred.
   * 
   */
  @Test
  void testRBT_001_insert_sorted_order_simple() {
    try {
      rbt.insert(10, "10");
      Assert.assertTrue(rbt.rootIsBlack());

      rbt.insert(20, "20");
      Assert.assertTrue(rbt.getKeyOfRightChildOf(10).equals(20));
      Assert.assertEquals(rbt.colorOf(20), RBT.RED);

      rbt.insert(30, "30"); // SHOULD CAUSE REBALANCING

      Assert.assertTrue(rbt.getKeyOfRightChildOf(20).equals(30));

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(20));

      Assert.assertEquals(rbt.getKeyOfLeftChildOf(20), Integer.valueOf(10));
      Assert.assertEquals(rbt.getKeyOfRightChildOf(20), Integer.valueOf(30));

      rbt.print();

    } catch (Exception e) {
      // e.printStackTrace();
      fail("Unexpected exception 001: " + e.getMessage());
    }
  }

  /**
   * CASE 321 Insert three values in reverse sorted order and then check the
   * root, left, and right keys to see if rebalancing occurred in the other
   * direction.
   */
  @Test
  void testRBT_002_insert_reversed_sorted_order_simple() {
    try {
      rbt.insert(30, "30");
      rbt.insert(20, "20");
      Assert.assertEquals(rbt.getKeyOfLeftChildOf(30), Integer.valueOf(20));
      rbt.insert(10, "10");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child
      Assert.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(20));
      Assert.assertEquals(rbt.getKeyOfLeftChildOf(20), Integer.valueOf(10));
      Assert.assertEquals(rbt.getKeyOfRightChildOf(20), Integer.valueOf(30));

    } catch (Exception e) {
      fail("Should not have any exceptions at 002: " + e.getMessage());
    }
  }

  /**
   * CASE 132 Insert three values so that rebalancing requires new key to be the
   * "new" root of the rebalanced tree.
   * 
   * Then check the root, left, and right keys to see if rebalancing occurred
   * correctly.
   */
  @Test
  void testRBT_003_insert_smallest_largest_middle_order_simple() {
    try {
      rbt.insert(10, "10");
      Assert.assertTrue(rbt.rootIsBlack());

      // insert a second node which should have a color of red
      rbt.insert(30, "30");

      Assert.assertTrue(rbt.getKeyOfRightChildOf(10).equals(30));
      Assert.assertEquals(rbt.colorOf(30), RBT.RED);

      rbt.insert(20, "20");
      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertTrue(rbt.getKeyOfRightChildOf(20).equals(30));

      Assert.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(20));

      Assert.assertEquals(rbt.getKeyOfLeftChildOf(20), Integer.valueOf(10));

    } catch (DuplicateKeyException e) {
      fail("Should not have any exceptions at 003: " + e.getMessage());
    } catch (IllegalNullKeyException e) {

      fail("Should not have any exceptions at 003: " + e.getMessage());
    } catch (KeyNotFoundException e) {
      fail("Should not have any exceptions at 003: " + e.getMessage());
    }

  }

  /**
   * CASE 312 Insert three values so that rebalancing requires new key to be the
   * "new" root of the rebalanced tree.
   * 
   * Then check the root, left, and right keys to see if rebalancing occurred
   * correctly.
   */
  @Test
  void testRBT_004_insert_largest_smallest_middle_order_simple() {
    try {
      rbt.insert(30, "30");
      Assert.assertTrue(rbt.rootIsBlack());

      // insert a second node which should have a color of red
      rbt.insert(10, "10");

      Assert.assertTrue(rbt.getKeyOfLeftChildOf(30).equals(10));
      Assert.assertEquals(rbt.colorOf(10), RBT.RED);

      rbt.insert(20, "20");
      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertTrue(rbt.getKeyOfRightChildOf(20).equals(30));

      Assert.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(20));

      Assert.assertEquals(rbt.getKeyOfLeftChildOf(20), Integer.valueOf(10));

    } catch (Exception e) {
      fail("Should not have any exceptions at 003: ");
    }

  }

  /**
   * Test inserting in a linear order
   */
  @Test
  void testRBT_insert_in_linear_order() {
    try {
      for (int i = 0; i < 5; i++) {
        rbt.insert(i, "i");
      }
      // should have rebalanced with 1 at the root
      assertTrue(rbt.getKeyAtRoot() == 1);
      assertTrue(rbt.getKeyOfLeftChildOf(1) == 0);
      // should have a recoloring that "pushed" down the color of the parent -->
      // black.
      assertTrue(rbt.colorOf(3) == rbt.BLACK);
      assertTrue(rbt.colorOf(0) == rbt.BLACK);
      assertTrue(rbt.colorOf(1) == rbt.BLACK);
      assertTrue(rbt.getKeyOfRightChildOf(1) == 3);
      assertTrue(rbt.getKeyOfLeftChildOf(3) == 2);
      assertTrue(rbt.colorOf(2) == rbt.RED);
      assertTrue(rbt.colorOf(4) == rbt.RED);
    } catch (Exception e) {
      fail("No exceptions from inserting in a linear order: " + e.getMessage());
    }
  }

  /**
   * Tests inserting a null key
   */
  @Test
  void testRBT_insert_null_key() {
    try {
      rbt.insert(null, "ew");
    } catch (IllegalNullKeyException e) {
    } catch (Exception e) {
      fail("No exception other than the IllegalNullKey should be thrown");
    }
  }

  /**
   * Tests inserting a duplicate key
   */
  @Test
  void testRBT_insert_duplicate() {
    try {
      // insert some values into the RBT
      for (int i = 0; i < 10; i++) {
        rbt.insert(i, i + "");
      }
      // insert the duplicate into the RBT
      rbt.insert(1, 1 + "1");
    } catch (DuplicateKeyException e) {

    } catch (Exception e) {
      fail("only DuplicateKeyException should have been thrown");
    }

  }

  /**
   * Tests a right rotate, then a left rotate --> TNR
   */
  @Test
  void testRBT_rebalance_rightRotate_leftRotate() {
    try {
      rbt.insert(10, "10");
      rbt.insert(30, "30");
      rbt.insert(20, "20");
      assertTrue(rbt.getKeyAtRoot() == 20);
      assertTrue(rbt.getKeyOfLeftChildOf(20) == 10);
      assertTrue(rbt.getKeyOfRightChildOf(20) == 30);
      assertTrue(rbt.colorOf(30) == rbt.RED); // uncle
      assertTrue(rbt.colorOf(20) == rbt.BLACK);
      assertTrue(rbt.colorOf(10) == rbt.RED);
    } catch (Exception e) {
      fail("No exceptions, rightRotate then a leftRotate is completely valid");
    }
  }

  /**
   * Recolors and then does a TNR
   */
  @Test
  void testRBT_rebalance_color_of_uncle() {
    // if the uncle is a red, can't we just do a recoloring? (instead of a
    // tri-node-restructuring)
    try {
      rbt.insert(20, "20");
      rbt.insert(10, "10");
      rbt.insert(30, "30");
      rbt.insert(5, "5");
      // everything should be recolored to black except for 5
      assertTrue(rbt.colorOf(20) == rbt.BLACK);
      assertTrue(rbt.colorOf(10) == rbt.BLACK);
      assertTrue(rbt.colorOf(30) == rbt.BLACK);
      assertTrue(rbt.colorOf(5) == rbt.RED);

      rbt.insert(0, "0");

      // should have done a tri-node-restructuring so 5 is the new "root" of the
      // subtree between 0, 5, 10
      assertTrue(rbt.getKeyOfLeftChildOf(20) == 5);
      assertTrue(rbt.getKeyOfLeftChildOf(5) == 0);
      assertTrue(rbt.getKeyOfRightChildOf(5) == 10);

    } catch (Exception e) {
      fail("no exceptions this time " + e.getMessage());
    }
  }

  /**
   * Tests a left rotate, then a right rotate --> TNR
   */
  @Test
  void testRBT_rebalance_leftRotate_rightRotate() {
    try {
      rbt.insert(30, "30");
      rbt.insert(10, "10");
      rbt.insert(20, "20");

      assertTrue(rbt.getKeyAtRoot() == 20);
      assertTrue(rbt.getKeyOfLeftChildOf(20) == 10);
      assertTrue(rbt.getKeyOfRightChildOf(20) == 30);
    } catch (Exception e) {
      fail("No exceptions, leftRotate then a rightRotate should be good to go");
    }
  }

  /**
   * Test a recoloring of two nodes --> the root and its left, right child
   */
  @Test
  void testRBT_recoloringSimple() {
    try {
      rbt.insert(20, "20");
      rbt.insert(10, "10");
      rbt.insert(30, "30");

      rbt.insert(40, "40");

      // the root, left child, and right child should be black
      assertTrue(rbt.colorOf(20) == rbt.BLACK);
      assertTrue(rbt.colorOf(10) == rbt.BLACK);
      assertTrue(rbt.colorOf(30) == rbt.BLACK);
      // the newly inserted node should stay red
      assertTrue(rbt.colorOf(40) == rbt.RED);
    } catch (Exception e) {
      fail("No messages should be thrown during a recoloring");
    }

  }

  /**
   * Test insert a lot, then a print
   */
  @Test
  void testRBT_insert() {
    try {
      for (int i = 0; i < 10; i++) {
        rbt.insert(i, "i: " + i);
      }

      rbt.print();
    } catch (Exception e) {
      fail("No exceptions thrown from print");
    }

  }

  /**
   * Tests inserting with a red parent and a black sibling
   */
  @Test
  void testRBT_insert_redParent_blackSibiling() {
    try {
      rbt.insert(20, "20");
      rbt.insert(10, "10");
      rbt.insert(30, "30");
      rbt.insert(350, "350");
      rbt.insert(25, "25");
      rbt.insert(5, "5");
      rbt.insert(15, "15");
      rbt.insert(0, "0");
      assertTrue(rbt.getKeyAtRoot() == 20);
      assertTrue(rbt.colorOf(10) == rbt.RED);
      assertTrue(rbt.colorOf(20) == rbt.BLACK);
      assertTrue(rbt.colorOf(30) == rbt.BLACK);
      assertTrue(rbt.colorOf(5) == rbt.BLACK);
      assertTrue(rbt.colorOf(15) == rbt.BLACK);
      assertTrue(rbt.colorOf(0) == rbt.RED);

    } catch (Exception e) {
      fail("no exception.." + e.getMessage());
    }
  }

  /**
   * Tests a simple insert and a get
   */
  @Test
  void testRBT_insert_get() {
    try {
      rbt.insert(20, "20");
      assertTrue(rbt.get(20).equals("20"));
    } catch (Exception e) {
      fail("Insert and get should not have exceptions");
    }
  }

  /**
   * Tests a simple insert and contains
   */
  @Test
  void testRBT_insert_contains() {

    try {
      rbt.insert(20, "20");
      assertTrue(rbt.contains(20));
    } catch (Exception e) {
      fail("Exception should not be thrown from this contains method.");
    }
  }

  /**
   * Tests insert more nodes and then get
   */
  @Test
  void testRBT_insert_more_get() {

    try {
      rbt.insert(20, "20");
      rbt.insert(30, "30");
      rbt.insert(40, "40");
      rbt.insert(10, "10");
      rbt.get(20);
    } catch (KeyNotFoundException e) {

    } catch (Exception e) {
      fail("no exceptions should be thrown" + e.getMessage());
    }

  }

  /**
   * Should return the correct levelorder
   */
  @Test
  void testRBT_implement_test09() {
    try {
      rbt.insert(7, "7");
      rbt.insert(2, "2");
      rbt.insert(8, "8");
      rbt.insert(0, "0");
      rbt.insert(421, "421");

      List<Integer> levelOrder = rbt.getLevelOrderTraversal();
      System.out.println(levelOrder);
    } catch (Exception e) {
      fail("why is it not 7,2,8,0,421?");
    }
  }

  /**
   * Should recolor everything but 450
   */
  @Test
  void testRBT_insert4_uncle_red() {
    try {
      rbt.insert(20, "20");
      rbt.insert(30, "30");
      rbt.insert(0, "0");
      rbt.insert(450, "450");
      assertTrue(rbt.getKeyAtRoot() == 20);
      assertTrue(rbt.colorOf(20) == rbt.BLACK);
      assertTrue(rbt.colorOf(30) == rbt.BLACK);
      assertTrue(rbt.colorOf(450) == rbt.RED);
      assertTrue(rbt.colorOf(0) == rbt.BLACK);
    } catch (Exception e) {
      fail("no exceptions: " + e.getMessage());
    }
  }

  /**
   * Catch an UnsupportedOperationException from the remove method
   */
  @Test
  void testRBT_remove_exception() {
    try {
      rbt.remove(0);
    } catch (UnsupportedOperationException e) {

    } catch (Exception e) {
      fail("Only UnsupportedOperationException is going to be thrown");
    }
  }

  /**
   * Insert so it does a right rotate
   */
  @Test
  void testRBT_left_left() {
    try {
      rbt.insert(20, "20");
      rbt.insert(10, "10");
      rbt.insert(5, "5");
      assertTrue(rbt.getKeyAtRoot() == 10);
      assertTrue(rbt.getKeyOfLeftChildOf(10) == 5);
      assertTrue(rbt.getKeyOfRightChildOf(10) == 20);

      assertTrue(rbt.colorOf(10) == rbt.BLACK);
      assertTrue(rbt.colorOf(5) == rbt.RED);
      assertTrue(rbt.colorOf(20) == rbt.RED);
    } catch (Exception e) {
      fail("No exceptions from left, left");
    }
  }

  /**
   * Insert so it does a left rotate then a right rotate
   */
  @Test
  void testRBT_left_right() {
    try {
      rbt.insert(20, "20");
      rbt.insert(5, "5");
      rbt.insert(10, "10");

      assertTrue(rbt.getKeyAtRoot() == 10);
      assertTrue(rbt.getKeyOfLeftChildOf(10) == 5);
      assertTrue(rbt.getKeyOfRightChildOf(10) == 20);

      assertTrue(rbt.colorOf(10) == rbt.BLACK);
      assertTrue(rbt.colorOf(5) == rbt.RED);
      assertTrue(rbt.colorOf(20) == rbt.RED);
    } catch (Exception e) {
      fail("No exceptions from left, left");
    }
  }

  /**
   * Insert so it does a left rotate
   */
  @Test
  void testRBT_right_right() {
    try {
      rbt.insert(5, "5");
      rbt.insert(10, "10");
      rbt.insert(20, "20");

      assertTrue(rbt.getKeyAtRoot() == 10);
      assertTrue(rbt.getKeyOfLeftChildOf(10) == 5);
      assertTrue(rbt.getKeyOfRightChildOf(10) == 20);

      assertTrue(rbt.colorOf(10) == rbt.BLACK);
      assertTrue(rbt.colorOf(5) == rbt.RED);
      assertTrue(rbt.colorOf(20) == rbt.RED);
    } catch (Exception e) {
      fail("No exceptions from left, left");
    }
  }

  /**
   * Insert so it does a right rotate then a left rotate
   */
  @Test
  void testRBT_right_left() {
    try {
      rbt.insert(5, "5");
      rbt.insert(20, "20");
      rbt.insert(10, "10");

      assertTrue(rbt.getKeyAtRoot() == 10);
      assertTrue(rbt.getKeyOfLeftChildOf(10) == 5);
      assertTrue(rbt.getKeyOfRightChildOf(10) == 20);

      assertTrue(rbt.colorOf(10) == rbt.BLACK);
      assertTrue(rbt.colorOf(5) == rbt.RED);
      assertTrue(rbt.colorOf(20) == rbt.RED);
    } catch (Exception e) {
      fail("No exceptions from left, left");
    }
  }

  /**
   * Tests using the get method with a valid input
   */
  @Test
  void testRBT_getValid() {
    try {
      for (int i = 0; i < 10; i++) {
        rbt.insert(i, "" + i);
      }

      assertTrue(rbt.get(1).equals("1"));
    } catch (Exception e) {
      fail("No exceptions from a valid get: " + e.getMessage());
    }
  }

  /**
   * Tests get that does not have a key in the RBT
   */
  @Test
  void testRBT_get_not_in_RBT() {
    try {
      for (int i = 0; i < 10; i++) {
        rbt.insert(i, i + "");
      }
      rbt.get(20);
    } catch (KeyNotFoundException e) {

    } catch (Exception e) {
      fail("No exceptions from an invalid get other than a KeyNotFound: "
          + e.getMessage());
    }
  }

  /**
   * Tests numKeys() to see if it returns the correct size after every insert.
   */
  @Test
  void testRBT_size() {
    try {
      for (int i = 0; i < 50; i++) {
        rbt.insert(i, i + "");
        // numKeys starts at 1
        assertTrue(rbt.numKeys() == i + 1);
      }

    } catch (Exception e) {
      fail("No exceptions from the numKeys: " + e.getMessage());
    }
  }

  // TODO: Add your own tests

  // Add tests to make sure that rebalancing occurs even if the
  // tree is larger. Does it maintain it's balance?
  // Does the height of the tree reflect it's actual height
  // Use the traversal orders to check.

  // Can you insert many and still "get" them back out?

  // Does delete work? Does the tree maintain balance when a key is deleted?
  // If delete is not implemented, does calling it throw an
  // UnsupportedOperationException

// copyright Deb Deppeler, all rights reserved, DO NOT SHARE

}