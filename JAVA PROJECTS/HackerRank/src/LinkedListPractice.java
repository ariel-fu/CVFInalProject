public class LinkedListPractice {

  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode head = null;
    // returns the other list if it is null
    if (l1 == null) {
      return l2;
    } else if (l2 == null) {
      return l1;
    }
    // get smaller value and set as head of merged list
    if (l1.val > l2.val) {
      head = new ListNode(l2.val);
      l2 = l2.next;
    } else {
      head = new ListNode(l1.val);
      l1 = l1.next;
    }
    // set return list
    ListNode ret = head;

    while (l1 != null || l2 != null) {

      if (l1 == null) {
        head.next = l2;
        break;
      } else if (l2 == null) {
        head.next = l1;
        break;
      } else {
        int value1 = l1.val;
        int value2 = l2.val;
        // create a new node with the lesser value
        // attach it to "head"
        if (value1 >= value2) {
          head.next = new ListNode(value2);
          // increment l2
          l2 = l2.next;
        } else {
          head.next = new ListNode(value1);
          // increment l1
          l1 = l1.next;
        }
        // move "head" forward
        head = head.next;
      }
    }
    return ret;
  }

  public ListNode removeNthFromEnd(ListNode head, int n) {
    int numFromStart = getSize(head) - n;
    ListNode ret = head;
    if (numFromStart == 0) {
      head = head.next;
      return head;
    }
    // get to the nth-1 spot from the end of the list
    for (int i = 0; i < numFromStart - 1; i++) {
      head = head.next;
      if (head == null) {
        return null;
      }
    }
    // remove the node
    if (head.next == null) {
      return null;
    }
    ListNode nextNode = head.next.next;
    head.next = nextNode;

    return ret;

  }

  private int getSize(ListNode head) {
    int size = 0;
    while (head != null) {
      head = head.next;
      size++;
    }
    return size;
  }

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    if (l1 == null || l2 == null) {
      return null;
    }

    /**
     * iterate through both of them at the same time if we run into the end of one
     * of them, we add the longer list's rest, including any carryovers
     */
    ListNode head = null;
    int carryover = 0;
    while (l1 != null || l2 != null) {
      int value1 = 0;
      int value2 = 0;
      if (l1 != null) {
        value1 = l1.val;
        l1 = l1.next;
      }
      if (l2 != null) {
        value2 = l2.val;
        l2 = l2.next;
      }

      int newValue = value1 + value2 + carryover;
      int onesPlace = newValue % 10;
      ListNode currSum = new ListNode(onesPlace);
      head = addNode(head, currSum);
      carryover = newValue / 10;

    }

    if (carryover != 0) {
      head = addNode(head, new ListNode(carryover));
    }

    // return the head of the sum linkedlist
    return head;

  }

  private ListNode addNode(ListNode head, ListNode node) {
    if (head == null) {
      head = node;
      return head;
    }
    ListNode runner = head;
    while (head.next != null) {
      head = head.next;
    }
    head.next = node;
    return runner;
  }

}