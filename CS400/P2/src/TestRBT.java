import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

			// Assert.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(20));

			// Assert.assertEquals(rbt.getKeyOfLeftChildOf(20), Integer.valueOf(10));
			// Assert.assertEquals(rbt.getKeyOfRightChildOf(20), Integer.valueOf(30));

			rbt.print();

		} catch (Exception e) {
			// e.printStackTrace();
			fail("Unexpected exception 001: " + e.getMessage());
		}
	}

	/**
	 * CASE 321 Insert three values in reverse sorted order and then check the root,
	 * left, and right keys to see if rebalancing occurred in the other direction.
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

			// Assert.assertTrue(rbt.getKeyOfRightChildOf(20).equals(30));

			Assert.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(20));

			// Assert.assertEquals(rbt.getKeyOfLeftChildOf(20), Integer.valueOf(10));

		} catch (Exception e) {
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

	@Test
	void testRBT_insert_in_linear_order() {
		try {
			for (int i = 0; i < 5; i++) {
				rbt.insert(i, "i");
			}
		} catch (Exception e) {

		}

		Assert.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(1));
		try {
			Assert.assertEquals(rbt.getKeyOfLeftChildOf(1), Integer.valueOf(0));
		} catch (IllegalNullKeyException | KeyNotFoundException e) {
			fail("Should not throw an exception " + e.getMessage());
		}
	}

//	@Test
//	void testRBT_BSTInsert() {
//		try {
//			rbt.BSTInsert(rbt.root, 10, "10");
//			if (!rbt.getKeyAtRoot().equals(10))
//				fail("insert at root does not work");
//
//			rbt.BSTInsert(rbt.root, 20, "20");
//			if (!rbt.getKeyOfRightChildOf(10).equals(20))
//				fail("insert to right child of root does not work");
//
//			rbt.BSTInsert(rbt.root, 30, "30");
//			if (!rbt.getKeyAtRoot().equals(10))
//				fail("inserting 30 changed root");
//
//			if (!rbt.getKeyOfRightChildOf(20).equals(30))
//				fail("inserting 30 as right child of 20");
//
//			// IF rebalancing is working,
//			// the tree should have 20 at the root
//			// and 10 as its left child and 30 as its right child
//
//			Assert.assertEquals(rbt.getKeyAtRoot(), Integer.valueOf(10));
//			Assert.assertEquals(rbt.getKeyOfRightChildOf(10), Integer.valueOf(20));
//			Assert.assertEquals(rbt.getKeyOfRightChildOf(20), Integer.valueOf(30));
//
//			rbt.print();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			fail("Unexpected exception 001: " + e.getMessage());
//		}
//
//	}
	// TODO: Add your own tests

	// Add tests to make sure that rebalancing occurs even if the
	// tree is larger. Does it maintain it's balance?
	// Does the height of the tree reflect it's actual height
	// Use the traversal orders to check.

	// Can you insert many and still "get" them back out?

	// Does delete work? Does the tree maintain balance when a key is deleted?
	// If delete is not implemented, does calling it throw an
	// UnsupportedOperationException

} // copyright Deb Deppeler, all rights reserved, DO NOT SHARE
