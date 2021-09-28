import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import org.graalvm.compiler.lir.amd64.AMD64Binary.TwoOp;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {

  // Complete the compareTriplets function below.
  static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {
    // initialize List with two 0 values
    List<Integer> resultList = new ArrayList<Integer>();
    resultList.add(0);
    resultList.add(0);

    for (int i = 0; i < a.size(); i++) {
      // get values from array a, b, and the resultList
      int aAtI = a.get(i);
      int bAtI = b.get(i);
      int AliceResult = resultList.get(0);
      int BobResult = resultList.get(1);

      // compare a[i] to b[i] and add to Alice or Bob accordingly
      if (aAtI > bAtI) {
        AliceResult++;
        resultList.set(0, AliceResult);
      } else if (aAtI < bAtI) {
        BobResult++;
        resultList.set(1, BobResult);
      }

    }

    return resultList;

  }

  public static void main(String[] args) {
//    ArrayList<Integer> a = new ArrayList<Integer>();
//    a.add(10);
//    a.add(9);
//    ArrayList<Integer> b = new ArrayList<Integer>();
//    b.add(1);
//    b.add(19);
//    ArrayList<Integer> results = (ArrayList<Integer>) compareTriplets(a, b);
//    for (int i = 0; i < results.size(); i++) {
//      System.out.println(results.get(i));
//    }
//
//    int[] sums = new int[] { 3, 2, 4 };
//    int target = 6;
//
//    int[] twoSums = twoSums(sums, target);

//    String[] word1 = new String[] { "ab", "c" };
//    String[] word2 = new String[] { "a", "bc" };
//
//    System.out.println(arrayStringsAreEqual(word1, word2));

//    SinglyLinkedListNode head = null;
//    head = insertNodeAtTail(head, 2);
//
//    SinglyLinkedListNode next = new SinglyLinkedListNode();
//    head = insertNodeAtTail(next, 5);
//    head = insertNodeAtTail(next, 12);
//    head = insertNodeAtTail(next, 2005);
//    head = insertNodeAtTail(next, 1);
//    head = insertNodeAtTail(next, 150);
//
//  head = insertNodeAtHead(head, 5);
//
//  SinglyLinkedListNode next = new SinglyLinkedListNode(15);
//  head = insertNodeAtHead(head, 15);
//  head = insertNodeAtHead(head, 963);
//  head = insertNodeAtHead(head, 20);
//  head = insertNodeAtHead(head, 1926);

    SinglyLinkedListNode head1 = new SinglyLinkedListNode(10);
    SinglyLinkedListNode merge = new SinglyLinkedListNode(20);
    SinglyLinkedListNode head2 = new SinglyLinkedListNode(15);

    head1.next = new SinglyLinkedListNode(15);
    head1.next.next = merge;

    head2.next = merge;

    System.out.println(findMergeNode(head1, head2));

  }

  private static class SinglyLinkedListNode {
    int data;
    SinglyLinkedListNode next;

    private SinglyLinkedListNode(int data) {
      this.data = data;
    }
  }

  private static int findMergeNode(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
    /**
     * loop through the first linkedlist, head 1, and check if any of the .next data
     * values matches head2's Repeat until both linked lists have been exhausted.
     */
    SinglyLinkedListNode next2 = head2;
    while (head2.next != null) {
      SinglyLinkedListNode currNode = head1;
      while (currNode.next != null) {
        currNode = currNode.next;
        if (currNode == next2) {
          return currNode.data;
        }
      }
      next2 = next2.next;
    }
    return -1;
  }

//  private static SinglyLinkedListNode insertNodeAtHead(SinglyLinkedListNode llist, int data) {
//    SinglyLinkedListNode head = new SinglyLinkedListNode(data);
//    if (llist != null) {
//      head.next = llist;
//
//    }
//    return head;
//  }

  private static SinglyLinkedListNode insertNodeAtTail(SinglyLinkedListNode head, int data) {
    if (head == null) {
      head = new SinglyLinkedListNode();
      head.data = data;
    } else {
      SinglyLinkedListNode currNode = head;
      while (currNode.next != null) {
        currNode = currNode.next;
      }

      currNode.next = new SinglyLinkedListNode();
      currNode.next.data = data;
    }

    return head;
  }

//  private class ListNode {
//    int val;
//    ListNode next;
//
//    ListNode() {
//
//    }
//
//    ListNode(int val) {
//      this.val = val;
//    }
//
//    ListNode(int val, ListNode next) {
//      this.val = val;
//      this.next = next;
//    }
//  }
//  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//    /**
//     * Our head is going to be either l1.head or l2.head, depending on which one is
//     * bigger
//     * 
//     * Then, we can compare L1's current node to L2's current node until L2's node
//     * is greater than L1, then we can add L1 to the new list Repeat until .next is
//     * null
//     *
//     */
//
//    ListNode head;
//
//    if (l1.val >= l2.val) {
//      head = l2;
//    } else {
//      head = l1;
//    }
//
//    // go until one of them is null
//    while (l1.next != null || l2.next != null) {
//      ListNode curr1 = l1.next;
//
//    }
//
//  }

  public static boolean arrayStringsAreEqual(String[] word1, String[] word2) {
    String word1Str = "";
    String word2Str = "";

    for (int i = 0; i < word1.length; i++) {
      word1Str += word1[i];
    }

    for (int i = 0; i < word2.length; i++) {
      word2Str += word2[i];
    }

    return word1Str.equals(word2Str);

  }

  public static int[] shuffle(int[] nums, int n) {
    /**
     * we can split the array into two different arrays by splitting it at index n
     * 
     * now that we have two arrays, the first half and the second half, we can merge
     * them. we take one element from the first half and place it at index i and one
     * element from the second half and place it at index i+1. and repeat until we
     * have exhausted both arrays
     * 
     */

    int[] merged = new int[2 * n];
    // divide
    int[] firstHalf = new int[n];
    int[] secondHalf = new int[n];

    for (int i = 0; i < n; i++) {
      firstHalf[i] = nums[i];
      print(firstHalf);
    }
    System.out.println();
    for (int i = 0; i < n; i++) {
      secondHalf[i] = nums[i + n];
      print(secondHalf);
    }

    System.out.println();

    // merge
    int mergeIndex = 0;
    for (int i = 0; i < nums.length; i++) {
      if (i % 2 == 0) {
        merged[i] = firstHalf[mergeIndex];
      } else {
        merged[i] = secondHalf[mergeIndex];
        mergeIndex++;
      }

      print(merged);
    }

    return merged;
  }

  public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
    /**
     * Create a new List that is going to hold the values of whether the kid has
     * more candies than anyone else or not.
     * 
     * run through each of the kid, and for each kid, we can compare his number of
     * candies + extracandies to everyone else's number of candies. depending on if
     * they are bigger, we can add either a true or false value to the list. (repeat
     * for each of the kids)
     * 
     *
     */

    ArrayList<Boolean> holder = new ArrayList<>();

    for (int i = 0; i < candies.length; i++) {
      int extraPlus = candies[i] + extraCandies;
      boolean greater = true;
      for (int j = 0; j < candies.length; j++) {
        int currKid = candies[j];
        // if the current kid has more candies that the initial kid's candies plus the
        // extras, then add false to the holder array
        if (extraPlus < currKid) {
          greater = false;
        }
      }
      holder.add(greater);
    }

    return holder;
  }

  public static int[] twoSums(int[] nums, int target) {

    /**
     * Create a new array that holds the value of the two indices
     * 
     * run through each element in the array and add it to the other elements An
     * example would be if our target was 9, and the first element was a 1, then I
     * would go through the rest of the array looking for an 8. If there wasn't an
     * 8, then I would move onto the next index. We can repeat this process for all
     * the elements in the array. If we find two elements whose sum adds up to the
     * target, then we can add those indices to the array and return the array.
     */
    int[] result = new int[2];

    for (int i = 0; i < nums.length; i++) {
      int index1Value = nums[i];
      for (int j = i + 1; j < nums.length; j++) {
        int index2Value = nums[j];

        int sum = index1Value + index2Value;
        if (sum == target) {
          result[0] = i;
          result[1] = j;
          return result;
        }
      }
    }
    // will never get here unless there isn't a solution
    return null;

  }

  public static int[] runningSum(int[] nums) {
    /**
     * Create a new array to hold the running sums. For each element in the array,
     * add it up to its index in the original array. For example, if we had an array
     * of size 2, then the first element would only add the values from index 0 to
     * index 0, aka just itself. Then the second element in the holder array would
     * be all the values from index 0 to index 1 added up. Return that array
     */

    int[] holder = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      int sum = 0;
      // adding all the elements from index 0 to the current index in the nums array
      for (int j = 0; j <= i; j++) {
        sum += nums[j];
      }
      // add sum to the holder array
      holder[i] = sum;
    }

    return holder;
  }

  private static void print(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  private static void print(List<Boolean> arr) {
    for (int i = 0; i < arr.size(); i++) {
      System.out.print(arr.get(i) + " ");
    }
    System.out.println();
  }
}
