import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DuplicatesTest {
  Duplicates test;

  @BeforeEach
  void setUp() throws Exception {
    test = new Duplicates();
  }

  @AfterEach
  void tearDown() throws Exception {
  }

//  @Test
//  void test() {
//    int[] nums = new int[] { 1, 2, 3, 1 };
//    int k = 3;
//    assertTrue(test.containsDuplicate(nums, k));
//
//    nums = new int[] { 1, 0, 1, 1 };
//    k = 1;
//    assertTrue(test.containsDuplicate(nums, k));
//
//    nums = new int[] { 1, 2, 3, 1, 2, 3 };
//    k = 2;
//    assertTrue(!test.containsDuplicate(nums, k));
//
//  }

//  @Test
//  void testListNode() {
//    ListNode head = new ListNode(1, new ListNode(1, new ListNode(2)));
//    ListNode expected = new ListNode(1, new ListNode(2));
//    head = test.deleteDuplicates(head);
//    while (expected != null) {
//      assertEquals(expected.val, head.val);
//      head = head.next;
//      expected = expected.next;
//    }
//
//    head = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3)))));
//    expected = new ListNode(1, new ListNode(2, new ListNode(3)));
//    head = test.deleteDuplicates(head);
//    while (expected != null) {
//      assertEquals(expected.val, head.val);
//      head = head.next;
//      expected = expected.next;
//    }
//
//    head = null;
//
//    head = test.deleteDuplicates(head);
//    assertTrue(head == null);
//  }

  @Test
  void testDuplicates() {
    ListNode head = new ListNode(1, new ListNode(2,
        new ListNode(3, new ListNode(3, new ListNode(4, new ListNode(4, new ListNode(5)))))));
    ListNode expected = new ListNode(1, new ListNode(2, new ListNode(5)));
    head = test.deleteDuplicates(head);
    while (expected != null) {
      assertEquals(expected.val, head.val);
      head = head.next;
      expected = expected.next;
    }

    head = new ListNode(1, new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3)))));
    expected = new ListNode(2, new ListNode(3));
    head = test.deleteDuplicates(head);
    while (expected != null) {
      assertEquals(expected.val, head.val);
      head = head.next;
      expected = expected.next;
    }

  }

}
