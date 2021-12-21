// linked list problems from leet code
public class LinkedList {
	

	public int getDecimalValue(ListNode head) {
		if(head == null) {
			return 0;
		}
		// get the length of the linked list
		ListNode runner = head;
		int length=0;
		while(runner != null) {
			runner= runner.next;
			length+=1;
		}
		int total = 0;
		int power = length;
		int currValue = 0;
		runner = head;
		while(runner != null) {
			power-=1;
			currValue = (int) Math.pow(2, power);
			if(runner.val == 1) {
				total+= currValue;
			}
			runner = runner.next;
		}
		
		return total;
	}
	public ListNode removeNthFromEnd2(ListNode head, int n) {
		// if n = 0 or head is null, then we can return head
		if(n == 0 || head == null) {
			return head;
		}
		
	
		// nth from end is lenght-n
		// find length:
		ListNode runner = head;
		int length = 0;
		while(runner!= null) {
			runner = runner.next;
			length++;
		}
		
		// remove the head is a special case
		if(n == length) {
			head = head.next;
			return head;
		}
		
		// we want to get to the node right before the nth node from the end
		int numTimes = length-n-1;
		runner = head;
		for(int i =0; i<numTimes; i++) {
			runner = runner.next;
		}
		
		if(runner == null || runner.next == null) {
			return head;
		}
		
		runner.next = runner.next.next;
		return head;
		
	}

	public boolean isPalindrome(ListNode head) {
		// get the length
		if (head == null) {
			return true;
		}
		ListNode curr = head;
		int length = 0;
		while (curr != null) {
			length++;
			curr = curr.next;
		}
		ListNode secondHalfHead = head;
		int numTimesLoop = length / 2;
		if (length % 2 == 1) {
			// if the length is odd, then we need to add one to bypass the middle node
			numTimesLoop += 1;
		}
		for (int i = 0; i < numTimesLoop; i++) {
			secondHalfHead = secondHalfHead.next;
		}

		secondHalfHead = reverseListIterative(secondHalfHead);
		curr = head;
		// curr is still the whole list, so the shorter linked list is secondHalfHead
		while (secondHalfHead != null) {
			if (curr.val != secondHalfHead.val) {
				return false;
			}
			curr = curr.next;
			secondHalfHead = secondHalfHead.next;
		}
		
		return true;
	}

	// uses too much memory
//	public boolean isPalindrome(ListNode head) {
//		if(head == null) {
//			return true;
//		}
//		ListNode curr = head;
//		String temp = "";
//		while(curr != null) {
//			temp += curr.val + " ";
//			curr = curr.next;
//		}
//		
//		temp = temp.strip(); // strip the trailing spaces
//		String[] palindrome = temp.split(" "); // split by spaces
//		int startIndex = 0;
//		int endIndex = palindrome.length-1;
//		while(startIndex < endIndex) {
//			if(!palindrome[startIndex].equals(palindrome[endIndex])) {
//				return false; // not a palindrome
//			}
//			startIndex++;
//			endIndex--;
//		}
//		
//		return true;
//	}


	public ListNode reverseListIterative(ListNode head) {
		ListNode runner = head;
		ListNode newHead = null;
		while (runner != null) {
			// if the new head is null, then set it to the runner
			ListNode temp = new ListNode(runner.val);
			temp.next = newHead;
			newHead = temp;
			runner = runner.next;
		}

		return newHead;
	}

	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		ListNode pointerA = headA;
		ListNode pointerB = headB;
		if (pointerA == null || pointerB == null) {
			return null; // there can't be a node if one of the lists is empty
		}
		while (pointerA != pointerB) {
			pointerA = pointerA.next;
			pointerB = pointerB.next;
			if (pointerA == pointerB) {
				// either they are both null or we have reached our intersection node
				return pointerA;
			}

			if (pointerA == null) {
				// traversed the end of the linked list headA, change to headB
				pointerA = headB;
			}

			if (pointerB == null) {
				pointerB = headA;
			}
		}

		return pointerA;
	}
	// inefficient method
//	public ListNode getIntersectionNodeInefficient(ListNode headA, ListNode headB) {
//		ListNode curr = headA;
//		while(curr!= null) {
//			ListNode runner = headB;
//			while(runner != null) {
//				if(curr.val == runner.val) {
//					if(curr.next != null && runner.next != null) {
//						if(curr.next.val == runner.next.val) {
//							return curr;
//						}
//					}
//					// connect at the end of the list
//					return curr;
//				} else {
//					runner = runner.next;
//				}
//			}
//			curr=curr.next;
//		}
//		return null;
//	}

	public void deleteNode(ListNode node) {
		if (node == null) {
			return;
		}
		ListNode curr = node;
		ListNode next = node.next;
		while (next.next != null) {
			// shift all the values in the linked list down by 1
			curr.val = next.val;
			curr = curr.next;
			next = next.next;
		}
		// at the end of the list, curr is the new tail
		// put next's value in curr
		curr.val = next.val;
		// cut off next
		curr.next = null;
	}

	/**
	 * Determines if the linked list has a cycle
	 * 
	 * @param head - head of the linked list
	 * @return whether or not the linked list has a cycle
	 */
	public boolean hasCycle(ListNode head) {
		if (head == null) {
			return false;
		}
		ListNode prev = head;
		ListNode next = head.next;

		while (next != null) {
			// if they match, then next has gone around again, so it is a cycle
			if (prev.val == next.val) {
				return true;
			}
			if (next.next != null) {
				// increment next by 2 and prev by 1
				next = next.next.next;
			} else {
				break; // if there are no more nodes, then there isn't a loop
			}
			prev = prev.next;
		}
		// if next.next becomes null, then there isn't a cycle
		return false;
	}

	/**
	 * Partitions the linked list so that all values less than x are on the left
	 * side, all values greater than or equal to x are on the right side
	 * 
	 * @param head
	 * @param x
	 * @return
	 */
	public ListNode partition(ListNode head, int x) {
		ListNode newHead = null;
		if (head == null) {
			return head;
		}
		ListNode prev;
		ListNode curr;
		// get the new head of the linked list, ie the first node whose val is < x
		if (head.val < x) {
			newHead = new ListNode(head.val);
			// TODO: need to remove head
			head = head.next;
			if (head == null) {
				newHead.next = head;
				return newHead;
			} else {
				prev = head;
				curr = head.next;
			}
		} else {
			prev = head;
			curr = head.next;
			while (curr != null) {
				if (curr.val < x) {
					newHead = new ListNode(curr.val);
					// remove curr
					curr = curr.next;
					prev.next = curr;
					prev = prev.next;
					break;
				} else {
					prev = prev.next;
					curr = curr.next;
				}
			}
			// no smaller values were found
			if (newHead == null) {
				return head;
			}
		}
		// add all the nodes whose val is < x
		ListNode runner = newHead;
		while (curr != null) {
			if (curr.val < x) {
				// set the next node to curr
				runner.next = curr;

				// remove curr
				curr = curr.next;
				prev.next = curr;
				prev = prev.next;

				// increment runner
				runner = runner.next;
			} else {
				// increment curr and prev
				curr = curr.next;
				prev = prev.next;
			}
		}

		// after all of the nodes whose val is < x are in the new linked list, add the
		// ones that aren't.
		runner.next = head;

		return newHead;

	}
}
