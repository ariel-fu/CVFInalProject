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
    assertTrue(no_arg.getCapacity() == 123);
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
   * Tests a rehashing and resizing
   */
  @Test
  public void test_need_rehash_and_resize() {
    try {
      for (int i = 0; i < 8; i++) {
        ht.insert(i, i);
      }

      assertTrue(ht.getCapacity() == 23);
      assertTrue(ht.numKeys() == 8);
    } catch (IllegalNullKeyException e) {
      fail("No exceptions should be thrown from rehashing and resizing");
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
  // TODO add your own tests of your implementation

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
   * Tests #18 on Gradescope
   */
  @Test
  public void test_get_247001() {
    try {
      ht.insert(247001, 01);
      ht.insert(00, 00);
      ht.insert(80, 80);

      ht.insert(30, 30);
      ht.insert(27, 27);
      ht.insert(23, 23);
      ht.insert(49, 49);

      ht.remove(247001);

      ht.insert(50, 50);
      ht.insert(12, 12);
      ht.insert(247001, 01);

      assertTrue(ht.numKeys() == 9);
      ht.insert(15, 15);
      ht.insert(93, 93);
      assertTrue(ht.numKeys() == 11);
      Integer one = 01;
      System.out.println(one.hashCode());
      System.out.println(ht.get(247001));

      assertTrue(ht.get(247001) == 01);
    } catch (KeyNotFoundException e) {
      fail("No exceptions" + e.getMessage());
    } catch (Exception e) {
      fail("No excpetions");
    }
  }
}
