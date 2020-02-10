import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

abstract class DataStructureADTTest<T extends DataStructureADT<String, String>> {

  private T ds;

  protected abstract T createInstance();

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
  }

  @AfterAll
  static void tearDownAfterClass() throws Exception {
  }

  @BeforeEach
  void setUp() throws Exception {
    ds = createInstance();
  }

  @AfterEach
  void tearDown() throws Exception {
    ds = null;
  }

  @Test
  void test00_empty_ds_size() {
    if (ds.size() != 0)
      fail(
          "data structure should be empty, with size=0, but size=" + ds.size());
  }

  // TODO: review tests 01 - 04

  @Test
  void test01_insert_one() {
    String key = "1";
    String value = "one";
    ds.insert(key, value);
    assert (ds.size() == 1);
  }

  @Test
  void test02_insert_remove_one_size_0() {
    String key = "1";
    String value = "one";
    ds.insert(key, value);
    assert (ds.remove(key)); // remove the key
    if (ds.size() != 0)
      fail(
          "data structure should be empty, with size=0, but size=" + ds.size());
  }

  @Test
  void test03_duplicate_exception_thrown() {
    String key = "1";
    String value = "one";
    ds.insert("1", "one");
    ds.insert("2", "two");
    try {
      ds.insert(key, value);
      fail("duplicate exception not thrown");
    } catch (RuntimeException re) {
    }
    assert (ds.size() == 2);
  }

  @Test
  void test04_remove_returns_false_when_key_not_present() {
    String key = "1";
    String value = "one";
    ds.insert(key, value);
    assert (!ds.remove("2")); // remove non-existent key is false
    assert (ds.remove(key)); // remove existing key is true
    if (ds.get(key) != null)
      fail("get(" + key + ") returned " + ds.get(key)
          + " which should have been removed");
  }

  // TODO: add tests 05 - 07 as described in assignment
  // TODO: add method headers for test methods.

  /**
   * This test method tests insert and remove only 1 key-value pair.
   */
  @Test
  void test05_insert_remove_one() {
    String key = "1";
    String value = "one";
    ds.insert(key, value);
    assertTrue(ds.remove(key));
    if (ds.size() != 0) {
      fail(
          "data structure should be empty, with size=0, but size=" + ds.size());
    }

  }

  /**
   * This test method inserts a lot of pairs and see if size is correct
   */
  @Test
  void test06_insert_many_size() {
    String value = "1";
    try {
      for (int key = 0; key < 100; key++) {
        ds.insert(Integer.toString(key), value);
      }
    } catch (Exception e) {
      System.out.println(ds.size());
      fail("data structure is not expanding itself when it is full");
    }
    assertTrue(ds.size() == 100);
  }

  /**
   * This test method inserts many pairs, then removes a pair, inserts more
   * pairs, then inserts a duplicate of the removed pair.
   */
  @Test
  void test07_no_duplicates() {
    String duplicate = "1";
    String key = "one";

    // insert the key that will have a duplicate key - later on.
    ds.insert(key, duplicate);

    // insert more Pairs into the array
    for (int i = 0; i < 15; i++) {
      ds.insert("#" + i, "value");
    }

    // remove "duplicate" key
    ds.remove(key);

    // insert more Pairs into the array again
    for (int i = 0; i < 15; i++) {
      ds.insert("#2" + i, "value");
    }

    try {
      ds.insert(key, duplicate);

      assertTrue(ds.size() == 31);
    } catch (RuntimeException e) {
      fail("NO EXCEPTIONS SHOULD BE THROWN!");
    }

  }

  /**
   * This test method inserts a null value with a valid key
   */
  @Test
  void test08_insert_null_values() {
    String value = null;
    String key = "one";

    try {
      ds.insert(key, value);
    } catch (IllegalArgumentException e) {
      fail("Should not consider value");
    } catch (Exception e) {
      fail("NOOOOOOOOOoooooo nOt An ExCePtIoN !");
    }
    assertTrue(ds.size() == 1);
  }

  /**
   * This test method insert 1,000 and remove all of them, cannot run quickly
   * when reach // about 25,000 pairs.
   */
  @Test
  void test09_insert_and_remove() {
    for (int i = 0; i < 25000; i++) {
      ds.insert(Integer.toString(i), "#" + i);
    }
    assertTrue(ds.size() == 25000);

    for (int i = 0; i < 25000; i++) {
      ds.remove(Integer.toString(i));
    }
    assertTrue(ds.size() == 0);
  }

  /**
   * This test method tests removing from an empty
   */
  @Test
  void test10_remove_empty_array() {
    try {
      assertFalse(ds.remove("one"));
    } catch (Exception e) {
      fail("NO EXCEPTIONS!");
    }
  }

  /**
   * This test method tests what contains returns when given an empty array
   */
  @Test
  void test11_contains_empty() {
    assertFalse(ds.contains("one"));
  }

  /**
   * This test method tests using contains when given a null key
   */
  @Test
  void test012_contains_null() {
    ds.insert("one", "1");
    ds.insert("two", "1");
    ds.insert("three", "1");
    assertFalse(ds.contains("null"));
  }
  // 014 - remove something that is not there

  /**
   * This test method uses removes with a key that is not in the array, checking
   * that size was not changed.
   */
  @Test
  void test013_remove_nothing() {
    ds.insert("", "1"); // inserting empty strings should be ok. (According to Professor Deppeler.)
    ds.insert("two", "1");
    ds.insert("three", "1");
    ds.remove("four");
    assertTrue(ds.size() == 3);
  }

  /**
   * This method tests if contains will return false when looking for an element
   * not in the array.
   */
  @Test
  void test014_contains_not_in_array() {
    for (int i = 0; i < 20; i++) {
      ds.insert("#" + i, i + "");
    }
    assertTrue(!ds.contains("#20"));
  }

  /**
   * This method tests if calling contains with a null key with result in an
   * error, or return false.
   */
  @Test
  void test014_contains_null_key() {
    for (int i = 0; i < 20; i++) {
      ds.insert("#" + i, i + "");
    }
    assertTrue(!ds.contains(null));

  }

  /**
   * Tests if contains will return true when the key is in the array
   */
  @Test
  void test015_contains_work() {
    String key = "valid";
    String value = "deww";

    ds.insert(key, value);
    for (int i = 0; i < 9; i++) {
      ds.insert("#" + i, "" + i);
    }
    assertTrue(ds.contains(key));

  }

  /**
   * Test if get will work when given a valid key, and the key is in the array.
   * The value returned should be equal to the value inserted.
   */
  @Test
  void test016_test_valid_get() {
    String key = "super valid key";
    String value = "value";
    ds.insert(key, value);
    assertTrue(value.equals(ds.get(key)));
  }

  /**
   * Test if the get method will throw a IllegalArgumentException when a null
   * key is passed in as input.
   * 
   */
  @Test
  void test017_test_null_get() {

    String key = "super valid key";
    String value = "value";
    ds.insert(key, value);
    try {
      assertTrue(null == ds.get(null));
      fail("Should have thrown an IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // make sure that get didn't do anything to the size of the array.
      assertTrue(ds.size() == 1);
    } catch (Exception e) {
      fail("Only IllegalArgumentException should have been thrown.");
    }
  }

  /**
   * This method tests if get will return null if a key is not in the array
   */
  @Test
  void test018_test_get_fail() {
    String key = "valid";
    String value = "also valid";
    ds.insert(key, value);
    try {
      assertTrue(null == ds.get("not in array"));
    } catch (Exception e) {
      fail("No exceptions!");
    }
  }
  
  /**
   * This method tests if the get method will return null if the key was inserted then removed. 
   */
  @Test
  void test019_test_get_removed_pair() {
    String key = "remove";
    String value = "random value";
    ds.insert(key, value);
    for(int i=0; i<21; i++) {
      ds.insert("#"+i, "i: "+i);
    }
    ds.remove(key);
    assertTrue(ds.get(key) == null);
  }
  // TODO: add more tests of your own design to ensure that you can detect
  // implementation that fail

  // Tip: consider different numbers of inserts and removes and how different
  // combinations of insert and removes

}
