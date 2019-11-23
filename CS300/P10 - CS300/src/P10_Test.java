import static org.junit.Assert.*;

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
    CustomProcess  leftOfTheLeft = new CustomProcess(7);
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
    
    // test if it reexpands itself
    test = new WaitingProcessQueue();
    for(int i=0; i<20; i++) {
      test.insert(new CustomProcess(i+3));
    }
    try {
      test.insert(new CustomProcess(50));
      assertTrue(test.size() == 21);
      test.insert(new CustomProcess(51));
      assertTrue(test.size() == 22);
    } catch(Exception e) {
      assertTrue(false);
    }
  }

  public WaitingProcessQueue rebuild() {
    WaitingProcessQueue test = new WaitingProcessQueue();
    test.insert(new CustomProcess(5));
    test.insert(new CustomProcess(8));
    test.insert(new CustomProcess(10));
    test.insert(new CustomProcess(11));
    test.insert(new CustomProcess(12));
    test.insert(new CustomProcess(16));
    test.insert(new CustomProcess(17));
    return test;
  }
}
