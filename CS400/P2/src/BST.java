import java.util.LinkedList;
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
		 * Constructor that sets the key and value to the ones inputed, also sets the
		 * left and right node to null
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

		private void setRight(Node node) {
			this.rightNode = node;
		}

		private void setLeft(Node node) {
			this.leftNode = node;
		}
	}

	private Node root; // root of the BST
	private int numNodes; // total number of nodes in the BST

	/**
	 * No argument constructor for the BST
	 */
	public BST() {
		root = null;
		numNodes = 0;
	}

	/**
	 * Returns the key that is in the root node of this ST. If root is null, returns
	 * null.
	 * 
	 * @return key found at root node, or null
	 */
	public K getKeyAtRoot() {
		return root.getKey();
	}

	/**
	 * Tries to find a node with a key that matches the specified key. If a matching
	 * node is found, it returns the returns the key that is in the left child. If
	 * the left child of the found node is null, returns null.
	 * 
	 * @param key A key to search for
	 * @return The key that is in the left child of the found key
	 * 
	 * @throws IllegalNullKeyException if key argument is null
	 * @throws KeyNotFoundException    if key is not found in this BST
	 */
	public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		// key arg is null
		if (key == null) {
			throw new IllegalNullKeyException("Null Key");
		}
		// found the node, or didn't and it is null
		Node keyNode = getNode(root, key);
		if (keyNode == null) {
			throw new KeyNotFoundException("Key is not in BST");
		}
		// return the left child's key of the found node
		return keyNode.leftNode.getKey();
	}

	/**
	 * Tries to find a node with a key that matches the specified key. If a matching
	 * node is found, it returns the returns the key that is in the right child. If
	 * the right child of the found node is null, returns null.
	 * 
	 * @param key A key to search for
	 * @return The key that is in the right child of the found key
	 * 
	 * @throws IllegalNullKeyException if key is null
	 * @throws KeyNotFoundException    if key is not found in this BST
	 */
	public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		if (key == null) {
			throw new IllegalNullKeyException("Null key input");
		}
		Node keyNode = this.getNode(root, key);
		// if getNode returns a null,
		if (keyNode == null) {
			throw new KeyNotFoundException("Key is not in BST");
		}

		return keyNode.getRightNode().getKey();
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
	 * Returns the height of this BST. H is defined as the number of levels in the
	 * tree.
	 * 
	 * If root is null, return 0 If root is a leaf, return 1 Else return 1 + max(
	 * height(root.left), height(root.right) )
	 * 
	 * Examples: A BST with no keys, has a height of zero (0). A BST with one key,
	 * has a height of one (1). A BST with two keys, has a height of two (2). A BST
	 * with three keys, can be balanced with a height of two(2) or it may be linear
	 * with a height of three (3) ... and so on for tree with other heights
	 * 
	 * @return the number of levels that contain keys in this BINARY SEARCH TREE
	 */
	public int getHeight() {
		return 1 + maxHeight(root.getLeftNode()) + maxHeight(root.getRightNode());
	}

	/**
	 * Gets the max height of the curr tree
	 * 
	 * @param curr - current node
	 * @return the max height of the curr tree
	 */
	private int maxHeight(Node curr) {
		if (curr == null) {
			return 0;
		} else {
			int left = maxHeight(curr.getLeftNode()); // recur to find the maxHeight in the left subtree
			int right = maxHeight(curr.getRightNode()); // recur to find the maxHeight in the right subtree
			if (left > right) {
				// add one for the current node
				return left + 1;
			} else {
				// add one for the current node
				return right + 1;
			}
		}
	}

	/**
	 * Returns the keys of the data structure in sorted order. In the case of binary
	 * search trees, the visit order is: L V R
	 * 
	 * If the SearchTree is empty, an empty list is returned.
	 * 
	 * @return List of Keys in-order
	 */
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
	 * Recursive helper method to do a pre-order traversal and add each node to the
	 * list.
	 * 
	 * @param currentNode - current node in the BSt
	 * @return a list with all the nodes in the BST in a pre-order, or an empty list
	 *         if the BST is empty
	 */
	private List<Node> preOrderTraversalHelper(Node currentNode) {
		List<Node> preOrderList = new LinkedList<Node>();
		if (currentNode != null) {
			// add the parent to the list.
			preOrderList.add(currentNode);
			// recur on the left sub-tree if currentNode has a left child
			if (currentNode.getLeftNode() != null) {
				List<Node> leftSide = preOrderTraversalHelper(currentNode.getLeftNode());
				preOrderList = transferValues(preOrderList, leftSide);
			}
			// recur on the right sub-tree if the currentNode has a right child
			if (currentNode.getRightNode() != null) {
				List<Node> rightSide = preOrderTraversalHelper(currentNode.getRightNode());
				preOrderList = transferValues(preOrderList, rightSide);
			}
		}
		// base case --> return an empty list
		return preOrderList;
	}

	/**
	 * Returns the keys of the data structure in post-order traversal order. In the
	 * case of binary search trees, the order is: L R V
	 * 
	 * If the SearchTree is empty, an empty list is returned.
	 * 
	 * @return List of Keys in post-order
	 */
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
	 * Recursive helper method to do a post-order traversal and add each node to the
	 * list.
	 * 
	 * @param currentNode - current node in the BSt
	 * @return a list with all the nodes in the BST in a pre-order, or an empty list
	 *         if the BST is empty
	 */
	private List<Node> postOrderTraversalHelper(Node currentNode) {
		List<Node> postOrderList = new LinkedList<Node>();
		if (currentNode != null) {
			// recur on the left sub-tree if currentNode has a left child
			if (currentNode.hasLeft()) {
				List<Node> leftSide = postOrderTraversalHelper(currentNode.getLeftNode());
				postOrderList = transferValues(postOrderList, leftSide);
			}
			// recur on the right sub-tree if the currentNode has a right child
			if (currentNode.hasRight()) {
				List<Node> rightSide = postOrderTraversalHelper(currentNode.getRightNode());
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
			for (int i = 1; i < this.maxHeight(currNode) + 1; i++) {
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
	 * Add the key,value pair to the data structure and increase the number of keys.
	 * If key is null, throw IllegalNullKeyException; If key is already in data
	 * structure, throw DuplicateKeyException(); Do not increase the num of keys in
	 * the structure, if key,value pair is not added.
	 */
	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
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

			curr.setLeft(insertHelp(curr.getLeftNode(), key, value));
		} else if (curr.key.compareTo(key) < 0) {
			// if the key is greater than the current node's key, go right

			curr.setRight(insertHelp(curr.getRightNode(), key, value));
		}
		return curr;

	}

	/**
	 * If key is found, remove the key,value pair from the data structure and
	 * decrease num keys, and return true.
	 * 
	 * @return false if key is not found, do not decrease the number of keys in the
	 *         data structure
	 * @throw IllegalNullKeyException - If key is null
	 */
	public boolean remove(K key) throws IllegalNullKeyException {
		if (key == null) {
			throw new IllegalNullKeyException("Null Key");
		}
		if (!contains(key)) {
			return false;
		}
		removeHelper(root, key);
		numNodes--;
		return true;
	}

	/**
	 * Helper method for remove
	 * 
	 * @param curr - current node
	 * @param key  - key to find to remove
	 * @return the new root (or same root)
	 */
	private Node removeHelper(Node curr, K key) {
		if (curr.getKey().compareTo(key) > 0) {
			// if the curr key is greater than the key, go left
			curr.setLeft(removeHelper(curr.getLeftNode(), key));
		} else if (curr.getKey().compareTo(key) < 0) {
			// if the curr key is less than the key, go right
			curr.setRight(removeHelper(curr.getRightNode(), key));
		} else {
			// found node to delete
			// leaf --> has no children
			if (curr.getLeftNode() == null && curr.getRightNode() == null) {
				curr = null;
			} else if (curr.getLeftNode() == null) {
				// set the curr node to its only child (right child) and set its only child to
				// null
				curr = curr.getRightNode();
				curr.setRight(null);
			} else if (curr.getRightNode() == null) {
				// set the curr node to its only child (left child) and set its only child to
				// null
				curr = curr.getLeftNode();
				curr.setLeft(null);
			} else {
				// Find the successor: leftmost node on the right side
				Node successor = leftMost(curr.getRightNode());
				// remove the successor
				removeHelper(curr, successor.getKey());
				// set curr's key to the successor's key
				curr.setKey(successor.key);
			}
		}
		return curr;
	}

	/**
	 * Helper method that gets the leftmost node from the current node
	 * 
	 * @param curr - current node
	 * @return the leftmost node from the current node.
	 */
	private Node leftMost(Node curr) {
		curr = curr.getLeftNode();
		while (curr.hasRight()) {
			curr = curr.getRightNode();
		}
		return curr;
	}

	/**
	 * Returns the value associated with the specified key. Does not remove key or
	 * decrease number of keys
	 * 
	 * @param key - key to find
	 * @return the value associated with the specified key
	 * @throw IllegalNullKeyException - key is null,
	 * @throw KeyNotFoundException() - key is not found
	 */
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		// if the key is null, throw IllegalNullKeyException
		if (key == null) {
			throw new IllegalNullKeyException("Null key");
		}
		Node keyNode = getNode(root, key);
		// if getNode returns a null, the key is not in the BST
		if (keyNode == null) {
			throw new IllegalNullKeyException("Key is not in BST");
		}
		// otherwise return the key's associated value.
		return keyNode.getValue();
	}

	/**
	 * Returns true if the key is in the data structure
	 * 
	 * @throw IllegalNullKeyException - key is null
	 * @return false if key is not null and is not present
	 */
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
	 * Returns the number of key,value pairs in the data structure
	 * 
	 * @return number of key-value pairs in the BST
	 */
	public int numKeys() {
		return numNodes;
	}

	/**
	 * Print the tree.
	 *
	 * For our testing purposes of your print method: all keys that we insert in the
	 * tree will have a string length of exactly 2 characters. example: numbers
	 * 10-99, or strings aa - zz, or AA to ZZ
	 *
	 * This makes it easier for you to not worry about spacing issues.
	 *
	 * You can display a binary search in any of a variety of ways, but we must see
	 * a tree that we can identify left and right children of each node
	 *
	 * For example:
	 * 
	 * 30 /\ / \ 20 40 / /\ / / \ 10 35 50
	 * 
	 * Look from bottom to top. Inorder traversal of above tree (10,20,30,35,40,50)
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
	// TODO: figure out how to do this
	public void print() {
		List<Node> preOrder = preOrderTraversalHelper(root);
		for (int i = 0; i < preOrder.size(); i++) {
			Node currNode = preOrder.get(i);

			String numSpaces = getNumSpaces(i + 1);
			System.out.println(numSpaces + currNode.getKey());
			if (currNode.hasLeft()) {
				System.out.print(numSpaces + " /");
			}
			if (currNode.hasRight()) {
				System.out.println(numSpaces + "  \\");
			}
		}

	}

	/**
	 * Helper method that returns the number of spaces for a certain number: 1 = 1
	 * spaces etc
	 * 
	 * @param i - number of spaces in the String
	 * @return a String that has a certain number of spaces.
	 */
	private String getNumSpaces(int i) {
		String numSpaces = "";
		for (int spaces = 0; spaces < i; spaces++) {
			numSpaces += " ";
		}
		return numSpaces;
	}

} // copyrighted material, students do not have permission to post on public
	// sites

//  deppeler@cs.wisc.edu
