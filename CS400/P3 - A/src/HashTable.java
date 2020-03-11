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
// I will use open addressing (double hashing) to solve collisions.
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

    private void setValue(V value) {
      this.value = value;
    }
  }
  
  private class NodeList extends ArrayList<Node> {
	  private boolean insert(K key, V value) {
		  for (int i=0 ; i<this.size(); i++) {
			  Node currentNode = get(i);
			  if (currentNode.getKey().compareTo(key)==0) {
				  currentNode.setValue(value);
				  return false;
			  }
		  }
		  add((new Node(key,value));
		  return true;
	  }
  }

  private int tableSize; // curr capacity
  private double loadFactorThreshold; // threshold
  private ArrayList<NodeList> hashTable; // hashTable
  private int currentNumberOfElements;
  private int numberOfFilled

  /**
   * No arg-constructor that sets the capacity of the hashtable to 103, and the
   * load factor threshold to 3/4 full.
   */
  public HashTable() {
    // set to default numbers, 101 and the load factor threshold is 3/4 full.
    this(101,.75);
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
      this.currentNumberOfElements = 0;
      hashTable = new ArrayList<NodeList>();

      // initialize every value in the ArrayList to null
      for (int i = 0; i < tableSize; i++) {
        hashTable.add(new NodeList(0));
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

    int hashIndex = key.hashCode() % tableSize;
    
    

    if (hashTable.get(hashIndex) == null) {
    	hashTable.set(hashIndex, new NodeList(0));
    }
    
    NodeList currentList =hashTable.get(hashIndex); 
    
    if (currentList.size()==0) {
    	numberOfFilled++;
    }
    
    boolean result = currentList.insert(key, value);
    
    if (result)
      currentNumberOfElements++;
    }
  
    double currLoadFactor = currentNumberOfElements / tableSize;
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

    if (hashTable.get(hashIndex) != null) {
      Node prev = null;
      Node curr = hashTable.get(hashIndex);

      while (curr.next != null && curr.getKey().compareTo(key) != 0) {
        prev = curr;
        curr = curr.next;
      }

      if (curr.getKey().compareTo(key) == 0) {
        if (prev == null) {
          hashTable.set(hashIndex, curr.next);
        } else {
          prev.next = curr.next;
        }
        currentNumberOfElements--;
        return true; // removed
      }
    }
    return false;
  }

  /**
   * Resizes the hash table, rehashes all elements and adds them to the new hash
   * table
   */
  private void resizeAndRehash() {

    ArrayList<Node> temp = hashTable;
    hashTable = new ArrayList<Node>();

    tableSize = tableSize * 2 + 1;

    for (int i = 0; i < tableSize; i++) {
      hashTable.add(null);
    }

    for (Node node : temp) {
      while (node != null) {
        try {
          this.insert(node.getKey(), node.getValue());
        } catch (IllegalNullKeyException e) {
          // do nothing
        }
        node = node.next;
      }
    }
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
    return currentNumberOfElements;
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
    return currentNumberOfElements / tableSize;
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
   * Returns 3 - for double hashing
   * 
   * @return double hashing - 3
   */
  @Override
  public int getCollisionResolution() {
    return 3;
  }

  /**
   * Finds the node with the corresponding key
   * 
   * @param key - key of the node
   * @return null if the key is not in the hash table or the node
   */
  private Node getNode(K key) {
    int hashIndex = Math.abs(key.hashCode() % tableSize);
    Node keyNode = hashTable.get(hashIndex);
    if (keyNode != null) {
      while (keyNode != null && keyNode.getKey().compareTo(key) != 0) {
        keyNode = keyNode.next;
      }

      return keyNode;
    }
    return null;
  }

}
