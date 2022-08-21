import java.util.NoSuchElementException;
import java.util.Iterator;
import java.util.LinkedList;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           CamperBST
// Files:           CampEnrollmentApp, Camper, CamperBST, CampManager, CampTreeNode 
// Course:          300, Fall, and 2019
//
// Author:          (Ariel Fu)
// Email:           afu5@wisc.edu
// Lecturer's Name: Mouna Ayari Ben Hadj Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    None
// Partner Email:   None
// Partner Lecturer's Name: None
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         NONE
// Online Sources:  NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/**
 * This class models a binary search trees for Campers
 * 
 * @author Ariel
 *
 */
public class CamperBST {

  public CampTreeNode root; // root of the BST
  private int size; // size of the BST
  private LinkedList<Camper> traversedLList; // LinkedList to maintain current traversal

  /**
   * No-argument constructor for CamperBST class. Creates an empty binary search
   * tree
   */
  public CamperBST() {
    root = null;
    size = 0;
  }

  /**
   * Getter method for size
   * 
   * @return size of the BST
   */
  public int size() {
    return size;
  }

  /**
   * Returns true if the tree is empty, false otherwise
   * 
   * @return if root == null
   */
  public boolean isEmpty() {
    return root == null;
  }

  /**
   * Starts tree insertion by calling insertHelp() on the root and assigning root
   * to be the subtree returned by that method
   * 
   * @param newCamper
   */
  public void insert(Camper newCamper) {
    root = insertHelp(root, newCamper);
  }

  /**
   * Recursive helper method to insert.
   * 
   * @param current,   The "root" of the subtree we are inserting into, ie the
   *                   node we are currently at.
   * @param newCamper, the camper to be inserted into the tree
   * @return the root of the modified subtree we inserted into
   */
  private CampTreeNode insertHelp(CampTreeNode current, Camper newCamper) {
    if(newCamper == null) {
      return current;
    }

    if(current == null) {
      CampTreeNode node = new CampTreeNode();
      node.setData(newCamper);
      current = node;
      size++;
    } else {
      if(newCamper.compareTo(current.getData()) < 0) {
        current.setLeftNode(insertHelp(current.getLeftNode(), newCamper));
      } else {
        current.setRightNode(insertHelp(current.getRightNode(), newCamper));
      }
    }
    return current;
  }

  /**
   * Prints the contents of this tree in alphabetical order based on the string
   * "lastName, firstName"
   */
  public void print() {
    printHelp(root);
  }

  /**
   * Helper method for print
   * @param current - The root of the subtree to print from
   */
  private void printHelp(CampTreeNode current) {
    if(current == null) {
      return;
    }
    printHelp(current.getLeftNode());
    System.out.println(current.getData());
    printHelp(current.getRightNode());
  }

  /**
   * Deletes a Camper into the binary search tree if it exists.
   * 
   * @param key, the camper to be deleted from the tree
   * @throws NoSuchElementException if it is thrown by deleteHelp
   */
  public void delete(Camper key) throws NoSuchElementException {
    root = deleteHelp(root, key);
    size--;
  }

  /**
   * Recursive helper method to delete.
   * 
   * @param current, The "root" of the subtree we are deleting from, ie the node
   *                 we are currently at.
   * @param key,     the camper to be deleted from the tree
   * @return the root of the modified subtree we deleted from
   * @throws NoSuchElementException if the camper is not in the tree or if key is
   *                                null
   */
  private CampTreeNode deleteHelp(CampTreeNode current, Camper key) {
    // if the key or the current node is null, throw the NoSuchElementException
    if(key == null) {
      throw new NoSuchElementException("Key cannot be null.");
    }
    if(current == null) {
      // element was not found, throw NoSuchElementException
      throw new NoSuchElementException("Warning: unable to remove an item not found in this tree.");
    }
    // recur on the left subtree at current.getLeft()
    if(key.compareTo(current.getData()) < 0) {
      current.setLeftNode(deleteHelp(current.getLeftNode(), key));
    }
    // recur on the right subtree at current.getRight()
    else if(key.compareTo(current.getData()) > 0) {

      current.setRightNode(deleteHelp(current.getRightNode(), key));
    }
    // found the key
    else {
      // nodes with only one child or no child
      if(current.getLeftNode() == null && current.getRightNode() == null) {
        current = null;
      } else if(current.getLeftNode() == null) {

        return current.getRightNode();
      } else if(current.getRightNode() == null) {

        return current.getLeftNode();
      } else {
        // nodes with two children
        // find the inorder successor's data and set it to the current node's data
        Camper leftMost = getLeftMost(current.getRightNode()).getData();

        // delete the inorder successor, replace the item in this node with the smallest
        // item in the right subtree.
        deleteHelp(current, leftMost);

        // set current to the leftmost node's value
        current.setData(leftMost);

      }
    }
    return current;
  }

  /**
   * Gets the left most node of current node
   * 
   * @param current - node to get the left most node
   * @return left most node of the current node
   */
  public CampTreeNode getLeftMost(CampTreeNode current) {
    if(current.getLeftNode() == null) {
      return current;
    } else {
      return getLeftMost(current.getLeftNode());
    }
  }

  /**
   * Gets the right node of the current node
   * 
   * @return - right node of this node
   */
  public CampTreeNode getRightNode() {
    return this.getRightNode();
  }

  /**
   * Gets the left node of the current node
   * 
   * @return - left node of this node
   */
  public CampTreeNode getLeftNode() {
    return this.getLeftNode();
  }

  /**
   * Returns an iterator of camper in the correct order as designated
   * 
   * @param order - type of traversal
   * @return - iterator of camper in the order inputted
   */
  public Iterator<Camper> traverse(String order) {
    // first time traversing need to initialize LinkedList
    if(traversedLList == null) {
      traversedLList = new LinkedList<Camper>();
    } else {
//clear the list to start over for a new traversal
      traversedLList.clear();
    }
    traverseHelp(root, order);
    return traversedLList.listIterator();
  }

  /**
   * Helper method for traversing the BST
   * 
   * @param current - current subtree to traverse
   * @param order   - order to traverse in
   */
  private void traverseHelp(CampTreeNode current, String order) {
    if(order.equals("PREORDER")) {
      printPreOrder(current);
    } else if(order.equals("INORDER")) {
      printInOrder(current);
    } else if(order.equals("POSTORDER")) {
      printPostOrder(current);
    }
  }

  /**
   * Helper method that performs a pre-order traversal of the BST Pre-order:
   * parent --> left child --> right child
   * 
   * @param current - root of the current subtree to traverse
   */
  public void printPreOrder(CampTreeNode current) {
    if(isEmpty()) {
      return;
    } else {
      // process the parent/add to the traverse LinkedList
      traversedLList.add(current.getData());

      // if the parent has a left child, recur on the left side
      if(current.getLeftNode() != null)
        printPreOrder(current.getLeftNode());

      // If the parent has a right child, recur on the right side
      if(current.getRightNode() != null)
        printPreOrder(current.getRightNode());
    }
  }

  /**
   * Helper method that performs a in-order traversal of the BST In-order: left
   * child --> parent --> right child
   * 
   * @param current - root of the current subtree to traverse
   */
  public void printInOrder(CampTreeNode current) {
    if(isEmpty()) {
      return;
    } else {
      // if the parent has a left child, recur on the left side
      if(current.getLeftNode() != null) {
        printInOrder(current.getLeftNode());
      }
      // process the parent/add to the traverse LinkedList
      traversedLList.add(current.getData());

      // if the parent has a right child, recur on the right side
      if(current.getRightNode() != null) {
        printInOrder(current.getRightNode());
      }
    }
  }

  /**
   * Helper method that performs a post-order traversal of the BST Post-order:
   * left child --> right child --> parent
   * 
   * @param current - root of the current subtree to traverse
   */
  public void printPostOrder(CampTreeNode current) {
    if(isEmpty()) {
      return;
    } else {
      // if the parent has a left child, recur on the left side
      if(current.getLeftNode() != null) {
        printPostOrder(current.getLeftNode());
      }
      // if the parent has a right child, recur on the right side
      if(current.getRightNode() != null) {
        printPostOrder(current.getRightNode());
      }
      // process the parent/add to the traverse LinkedList
      traversedLList.add(current.getData());
    }
  }

  /**
   * Gets the traversed list
   * 
   * @return traverseLList - LinkedList to maintain current traversal
   */
  public LinkedList getTraversalList() {
    return traversedLList;
  }
}
