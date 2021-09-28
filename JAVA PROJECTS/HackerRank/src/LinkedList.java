
public class LinkedList {

  public ListNode swapPairs(ListNode head) {
    ListNode curr = head;
    if (curr == null || curr.next == null) {
      return curr;
    }
    ListNode next = curr.next;
    curr.next = swapPairs(next.next);
    next.next = curr;
    return next;
  }

  public ListNode swapPairs2(ListNode head) {
    if (head == null) {
      return null;
    } else if (head.next == null) {
      return head;
    }
    // create a runner
    // swap the first two nodes
    ListNode runner = swap(head, head.next, null);
    head = runner;
    // skip forwards two
    runner = runner.next;

    while (runner != null) {
      // current node
      ListNode node1 = runner.next;
      if (node1 == null) {
        // reached the end of the list, quit.
        break;
      }
      // current node's adjacent node
      ListNode node2 = runner.next.next;

      ListNode swap = swap(node1, node2, runner); // ?

      runner.next = swap;
      runner = runner.next.next; // aka node1 aka the 2nd swapped node

    }
    return head;

  }

  private ListNode swap(ListNode node1, ListNode node2, ListNode pointerTo1) {
    if (node2 == null) {
      return node1;
    }

    node1.next = node2.next;
    node2.next = node1;
    if (pointerTo1 != null) {
      pointerTo1.next = node2;
    }
    return node2;

  }

  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    } else if (l2 == null) {
      return l1;
    } else {
      ListNode head;
      if (l1.val < l2.val) {
        // l1.val is the smaller value
        head = new ListNode(l1.val);
        // increment l1
        l1 = l1.next;
      } else {
        head = new ListNode(l2.val);
        l2 = l2.next;
      }
      ListNode curr = head;
      while (l1 != null || l2 != null) {
        if (l1 == null) {
          curr.next = l2;
          break;
        } else if (l2 == null) {
          curr.next = l1;
          break;
        } else {
          int val1 = l1.val;
          int val2 = l2.val;
          // find the next smallest element
          if (val1 < val2) {
            // append onto the ret list
            curr.next = new ListNode(val1);
            // increment l1
            l1 = l1.next;
          } else {
            // val2 is the smaller (or equal) value
            curr.next = new ListNode(val2);
            // increment l2
            l2 = l2.next;
          }
          curr = curr.next;
        }
      }
      return head;
    }

  }

}
