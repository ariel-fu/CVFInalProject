import java.util.List;

/**
 * Defines the operations required of student's BST class.
 *
 * NOTE: There are many methods in this interface that are required solely to
 * support gray-box testing of the internal tree structure. They must be
 * implemented as described to pass all grading tests.
 * 
 * @author Deb Deppeler (deppeler@cs.wisc.edu)
 * @param <K> A Comparable type to be used as a key to an associated value.
 * @param <V> A value associated with the given key.
 */
public class BST<K extends Comparable<K>, V> implements STADT<K, V> {
  /**
   * Private class to store key-value pairs
   * 
   * @author Ariel
   *
   */
  private class Node {
    private K key;
    private V value;
    private Node leftNode;
    private Node rightNode;

    /**
     * Constructor that sets the key and value to the ones inputed, also sets
     * the left and right node to null
     * 
     * @param key   - key of the node
     * @param value - value of the node
     */
    private Node(K key, V value) {
      this.key = key;
      this.value = value;
      leftNode = null;
      rightNode = null;
    }

    /**
     * Helper method to get the key of the Node
     * 
     * @return key of the Node
     */
    private K getKey() {
      return key;
    }

    /**
     * Helper method to set the key of the Node
     * 
     * @param newKey - new value of the key in the Node
     */
    private void setKey(K key) {
      this.key = key;
    }

    /**
     * Returns the value of the Node
     * 
     * @return value of the node
     */
    private V getValue() {
      return value;
    }

    /**
     * Sets the right node to the node inputted
     * 
     * @param node - node to replace the right child node
     */
    private void setRight(Node node) {
      this.rightNode = node;
    }

    /**
     * Sets the left node to the node inputted
     * 
     * @param node - node to replace the left child node
     */
    private void setLeft(Node node) {
      this.leftNode = node;
    }
  }

  private Node root; // root of the BST
  private int numNodes; // total number of nodes in the BST

  /**
   * Returns the key that is in the root node of this ST. If root is null,
   * returns null.
   * 
   * @return key found at root node, or null
   */
  public K getKeyAtRoot() {
    return root.getKey();
  }

  /**
   * Tries to find a node with a key that matches the specified key. If a
   * matching node is found, it returns the returns the key that is in the left
   * child. If the left child of the found node is null, returns null.
   * 
   * @param key A key to search for
   * @return The key that is in the left child of the found key
   * 
   * @throws IllegalNullKeyException if key argument is null
   * @throws KeyNotFoundException    if key is not found in this BST
   */
  public K getKeyOfLeftChildOf(K key)
      throws IllegalNullKeyException, KeyNotFoundException {

    return null;
  }

  /**
   * Tries to find a node with a key that matches the specified key. If a
   * matching node is found, it returns the returns the key that is in the right
   * child. If the right child of the found node is null, returns null.
   * 
   * @param key A key to search for
   * @return The key that is in the right child of the found key
   * 
   * @throws IllegalNullKeyException if key is null
   * @throws KeyNotFoundException    if key is not found in this BST
   */
  public K getKeyOfRightChildOf(K key)
      throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException("Null key input");
    }
    Node node = this.getNode(key);

    return null;
  }

  /**
   * Tries to find Node given the key.
   * 
   * @param key - key of the Node
   * @return node with associated key
   */
  private Node getNode(K key) {
    // TODO: implement get and this will come easily (hopefully)
    return null;
  }

  /**
   * Returns the height of this BST. H is defined as the number of levels in the
   * tree.
   * 
   * If root is null, return 0 If root is a leaf, return 1 Else return 1 + max(
   * height(root.left), height(root.right) )
   * 
   * Examples: A BST with no keys, has a height of zero (0). A BST with one key,
   * has a height of one (1). A BST with two keys, has a height of two (2). A
   * BST with three keys, can be balanced with a height of two(2) or it may be
   * linear with a height of three (3) ... and so on for tree with other heights
   * 
   * @return the number of levels that contain keys in this BINARY SEARCH TREE
   */
  public int getHeight() {
    return 0;
  }

  /**
   * Returns the keys of the data structure in sorted order. In the case of
   * binary search trees, the visit order is: L V R
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in-order
   */
  public List<K> getInOrderTraversal() {
    return null;
  }

  /**
   * Returns the keys of the data structure in pre-order traversal order. In the
   * case of binary search trees, the order is: V L R
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in pre-order
   */
  public List<K> getPreOrderTraversal() {
    return null;
  }

  /**
   * Returns the keys of the data structure in post-order traversal order. In
   * the case of binary search trees, the order is: L R V
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in post-order
   */
  public List<K> getPostOrderTraversal() {
    return null;
  }

  /**
   * Returns the keys of the data structure in level-order traversal order.
   * 
   * The root is first in the list, then the keys found in the next level down,
   * and so on.
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in level-order
   */
  public List<K> getLevelOrderTraversal() {
    return null;
  }

  /**
   * Add the key,value pair to the data structure and increase the number of
   * keys. If key is null, throw IllegalNullKeyException; If key is already in
   * data structure, throw DuplicateKeyException(); Do not increase the num of
   * keys in the structure, if key,value pair is not added.
   */
  public void insert(K key, V value)
      throws IllegalNullKeyException, DuplicateKeyException {
    if (key == null) {
      throw new IllegalNullKeyException("Key is null");
    }
    // TODO: implement contains method
    if (contains(key)) {
      throw new DuplicateKeyException("Duplicate key!");
    }
    root = insertHelp(root, key, value);
    numNodes++;
    return;
  }

  /**
   * Finds the spot to put the node with a key and puts it there
   * 
   * @param curr  - current node
   * @param key   - key of the node to insert in the BST
   * @param value - value of the node to insert in the BST
   * @return root of the curr BST
   */
  private Node insertHelp(Node curr, K key, V value) {
    if (curr == null) {
      curr = new Node(key, value);
    } else if (curr.key.compareTo(key) > 0) {
      // if the key is less than the current node's key, go left

      curr.setLeft(insertHelp(curr.leftNode, key, value));
    } else if (curr.key.compareTo(key) < 0) {
      // if the key is greater than the current node's key, go right

      curr.setRight(insertHelp(curr.rightNode, key, value));
    }
    return curr;

  }

  /**
   * If key is found, remove the key,value pair from the data structure and
   * decrease num keys, and return true. If key is not found, do not decrease
   * the number of keys in the data structure, return false. If key is null,
   * throw IllegalNullKeyException
   */
  public boolean remove(K key) throws IllegalNullKeyException {
    if(key == null) {
      throw new IllegalNullKeyException("Null Key");
    }
    if(!contains(key)) {
      return false;
    }
    numNodes--;
    return true;
  }

  /**
   * Helper method for remove
   * @param curr - current node
   * @param key - key to find to remove
   * @return
   */
  private Node removeHelper(Node curr, K key) {
    if(curr.getKey().compareTo(key) > 0) {
      // if the curr key is greater than the key, go left
      curr.setLeft(removeHelper(curr.leftNode, key));
    } else if(curr.getKey().compareTo(key)<0) {
      // if the curr key is less than the key, go right
      curr.setRight(removeHelper(curr.rightNode, key));
    } else {
      // found node to delete
      // leaf --> has no children
      if(curr.leftNode == null && curr.rightNode == null) {
        curr = null;
      } else if(curr.leftNode == null) {
        // set the curr node to its only child (right child) and set its only child to null
        curr = curr.rightNode;
        curr.rightNode = null;
      } else if(curr.rightNode == null) {
        // set the curr node to its only child (left child) and set its only child to null
        curr = curr.leftNode;
        curr.leftNode = null;
      } else {
        // has two children, so set curr to its 
      }
    }
    return curr;
  }
  
  /**
   * Returns the value associated with the specified key.
   *
   * Does not remove key or decrease number of keys If key is null, throw
   * IllegalNullKeyException If key is not found, throw KeyNotFoundException().
   */
  public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    return null;
  }

  /**
   * Returns true if the key is in the data structure If key is null, throw
   * IllegalNullKeyException Returns false if key is not null and is not present
   */
  public boolean contains(K key) throws IllegalNullKeyException {
    return false;
  }

  /**
   * Returns the number of key,value pairs in the data structure
   */
  public int numKeys() {
    return 0;
  }

  /**
   * Print the tree.
   *
   * For our testing purposes of your print method: all keys that we insert in
   * the tree will have a string length of exactly 2 characters. example:
   * numbers 10-99, or strings aa - zz, or AA to ZZ
   *
   * This makes it easier for you to not worry about spacing issues.
   *
   * You can display a binary search in any of a variety of ways, but we must
   * see a tree that we can identify left and right children of each node
   *
   * For example:
   * 
   * 30 /\ / \ 20 40 / /\ / / \ 10 35 50
   * 
   * Look from bottom to top. Inorder traversal of above tree
   * (10,20,30,35,40,50)
   * 
   * Or, you can display a tree of this kind.
   * 
   * | |-------50 |-------40 | |-------35 30 |-------20 | |-------10
   * 
   * Or, you can come up with your own orientation pattern, like this.
   * 
   * 10 20 30 35 40 50
   * 
   * The connecting lines are not required if we can interpret your tree.
   * 
   */
  public void print() {
    System.out.println("not yet implemented");
  }

} // copyrighted material, students do not have permission to post on public
  // sites

//  deppeler@cs.wisc.edu
