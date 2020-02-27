import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//@SuppressWarnings("rawtypes")
public class TestBST {

	protected STADT<Integer, String> bst;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		bst = new BST<Integer, String>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	// TODO: add java docs

	/**
	 * CASE 123 Insert three values in sorted order and then check the root, left,
	 * and right keys to see if insert worked correctly.
	 */
	@Test
	void testBST_001_insert_sorted_order_simple() {
		try {
			bst.insert(10, "10");
			if (!bst.getKeyAtRoot().equals(10))
				fail("insert at root does not work");

			bst.insert(20, "20");
			if (!bst.getKeyOfRightChildOf(10).equals(20))
				fail("insert to right child of root does not work");

			bst.insert(30, "30");
			if (!bst.getKeyAtRoot().equals(10))
				fail("inserting 30 changed root");

			if (!bst.getKeyOfRightChildOf(20).equals(30))
				fail("inserting 30 as right child of 20");

			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child

			Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(10));
			Assert.assertEquals(bst.getKeyOfRightChildOf(10), Integer.valueOf(20));
			Assert.assertEquals(bst.getKeyOfRightChildOf(20), Integer.valueOf(30));

			System.out.println("10 20 30");
			bst.print();
			System.out.println("end");

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 001: " + e.getMessage());
		}
	}

	/**
	 * CASE 321 Insert three values in reverse sorted order and then check the root,
	 * left, and right keys to see if insert worked in the other direction.
	 */
	@Test
	void testBST_002_insert_reversed_sorted_order_simple() {
		try {
			bst.insert(30, "30");
			if (!bst.getKeyAtRoot().equals(30))
				fail("insert at root does not work");

			bst.insert(20, "20");
			if (!bst.getKeyOfLeftChildOf(30).equals(20))
				fail("insert to left child of root does not work");

			bst.insert(10, "10");
			if (!bst.getKeyAtRoot().equals(30))
				fail("inserting 10 changed root");

			if (!bst.getKeyOfLeftChildOf(20).equals(10))
				fail("inserting 10 as left child of 20");

			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child

			Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(30));
			Assert.assertEquals(bst.getKeyOfLeftChildOf(30), Integer.valueOf(20));
			Assert.assertEquals(bst.getKeyOfLeftChildOf(20), Integer.valueOf(10));

			System.out.println("30 20 10");
			bst.print();
			System.out.println("End");

		} catch (Exception e) {
			e.printStackTrace();

			fail("Unexpected exception 002: " + e.getMessage());
		}
	}

	/**
	 * CASE 132 Insert three values so that rebalancing requires new key to be the
	 * "new" root of the rebalanced tree.
	 * 
	 * Then check the root, left, and right keys to see if insert occurred
	 * correctly.
	 */
	@Test
	void testBST_003_insert_smallest_largest_middle_order_simple() {
		try {
			bst.insert(10, "10");
			if (!bst.getKeyAtRoot().equals(10))
				fail("insert at root does not work");

			bst.insert(30, "30");
			if (!bst.getKeyOfRightChildOf(10).equals(30))
				fail("insert to right child of root does not work");
			Assert.assertEquals(bst.getKeyOfRightChildOf(10), Integer.valueOf(30));

			bst.insert(20, "20");
			if (!bst.getKeyAtRoot().equals(10))
				fail("inserting 20 changed root");

			if (!bst.getKeyOfLeftChildOf(30).equals(20))
				fail("inserting 20 as left child of 30");

			// IF rebalancing is working,
			// the tree should have 20 at the root
			// and 10 as its left child and 30 as its right child

			Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(10));
			Assert.assertEquals(bst.getKeyOfRightChildOf(10), Integer.valueOf(30));
			Assert.assertEquals(bst.getKeyOfLeftChildOf(30), Integer.valueOf(20));

			System.out.println("10 30 20");
			bst.print();
			System.out.println("End");

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 003: " + e.getMessage());
		}
	}

	/**
	 * CASE 312 Insert three values so that rebalancing requires new key to be the
	 * "new" root of the rebalanced tree.
	 * 
	 * Then check the root, left, and right keys to see if insert occurred
	 * correctly.
	 */
	@Test
	void testBST_004_insert_largest_smallest_middle_order_simple() {
		try {
			bst.insert(30, "30");
			if (!bst.getKeyAtRoot().equals(30))
				fail("insert at root does not work");

			bst.insert(10, "10");
			if (!bst.getKeyOfLeftChildOf(30).equals(10))
				fail("insert to left child of root does not work");

			bst.insert(20, "20");
			if (!bst.getKeyAtRoot().equals(30))
				fail("inserting 10 changed root");

			if (!bst.getKeyOfRightChildOf(10).equals(20))
				fail("inserting 20 as right child of 10");

			// the tree should have 30 at the root
			// and 10 as its left child and 20 as 10's right child

			Assert.assertEquals(bst.getKeyAtRoot(), Integer.valueOf(30));
			Assert.assertEquals(bst.getKeyOfLeftChildOf(30), Integer.valueOf(10));
			Assert.assertEquals(bst.getKeyOfRightChildOf(10), Integer.valueOf(20));

			System.out.println("30 10 20");
			bst.print();
			System.out.println("End");

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 001: " + e.getMessage());
		}
	}

	/**
	 * Tests basic insert
	 */
	@Test
	void testBST_insert() {
		try {
			bst.insert(90, "e");
			bst.insert(903, "e");
			bst.insert(910, "e");
			bst.insert(904, "e");
			bst.insert(908, "e");
			bst.insert(900, "e");
		} catch (IllegalNullKeyException | DuplicateKeyException e) {
			fail(e.getMessage());
		}

	}

	/**
	 * Tests inserting a null value --> should pass
	 */
	@Test
	void testBST_insert_null_value() {
		try {
			bst.insert(2, null);
		} catch (IllegalNullKeyException e) {
			fail(e.getMessage());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Tests inserting a null key, which should throw an IllegalNullKeyException
	 */
	@Test
	void testBST_insert_null_keys() {
		try {
			bst.insert(null, "valid");
		} catch (IllegalNullKeyException e) {
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Tests inserting a duplicate --> throw a DuplicateKeyException
	 */
	void testBST_insert_duplicate() {
		String value = "valid value";
		try {
			bst.insert(1, value);
			bst.insert(12, value);
			bst.insert(15, value);
			bst.insert(10, value);
			bst.insert(1, value);
		} catch (DuplicateKeyException e) {

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Tests a simple remove
	 */
	@Test
	void testBST_remove_simple() {
		try {
			for (int i = 0; i < 10; i++) {
				bst.insert(i, "i" + i);
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}

		assertTrue(bst.numKeys() == 10);
		try {
			assertTrue(bst.remove(2));
		} catch (IllegalNullKeyException e) {
			fail(e.getMessage());
		}
		assertTrue(bst.numKeys() == 9);
	}

	/**
	 * Tests inserting and removing one --> the numKeys should = 0
	 */
	@Test
	void testBST_insert_remove_1() {

		try {
			bst.insert(1, "1");
			bst.remove(1);
			assertTrue(bst.numKeys() == 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

	/**
	 * Tests removing from an empty BST, remove() should return false
	 */
	@Test
	void testBST_remove_nothing() {
		try {
			assertTrue(!bst.remove(1));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Tests removing an input that is null --> throw an IllegalNullKeyException
	 */
	@Test
	void testBST_remove_null_input() {
		try {
			bst.remove(null);
		} catch (IllegalNullKeyException e) {
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Tests removing a Node that is not in the BST
	 */
	@Test
	void testBST_remove_not_in_BST() {
		String value = "e";
		try {
			bst.insert(1, value);
			bst.insert(2, value);
			bst.insert(3, value);
			bst.insert(4, value);
			bst.insert(5, value);
			bst.insert(6, value);
			bst.insert(7, value);
			assertTrue(!bst.remove(0));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Tests height method with a height of N
	 */
	@Test
	void testBST_simple_height() {
		try {
			for (int i = 0; i < 5; i++) {
				bst.insert(i, "" + i);
			}
		} catch (Exception e) {
			fail("no exceptions should be thrown");
		}
		assertTrue(bst.getHeight() == 5);
	}

	/**
	 * Tests height of a balanced tree
	 */
	@Test
	void testBST_balanced_height() {
		String value = "value";
		try {
			bst.insert(10, value);
			bst.insert(5, value);
			bst.insert(15, value);
			bst.insert(3, value);
			bst.insert(7, value);
			bst.insert(1, value);
			bst.insert(4, value);
			bst.insert(6, value);
			bst.insert(9, value);
			bst.insert(12, value);
			bst.insert(11, value);
			bst.insert(14, value);
			bst.insert(18, value);
			bst.insert(16, value);
			bst.insert(20, value);
			assertTrue(bst.getHeight() == 4);
		} catch (Exception e) {
			fail("No exceptions for a getMaxHeight()");
		}

	}

	/**
	 * Tests if BST's preorder
	 */
	@Test
	void testBST_preOrder() {

		try {
			bst.insert(2, "e");
			bst.insert(9, "e");
			bst.insert(1, "e");

			bst.insert(0, "e");
			bst.insert(3, "e");
		} catch (IllegalNullKeyException e) {

			fail(e.getMessage());
		} catch (DuplicateKeyException e) {

			fail(e.getMessage());
		}
		List<Integer> resultsPreOrder = bst.getPreOrderTraversal();
		LinkedList<Integer> expected = new LinkedList<Integer>();
		expected.add(2);
		expected.add(1);
		expected.add(0);
		expected.add(9);
		expected.add(3);
		System.out.println(bst.getInOrderTraversal());
		for (int i = 0; i < expected.size(); i++) {
			if (!resultsPreOrder.get(i).equals(expected.get(i))) {
				System.out.println(resultsPreOrder);
				fail("Results @i: " + resultsPreOrder.get(i) + " expected: " + expected.get(i));
			}
		}
	}

	/**
	 * Tests the inorder operation
	 */
	@Test
	void testBST_inOrder() {
		try {
			bst.insert(12, "1");
			bst.insert(123, "1");
			bst.insert(75, "1");
			bst.insert(15, "1");
			bst.insert(411, "1");
			bst.insert(11, "1");
			bst.insert(175, "1");
			bst.insert(91, "1");
			List<Integer> inOrderBST = bst.getInOrderTraversal();
			Integer[] expected = new Integer[] { 11, 12, 15, 75, 91, 123, 175, 411 };
			for (int i = 0; i < expected.length; i++) {
				if (!inOrderBST.get(i).equals(expected[i])) {
					fail("BST: " + inOrderBST.get(i) + " expected: " + expected[i]);
				}
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Tests the postorder operation
	 */
	@Test
	void testBST_postOrder() {
		try {
			bst.insert(50, "1");
			bst.insert(20, "1");
			bst.insert(30, "1");
			bst.insert(10, "1");
			bst.insert(0, "1");
			bst.insert(15, "1");
			bst.insert(80, "1");
			bst.insert(70, "1");
			bst.insert(90, "1");
			bst.insert(85, "1");
			List<Integer> postOrder = bst.getPostOrderTraversal();
			Integer[] expected = new Integer[] { 0, 15, 10, 30, 20, 70, 85, 90, 80, 50 };
			for (int i = 0; i < expected.length; i++) {
				if (!postOrder.get(i).equals(expected[i])) {
					fail("BST: " + postOrder.get(i) + " expected: " + expected[i]);
				}
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Tests the level order operation
	 */
	@Test
	void testBST_level_order() {
		try {
			bst.insert(50, "1");
			bst.insert(20, "1");
			bst.insert(30, "1");
			bst.insert(10, "1");
			bst.insert(0, "1");
			bst.insert(15, "1");
			bst.insert(80, "1");
			bst.insert(70, "1");
			bst.insert(90, "1");
			bst.insert(85, "1");
			List<Integer> levelOrder = bst.getLevelOrderTraversal();
			Integer[] expected = new Integer[] { 50, 20, 80, 10, 30, 70, 90, 0, 15, 85 };
			for (int i = 0; i < expected.length; i++) {
				if (!levelOrder.get(i).equals(expected[i])) {
					fail("BST: " + levelOrder.get(i) + " expected: " + expected[i]);
				}
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testPrint() {
		try {
			bst.insert(1, "1");
			bst.insert(2, "2");
		} catch (IllegalNullKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DuplicateKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void test_insert_remove_contains() {
		try {
			bst.insert(20, "20");
			bst.remove(20);
			assertTrue(!bst.contains(20));
		} catch (Exception e) {
			fail("why?" + e.getMessage());
		}
	}

	@Test
	void test_remove() {
		try {
			bst.insert(20, "20");
			bst.insert(70, "70");
			bst.insert(01, "01");
			bst.insert(00, "00");
			bst.insert(40, "40");
			bst.insert(30, "30");
			assertTrue(bst.remove(20));
			assertTrue(bst.getKeyAtRoot() == 01);
			assertTrue(bst.remove(30));
			assertTrue(bst.remove(40));
			assertTrue(bst.remove(00));
			assertTrue(bst.getKeyOfRightChildOf(01) == 70);
			assertTrue(bst.remove(01));
			assertTrue(bst.getKeyAtRoot() == 70);
			assertTrue(bst.remove(70));
			assertTrue(bst.numKeys() == 0);

		} catch (KeyNotFoundException e) {
			fail("Key not found? I dont think so." + e.getMessage());
		} catch (IllegalNullKeyException e) {
			fail("Null key? I dont think so." + e.getMessage());
		} catch (DuplicateKeyException e) {
			fail("Duplicate key? not on my watch." + e.getMessage());
		}
	}
	// TODO: Add your own tests

	// Add tests to make sure that bst grows as expected.
	// Does it maintain it's balance?
	// Does the height of the tree reflect it's actual height
	// Use the traversal orders to check.

	// Can you insert many and still "get" them back out?

	// Does delete work?
	// If delete is not implemented, does calling it throw an
	// UnsupportedOperationException

}
