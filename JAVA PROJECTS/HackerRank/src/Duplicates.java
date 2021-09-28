
public class Duplicates {
  public ListNode deleteDuplicates(ListNode head) {
    //
    if (head == null) {
      return head;
    }
    ListNode prev = null;
    ListNode curr = head;
    int val = head.val - 1;
    // go through all of the nodes
    while (curr != null) {
      ListNode next = curr.next;
      // delete all duplicates except curr
      while (next != null && curr.val == next.val) {
        // remember curr val
        val = curr.val;
        curr.next = next.next;
        next = next.next;
      }
      // if the curr's val is a duplicate, remove curr
      if (curr.val == val) {
        // if we haven't found + removed any duplicates, move the head, which deletes
        // curr
        if (prev == null) {
          head = curr.next;
        } else {
          // since we have found prior duplicates, delete curr
          prev.next = curr.next;
        }
      } else {
        // no duplicate val, update prev and curr
        prev = curr;
      }
      curr = curr.next;

    }
    return head;
  }

  public boolean containsDuplicate(int[] nums, int k) {
    for (int i = 0; i < nums.length - 1; i++) {
      int currValue = nums[i];
      for (int j = i + 1; (j < nums.length) && (j < i + k + 1); j++) {
        int duplicate = nums[j];
        if (currValue == duplicate) {
          // duplicate found
          if (abs(i - j) <= k) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private int abs(int num) {
    if (num < 0) {
      return -1 * num;
    } else {
      return num;
    }
  }

}
