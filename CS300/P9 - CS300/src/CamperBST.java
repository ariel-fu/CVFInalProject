import java.util.NoSuchElementException;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           CamperBST
// Files:           Camper, CamperBST, CamperTreeNode, CampEnrollmentApp, CamperManager, 
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
    size++;
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
    CampTreeNode node = new CampTreeNode();
    node.setData(newCamper);
    if(current == null) {
      current = node;
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
  }

  /**
   * Recursive helper method to delete.
   * 
   * @param current, The "root" of the subtree we are deleting from, ie the node
   *                 we are currently at.
   * @param key,     the camper to be deleted from the tree
   * @return the root of the modified subtree we deleted from
   * @throws NoSuchElementException if the camper is not in the tree
   */
  private CampTreeNode deleteHelp(CampTreeNode current, Camper key) {
    if(current == null) {
      // element was not found, throw NoSuchElementException
      throw new NoSuchElementException("Warning: unable to remove an item not found in this tree.");
    }
    // recur on the left subtree at current.getLeft()
    if(key.compareTo(current.getData()) < 0) {
      current.setLeftNode(deleteHelp(current.getLeftNode(), key));
    }
    // recur on the right subtree at current.getRight()
    if(key.compareTo(current.getData()) > 0) {
      current.setRightNode(deleteHelp(current.getRightNode(), key));
    } else {
      // nodes with only one child or no child
      if(current.getRightNode() == null) {
        return current.getLeftNode();
      } else if(current.getLeftNode() == null) {
        return current.getRightNode();
      }

      // nodes with two children
      // find the inorder successor's data and set it to the current node's data
      current.setData(findMinimum(current.getRightNode()));

      // delete the inorder successor, replace the item in this node with the smallest
      // item in the right subtree.
      current.setRightNode(deleteHelp(current.getRightNode(), current.getRightNode().getData()));
    }
    return current;
  }

  private Camper findMinimum(CampTreeNode current) {
    CampTreeNode minimum = current;
    // if current is null, return it.
    if(current == null) {
      return current.getData();
    }
    // works like an iterator.
    while (current.getRightNode() != null) {
      minimum = current;
      current = current.getLeftNode();
    }
    return minimum.getData();
  }

  // DEBUG
  public CampTreeNode getRightNode() {
    return this.getRightNode();
  }

  public CampTreeNode getLeftNode() {
    return this.getLeftNode();
  }

}
