
public class LinkedListRotate {

  public ListNode rotateRight(ListNode head, int k) {
    if (head == null) {
      // if head is null, we can't/don't want to do anything else
      return null;
    }

    if (k == 0) {
      // if k = 0, we don't need to rotate
      return head;
    }

    int size = 1;
    // get the tail and the size of the linked list
    ListNode tail = head;
    while (tail.next != null) {
      tail = tail.next;
      size++;
    }

    // get the number of times to loop
    int numTimesLoop = size - (k % size);
    if (numTimesLoop == 0) {
      // if the number of times to rotate = 0, we don't need to rotate
      return head;
    }

    // connect the end of the list to the head
    tail.next = head;

    // find the new tail of the linked list
    ListNode newTail = head;
    for (int i = 0; i < numTimesLoop - 1; i++) {
      newTail = newTail.next;
    }
    // get the new head of the linked list
    ListNode newHead = newTail.next;
    // sever the ties between the tail and the new head
    newTail.next = null;
    // return the new head
    return newHead;

  }

}
