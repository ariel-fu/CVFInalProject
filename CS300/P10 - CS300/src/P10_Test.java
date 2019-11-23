import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

public class P10_Test {
//
//  @Test
//  public void testArrayDoubler() {
//    WaitingProcessQueue test = new WaitingProcessQueue();
//    test.arraySizeDoubler();
//    System.out.println(test.size());
//    assertTrue(test.size() == 40);
//  }

  @Test
  public void testInsert() {
    // insert to an empty queue
    WaitingProcessQueue test = new WaitingProcessQueue();
    CustomProcess obj = new CustomProcess(5);
    test.insert(obj);
    assertTrue(test.peekBest().compareTo(obj) == 0);

    // test a swap
    CustomProcess obj1 = new CustomProcess(2);
    test.insert(obj1);
    assertTrue(test.peekBest().compareTo(obj1) == 0);

    // test inserting to the right side
    CustomProcess rightSide = new CustomProcess(4);
    test.insert(rightSide);
    assertTrue(test.getRightNode(0).compareTo(rightSide) == 0);

    // test inserting again --> left side of the left side
    CustomProcess leftOfTheLeft = new CustomProcess(7);
    test.insert(leftOfTheLeft);
    assertTrue(test.getLeftNode(1).compareTo(leftOfTheLeft) == 0);

    // insert right side of the left side
    CustomProcess rightOfTheLeft = new CustomProcess(8);
    test.insert(rightOfTheLeft);
    assertTrue(test.getRightNode(1).compareTo(rightOfTheLeft) == 0);

    // insert left side of the right side
    CustomProcess leftOfTheRight = new CustomProcess(5);
    test.insert(leftOfTheRight);
    assertTrue(test.getLeftNode(2).compareTo(leftOfTheRight) == 0);

    // insert right side of the right side
    CustomProcess rightOfTheRight = new CustomProcess(6);
    test.insert(rightOfTheRight);
    assertTrue(test.getRightNode(2).compareTo(rightOfTheRight) == 0);


  }

  @Test
  public void testRemove() {
    WaitingProcessQueue test = new WaitingProcessQueue();
    test.data = new CustomProcess[] { new CustomProcess(5), new CustomProcess(6),
        new CustomProcess(7), new CustomProcess(11), new CustomProcess(11), new CustomProcess(12) };
    test.size = 6;
    
    // remove the root
    test.removeBest();
    
    assertTrue(test.data[0].getBurstTime() == 6);
  }

  @Test
  public void peekBest() {
    // test a normal queue
    WaitingProcessQueue test = new WaitingProcessQueue();
    test.data = new CustomProcess[] { new CustomProcess(5), new CustomProcess(6),
        new CustomProcess(7), new CustomProcess(11), new CustomProcess(11), new CustomProcess(12) };
    test.size = 6;
    assertTrue(test.peekBest().getBurstTime() == 5);

    // test an empty queue
    test = new WaitingProcessQueue();
    test.size = 0;
    try {
      test.peekBest();
      assertTrue(false);
    } catch (NoSuchElementException e) {
      assertTrue(true);
    }
  }

  @Test
  public void percolateUp() {
    WaitingProcessQueue test = new WaitingProcessQueue();
    test.data = new CustomProcess[] { new CustomProcess(5), new CustomProcess(6),
        new CustomProcess(7), new CustomProcess(11), new CustomProcess(11), new CustomProcess(12) };
    // percolate up
    test.minHeapPercolateUp(2);
    CustomProcess[] copy = new CustomProcess[] { new CustomProcess(7), new CustomProcess(6),
        new CustomProcess(5), new CustomProcess(11), new CustomProcess(11), new CustomProcess(12) };
    // compare all the values
    for(int i = 0; i < copy.length; i++) {
      assertTrue(test.data[i].getBurstTime() == copy[i].getBurstTime());
    }
  }

  @Test
  public void percolateDown() {
    WaitingProcessQueue test = new WaitingProcessQueue();
    test.data = new CustomProcess[] { new CustomProcess(7), new CustomProcess(5),
        new CustomProcess(6), new CustomProcess(11), new CustomProcess(11), new CustomProcess(12) };
    test.size = 6;
    // percolate down
    test.minHeapPercolateDown(0);
    CustomProcess[] copy = new CustomProcess[] { new CustomProcess(5), new CustomProcess(7),
        new CustomProcess(6), new CustomProcess(11), new CustomProcess(11), new CustomProcess(12) };
    // compare all the values
    for(int i = 0; i < copy.length; i++) {
      assertTrue(test.data[i].getBurstTime() == copy[i].getBurstTime());
    }

    // didn't change because it is in order
    test.data = new CustomProcess[] { new CustomProcess(5), new CustomProcess(6),
        new CustomProcess(7), new CustomProcess(11), new CustomProcess(11), new CustomProcess(12) };
    test.size = 6;
    test.minHeapPercolateDown(1);
    copy = new CustomProcess[] { new CustomProcess(5), new CustomProcess(6), new CustomProcess(7),
        new CustomProcess(11), new CustomProcess(11), new CustomProcess(12) };
    for(int i = 0; i < copy.length; i++) {
      assertTrue(test.data[i].getBurstTime() == copy[i].getBurstTime());
    }
  }

  @Test
  public void arraySizeDoubler() {
    WaitingProcessQueue test = new WaitingProcessQueue();
    test.arraySizeDoubler();
    assertTrue(test.data.length == 40);
  }

  @Test
  public void getLeftChild() {
    // test root
    WaitingProcessQueue test = new WaitingProcessQueue();
    test.data = new CustomProcess[] { new CustomProcess(5), new CustomProcess(6),
        new CustomProcess(7), new CustomProcess(11), new CustomProcess(11), new CustomProcess(12) };
    test.size = 6;
    assertTrue(test.data[test.getLeftChild(0)].getBurstTime() == 6);

    // test leaf
    test.data = new CustomProcess[] { new CustomProcess(5), new CustomProcess(6),
        new CustomProcess(7), new CustomProcess(11), new CustomProcess(11), new CustomProcess(12) };
    test.size = 6;
    assertTrue(test.data[test.getLeftChild(5)].getBurstTime() == 12);
  }

  @Test
  public void getRightChild() {
    // test root
    WaitingProcessQueue test = new WaitingProcessQueue();
    test.data = new CustomProcess[] { new CustomProcess(5), new CustomProcess(6),
        new CustomProcess(7), new CustomProcess(11), new CustomProcess(11), new CustomProcess(12) };
    test.size = 6;
    assertTrue(test.data[test.getRightChild(0)].getBurstTime() == 7);

    // test leaf
    test.data = new CustomProcess[] { new CustomProcess(5), new CustomProcess(6),
        new CustomProcess(7), new CustomProcess(11), new CustomProcess(11), new CustomProcess(12) };
    test.size = 6;
    assertTrue(test.data[test.getRightChild(4)].getBurstTime() == 11);

  }

  @Test
  public void getHighestPriorityChild() {
    // get from a root subtree
    WaitingProcessQueue test = new WaitingProcessQueue();
    test.data = new CustomProcess[] { new CustomProcess(5), new CustomProcess(6),
        new CustomProcess(7), new CustomProcess(11), new CustomProcess(11), new CustomProcess(12) };

    test.size = 6;
    CustomProcess priorityChild = test.data[test.getHighestPriorityChild(0)];
    assertTrue(priorityChild.getBurstTime() == 6);

    // get from a left subtree with two subchildren
    test = new WaitingProcessQueue();
    test.data = new CustomProcess[] { new CustomProcess(5), new CustomProcess(6),
        new CustomProcess(7), new CustomProcess(11), new CustomProcess(21), new CustomProcess(12) };
    test.size = 6;
    priorityChild = test.data[test.getHighestPriorityChild(1)];

    assertTrue(priorityChild.getBurstTime() == 11);

    // get from a right subtree with two subchildren
    test = new WaitingProcessQueue();
    test.data = new CustomProcess[] { new CustomProcess(5), new CustomProcess(6),
        new CustomProcess(7), new CustomProcess(11), new CustomProcess(11), new CustomProcess(12),
        new CustomProcess(13) };
    test.size = 7;
    priorityChild = test.data[test.getHighestPriorityChild(2)];
    assertTrue(priorityChild.getBurstTime() == 12);

    // get from a left subtree with one child
    test = new WaitingProcessQueue();
    test.data = new CustomProcess[] { new CustomProcess(5), new CustomProcess(6),
        new CustomProcess(7), new CustomProcess(11) };
    test.size = 4;
    priorityChild = test.data[test.getHighestPriorityChild(1)];
    assertTrue(priorityChild.getBurstTime() == 11);

    // get from a right subtree with one child
    test = new WaitingProcessQueue();
    test.data = new CustomProcess[] { new CustomProcess(5), new CustomProcess(6),
        new CustomProcess(7), new CustomProcess(11), new CustomProcess(11), new CustomProcess(12) };
    test.size = 6;
    priorityChild = test.data[test.getHighestPriorityChild(2)];
    assertTrue(priorityChild.getBurstTime() == 12);

    // get from a leaf --> returns itself
    test = new WaitingProcessQueue();
    test.data = new CustomProcess[] { new CustomProcess(5), new CustomProcess(6),
        new CustomProcess(7), new CustomProcess(11), new CustomProcess(11), new CustomProcess(12) };
    test.size = 6;
    priorityChild = test.data[test.getHighestPriorityChild(5)];
    assertTrue(priorityChild.getBurstTime() == 12);
  }

  @Test
  public void getParent() {

    // test right subtree
    WaitingProcessQueue test = new WaitingProcessQueue();
    test.data = new CustomProcess[] { new CustomProcess(5), new CustomProcess(6),
        new CustomProcess(7), new CustomProcess(11), new CustomProcess(11), new CustomProcess(12) };
    test.size = 6;
    assertTrue(test.data[test.getParent(2)].getBurstTime() == 5);

    // test left subtree
    test = new WaitingProcessQueue();
    test.data = new CustomProcess[] { new CustomProcess(5), new CustomProcess(6),
        new CustomProcess(7), new CustomProcess(11), new CustomProcess(11), new CustomProcess(12) };
    test.size = 6;
    assertTrue(test.data[test.getParent(1)].getBurstTime() == 5);

    // test root
    test = new WaitingProcessQueue();
    test.data = new CustomProcess[] { new CustomProcess(5), new CustomProcess(6),
        new CustomProcess(7), new CustomProcess(11), new CustomProcess(11), new CustomProcess(12) };
    test.size = 6;
    assertTrue(test.data[test.getParent(0)].getBurstTime() == 5);

  }

}
