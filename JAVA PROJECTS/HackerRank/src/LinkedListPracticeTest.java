import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LinkedListPracticeTest {
  private LinkedListPractice test;

  @BeforeEach
  void setUp() throws Exception {
    test = new LinkedListPractice();
  }

  @AfterEach
  void tearDown() throws Exception {
  }

  @Test
  void testAddTwoNumbers() {
    ListNode li1 = new ListNode(2, new ListNode(4, new ListNode(3)));
    ListNode li2 = new ListNode(5, new ListNode(6, new ListNode(4)));
    ListNode finished = new ListNode(7, new ListNode(0, new ListNode(8)));
    ListNode sum = test.addTwoNumbers(li1, li2);

    while (sum != null) {
      if (sum.val != finished.val) {

        assertTrue(false);
      }
//      System.out.print(sum.val + " vs " + finished.val);
      sum = sum.next;
      finished = finished.next;

    }
  }

  @Test
  void testremoveNthFromEnd() {
    ListNode head = new ListNode(1, new ListNode(2));
    int n = 2;
    // removing 3
    ListNode removed = new ListNode(2);

    head = test.removeNthFromEnd(head, n);

    while (head != null) {
//      System.out.println(head.val + " vs " + removed.val);
      assertTrue(head.val == removed.val);

      head = head.next;
      removed = removed.next;
    }
  }

  @Test
  void testMergeTwoLists() {
    ListNode l1 = new ListNode(2);
    ListNode l2 = new ListNode(1);

    ListNode result = new ListNode(1, new ListNode(2));

    l1 = test.mergeTwoLists(l1, l2);

    while (result != null) {
      System.out.println(l1.val + "  vs  " + result.val);
      assertTrue(l1.val == result.val);

      l1 = l1.next;
      result = result.next;
    }

  }

}
