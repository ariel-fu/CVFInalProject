import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import org.junit.Test;

public class P09_Camp_Badger {

  @Test
  public void testInsert() {
    // insert at the left side
    CamperBST tree = new CamperBST();
    Camper key = new Camper("Alexa", "Fu", 12);
    tree.insert(key);
    assertTrue(tree.root.getData() == (key));

    // insert at the left subtree
    Camper insertLeftCamper = new Camper("Abe", "Bet", 10);
    tree.insert(insertLeftCamper);
    assertTrue(tree.root.getLeftNode().getData() == (insertLeftCamper));

    // insert at the left subtree's left subtree
    Camper insertLeftSubtree = new Camper("Earl", "Bater", 8);
    tree.insert(insertLeftSubtree);
    assertTrue(tree.root.getLeftNode().getLeftNode().getData() == (insertLeftSubtree));

    // insert at the left subtree's right subtree
    Camper insertRightSubtree = new Camper("Eda", "Bouti", 9);
    tree.insert(insertRightSubtree);
    assertTrue(tree.root.getLeftNode().getRightNode().getData() == (insertRightSubtree));

    // insert at the right side
    tree = new CamperBST();
    key = new Camper("Alexa", "Fu", 12);
    tree.insert(key);
    assertTrue(tree.root.getData() == (key));

    // insert at the right subtree
    Camper newCamper = new Camper("Loius", "Maker", 14);
    tree.insert(newCamper);
    assertTrue(tree.size() == 2);
    assertTrue(tree.root.getData().equals(key));
    assertTrue(tree.root.getRightNode().getData() == (newCamper));

    // insert at the right subtree's left subtree
    Camper insertRightCamper = new Camper("Carl", "Maitey", 12);
    tree.insert(insertRightCamper);
    assertTrue(tree.size() == 3);
    assertTrue(tree.root.getRightNode().getLeftNode().getData() == (insertRightCamper));

    // insert at the right subtree's right subtree
    Camper insertRightOfTheRight = new Camper("Tainta", "Zink", 14);
    tree.insert(insertRightOfTheRight);
    assertTrue(tree.size() == 4);
    assertTrue(tree.root.getRightNode().getRightNode().getData() == (insertRightOfTheRight));
  }

  @Test
  public void testCompareTo() {
    Camper camper1 = new Camper("Earl", "Walter", 12);
    Camper camper2 = new Camper("Earl", "Walter", 10);
    assertTrue(camper1.compareTo(camper2) == 0);

    camper1 = new Camper("abe", "EARL", 12);
    camper2 = new Camper("abe", "earl", 12);
    assertTrue(camper1.compareTo(camper2) < 0);

    camper1 = new Camper("abe", "eArl", 12);
    camper2 = new Camper("abe", "eaRl", 12);
    assertTrue(camper1.compareTo(camper2) < 0);

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

    // remove one node
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

    // remove from a right subtree of a left subtree
    tree = new CamperBST();
    Camper B = new Camper("B", "abe", 10);
    tree.insert(B);
    Camper A2 = new Camper("A2", "abe", 10);
    tree.insert(A2);
    Camper A1 = new Camper("A1", "abe", 10);
    tree.insert(A1);
    Camper A3 = new Camper("A3", "abe", 10);
    tree.insert(A3);
    Camper C2 = new Camper("C2", "abe", 10);
    tree.insert(C2);
    Camper C1 = new Camper("C1", "abe", 10);
    tree.insert(C1);
    Camper C3 = new Camper("C3", "abe", 10);
    tree.insert(C3);

    tree.delete(A1);
    assertTrue(tree.root.getLeftNode().getLeftNode() == null);
    
    tree.insert(A1);
    tree.delete(A3);
    assertTrue(tree.root.getLeftNode().getRightNode() == null);

    tree.insert(A3);
    tree.delete(C1);
    assertTrue(tree.root.getRightNode().getLeftNode() == null);

    tree.insert(C1);
    tree.delete(C3);
    assertTrue(tree.root.getRightNode().getRightNode() == null);

    // remove from a left subtree with two children
    tree.insert(C3);
    tree.delete(A2);
    assertTrue(tree.root.getLeftNode().getData() == A3);

    // remove from a right subtree with two children
    tree = rebuild();
    tree.delete(C2);
    assertTrue(tree.root.getRightNode().getData().compareTo(C3) == 0);

    // remove the root
    tree = rebuild();
    tree.delete(B);
    assertTrue(tree.root.getData().compareTo(C1) == 0);
    String[] array = new String[] { "A1", "A2", "A3", "C1", "C2", "C3" };
    Iterator<Camper> iterate = tree.traverse("INORDER");
    int i = 0;
    while (iterate.hasNext()) {
      assertTrue(iterate.next().getFirstName().equals(array[i]));
      i++;
    }
  }

  private CamperBST rebuild() {
    CamperBST tree = new CamperBST();
    tree.insert(new Camper("B", "abe", 10));
    Camper A2 = new Camper("A2", "abe", 10);
    tree.insert(A2);
    Camper A1 = new Camper("A1", "abe", 10);
    tree.insert(A1);
    Camper A3 = new Camper("A3", "abe", 10);
    tree.insert(A3);
    Camper C2 = new Camper("C2", "abe", 10);
    tree.insert(C2);
    Camper C1 = new Camper("C1", "abe", 10);
    tree.insert(C1);
    Camper C3 = new Camper("C3", "abe", 10);
    tree.insert(C3);
    return tree;
  }

  @Test
  public void testLeftMost() {
    // test one tree node
    CamperBST tree = new CamperBST();
    Camper camper1 = new Camper("1", "A", 12);
    tree.insert(camper1);
    assertTrue(tree.getLeftMost(tree.root).getData() == camper1);

    // test recurse once
    tree = new CamperBST();
    Camper camper2 = new Camper("2", "B", 10);
    tree.insert(camper2);
    tree.insert(camper1);

    CampTreeNode treeNode = tree.getLeftMost(tree.root);
    assertTrue(treeNode.getData() == camper1);

    // test a full tree
    tree = new CamperBST();
    camper1 = new Camper("a", "a", 12); // highest
    camper2 = new Camper("a", "b", 12); // 2nd highest
    Camper camper3 = new Camper("a", "c", 12); // third highest

    tree.insert(camper3);
    tree.insert(camper2);
    tree.insert(camper1);

    treeNode = tree.getLeftMost(tree.root);
    assertTrue(treeNode.getData() == camper1);

  }

  @Test

  public void testTraversal() {
    String[] lastNames;
    CamperBST tree;
    Iterator<Camper> iterate;
    int i;

    // test Post-order
    lastNames = new String[] { "A1", "A3", "A2", "C1", "C3", "C2", "B" };
    tree = new CamperBST();
    tree = rebuild();

    iterate = tree.traverse("POSTORDER");
    i = 0;
    while (iterate.hasNext()) {
      assertTrue(iterate.next().getFirstName().equals(lastNames[i]));
      i++;
    }

    // test pre-order
    lastNames = new String[] { "B", "A2", "A1", "A3", "C2", "C1", "C3" };
    tree = new CamperBST();
    tree = rebuild();

    iterate = tree.traverse("PREORDER");
    i = 0;
    while (iterate.hasNext()) {
      assertTrue(iterate.next().getFirstName().equals(lastNames[i]));
      i++;
    }
    // test in-order
    lastNames = new String[] { "A1", "A2", "A3", "B", "C1", "C2", "C3" };
    tree = new CamperBST();
    tree = rebuild();
    System.out.println("_---Traversal----");
    iterate = tree.traverse("INORDER");
    i = 0;
    while (iterate.hasNext()) {
      assertTrue(iterate.next().getFirstName().equals(lastNames[i]));
      i++;
    }
  }

}
