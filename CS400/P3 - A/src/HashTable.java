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

    private int hash() {
      return key.hashCode();
    }
  }

  private int tableSize; // curr capacity
  private double loadFactorThreshold; // threshold
  private Object[] hashTable;
  private int currentNumberOfElements;

  /**
   * No arg-constructor that sets the capacity of the hashtable to 103, and the
   * load factor threshold to 3/4 full.
   */
  public HashTable() {
    // set to default numbers, 123 and the load factor threshold is 3/4 full.
    tableSize = 123;
    loadFactorThreshold = 0.75;
    currentNumberOfElements = 0;
    hashTable = new Object[tableSize];

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
    // TODO: ask if need to do something if the IC and the LFT are negative
    this.tableSize = initialCapacity;
    this.loadFactorThreshold = loadFactorThreshold;
    this.currentNumberOfElements = 0;
    hashTable = new Object[this.tableSize];
  }

  /**
   * Inserts a key-value pair into the hash table, will replace if the
   * 
   * @param key   - key associated with the pair
   * @param value - value
   * @throws IllegalNullKeyException -
   */
  @Override
  public void insert(K key, V value) throws IllegalNullKeyException {
    if (key == null) {
      throw new IllegalNullKeyException("Key is null.");
    }
    int hashIndex = key.hashCode() % tableSize;
    // check if need for rehashing and resizing
    double loadFactor = currentNumberOfElements / (double) tableSize;
    if (loadFactor >= loadFactorThreshold) {
      // resize and rehash
      this.resizeAndRehash();
    }

    Node nodeAtHashIndex = (Node) hashTable[hashIndex];
    // check if the hashIndex is null
    if (hashTable[hashIndex] == null) {
      // insert
      hashTable[hashIndex] = new Node(key, value);
      currentNumberOfElements++;
      return;
    } else if (nodeAtHashIndex.getKey().compareTo(key) == 0) {
      // replace that key-value pair with a new key-value pair
      hashTable[hashIndex] = new Node(key, value);
      // increment the number of key-value pairs in the hash table
      currentNumberOfElements++;
      return;
    } else {
      // do double hashing for collision resolving
      // start off because the hashIndex is not null
      int currentIndex = hashIndex;
      for (int i = 1; i < tableSize; i++) {
        // if currIndex == null, insert
        if (hashTable[currentIndex] == null) {
          // insert if the curr index is null
          hashTable[currentIndex] = new Node(key, value);

          // increment the number of key-value pairs in the hash table
          currentNumberOfElements++;
          return;
          // check if it is on a key that is the same (replace)
        } else if (nodeAtHashIndex.getKey().compareTo(key) == 0) {
          // replace that key-value pair with a new key-value pair
          hashTable[currentIndex] = new Node(key, value);
          // increment the number of key-value pairs in the hash table
          currentNumberOfElements++;
          return;
        }
        currentIndex = (currentIndex + (i * i)) % tableSize;
      }
    }

    currentNumberOfElements++;
  }

  /**
   * Resizes the hash table, rehashes all elements and adds them to the new hash
   * table
   */
  private void resizeAndRehash() {
    // temp var to hold the current 3/4 full hash table
    Object[] temp = hashTable;

    // new hash table that has double size of the old hash table
    hashTable = new Object[tableSize * 2 + 1];

    // set the table size to double the old table size + 1
    this.tableSize = hashTable.length;
    for (int i = 0; i < temp.length; i++) {
      // if the current index is an actual value, add to new hash table
      if (temp[i] != null) {
        Node currNode = (Node) temp[i];
        int hashIndex = currNode.getKey().hashCode() % tableSize;
        hashTable[hashIndex] = currNode;

      }
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
    Node removeNode = this.getNode(key);
    // if getNode() returned null, the key is not in the hash table
    if (removeNode == null) {
      return false;
    }
    // else, the key is in the hash table, so get the hash index
    int keyHashIndex = removeNode.getKey().hashCode() % tableSize;

    // set the key at the hash index to a new key that marks it removed - null
    hashTable[keyHashIndex] = null;
    // decrease the number of elements in the table
    this.currentNumberOfElements--;

    return true; // removed :)
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
    Node keyNode = (Node) hashTable[hashIndex];
    if (keyNode != null) {
      if (keyNode.getKey().compareTo(key) != 0) {
        for (int i = 1; i < tableSize; i++) {
          Node currNode = (Node) hashTable[i];
          if (currNode != null) {
            if (currNode.getKey().compareTo(key) == 0) {
              return currNode; // found key!
            }
          }
        }
        return null; // key is not in the hash table
      }
    }
    return keyNode;
  }

}
