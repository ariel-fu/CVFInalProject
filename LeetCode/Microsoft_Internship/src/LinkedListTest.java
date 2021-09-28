import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LinkedListTest {
	LinkedList test;

	@BeforeEach
	void setUp() throws Exception {
		test = new LinkedList();
	}

	
	@Test
	void testGetDecimalValue() {
		ListNode head = new ListNode(1);
		int testValue = test.getDecimalValue(head);
		assertEquals(1, testValue);
		
		head.next = new ListNode(0);
		testValue = test.getDecimalValue(head);
		assertEquals(2, testValue);
		
		head.next.next = new ListNode(1);
		testValue = test.getDecimalValue(head);
		assertEquals(5, testValue);
	}
//	@Test
//	void testRemoveNthNode() {
//		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3)));
//		ListNode result = new ListNode(1, new ListNode(2, new ListNode(3)));
//		head = test.removeNthFromEnd2(head, 0);
//		while (head != null) {
//			assertEquals(result.val, head.val);
//			result = result.next;
//			head = head.next;
//		}
//
//		head = new ListNode(1, new ListNode(2, new ListNode(3)));
//		result = new ListNode(1, new ListNode(2));
//		head = test.removeNthFromEnd2(head, 1);
//		while (head != null) {
//			assertEquals(result.val, head.val);
//			result = result.next;
//			head = head.next;
//		}
//
//		head = new ListNode(1, new ListNode(2, new ListNode(3)));
//		result = new ListNode(2, new ListNode(3));
//		head = test.removeNthFromEnd2(head, 3);
//		while (head != null) {
//			assertEquals(result.val, head.val);
//			result = result.next;
//			head = head.next;
//		}
//		
//		head = new ListNode(1);
//		result = null;
//		assertEquals(null, test.removeNthFromEnd2(head, 1));
//				
//	}
//	@Test
//	void testIsPalindrome() {
//		ListNode head = new ListNode(1, new ListNode(2));
//		assertTrue(!test.isPalindrome(head));
//		
//		head = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));
//		assertTrue(test.isPalindrome(head));
//		
//		head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(1))));
//		assertTrue(!test.isPalindrome(head));
//		
//		head = new ListNode(-1, new ListNode(-1));
//		assertTrue(test.isPalindrome(head));
//	}
	//
//	@Test
//	void testReverseListRecursive() {
//		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3)));
//		ListNode result = new ListNode(3, new ListNode(2, new ListNode(1)));
//		
//		head = test.reverseListRecursive(head);
//		while(head != null) {
//			assertEquals(result.val, head.val);
//			result = result.next;
//			head = head.next;
//		}
//	}
//	@Test
//	void testReverseListIterative() {
//		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3)));
//		ListNode result = new ListNode(3, new ListNode(2, new ListNode(1)));
//		
//		head = test.reverseListIterative(head);
//		while(head != null) {
//			assertEquals(result.val, head.val);
//			result = result.next;
//			head = head.next;
//		}
//		
//	}

//	@Test
//	void testGetIntersectionNode() {
//		ListNode headA = new ListNode(1);
//		ListNode headB = new ListNode(5);
//		ListNode attach = new ListNode(2, new ListNode(3));
//		headA.next = attach;
//		headB.next = attach;
//		ListNode result = test.getIntersectionNode(headA, headB);
//		assertEquals(2, result.val);
//		
//		headA = new ListNode(4, new ListNode(1));
//		
//		headB = new ListNode(5, new ListNode(6, new ListNode(1)));
//		attach = new ListNode(8, new ListNode(4, new ListNode(5)));
//		headA.next = attach;
//		headB.next = attach;
//		result = test.getIntersectionNode(headA, headB);
//		assertEquals(8, result.val);
//		
//		headA = new ListNode(1);
//		headB = null;
//		result = test.getIntersectionNode(headA, headB);
//		assertEquals(null, result);
//	}
//	
//	@Test
//	void testDeleteNode() {
//		ListNode head = new ListNode(4, new ListNode(5, new ListNode(1, new ListNode(9))));
//		ListNode result = new ListNode(4, new ListNode(1, new ListNode(9)));
//		ListNode node = head.next; // node 5
//		test.deleteNode(node);
//		while(result != null) {
//			System.out.println(head.val);
//			assertEquals(result.val, head.val);
//			head = head.next;
//			result = result.next;
//		}
//		
//		head = new ListNode(1, new ListNode(2, new ListNode(3)));
//		result = new ListNode(2, new ListNode(3));
//		node = head;
//		test.deleteNode(node);
//		while(result != null) {
//			assertEquals(result.val, head.val);
//			head = head.next;
//			result = result.next;
//		}
//		
//	}
//
//	@Test
//	void testPartition() {
//		ListNode head = new ListNode(1, new ListNode(3, new ListNode(2)));
//		ListNode result = new ListNode(1, new ListNode(2, new ListNode(3)));
//
//		head = test.partition(head, 3);
//		while (result != null) {
//			assertEquals(result.val, head.val);
//			head = head.next;
//			result = result.next;
//		}
//
//		head = new ListNode(1, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(5, new ListNode(2))))));
//		result = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(4, new ListNode(3, new ListNode(5))))));
//		head = test.partition(head, 3);
//		while (result != null) {
//			assertEquals(result.val, head.val);
//			head = head.next;
//			result = result.next;
//		}
//
//		head = null;
//		result = null;
//		head = test.partition(head, 100);
//		assertEquals(result, head);
//
//		head = new ListNode(2);
//		result = new ListNode(2);
//		head = test.partition(head, 1);
//		assertEquals(result.val, head.val);
//
//		head = new ListNode(50);
//		result = new ListNode(50);
//		head = test.partition(head, 55);
//		assertEquals(result.val, head.val);
//
//		head = new ListNode(50, new ListNode(49));
//		result = new ListNode(49, new ListNode(50));
//		head = test.partition(head, 50);
//		while (result != null) {
//			assertEquals(result.val, head.val);
//			head = head.next;
//			result = result.next;
//		}
//
//		head = new ListNode(1, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(5, new ListNode(2))))));
//		result = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(4, new ListNode(3, new ListNode(5))))));
//		head = test.partition(head, 3);
//		while (result != null) {
//			assertEquals(result.val, head.val);
//			head = head.next;
//			result = result.next;
//		}
//	}
//
//	@Test
//	void testCycle() {
//		ListNode head = new ListNode(1);
//		ListNode l1 = new ListNode(2);
//		ListNode l2 = new ListNode(5);
//		head.next = l1;
//		l1.next = l2;
//		l2.next = head;
//		assertTrue(test.hasCycle(head));
//
//		l1 = new ListNode(1);
//		l2 = new ListNode(2);
//		l1.next = l2;
//		l2.next = l1;
//		assertTrue(test.hasCycle(l1));
//		assertTrue(test.hasCycle(l2));
//
//		l1 = new ListNode(1);
//		assertTrue(!test.hasCycle(l1));
//
//		l1 = new ListNode(1);
//		l2 = new ListNode(2);
//		ListNode l3 = new ListNode(3);
//		ListNode l4 = new ListNode(40);
//		// has a cycle between l1, l2, and l4
//		l2.next = l1;
//		l4.next = l2;
//		l3.next = l1;
//		l1.next = l4;
//		assertTrue(test.hasCycle(l3));
//
//		l1 = null;
//		assertTrue(!test.hasCycle(l1));
//
//		l1 = new ListNode(1);
//		l2 = new ListNode(2);
//		l1.next = l2;
//		assertTrue(!test.hasCycle(l1));
//	}
//
}
