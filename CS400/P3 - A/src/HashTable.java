import java.util.ArrayList;

//////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           HashTable.java
//Files:           HashTable.java, HashTableADT.java, HashTableTest.java
//Course:          (CS400, Spring, 2020)
//
//Author:          (Ariel Fu)
//Email:           (afu5@wisc.edu)
//Lecture Number: 001
//

// TODO: comment and complete your HashTableADT implementation
// DO ADD UNIMPLEMENTED PUBLIC METHODS FROM HashTableADT and DataStructureADT TO YOUR CLASS
// DO IMPLEMENT THE PUBLIC CONSTRUCTORS STARTED
// DO NOT ADD OTHER PUBLIC MEMBERS (fields or methods) TO YOUR CLASS
//
// TODO: implement all required methods
//
// TODO: describe the collision resolution scheme you have chosen
// identify your scheme as open addressing or bucket
// I will use chained buckets. More specifically, an ArrayList of ArrayLists.
//
// TODO: explain your hashing algorithm here 
// I am using .hashCode() to get the hash code for an element
// NOTE: you are not required to design your own algorithm for hashing,
//       since you do not know the type for K,
//       you must use the hashCode provided by the <K key> object
//       and one of the techniques presented in lecture
//
public class HashTable<K extends Comparable<K>, V>
    implements HashTableADT<K, V> {

  /**
   * Class stores key-value pair as one node
   * 
   * @author Ariel
   *
   */
  private class Node {
    private K key; // key in this node
    private V value; // value associated with the key
    private Node next; // next value

    /**
     * Constructor that sets the key and value to the input
     * 
     * @param key   - new key
     * @param value - value associated with the key
     */
    private Node(K key, V value) {
      this.key = key;
      this.value = value;
    }

    /**
     * Getter method for the key
     * 
     * @return key
     */
    private K getKey() {
      return key;
    }

    /**
     * Getter method for the value
     * 
     * @return the value
     */
    private V getValue() {
      return value;
    }

    /**
     * Setter method for the value of this node
     * 
     * @param value - new value
     */
    private void setValue(V value) {
      this.value = value;
    }

  }

  /**
   * This class models a list inside the hash table that stores the Nodes that
   * have collisions.
   * 
   * @author Ariel
   *
   */
  private class NodeList extends ArrayList<Node> {

    /**
     * This inserts a Node into the list. If the key is already in the list, it
     * replaces the value
     * 
     * @param key   - new key
     * @param value - new value
     * @return true if there was no replacing, false if a value was replaced.
     */
    private boolean insert(K key, V value) {
      for (int i = 0; i < this.size(); i++) {
        Node currentNode = get(i);
        if (currentNode.getKey().compareTo(key) == 0) {
          currentNode.setValue(value);
          return false;
        }
      }
      // no replacing needed!
      this.add(new Node(key, value));
      return true;
    }

    /**
     * This removes a Node from the list given a key
     * 
     * @param key - key of the Node to remove
     * @return true if the Node was in the list and remmoved properly, false
     *         otherwise
     */
    private boolean remove(K key) {
      for (int i = 0; i < this.size(); i++) {
        Node currentNode = get(i);
        if (currentNode.getKey().compareTo(key) == 0) {
          this.remove(i);
          return true;
        }
      }
      return false;
    }
  }

  private int tableSize; // curr capacity
  private double loadFactorThreshold; // threshold
  private ArrayList<NodeList> hashTable; // hashTable
  private int numElements; // number of elements in the hash table
  private int numBuckets; // number of filled slots in the hashTable

  /**
   * No arg-constructor that sets the capacity of the hashtable to 103, and the
   * load factor threshold to 3/4 full.
   */
  public HashTable() {
    // set to default numbers, 101 and the load factor threshold is 3/4 full.
    this(101, .75);
  }

  // TODO: comment and complete a constructor that accepts
  // initial capacity and load factor threshold
  // threshold is the load factor that causes a resize and rehash
  /**
   * Takes in a capacity for the hash table and a load factor threshold that
   * causes resizing and rehashing.
   * 
   * @param initialCapacity     - initial capacity of the hash table
   * @param loadFactorThreshold - load factor theshold that causes resizing and
   *                            rehashing.
   * @throws IllegalArgumentException - if the inital capacity is negative or
   *                                  the load factor threshold is 0 or less.
   */
  public HashTable(int initialCapacity, double loadFactorThreshold) {
    // TODO: ask if need to do something if the IC and the LFT are negative
    if (initialCapacity < 0) {
      throw new IllegalArgumentException(
          "Initial capacity must be a positive integer.");
    } else if (loadFactorThreshold <= 0.0) {
      throw new IllegalArgumentException(
          "The load factor threshold must be a non-zero, positive integer");
    } else {
      this.tableSize = initialCapacity;
      this.loadFactorThreshold = loadFactorThreshold;
      this.numBuckets = 0;
      this.numElements = 0;
      hashTable = new ArrayList<NodeList>();

      // initialize every value in the ArrayList to null
      for (int i = 0; i < tableSize; i++) {
        hashTable.add(null);
      }
    }
  }

  /**
   * Inserts a key-value pair into the hash table, will replace if the key
   * matches with anyother key in the hashtable
   * 
   * @param key   - key associated with the pair
   * @param value - value
   * @throws IllegalNullKeyException - if the key is null.
   */
  @Override
  public void insert(K key, V value) throws IllegalNullKeyException {
    if (key == null) {
      throw new IllegalNullKeyException("Key is null.");
    }

    int hashIndex = this.getHashIndex(key);
    NodeList currentList = hashTable.get(hashIndex);
    // if there was no collision, increment the number of buckets
    if (currentList == null) {
      currentList = new NodeList();
      hashTable.set(hashIndex, currentList);
      numBuckets++;
    }

    boolean result = currentList.insert(key, value);

    // if a new NodeList was created, aka there wasn't a collision, increment
    // the number of buckets filled.
    if (result == true) {
      numElements++;
    }

    double currLoadFactor = numBuckets / tableSize;
    if (currLoadFactor >= loadFactorThreshold) {
      this.resizeAndRehash();
    }

  }

  /**
   * Removes the Node given the key
   * 
   * @param key - key associated with the node to remove
   * @throws IllegalNullKeyException - null key
   * @return true if the key was in the hash table and was properly removed
   */
  @Override
  public boolean remove(K key) throws IllegalNullKeyException {
    if (key == null) {
      throw new IllegalNullKeyException("Null key, cannot remove");
    }

    int hashIndex = key.hashCode() % tableSize;

    NodeList currentList = hashTable.get(hashIndex);

    boolean result = currentList.remove(key);
    // if removed, decrement the number of elements
    if (result == true) {
      numElements--;
      // if the list of nodes is now empty, decrement the number of filled
      // lists/buckets this hash table has.
      if (currentList.size() == 0) {
        numBuckets--;
      }
    }
    return result;

  }

  /**
   * Resizes the hash table, rehashes all elements and adds them to the new hash
   * table
   */
  private void resizeAndRehash() {

    ArrayList<NodeList> temp = hashTable;
    hashTable = new ArrayList<NodeList>();

    tableSize = tableSize * 2 + 1;

    for (int i = 0; i < tableSize; i++) {
      hashTable.add(null);
    }

    for (NodeList nodeList : temp) {
      if (nodeList != null) {
        for (int i = 0; i < nodeList.size(); i++) {
          Node currNode = nodeList.get(i);
          // insert the current node into the hash table
          try {
            this.insert(currNode.getKey(), currNode.getValue());
          } catch (IllegalNullKeyException e) {
          }

        }
      }
    }
    return;
  }

  /**
   * Helper method with the hashIndex
   * 
   * @param key - key that defines the hashCode
   * @return the key's hashCode % by the tableSize
   */
  private int getHashIndex(K key) {
    return Math.abs(key.hashCode()) % tableSize;
  }

  /**
   * Gets the value associated with the key
   * 
   * @param key - key of the node with the value
   * @throws IllegalNullKeyException - if the key is null
   * @throws KeyNotFoundException    - if the key is not in the hash table
   * @return the value associated with the key
   */
  @Override
  public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException("Null key");
    }

    Node keyNode = getNode(key);
    // key was not in the hash table
    if (keyNode == null) {
      throw new KeyNotFoundException("Tried to get this key: " + key);
    } else {
      return keyNode.getValue();
    }
  }

  /**
   * Gets the number of elements in the hash table
   * 
   * @return the number of keys / elements
   */
  @Override
  public int numKeys() {
    return numElements;
  }

  /**
   * Gets the load factor threshold, how full the table can get before resizing
   * and rehashing is needed.
   * 
   * @return the load factor threshold
   */
  @Override
  public double getLoadFactorThreshold() {
    return loadFactorThreshold;
  }

  /**
   * Returns how full the table is, also called the load factor
   * 
   * @return number of elements divided by the table size
   */
  @Override
  public double getLoadFactor() {

    // (how full the table is)
    return (double) numBuckets / tableSize;
  }

  /**
   * Returns the table size of the hash table
   * 
   * @return tableSize of the hash table
   */
  @Override
  public int getCapacity() {

    return tableSize;
  }

  /**
   * Returns 4 - for array of arrays
   * 
   * @return CHAINING - array of arrays
   */
  @Override
  public int getCollisionResolution() {
    return 4;
  }

  /**
   * Finds the node with the corresponding key
   * 
   * @param key - key of the node
   * @return null if the key is not in the hash table or the node
   */
  private Node getNode(K key) {
    int hashIndex = this.getHashIndex(key);
    NodeList keyNode = hashTable.get(hashIndex);
    if (keyNode != null) {
      for (int i = 0; i < keyNode.size(); i++) {
        if (keyNode.get(i).getKey().compareTo(key) == 0) {
          return keyNode.get(i);
        }
      }
    }
    return null;
  }

}
