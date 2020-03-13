
//////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           HashTableTest.java
//Files:           HashTable.java, HashTableADT.java, HashTableTest.java
//Course:          (CS400, Spring, 2020)
//
//Author:          (Ariel Fu)
//Email:           (afu5@wisc.edu)
//Lecture Number: 001
//
// TODO: add imports as needed

// org.junit.Assert.*; 
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** TODO: add class header comments here */
public class HashTableTest {

  // TODO: add other fields that will be used by multiple tests
  private HashTable<Integer, Integer> ht;

  // TODO: add code that runs before each test method
  @Before
  public void setUp() throws Exception {
    ht = new HashTable<Integer, Integer>(11, 0.5);
  }

  @After
  public void tearDown() throws Exception {
    ht = null;
  }

  /**
   * Tests that a HashTable returns an integer code indicating which collision
   * resolution strategy is used. REFER TO HashTableADT for valid collision
   * scheme codes.
   */
  @Test
  public void test000_collision_scheme() {
    HashTableADT htIntegerKey = new HashTable<Integer, String>();
    int scheme = htIntegerKey.getCollisionResolution();
    if (scheme < 1 || scheme > 9)
      fail("collision resolution must be indicated with 1-9");
  }

  /**
   * IMPLEMENTED AS EXAMPLE FOR YOU Tests that insert(null,null) throws
   * IllegalNullKeyException
   */
  @Test
  public void test001_IllegalNullKey() {
    try {
      HashTableADT htIntegerKey = new HashTable<Integer, String>();
      htIntegerKey.insert(null, null);
      fail("should not be able to insert null key");
    } catch (IllegalNullKeyException e) {
      /* expected */ } catch (Exception e) {
      fail("insert null key should not throw exception "
          + e.getClass().getName());
    }
  }

  /**
   * Tests all values are set to the "default"
   */
  @Test
  public void test_no_arg_constructor() {
    HashTable no_arg = new HashTable<Integer, Integer>();
    assertTrue(no_arg.getCapacity() == 101);
    assertTrue(no_arg.getLoadFactorThreshold() == 0.75);
  }

  /**
   * Tests all the values are set to the ones inputted.
   */
  @Test
  public void test_constructor() {
    assertTrue(ht.getCapacity() == 11);
    assertTrue(ht.getLoadFactorThreshold() == 0.5);
  }

  /**
   * Tests a simple insert
   */
  @Test
  public void test_simple_insert() {
    try {
      ht.insert(20, 20);
      ht.insert(30, 30);
      ht.insert(40, 40);
      assertTrue(ht.numKeys() == 3);
    } catch (IllegalNullKeyException e) {
      fail("No exceptions should be thrown from simple insert");
    }
  }

  /**
   * Tests replacing a value
   */
  @Test
  public void test_replace() {
    try {
      ht.insert(20, 10);
      ht.insert(20, 50);
      assertTrue(ht.get(20) == 50);
    } catch (IllegalNullKeyException e) {
      fail("No exceptions should've been thrown from replacing");
    } catch (KeyNotFoundException e) {
      fail("No exceptions should've been thrown from replacing");
    }
  }

  /**
   * Tests a simple remove
   */
  @Test
  public void test_remove_simple() {
    try {
      ht.insert(1, 1);
      ht.insert(2, 2);
      ht.insert(9, 9);
      ht.insert(50, 50);
      ht.insert(12, 12);
      assertTrue(ht.remove(1));
      assertTrue(ht.numKeys() == 4);
    } catch (IllegalNullKeyException e) {
      fail("No exceptions should've been thrown from a simple remove");
    }
  }

  /**
   * Tests inserting, removing then getting that value
   */
  @Test
  public void test_insert_remove_get() {
    try {
      ht.insert(20, 20);
      assertTrue(ht.remove(20));
      ht.get(20);
    } catch (IllegalNullKeyException e) {
      fail("No exceptions should be thrown from a insert/remove/get");
    } catch (KeyNotFoundException e) {
      assertTrue(ht.numKeys() == 0);
    } catch (Exception e) {
      fail("No exceptions should be thrown from a insert/remove/get");
    }
  }

  /**
   * Remove all keys
   */
  @Test
  public void test_remove_all() {
    try {

      // insert 10 values
      for (int i = 0; i < 10; i++) {
        ht.insert(i, i);
      }
      // remove all the values
      for (int i = 0; i < 10; i++) {
        assertTrue(ht.remove(i));
      }

      ht.get(2);
    } catch (KeyNotFoundException e) {
      assertTrue(ht.numKeys() == 0);
    } catch (Exception e) {
      fail("No exceptions should be thrown from remove all");
    }
  }

  /**
   * Tries to get a key that is not in hash table
   */
  @Test
  public void test_get_nothing_there() {
    try {
      ht.insert(5, 5);
      ht.insert(2, 2);
      ht.insert(9, 9);
      ht.insert(1, 1);
      ht.insert(0, 0);
      ht.get(4);
    } catch (KeyNotFoundException e) {

    } catch (IllegalNullKeyException e) {

    }
  }

  /**
   * Tests getting a null value
   */
  @Test
  public void test_get_null() {
    try {
      ht.get(null);
    } catch (IllegalNullKeyException e) {

    } catch (Exception e) {
      fail("No exceptions should be thrown from get");
    }
  }

  /**
   * Tests resize and rehash operation
   */
  @Test
  public void test_resize() {
    try {
      HashTable test = new HashTable<Integer, Integer>(2, 0.5);
      test.insert(10, 10);
      assertTrue(test.getCapacity() == 2);
      test.insert(40, 40);
      assertTrue(test.getCapacity() == 2);

      test.insert(37, 37);
      assertTrue(test.getCapacity() == 5);

      assertTrue(test.get(37) == Integer.valueOf(37));
    } catch (IllegalNullKeyException e) {
      fail(e.getMessage());
    } catch (KeyNotFoundException e) {
      fail(e.getMessage());
    }
  }

  /**
   * Tests inserting large-ish values
   */
  @Test
  public void testLargeValues() {
    try {
      for (int i = 0; i < 5; i++) {
        ht.insert(i * 50, i);
      }

      for (int i = 0; i < 5; i++) {
        try {
          assertTrue(ht.get(i * 50) == i);
        } catch (KeyNotFoundException e) {
          fail("Key " + e.getMessage());
        }
      }

    } catch (IllegalNullKeyException e) {
      fail(e.getMessage());
    }
  }

  /**
   * Tests inserting large values, stops running under 10 seconds when inserting
   * over 6000000.
   */
  @Test
  public void testAddALot() {
    try {
      for (int i = 0; i < 6000000; i++) {
        ht.insert(i, i);
      }

      for (int i = 0; i < 6000000; i++) {
        assertTrue(ht.get(i).equals(Integer.valueOf(i)));
      }

    } catch (IllegalNullKeyException e) {
      fail(e.getMessage());
    } catch (KeyNotFoundException e) {
      fail(e.getMessage());
    }

  }

  @Test
  public void testLoadFactor() {
    try {
      HashTable test = new HashTable<Integer, Integer>(2, 0.5);
      test.insert(0, 0);
      System.out.println(test.getLoadFactor());
      assertTrue(test.getLoadFactor() == 1.0 / test.getCapacity());
      test.insert(1, 1);
      test.insert(2, 2);
      assertTrue(test.getLoadFactor() == 3.0 / test.getCapacity());
      test.insert(3, 3);
      test.insert(4, 4);
    } catch (Exception e) {

    }
  }
}
