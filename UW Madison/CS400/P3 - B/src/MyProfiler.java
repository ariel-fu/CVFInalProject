//////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           MyProfiler.java
//Files:           HashTable.java, HashTableADT.java, HashTableTest.java, MyProfiler.java
//Course:          (CS400, Spring, 2020)
//
//Author:          (Ariel Fu)
//Email:           (afu5@wisc.edu)
//Lecture Number: 001
//

// Used as the data structure to test our hash table against Tree Map
import java.util.TreeMap;

public class MyProfiler<K extends Comparable<K>, V> {

  HashTableADT<K, V> hashtable;
  TreeMap<K, V> treemap;

  /**
   * 
   * Initializes the hashtable and the treemap
   */
  public MyProfiler() {
    hashtable = new HashTable<K, V>();
    treemap = new TreeMap<K, V>();
  }

  /**
   * Adds the key and value into the hashtable and treemap
   * 
   * @param key   - key to add into the hashtable and treemap
   * @param value - value to add into the hashtable and treemap
   * @throws IllegalNullKeyException - if the key is null
   */
  public void insert(K key, V value) throws IllegalNullKeyException {
    hashtable.insert(key, value);

  }

  /**
   * Inserts the key and value pair into the tree map
   * 
   * @param key   - key
   * @param value - value
   */
  private void insertTree(K key, V value) {
    treemap.put(key, value);
  }

  /**
   * Runs the get method from the hashtable
   * 
   * @param key - get this key
   * @throws KeyNotFoundException    - if the key is not in the hashtable
   * @throws IllegalNullKeyException - if the key is null
   */
  public void retrieve(K key)
      throws IllegalNullKeyException, KeyNotFoundException {
    hashtable.get(key);

  }

  /**
   * Runs the get method from the tree map
   * 
   * @param key - key to get
   */
  private void retrieveTree(K key) {
    treemap.get(key);
  }

  /**
   * Main method that runs the insert and retrieve method many times to figure
   * out if the hashtable has a time complexity of O(1)
   * 
   * @param args
   */
  public static void main(String[] args) {

    try {

      int numElements = Integer.parseInt(args[0]);
      // beware, runs out of heap space when running with an input of 10000000
      // items
      int i = 0;
      MyProfiler<Integer, Integer> profile = new MyProfiler<Integer, Integer>();

      // insert numElements time into the hash table
      for (i = 0; i < numElements; i++) {
        profile.insert(i, i);
      }

      // get all the key-value pairs inserted into the hash table
      for (i = 0; i < numElements; i++) {
        profile.retrieve(i);
      }

      // insert numElements time into the tree map
      for (i = 0; i < numElements; i++) {
        profile.insertTree(i, i);
      }

      // get all the key-value pairs inserted into the treemap
      for (i = 0; i < numElements; i++) {
        profile.retrieveTree(i);
      }

      String msg = String.format("Inserted and retreived %d (key,value) pairs",
          numElements);
      System.out.println(msg);
    } catch (Exception e) {
      // print the exception message
      System.out.println(
          "Usage: java MyProfiler <number_of_elements>" + e.getMessage());
      System.exit(1);
    }
  }
}
