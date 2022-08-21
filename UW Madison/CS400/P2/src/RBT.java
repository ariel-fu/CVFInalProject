import java.util.LinkedList;
import java.util.List;
//////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           RBT.java
//Files:           BST.java, RBT.java, STADT.java, TestBST.java, TestRBT.java, CompareDS.java
//Course:          (CS400, Spring, 2020)
//
//Author:          (Ariel Fu)
//Email:           (afu5@wisc.edu)
//Lecture Number: 001
//
////////////////////PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////

/**
 * Implements a generic Red-Black tree extension of BST<K,V>.
 *
 * DO NOT CHANGE THE METHOD SIGNATURES OF PUBLIC METHODS DO NOT ADD ANY PACKAGE
 * LEVEL OR PUBLIC ACCESS METHODS OR FIELDS.
 * 
 * You are not required to override remove. If you do not override this
 * operation, you may still have the method in your type, and have the method
 * throw new UnsupportedOperationException. See
 * https://docs.oracle.com/javase/7/docs/api/java/lang/UnsupportedOperationException.html
 * 
 * @param <K> is the generic type of key, must be a Comparable type
 * @param <V> is the generic type of value
 */
public class RBT<K extends Comparable<K>, V> implements STADT<K, V> {
  /**
   * Private class to store key-value pairs
   * 
   * @author Ariel
   *
   */
  class Node {
    private K key;
    private V value;
    private Node leftNode;
    private Node rightNode;
    private int color;
    private Node parent;

    /**
     * Constructor that sets the key and value to the ones inputed, also sets
     * the left and right node to null, and sets the color to red
     * 
     * @param key   - key of the node
     * @param value - value of the node
     */
    public Node(K key, V value) {
      this.key = key;
      this.value = value;
      leftNode = null;
      rightNode = null;
      this.color = 0; // new nodes are set to red
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
     * Returns the value of the Node
     * 
     * @return value of the node
     */
    private V getValue() {
      return value;
    }

    /**
     * Returns the right node of the current node
     * 
     * @return right node of the current node
     */

    private Node getRightNode() {
      return this.rightNode;
    }

    /**
     * Returns the left node of the current node
     * 
     * @return left node of the current node
     */
    private Node getLeftNode() {
      return this.leftNode;
    }

    /**
     * Method that returns if the current node has a right node
     * 
     * @return true if curr node has a child on the right side
     */
    private boolean hasRight() {
      return this.rightNode != null;
    }

    /**
     * Method that returns if the current node has a left node
     * 
     * @return true if curr node has a child on the left side
     */
    private boolean hasLeft() {
      return this.leftNode != null;
    }

    /**
     * Sets the right node to the specified node
     * 
     * @param node - specified node
     */
    // TODO:private void setRight(Node node) {
    protected void setRight(Node node) {
      this.rightNode = node;
    }

    /**
     * Sets the left node to the specified node
     * 
     * @param node - specified node
     */
    // TODO:private void setLeft(Node node) {
    public void setLeft(Node node) {
      this.leftNode = node;
    }

    /**
     * Sets the color of the node
     * 
     * @param color - either red or black
     */

    // TODO:private void setColor(int color) {
    public void setColor(int color) {
      this.color = color;
    }

    /**
     * Getter method for the color of the current node
     * 
     * @return color of the node
     */
    private int getColor() {
      return this.color;
    }

    public Node getParent() {
      return this.parent;
    }

    public void setParent(Node P) {
      this.parent = P;
    }

  }

  // USE AND DO NOT EDIT THESE CONSTANTS
  public static final int RED = 0;
  public static final int BLACK = 1;

  // TODO: private Node root; // root of the RBT
  protected Node root; // root of the RBT
  private int numKeys;

  /**
   * No argument constructor that sets the root to null
   */
  public RBT() {
    root = null;
  }

  /**
   * Returns the color of the node that contains the specified key. Returns
   * RBT.RED if the node is red, and RBT.BLACK if the node is black. Returns -1
   * if the node is not found.
   * 
   * @param key - color of the node with specific key
   * @return the value for RBT.RED if the node is red, and RBT.BLACK, and -1 if
   *         the node is not found
   */
  public int colorOf(K key) {
    Node keyNode = getNode(root, key);
    if (keyNode == null) {
      return -1;
    }
    if (keyNode.getColor() == 0) {
      return RBT.RED;
    } else {
      return RBT.BLACK;
    }
//		Node found = getNodeWith(root, key); // TODO Auto-generated method stub
//		return found == null ? -1 : found.color;
  }

  /**
   * Returns true if the color of the root is black. If root is null, returns
   * BLACK.
   * 
   * @return true if root is black, else returns false (for red)
   */
  public boolean rootIsBlack() {
    return root.getColor() == BLACK;
  }

  /**
   * Returns true if the node that contains this key is RED. If key is null,
   * 
   * @throws IllegalNullKeyException - if the key is null
   * @throws KeyNotFoundException    - if the key is not found
   * 
   * @return true if the key is found and the node's color is RED, else return
   *         false if key is found and the node's color is BLACK.
   */
  public boolean isRed(K key)
      throws IllegalNullKeyException, KeyNotFoundException {
    // if the key is null
    if (key == null) {
      throw new IllegalNullKeyException("Null key");
    }
    Node node = getNode(root, key);
    // if the key is not found
    if (node == null) {
      throw new KeyNotFoundException("Key not found");
    }
    return node.getColor() == RED;
  }

  /**
   * Returns true if the node that contains this key is BLACK.
   * 
   * @throws IllegalNullKeyException - if the key is null
   * @throws KeyNotFoundException    - if the key is not found
   * 
   * @return true if the key is found and the node's color is BLACK, else return
   *         false if key is found and the node's color is RED.
   */
  public boolean isBlack(K key) throws IllegalNullKeyException, KeyNotFoundException {
    // if the key is null
    if (key == null) {
      throw new IllegalNullKeyException("Null key");
    }
    Node node = getNode(root, key);
    // if the key is not found
    if (node == null) {
      throw new KeyNotFoundException("Key not found");
    }
    return node.getColor() == BLACK;
  }

  /**
   * Returns the key at the root
   * 
   * @return root
   */
  @Override
  public K getKeyAtRoot() {
    return root.getKey();
  }

  /**
   * Returns the key of the left child of the node with the specified key
   * 
   * @param key - key of the node to get the left child
   * @return the key of the left child of the node with the specified key
   * @throws IllegalNullKeyException - if the key is null
   * @throws KeyNotFoundException    - if the key is not in the RBT
   */
  @Override
  public K getKeyOfLeftChildOf(K key)
      throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException("Null key");
    }
    Node keyNode = getNode(root, key);
    if (keyNode == null) {
      throw new KeyNotFoundException("Key is not in RBT");
    }
    return keyNode.getLeftNode().getKey();
  }

  /**
   * Gets the key of the right child of the node with the specified key
   * 
   * @param key - key of the node
   * @return the key of the right child of the node with the specified key
   * @throws IllegalNullKeyException - if the key is null
   * @throws KeyNotFoundException    - if the key is not in the RBT
   */
  @Override
  public K getKeyOfRightChildOf(K key)
      throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException("Null key");
    }
    Node keyNode = getNode(root, key);
    if (keyNode == null) {
      throw new KeyNotFoundException("Key is not in RBT");
    }
    return keyNode.getRightNode().getKey();
  }

  // TODO: fix this
  /**
   * Gets the max height of the RBT
   * 
   * @return the number of levels that contains keys in the RBT
   */
  @Override
  public int getHeight() {
    if (root == null) {
      return 0;
    } else if (!root.hasLeft() && !root.hasRight()) {
      return 1;
    } else {
      return heightHelper(root);
    }
  }

  /**
   * Helper method that gets the max height of the current tree
   * 
   * @param curr - root of the current tree
   * @return the max height of the curr tree
   */
  private int heightHelper(Node curr) {
    if (curr == null) {
      return 0;
    } else {
      int left = heightHelper(curr.getLeftNode()); // recur to find the
      // maxHeight in the left
      // subtree
      int right = heightHelper(curr.getRightNode()); // recur to find the
      // maxHeight in the right
      // subtree
      if (left > right) {
        // add one for the current node
        return left + 1;
      } else {
        // add one for the current node
        return right + 1;
      }
    }
  }

  @Override
  public List<K> getInOrderTraversal() {
    List<K> listOfKeys = new LinkedList<K>();
    List<Node> listOfNodes = inOrderHelper(root);
    for (int i = 0; i < listOfNodes.size(); i++) {
      listOfKeys.add(listOfNodes.get(i).getKey());
    }
    return listOfKeys;
  }

  /**
   * Helper method for the in-order traversal
   * 
   * @param currNode - current node in the BST
   * @return an empty list of the BST is null, or a list with the nodes in
   *         ascending order
   */
  private List<Node> inOrderHelper(Node currNode) {
    List<Node> inOrderList = new LinkedList<Node>();
    // recursive cases
    if (currNode != null) {
      // recur on the left sub-tree if currentNode has a left child
      if (currNode.hasLeft()) {
        List<Node> leftSide = inOrderHelper(currNode.getLeftNode());
        inOrderList = transferValues(inOrderList, leftSide);
      }
      // process the parent
      inOrderList.add(currNode);
      // recur on the right sub-tree if the currentNode has a right child
      if (currNode.hasRight()) {
        List<Node> rightSide = inOrderHelper(currNode.getRightNode());
        inOrderList = transferValues(inOrderList, rightSide);
      }
    }

    // Base case: empty subtree (currentNode is null) --> return an empty list
    return inOrderList;
  }

  /**
   * Helper method for transfering values of one list to another
   * 
   * @param receiver - taking the values
   * @param giver    - giving the values
   * @return receiver with all the new values from the giver list.
   */
  private List<Node> transferValues(List<Node> receiver, List<Node> giver) {
    for (int i = 0; i < giver.size(); i++) {
      receiver.add(giver.get(i));
    }
    return receiver;
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
    // the helper method returns a list of nodes
    List<Node> listOfNode = preOrderTraversalHelper(root); // recursive method
    List<K> listOfKey = new LinkedList<K>();
    // get all the nodes' keys into a new list
    for (int i = 0; i < listOfNode.size(); i++) {
      Node currNode = listOfNode.get(i);
      listOfKey.add(currNode.getKey());
    }
    return listOfKey;
  }

  /**
   * Recursive helper method to do a pre-order traversal and add each node to
   * the list.
   * 
   * @param currentNode - current node in the BST
   * @return a list with all the nodes in the BST in a pre-order, or an empty
   *         list if the BST is empty
   */

  private List<Node> preOrderTraversalHelper(Node currentNode) {
    List<Node> preOrderList = new LinkedList<Node>();
    if (currentNode != null) {
      // add the parent to the list.
      preOrderList.add(currentNode);
      // recur on the left sub-tree if currentNode has a left child
      if (currentNode.getLeftNode() != null) {
        List<Node> leftSide = preOrderTraversalHelper(
            currentNode.getLeftNode());
        preOrderList = transferValues(preOrderList, leftSide);
      }
      // recur on the right sub-tree if the currentNode has a right child
      if (currentNode.getRightNode() != null) {
        List<Node> rightSide = preOrderTraversalHelper(
            currentNode.getRightNode());
        preOrderList = transferValues(preOrderList, rightSide);
      }
    }
    // base case --> return an empty list
    return preOrderList;
  }

  /**
   * Returns the keys of the data structure in post-order traversal order. In
   * the case of binary search trees, the order is: L R V
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in post-order
   */
  @Override
  public List<K> getPostOrderTraversal() {
    List<Node> listOfNodes = postOrderTraversalHelper(root);
    List<K> listOfKeys = new LinkedList<K>();
    for (int i = 0; i < listOfNodes.size(); i++) {
      K currkey = listOfNodes.get(i).getKey();
      listOfKeys.add(currkey);
    }
    return listOfKeys;
  }

  /**
   * Recursive helper method to do a post-order traversal and add each node to
   * the list.
   * 
   * @param currentNode - current node in the BSt
   * @return a list with all the nodes in the BST in a pre-order, or an empty
   *         list if the BST is empty
   */
  private List<Node> postOrderTraversalHelper(Node currentNode) {
    List<Node> postOrderList = new LinkedList<Node>();
    if (currentNode != null) {
      // recur on the left sub-tree if currentNode has a left child
      if (currentNode.hasLeft()) {
        List<Node> leftSide = postOrderTraversalHelper(
            currentNode.getLeftNode());
        postOrderList = transferValues(postOrderList, leftSide);
      }
      // recur on the right sub-tree if the currentNode has a right child
      if (currentNode.hasRight()) {
        List<Node> rightSide = postOrderTraversalHelper(
            currentNode.getRightNode());
        postOrderList = transferValues(postOrderList, rightSide);
      }
      // add the parent to the list.
      postOrderList.add(currentNode);

    }
    // base case --> return an empty list
    return postOrderList;
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
  @Override
  public List<K> getLevelOrderTraversal() {
    List<K> listOfKeys = new LinkedList<K>();
    List<Node> listOfNodes = levelOrderTraversalHelper(root);
    for (int i = 0; i < listOfNodes.size(); i++) {
      listOfKeys.add(listOfNodes.get(i).getKey());
    }
    return listOfKeys;
  }

  /**
   * Helper method/recursive method that traverses each level and adds to the
   * list.
   * 
   * @param currNode - current node
   * @return a list of nodes in order by level
   */
  private List<Node> levelOrderTraversalHelper(Node currNode) {
    List<Node> levelOrder = new LinkedList<Node>();
    if (currNode != null) {
      for (int i = 1; i < this.getHeight() + 1; i++) {
        levelOrder = transferValues(levelOrder, givenLevel(currNode, i));
      }
    }
    return levelOrder;
  }

  /**
   * Helper method that will add to a list the nodes at a given level
   * 
   * @param currNode - current node
   * @param level    - given level
   * @return a list with the nodes at a given level
   */
  private List<Node> givenLevel(Node currNode, int level) {
    List<Node> list = new LinkedList<Node>();
    if (currNode == null) {
      return list;
    }
    if (level == 1) {
      // add the current node
      list.add(currNode);
    } else if (level > 1) {
      // traverse the level under the current level
      transferValues(list, givenLevel(currNode.getLeftNode(), level - 1));
      transferValues(list, givenLevel(currNode.getRightNode(), level - 1));
    }
    return list;
  }

  /**
   * Inserts into the tree using a BST insert algorithm and then fixes the tree
   * 
   * @param
   */
  @Override
  public void insert(K key, V value)
      throws IllegalNullKeyException, DuplicateKeyException {
    // TODO Auto-generated method stub
    if (key == null) {
      throw new IllegalNullKeyException("Null key");
    } else if (this.contains(key)) {
      throw new DuplicateKeyException("Duplicate key");
    }
    // insert the new node
    BSTInsert(root, key, value);
    fixTree(getNode(root, key)); // start fixing the tree where we inserted the
    // new key.
    // increase the number of keys in the tree
    numKeys++;
  }

  /**
   * Fixes the tree after an insert
   * 
   * @param child - node that was just inserted
   */
  protected void fixTree(Node child) {
    // get the parent of the currNode --> P
    Node parent = child.getParent();
    if (parent == null) {
      // CASE #1: the current node is the root
      child.setColor(BLACK);
      return;
    } else if (parent.getColor() == BLACK) {
      // CASE #2: The parent of the current node is black so no properties are
      // violated.
      return;
    } else {
      // get the parent of the parent --> Grandparent --> G
      Node grandParent = parent.getParent();
      // get the sibling of the parent --> PS
      Node parentSibling = getSibling(grandParent, parent);

      if (parentSibling != null && parentSibling.getColor() == RED) {
        // case #3 --> recoloring
        parent.setColor(BLACK);
        parentSibling.setColor(BLACK);
        grandParent.setColor(RED);

        // fix grandparent after recoloring
        fixTree(grandParent);
      } else {
        // case #4 --> tri-node restructuring, parent sibling = black, parent =
        // red
        if (child == parent.getRightNode()
            && parent == grandParent.getLeftNode()) {
          rotateLeft(parent);
          child = child.getLeftNode();

        } else if (child == parent.getLeftNode()
            && parent == grandParent.getRightNode()) {
          rotateRight(parent);
          child = child.getRightNode();
        }

        // part 2
        stepTwoTNR(child);
      }
    }
  }

  private void stepTwoTNR(Node child) {
    Node parent = child.getParent();
    if (parent != null) {
      Node grandParent = parent.getParent();
      if (child == parent.getLeftNode()) {
        rotateRight(grandParent);
      } else {
        rotateLeft(grandParent);
      }

      grandParent.setColor(RED);
    }
    parent.setColor(BLACK);
  }

  /**
   * Rotates the subtree to the right
   * 
   * @param oldRoot - root of the subtree
   * 
   */
  private void rotateRight(Node oldRoot) {
    // set the new root to the left child of the old root
    Node newRoot = oldRoot.getLeftNode();
    Node rootParent = oldRoot.getParent();
    if (newRoot == null) {
      return;
    }
    oldRoot.setLeft(newRoot.getRightNode());
    newRoot.setRight(oldRoot);
    oldRoot.setParent(newRoot);

    // handle the child of the left node of the oldRoot
    if (oldRoot.getLeftNode() != null) {
      oldRoot.getLeftNode().setParent(oldRoot);
    }

    // if oldRoot is THE root of the RBT
    if (rootParent != null) {
      if (oldRoot == rootParent.getLeftNode()) {
        rootParent.setLeft(newRoot);
      } else {
        rootParent.setRight(newRoot);
      }
    } else {
      root = newRoot;
    }
    newRoot.setParent(rootParent);

  }

  /**
   * Rotates the subtree to the left
   * 
   * @param oldRoot - root of the subtree
   */
  private void rotateLeft(Node oldRoot) {
    // set the new root to the right child of the old root
    Node newRoot = oldRoot.getRightNode();
    Node rootParent = oldRoot.getParent();
    if (newRoot == null) {
      return;
    }
    oldRoot.setRight(newRoot.getLeftNode());
    newRoot.setLeft(oldRoot);
    oldRoot.setParent(newRoot);

    // handle the child of the right node of the oldRoot
    if (oldRoot.getRightNode() != null) {
      oldRoot.getRightNode().setParent(oldRoot);
    }
    // if oldRoot is THE root of the RBT
    if (rootParent != null) {
      if (oldRoot == rootParent.getLeftNode()) {
        rootParent.setLeft(newRoot);

      } else if (oldRoot == rootParent.getRightNode()) {
        rootParent.setRight(newRoot);

      }
    } else {

      root = newRoot;
    }
    newRoot.setParent(rootParent);

  }

  /**
   * Gets the sibling of the curr child
   * 
   * @param parent    - parent of both children
   * @param currChild - curr child
   * @return the sibling of the current child.
   */
  private Node getSibling(Node parent, Node currChild) {
    // if the curr child is bigger than the parent, the sibling is the left
    // child
    if (currChild.getKey().compareTo(parent.getKey()) > 0) {
      return parent.getLeftNode();
    } else {
      // otherwise the sibling is the right child.
      return parent.getRightNode();
    }
  }

  /**
   * Finds the spot to put the node with a key and puts it there
   * 
   * @param curr  - current node
   * @param key   - key of the node to insert in the BST
   * @param value - value of the node to insert in the BST
   * @return root of the curr BST
   */
  // TODO: change back to private
  protected void BSTInsert(Node parent, K key, V value) {
    Node curr;
    if (parent == null) {
      root = CreateNode(parent, key, value);
    } else if (parent.key.compareTo(key) > 0) {
      // if the key is less than the current node's key, go left
      curr = parent.getLeftNode();
      if (curr == null) {
        parent.setLeft(CreateNode(parent, key, value));
      } else {
        BSTInsert(curr, key, value);
      }
    } else if (parent.key.compareTo(key) < 0) {
      // if the key is greater than the current node's key, go right
      curr = parent.getRightNode();
      if (curr == null) {
        parent.setRight(CreateNode(parent, key, value));
      } else {
        BSTInsert(curr, key, value);
      }
    }
  }

  private Node CreateNode(Node parent, K key, V value) {
    Node current = new Node(key, value);
    // insert as red
    current.setColor(RED);
    current.setParent(parent);
    return current;
  }

  /**
   * This method is not supported
   * 
   * @throws UnsupportedOperationException - remove is not implemented
   */
  @Override
  public boolean remove(K key) throws IllegalNullKeyException {
    throw new UnsupportedOperationException("Unsupported, insert only :)");
  }

  /**
   * Gets the value of the key
   * 
   * @param key - key of the value to find
   * @return value of the key
   * @throws IllegalNullKeyException - if the key is null
   * @throws KeyNotFoundException    - if the key is not in the array
   */
  @Override
  public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException("Null key");
    } else {
      Node keyNode = getNode(root, key);
      if (keyNode == null) {
        throw new KeyNotFoundException("Key is not in RBT");
      } else {
        return keyNode.getValue();
      }
    }
  }

  /**
   * Returns true if the key is in the data structure
   * 
   * @throw IllegalNullKeyException - key is null
   * @return false if key is not null and is not present
   */
  @Override
  public boolean contains(K key) throws IllegalNullKeyException {
    if (key == null) {
      throw new IllegalNullKeyException("Null Key");
    }
    Node keyNode = getNode(root, key);
    if (keyNode == null) {
      return false;
    }
    return true;
  }

  /**
   * Tries to find Node given the key.
   * 
   * @param key - key of the Node
   * @return node with associated key
   */
  private Node getNode(Node curr, K key) {
    // if the curr node is null, the BST is empty
    if (curr == null) {
      return null;
    }
    // otherwise, start comparing and searching
    if (curr.key.compareTo(key) > 0) {
      // the key is less than the current node's key, go left
      curr = getNode(curr.leftNode, key);
    } else if (curr.key.compareTo(key) < 0) {
      // the key is greater than the current node's key, go right
      curr = getNode(curr.getRightNode(), key);
    }
    // found the node with matching keys
    return curr;

  }

  /**
   * Gets the number of keys
   * 
   * @return the number of keys in the RBT
   */
  @Override
  public int numKeys() {
    return numKeys;
  }

  /**
   * Prints out the RBT by level, with X for null nodes.
   */
  @Override
  public void print() {
    System.out.println(
        "------------------------------------------------------------------------");
    int maxLevel = this.getHeight();
    List<Node> children = new LinkedList<Node>();
    children.add(root);
    printChildren(children, maxLevel - 1);

  }

  /**
   * Prints the children from the list
   * 
   * @param children     - list of the children
   * @param currentLevel - current level in RBT
   */
  private void printChildren(List<Node> children, int currentLevel) {
    for (int i = 0; i < children.size(); i++) {
      String str = "X";
      Node child = children.get(i);

      if (child != null) {
        K key = child.getKey();
        if (key != null) {
          str = key.toString();
        }
      }

      printNode(str, currentLevel);

      printNode("", currentLevel);

    }

    System.out.println();
    if (currentLevel == 0) {
      return;
    }

    Node nullNode = new Node(null, null);
    List<Node> grandChildren = new LinkedList<Node>();
    for (int i = 0; i < children.size(); i++) {
      Node child = children.get(i);
      if (child == null) {
        grandChildren.add(nullNode);
        grandChildren.add(nullNode);
      } else {
        grandChildren.add(child.getLeftNode());
        grandChildren.add(child.getRightNode());
      }
    }
    printChildren(grandChildren, currentLevel - 1);

  }

  /**
   * Prints out the node with adequate spacing
   * 
   * @param str
   * @param level
   */
  private void printNode(String str, int level) {
    for (int i = 0; i < (int) Math.pow(2, level) - 1; i++) {
      System.out.print("\t");
    }
    System.out.print(str);
    System.out.print("\t");
  }

  // TODO: override the insert method so that it rebalances
  // according to red-black tree insert algorithm.

  // TODO [OPTIONAL] you may override print() to include
  // color R-red or B-black.

}

class FakeRBT extends RBT<Integer, Integer> {
  public Node getFakeNode(int v, int c, Node p) {
    Node fake = new Node(v, v);
    fake.setParent(p);
    fake.setColor(c);
    return fake;
  }

  protected void buildTestCase2A() {
    root = getFakeNode(40, BLACK, null);
    Node child20 = getFakeNode(20, RED, root);
    root.setLeft(child20);
    Node child45 = getFakeNode(45, BLACK, root);
    root.setRight(child45);

    Node child15 = getFakeNode(10, RED, child20);
    child20.setLeft(child15);

    print();
    this.fixTree(child15);
    System.out.println("F I X E D :");
    print();
  }

  protected void testCaseExtreme() {
    root = getFakeNode(40, BLACK, null);
    Node child20 = getFakeNode(20, RED, root);
    root.setLeft(child20);
    Node child45 = getFakeNode(45, BLACK, root);
    root.setRight(child45);
    Node child30 = getFakeNode(30, RED, child20);
    child20.setRight(child30);
    Node child35 = getFakeNode(35, RED, child30);
    child30.setRight(child35);
    Node child25 = getFakeNode(25, RED, child30);
    child30.setLeft(child25);
//		Node child10 = getFakeNode(10, RED, child20);
//		child20.setLeft(child10);

    print();

//		this.fixTree(child10);

  }

  protected void testCaseNotARealCase() {
    root = getFakeNode(40, BLACK, null);
    Node child30 = getFakeNode(30, RED, root);
    Node child45 = getFakeNode(45, BLACK, root);
    root.setRight(child45);
    root.setLeft(child30);
    Node child20 = getFakeNode(20, RED, child30);
    child30.setLeft(child20);
    System.out.println();
    System.out.println(" NOT A REAL CASE! ");
    System.out.println();
    print();
    fixTree(child20);
//    Node child35 = getFakeNode(35, BLACK, child30);
//    child30.setRight(child35);
//    Node child25 = getFakeNode(25, RED, child20);
//    child20.setRight(child25);
//    Node child10 = getFakeNode(10, RED, child20);
//    child20.setLeft(child10);
//
//    print();
//
//    Node child0 = getFakeNode(0, RED, child10);
//    child10.setLeft(child0);
//
//    fixTree(child0);
    print();
  }

}
