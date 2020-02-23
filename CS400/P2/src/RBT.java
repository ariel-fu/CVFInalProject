import java.util.LinkedList;
import java.util.List;

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
 * @param <K> is the generic type of key, must be a Comparable tyle
 * @param <V> is the generic type of value
 */
public class RBT<K extends Comparable<K>, V> implements STADT<K, V> {
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
		private int color;

		/**
		 * Constructor that sets the key and value to the ones inputed, also sets the
		 * left and right node to null, and sets the color to red
		 * 
		 * @param key   - key of the node
		 * @param value - value of the node
		 */
		private Node(K key, V value) {
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
		private Node getRightChild() {
			return this.rightNode;
		}

		/**
		 * Returns the left node of the current node
		 * 
		 * @return left node of the current node
		 */
		private Node getLeftChild() {
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
		private void setRight(Node node) {
			this.rightNode = node;
		}

		/**
		 * Sets the left node to the specified node
		 * 
		 * @param node - specified node
		 */
		private void setLeft(Node node) {
			this.leftNode = node;
		}

		/**
		 * Sets the color of the node
		 * 
		 * @param color - either red or black
		 */
		private void setColor(int color) {
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
	}

	// USE AND DO NOT EDIT THESE CONSTANTS
	public static final int RED = 0;
	public static final int BLACK = 1;

	Node root; // root of the RBT

	/**
	 * No argument constructor that sets the root to null
	 */
	public RBT() {
		root = null;
	}

	/**
	 * Returns the color of the node that contains the specified key. Returns
	 * RBT.RED if the node is red, and RBT.BLACK if the node is black. Returns -1 if
	 * the node is not found.
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
	public boolean isRed(K key) throws IllegalNullKeyException, KeyNotFoundException {
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
	public boolean isBlack(K key) {
		return false;
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
	public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		if (key == null) {
			throw new IllegalNullKeyException("Null key");
		}
		Node keyNode = getNode(root, key);
		if (keyNode == null) {
			throw new KeyNotFoundException("Key is not in RBT");
		}
		return keyNode.getLeftChild().getKey();
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
	public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		if (key == null) {
			throw new IllegalNullKeyException("Null key");
		}
		Node keyNode = getNode(root, key);
		if (keyNode == null) {
			throw new KeyNotFoundException("Key is not in RBT");
		}
		return keyNode.getRightChild().getKey();
	}

	/**
	 * Gets the max height of the RBT
	 * 
	 * @return the number of levels that contains keys in the RBT
	 */
	@Override
	public int getHeight() {
		return 1 + heightHelper(root.getLeftChild()) + heightHelper(root.getRightChild());
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
			int left = heightHelper(curr.getLeftChild()); // recur to find the maxHeight in the left subtree
			int right = heightHelper(curr.getRightChild()); // recur to find the maxHeight in the right subtree
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
				List<Node> leftSide = inOrderHelper(currNode.getLeftChild());
				inOrderList = transferValues(inOrderList, leftSide);
			}
			// process the parent
			inOrderList.add(currNode);
			// recur on the right sub-tree if the currentNode has a right child
			if (currNode.hasRight()) {
				List<Node> rightSide = inOrderHelper(currNode.getRightChild());
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
	 * Recursive helper method to do a pre-order traversal and add each node to the
	 * list.
	 * 
	 * @param currentNode - current node in the BST
	 * @return a list with all the nodes in the BST in a pre-order, or an empty list
	 *         if the BST is empty
	 */

	private List<Node> preOrderTraversalHelper(Node currentNode) {
		List<Node> preOrderList = new LinkedList<Node>();
		if (currentNode != null) {
			// add the parent to the list.
			preOrderList.add(currentNode);
			// recur on the left sub-tree if currentNode has a left child
			if (currentNode.getLeftChild() != null) {
				List<Node> leftSide = preOrderTraversalHelper(currentNode.getLeftChild());
				preOrderList = transferValues(preOrderList, leftSide);
			}
			// recur on the right sub-tree if the currentNode has a right child
			if (currentNode.getRightChild() != null) {
				List<Node> rightSide = preOrderTraversalHelper(currentNode.getRightChild());
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
				List<Node> leftSide = postOrderTraversalHelper(currentNode.getLeftChild());
				postOrderList = transferValues(postOrderList, leftSide);
			}
			// recur on the right sub-tree if the currentNode has a right child
			if (currentNode.hasRight()) {
				List<Node> rightSide = postOrderTraversalHelper(currentNode.getRightChild());
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
			for (int i = 1; i < this.getHeight(); i++) {
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
			transferValues(list, givenLevel(currNode.getLeftChild(), level - 1));
			transferValues(list, givenLevel(currNode.getRightChild(), level - 1));
		}
		return list;
	}

	@Override
	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
		// TODO Auto-generated method stub
		if (key == null) {
			throw new IllegalNullKeyException("Null key");
		} else if (this.contains(key)) {
			throw new DuplicateKeyException("Duplicate key");
		}
		// insert the new node
		root = BSTInsert(root, key, value);
		fixTree(getNode(root, key)); // start fixing the tree where we inserted the new key.
	}

	/**
	 * Fixes the tree after an insert
	 * 
	 * @param currNode
	 */
	private void fixTree(Node currNode) {
		// get the parent of the currNode --> P
		Node P = getParent(root, currNode.key);
		if (P == null) {
			// CASE #1: the current node is the root
			currNode.setColor(BLACK);
			return;
		}

		if (P.getColor() == BLACK) {
			// CASE #2: The parent of the current node is black so no properties are
			// violated.
			return;
		}

		// get the parent of the parent --> Grandparent --> G
		Node G = getParent(root, P.getKey());
		// get the sibling of the parent --> PS
		Node PS = getSibling(G, P);
		Node newRoot = null; // new root of subtree
//		Node parentOfGrandParent = getParent(root, G.getKey());
//		
		if (PS == null) {
			// CASE #3: the sibling of P is null or colored black
			// FIX: Tri-node restructring
			K currNodeKey = currNode.getKey();
			K parentKey = P.getKey();
			K grandParentKey = G.getKey();
			// TODO: running into null pointer errors somewhere
			if (currNodeKey.compareTo(parentKey) > 0 && currNodeKey.compareTo(grandParentKey) > 0) {
				// SUBCASE #1: currNode is bigger than parent and bigger than the grandparent
				// FIX: left rotate around the grandparent, parent becomes the new root

				newRoot = rotateLeft(G);
			} else if (currNodeKey.compareTo(parentKey) > 0 && currNodeKey.compareTo(grandParentKey) < 0) {
				// SUBCASE #2: currNode is bigger than parent and smaller than the grandparent
				// FIX: right rotate around parent, then right rotate around currNode
				P = rotateRight(P);
				currNode = rotateRight(currNode);
			} else if (currNodeKey.compareTo(parentKey) < 0 && currNodeKey.compareTo(grandParentKey) > 0) {
				// SUBCASE #3: currNode is smaller than parent and bigger than the grandparent
				// FIX: left rotate around the parent, then right rotate around currNode
				P = rotateLeft(P);
				currNode = rotateRight(currNode);
			} else if (currNodeKey.compareTo(parentKey) < 0 && currNodeKey.compareTo(grandParentKey) < 0) {
				// SUBCASE #2: currNode is smaller than parent and smaller than the grandparent
				// parent becomes the root, parent's left child is the grandparent, and parent's
				// right child is the currNode.
				P = rotateRight(P);
			}
		} else if (P.getColor() == RED && PS.getColor() == RED) {
			// CASE #4: Both the parent and the sibling are red. Both must be recolored to
			// black and the grandparent becomes red.
			// FIX: recoloring

			P.setColor(BLACK);
			PS.setColor(BLACK);
			if (G != null) {
				G.setColor(RED);
				// rebalance with the grandparent as a red node
				fixTree(G);
			}
		}

		// fix root

//		if (parentOfGrandParent == null) {
//			root = newRoot;
//		} else {
//			if (G.getKey().compareTo(parentOfGrandParent.getKey()) > 0) {
//				parentOfGrandParent.setRight(newRoot);
//			} else {
//				parentOfGrandParent.setLeft(newRoot);
//			}
//		}
	}

	/**
	 * Rotates the subtree to the right
	 * 
	 * @param oldRoot - root of the subtree
	 * @return the new root --> oldRoot's left child
	 */
	// TODO: fix
	private Node rotateRight(Node oldRoot) {
		// set the new root to the left child of the old root
		Node newRoot = oldRoot.getLeftChild();
		// rearrange the tree so the right child of the new root is the left child of
		// the old root, and the new root's right child is the old root.
		oldRoot.setLeft(newRoot.getRightChild());
		newRoot.setRight(newRoot);
		return newRoot;
	}

	/**
	 * Rotates the subtree to the left
	 * 
	 * @param oldRoot - root of the subtree
	 * @return the new root --> oldRoot's right child
	 */
	private Node rotateLeft(Node oldRoot) {
		Node oldRootParent = getParent(root, oldRoot.getKey());

		// set the new root to the right child of the old root
		Node newRoot = oldRoot.getRightChild();
		// rearrange the tree so the left child of the new root is the old root
		newRoot.setLeft(oldRoot);
		if (oldRootParent == null) {
			root = newRoot;
		} else {
			if (oldRoot.getKey().compareTo(oldRootParent.getKey()) > 0) {
				oldRootParent.setRight(newRoot);
			} else {
				oldRootParent.setLeft(newRoot);
			}
		}
		return newRoot;
	}

	/**
	 * Gets the sibling of the curr child
	 * 
	 * @param parent    - parent of both children
	 * @param currChild - curr child
	 * @return the sibling of the current child.
	 */
	private Node getSibling(Node parent, Node currChild) {
		// if the curr child is bigger than the parent, the sibling is the left child
		if (currChild.getKey().compareTo(parent.getKey()) > 0) {
			return parent.getLeftChild();
		} else {
			// otherwise the sibling is the right child.
			return parent.getRightChild();
		}
	}

	/**
	 * Gets the parent of the key's node
	 * 
	 * @param key - key of the child node (of the parent)
	 * @return the parent of the node with the key or null if there is no parent
	 */
	private Node getParent(Node node, K key) {
		List<Node> preOrderList = preOrderTraversalHelper(root);
		Node parent = null;
		if (node == null) {
			return null;
		}

		while (node != null) {
			if (key.compareTo(node.getKey()) < 0) {
				parent = node;
				node = node.getLeftChild();
			} else if (key.compareTo(node.getKey()) > 0) {
				parent = node;
				node = node.getRightChild();
			} else {
				break;
			}
		}
		return parent;

	}

	/**
	 * Finds the spot to put the node with a key and puts it there
	 * 
	 * @param curr  - current node
	 * @param key   - key of the node to insert in the BST
	 * @param value - value of the node to insert in the BST
	 * @return root of the curr BST
	 */
	private Node BSTInsert(Node curr, K key, V value) {
		if (curr == null) {
			curr = new Node(key, value);
			// insert as red
			curr.setColor(RED);
		} else if (curr.key.compareTo(key) > 0) {
			// if the key is less than the current node's key, go left

			curr.setLeft(BSTInsert(curr.getLeftChild(), key, value));
		} else if (curr.key.compareTo(key) < 0) {
			// if the key is greater than the current node's key, go right
			curr.setRight(BSTInsert(curr.getRightChild(), key, value));
		}
		return curr;

	}

	@Override
	public boolean remove(K key) throws IllegalNullKeyException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		// TODO Auto-generated method stub
		return getNode(root, key).getValue();
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
			curr = getNode(curr.getRightChild(), key);
		}
		// found the node with matching keys
		return curr;

	}

	@Override
	public int numKeys() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub

	}

	// TODO: override the insert method so that it rebalances
	// according to red-black tree insert algorithm.

	// TODO [OPTIONAL] you may override print() to include
	// color R-red or B-black.

}
