import java.util.ArrayList;
import java.util.LinkedList;

// TODO: comment and complete your HashTableADT implementation
// DO ADD UNIMPLEMENTED PUBLIC METHODS FROM HashTableADT and DataStructureADT TO YOUR CLASS
// DO IMPLEMENT THE PUBLIC CONSTRUCTORS STARTED
// DO NOT ADD OTHER PUBLIC MEMBERS (fields or methods) TO YOUR CLASS
//
// TODO: implement all required methods
//
// TODO: describe the collision resolution scheme you have chosen
// identify your scheme as open addressing or bucket
// I will use chaining to solve collisions.
//
// TODO: explain your hashing algorithm here 
// NOTE: you are not required to design your own algorithm for hashing,
//       since you do not know the type for K,
//       you must use the hashCode provided by the <K key> object
//       and one of the techniques presented in lecture
//
public class HashTable<K extends Comparable<K>, V>
    implements HashTableADT<K, V> {
  private class Node {
    private K key;
    private V value;

    private Node(K key, V value) {
      this.key = key;
      this.value = value;
    }

    private K getKey() {
      return key;
    }

    private V getValue() {
      return value;
    }
  }

  // TODO: ADD and comment DATA FIELD MEMBERS needed for your implementation
  private int tableSize; // curr capacity
  private double loadFactorThreshold; // threshold
  private ArrayList<Node> hashTable;
  private int currentNumberOfElements;

  /**
   * No arg-constructor that sets the capacity of the hashtable to 103, and the
   * load factor threshold to 3/4 full.
   */
  public HashTable() {
    // set to default numbers, 103 and 3/4 full.
    tableSize = 103;
    loadFactorThreshold = 0.75;
    currentNumberOfElements = 0;
    hashTable = new ArrayList<Node>();

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
   */
  public HashTable(int initialCapacity, double loadFactorThreshold) {
    this.tableSize = initialCapacity;
    this.loadFactorThreshold = loadFactorThreshold;
    this.currentNumberOfElements = 0;
  }

  @Override
  public void insert(K key, V value) throws IllegalNullKeyException {
    if (key == null) {
      throw new IllegalNullKeyException("Key is null.");
    }
    int hashIndex = key.hashCode() % tableSize;

    // check if the hashIndex is null
    if (hashTable.get(hashIndex) == null) {
      // insert
      hashTable.set(hashIndex, new Node(key, value));

      // check if need for rehashing and resizing
      double loadFactor = currentNumberOfElements / tableSize;
      if (loadFactor >= loadFactorThreshold) {
        // resize and rehash

      } else {
        return;
      }
    } else {
      // do double hashing for collision resolving
   // start off because the hashIndex is not null
      int currentIndex = hashIndex + 1; 
      for (int i = 2; i < tableSize; i++) {
        // if currIndex == null, insert
        if (hashTable.get(currentIndex) == null) {
          // insert
          hashTable.set(hashIndex, new Node(key, value));

          // check if need for rehashing and resizing
          

        } else if (hashTable.get(currentIndex).key.compareTo(key) == 0) {
          // insert
          hashTable.set(hashIndex, new Node(key, value));

          // check if need for rehashing and resizing
          double loadFactor = currentNumberOfElements / tableSize;

        } else {
          // resize and rehash
        }
        currentIndex += (i*i);
      } // else resize and rehash
      // load factor currently
      double loadFactor = currentNumberOfElements / tableSize;
      
      if(loadFactor >= loadFactorThreshold) {
        // resize and rehash
      }

    }
  }

  private void resize() {
    ArrayList<Node> temp = hashTable;
    
    hashTable = new ArrayList<Node>();
    
    for(int i =0; i< hashTable.size()*2; i++) {
      
    }
  }
  @Override
  public boolean remove(K key) throws IllegalNullKeyException {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * 
   * @param key
   * @throws IllegalNullKeyException
   * @throws KeyNotFoundException
   * @return
   */
  @Override
  public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException("Null key");
    }

    Node keyNode = getNode(key);
    if (keyNode == null) {
      throw new KeyNotFoundException(
          "Key is not in the hash table, cannot get it.");
    } else {
      return keyNode.getValue();
    }
  }

  @Override
  public int numKeys() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double getLoadFactorThreshold() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double getLoadFactor() {
    // may need fixing TODO
    return this.loadFactorThreshold * tableSize;
  }

  @Override
  public int getCapacity() {
    // TODO Auto-generated method stub
    return 0;
  }

  /**
   * Returns 3 - for double hashing
   * 
   * @return double hashing - 3
   */
  @Override
  public int getCollisionResolution() {
    // TODO Auto-generated method stub
    return 3;
  }

  /**
   * Finds the node with the corresponding key
   * 
   * @param key - key of the node
   * @return null if the key is not in the hash table or the node
   */
  private Node getNode(K key) {
    int index = key.hashCode() % tableSize;
    for (Node node : hashTable) {
      if (node == null) {

      } else if (node.key.compareTo(key) == 0) {
        return node; // key is in hash table
      }
    }
    return null; // key is not in hash table

  }
  // TODO: implement all unimplemented methods so that the class can compile

}
