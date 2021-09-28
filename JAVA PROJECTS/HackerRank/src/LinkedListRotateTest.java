import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LinkedListRotateTest {
  LinkedListRotate test;

  @BeforeEach
  void setUp() throws Exception {
    test = new LinkedListRotate();
  }

  @AfterEach
  void tearDown() throws Exception {
  }

//  @Test
//  void testRotateRight() {
//    ListNode rotate = new ListNode(0, new ListNode(1, new ListNode(2)));
//    ListNode rotateOnce = new ListNode(1, new ListNode(2, new ListNode(0)));
//
//    rotate = test.rotateRight(rotate, 1);
//    while (rotate != null) {
//      System.out.print("expected (rotate once): " + rotateOnce.val);
//      System.out.println(" actual: " + rotate.val);
//      assertEquals(rotateOnce.val, rotate.val);
//      rotate = rotate.next;
//      rotateOnce = rotateOnce.next;
//    }
//
//  }
//
//  @Test
//  void testRotateTwice() {
//    ListNode rotate = new ListNode(0, new ListNode(1, new ListNode(2)));
//    ListNode rotateTwice = new ListNode(2, new ListNode(0, new ListNode(1)));
//
//    // rotate twice
//    rotate = test.rotateRight(rotate, 2);
//    while (rotate != null) {
//      System.out.print("expected (rotate twice): " + rotateTwice.val);
//      System.out.println(" | actual: " + rotate.val);
//      assertEquals(rotateTwice.val, rotate.val);
//      rotate = rotate.next;
//      rotateTwice = rotateTwice.next;
//    }
//  }
//
  @Test
  void testRotateThrice() {
    ListNode rotate = new ListNode(1, new ListNode(2));
    ListNode rotateThrice = new ListNode(2, new ListNode(1));
    // rotate three times
    rotate = test.rotateRight(rotate, 1);
    while (rotate != null) {
      System.out.print("expected (rotate thrice): " + rotateThrice.val);
      System.out.println(" || actual: " + rotate.val);
      assertEquals(rotateThrice.val, rotate.val);
      rotate = rotate.next;
      rotateThrice = rotateThrice.next;
    }
  }

  @Test
  void testRotate() {
    ListNode rotate = new ListNode(1, new ListNode(2));
    ListNode rotateThrice = new ListNode(1, new ListNode(2));
    // rotate three times
    rotate = test.rotateRight(rotate, 2);
    while (rotate != null) {
      System.out.print("expected (rotate thrice): " + rotateThrice.val);
      System.out.println(" || actual: " + rotate.val);
      assertEquals(rotateThrice.val, rotate.val);
      rotate = rotate.next;
      rotateThrice = rotateThrice.next;
    }
  }

  @Test
  void whatIsWrong() {
    ListNode node = new ListNode(1,
        new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
    int k = 2;
    ListNode output = new ListNode(4,
        new ListNode(5, new ListNode(1, new ListNode(2, new ListNode(3)))));

    node = test.rotateRight(node, k);
    while (output != null) {
      System.out.print("Expected: " + output.val);
      System.out.println(" Actual: " + node.val);

      assertEquals(output.val, node.val);
      output = output.next;
      node = node.next;
    }
  }
}
