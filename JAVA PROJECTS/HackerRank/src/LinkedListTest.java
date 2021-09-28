import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LinkedListTest {
  private LinkedList test;

  @BeforeEach
  void setUp() throws Exception {
    test = new LinkedList();
  }

  @AfterEach
  void tearDown() throws Exception {
  }

//  @Test
//  void testMergeTwoLists() {
//    ListNode l1 = new ListNode(1, new ListNode(2));
//    ListNode l2 = new ListNode(2, new ListNode(3));
//
//    ListNode result = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(3))));
//
//    l1 = test.mergeTwoLists(l1, l2);
//
//    while (result != null) {
////      System.out.println(l1.val + "  vs  " + result.val);
//      assertTrue(l1.val == result.val);
//
//      l1 = l1.next;
//      result = result.next;
//    }
//  }

  @Test
  void testSwapPairs() {
    ListNode head = new ListNode(1, new ListNode(2, new ListNode(3)));
    ListNode result = new ListNode(2, new ListNode(1, new ListNode(3)));

    head = test.swapPairs(head);
    while (result != null) {
      System.out.println(head.val + " vs " + result.val);
      assertTrue(head.val == result.val);
      head = head.next;
      result = result.next;
    }

    head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
    result = new ListNode(2, new ListNode(1, new ListNode(4, new ListNode(3))));
    head = test.swapPairs(head);
    while (result != null) {
      System.out.println(head.val + " vs " + result.val);
      assertTrue(head.val == result.val);
      head = head.next;
      result = result.next;
    }
  }

}
