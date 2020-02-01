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
    assertEquals(ds.size(), 1);
  }

  @Test
  void test02_insert_remove_one_size_0() {
    String key = "1";
    String value = "one";
    ds.insert(key, value);
    assertTrue(ds.remove(key)); // remove the key
    assertEquals(ds.size(), 0); // confirm that the size becomes 0.
    if(ds.size() != 0) {
      fail("data structure should be empty, with size=0, but size=" + ds.size());
    }
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
    assertTrue(ds.size() == 2);
  }

  @Test
  void test04_remove_returns_false_when_key_not_present() {
    String key = "1";
    String value = "one";
    ds.insert(key, value);
    assertTrue(!ds.remove("2")); // remove non-existent key is false
    assertTrue(ds.remove(key)); // remove existing key is true
    if(ds.get(key) != null)
      fail("get(" + key + ") returned " + ds.get(key) + " which should have been removed");
  }

  // TODO: add tests 05 - 07 as described in assignment
  //005 - insert and remove 1
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
  void test06_insert_many_size(){
    String value = "1";
    try {
    for(int key=0; key<100; key++) {
      ds.insert(Integer.toString(key), value);
    }
    } catch(Exception e) {
      System.out.println(ds.size());
      fail("data structure is not expanding itself when it is full");
    }
    assertTrue(ds.size() == 100);
  }
  // 007 - insert duplicate values (should be ok)
  @Test
  void test07_no_duplicates(){
    
  }
  
  // 008 - insert duplicate keys after removal
  // TODO: add more tests of your own design to ensure that you can detect
  // implementation that fail

  // Tip: consider different numbers of inserts and removes and how different
  // combinations of insert and removes

}
