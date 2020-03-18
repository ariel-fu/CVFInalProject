
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
    treemap.put(key, value);
    // TODO: complete insert method
    // Insert K, V into both data structures
  }

  /**
   * Runs the get method from the hashtable and the treemap
   * 
   * @param key - get this key
   * @throws KeyNotFoundException    - if the key is not in the hashtable
   * @throws IllegalNullKeyException - if the key is null
   */
  public void retrieve(K key)
      throws IllegalNullKeyException, KeyNotFoundException {
    hashtable.get(key);
    treemap.get(key);
    // TODO: complete the retrieve method
    // get value V for key K from data structures
  }

  /**
   * Main method that runs the insert and retrieve method many times to figure
   * out if the hashtable has a time complexity of O(1)
   * 
   * @param args
   */
  public static void main(String[] args) {
    int i = 0;
    try {
//      int numElements = Integer.parseInt(args[0]);
      int numElements = 10000000;
      MyProfiler<Integer, Integer> profile = new MyProfiler<Integer, Integer>();
      // TODO: complete the main method.
      // Create a profile object.
      // For example, Profile<Integer, Integer> profile = new Profile<Integer,
      // Integer>();
      // execute the insert method of profile as many times as numElements
      // execute the retrieve method of profile as many times as numElements
      // See, ProfileSample.java for example.
      // insert numElements time
      for (i = 0; i < numElements; i++) {
        profile.insert(i, i);
      }

      // get all the key-value pairs inserted
      for (int j = 0; j < numElements; j++) {
        profile.retrieve(j);
      }

      String msg = String.format("Inserted and retreived %d (key,value) pairs",
          numElements);
      System.out.println(msg);
    } catch (OutOfMemoryError e) {
      // can I have this here? also ask
      // TODO: remove i (?) ask professor
      System.out.println("Usage: java MyProfiler <number_of_elements> " + i);
//    System.out.println(e.getMessage());
      System.exit(1);
    } catch (Exception e) {
      System.out.println("Usage: java MyProfiler <number_of_elements> " + i);
//      System.out.println(e.getMessage());
      System.exit(1);
    }
  }
}
