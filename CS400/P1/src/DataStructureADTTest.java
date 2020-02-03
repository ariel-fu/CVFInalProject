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
    if(ds.size() != 0)
      fail("data structure should be empty, with size=0, but size=" + ds.size());
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
    if(ds.size() != 0)
      fail("data structure should be empty, with size=0, but size=" + ds.size());
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
    if(ds.get(key) != null)
      fail("get(" + key + ") returned " + ds.get(key) + " which should have been removed");
  }

  // TODO: add tests 05 - 07 as described in assignment
  // 005 - insert and remove 1
  @Test
  void test05_insert_remove_one() {
    String key = "1";
    String value = "one";
    ds.insert(key, value);
    assertTrue(ds.remove(key));
    if(ds.size() != 0) {
      fail("data structure should be empty, with size=0, but size=" + ds.size());
    }

  }

  // 006 - insert lots of pairs and see if size is correct
  @Test
  void test06_insert_many_size() {
    String value = "1";
    try {
      for(int key = 0; key < 100; key++) {
        ds.insert(Integer.toString(key), value);
      }
    } catch (Exception e) {
      System.out.println(ds.size());
      fail("data structure is not expanding itself when it is full");
    }
    assertTrue(ds.size() == 100);
  }

  // 007 - insert duplicate values (should be ok)
  @Test
  void test07_no_duplicates() {
    String duplicate = "1";
    String key = "one";
    ds.insert(key, duplicate);
    try {
      ds.insert(key, duplicate);
      fail("Duplicate key should've threw a RuntimeException");
    } catch (RuntimeException e) {
    }

  }

  // 008 - insert duplicate keys after removal
  @Test
  void test08_insert_duplicate_after_removal() {
    String value = "1";
    String key = "one";

    for(int i = 0; i < 100; i++) {
      ds.insert(Integer.toString(i), "#" + i);
    }
    ds.remove("1");
    try {
      ds.insert(key, value);
    } catch (RuntimeException e) {
      fail("Should be able to insert. Since duplicate key is removed");
    }
  }

  // 009 - insert null values with valid keys.
  @Test
  void test009_insert_null_values() {
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

  // 010 - insert 1,000 and remove all of them, cannot run quickly when reached
  // 100,000
  @Test
  void test010_insert_and_remove() {
    for(int i = 0; i < 10000; i++) {
      ds.insert(Integer.toString(i), "#" + i);
    }
    assertTrue(ds.size() == 10000);

    for(int i = 0; i < 10000; i++) {
      ds.remove(Integer.toString(i));
    }
    assertTrue(ds.size() == 0);
  }

  // 011 - remove from an empty
  @Test
  void test011_remove_empty_array() {
    try {
      assertFalse(ds.remove("one"));
    } catch (Exception e) {
      fail("NO EXCEPTIONS!");
    }
  }

  // 012 - contains empty array
  @Test
  void test012_contains_empty() {
    assertFalse(ds.contains("one"));
  }
  
  // 013 - contains null key
  @Test
  void test013_contains_null() {
    ds.insert("one", "1");
    ds.insert("two", "1");
    ds.insert("three", "1");
    assertFalse(ds.contains("null"));
  }
  // 014 -
  // TODO: add more tests of your own design to ensure that you can detect
  // implementation that fail

  // Tip: consider different numbers of inserts and removes and how different
  // combinations of insert and removes

}
