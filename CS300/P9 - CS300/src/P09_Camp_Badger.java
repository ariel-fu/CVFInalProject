import static org.junit.Assert.*;

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

}
