import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

public class P09_Camp_Badger {

  @Test
  public void testInsert() {
    // insert at the left side
    CamperBST tree = new CamperBST();
    Camper key = new Camper("Alexa", "Fu", 12);
    tree.insert(key);
    assertTrue(tree.root.getData().equals(key));

    // insert at the left subtree
    Camper insertLeftCamper = new Camper("Abe", "Bet", 10);
    tree.insert(insertLeftCamper);
    assertTrue(tree.root.getLeftNode().getData().equals(insertLeftCamper));

    // insert at the left subtree's left subtree
    Camper insertLeftSubtree = new Camper("Earl", "Bater", 8);
    tree.insert(insertLeftSubtree);
    assertTrue(tree.root.getLeftNode().getLeftNode().getData().equals(insertLeftSubtree));

    // insert at the left subtree's right subtree
    Camper insertRightSubtree = new Camper("Eda", "Bouti", 9);
    tree.insert(insertRightSubtree);
    assertTrue(tree.root.getLeftNode().getRightNode().getData().equals(insertRightSubtree));

    // insert at the right side
    tree = new CamperBST();
    key = new Camper("Alexa", "Fu", 12);
    tree.insert(key);
    assertTrue(tree.root.getData().equals(key));

    // insert at the right subtree
    Camper newCamper = new Camper("Loius", "Maker", 14);
    tree.insert(newCamper);
    assertTrue(tree.size() == 2);
    assertTrue(tree.root.getData().equals(key));
    assertTrue(tree.root.getRightNode().getData().equals(newCamper));

    // insert at the right subtree's left subtree
    Camper insertRightCamper = new Camper("Carl", "Maitey", 12);
    tree.insert(insertRightCamper);
    assertTrue(tree.size() == 3);
    assertTrue(tree.root.getRightNode().getLeftNode().getData().equals(insertRightCamper));

    // insert at the right subtree's right subtree
    Camper insertRightOfTheRight = new Camper("Tainta", "Zink", 14);
    tree.insert(insertRightOfTheRight);
    assertTrue(tree.size() == 4);
    assertTrue(tree.root.getRightNode().getRightNode().getData().equals(insertRightOfTheRight));
  }

  @Test
  public void testRemove() {
    // remove from nothing
    CamperBST tree = new CamperBST();
    Camper key = new Camper("Alexa", "Fu", 12);
    try {
      tree.delete(key);
      assertTrue(false);
    } catch (NoSuchElementException e) {
      assertTrue(true);
    }

    tree.insert(key);
    try {
      tree.delete(key);
      assertTrue(tree.isEmpty());
    } catch (Exception e) {
      assertTrue(false);
    }

    Camper camper2 = new Camper("carl", "Paearl", 10);
    tree.insert(key);
    tree.insert(camper2);
    try {
      tree.delete(key);      
      assertTrue(tree.root.getData().equals(camper2));
    } catch (Exception e) {
      System.out.println(e.getMessage());
      assertTrue(false);
    }

    // remove from a left subtree leaf
    tree = new CamperBST();
    key = new Camper("earl", "Westhood", 12);
    camper2 = new Camper("ariel", "fu", 10);
    tree.insert(key);
    tree.insert(camper2);
    tree.delete(camper2);
    assertTrue(!tree.isEmpty());
    assertTrue(tree.root.getData().equals(key));

    // remove from a right subtree leaf
    tree = new CamperBST();
    key = new Camper("earl", "Westhood", 12);
    camper2 = new Camper("ariel", "Zye", 10);
    tree.insert(key);
    tree.insert(camper2);
    tree.delete(camper2);
    assertTrue(!tree.isEmpty());
    assertTrue(tree.root.getData().equals(key));
    
    // remove from a left subtree with one child
    tree = new CamperBST();
    key = new Camper("Eric", "Kyle", 12);
    camper2 = new Camper("Ariel", "Fu", 10);
    Camper camper3 = new Camper("Louis", "Akle", 12);
    tree.insert(key);
    tree.insert(camper2);
    tree.insert(camper3);
    
    tree.delete(camper2);
    assertTrue(!tree.isEmpty());
    System.out.println(tree.root.getData());
    assertTrue(tree.root.getLeftNode().getData().equals(camper3));
    // remove from a right subtree with one child

    // remove from a left subtree with two children

    // remove from a right subtree with two children

    // remove the root
  }

}
