import java.util.Random;

public class linkedListCycle {
  static class Node {
    int data;
    Node next;

    /**
     * Create a node and set its data
     * 
     * @param data - int value that is its data
     */
    public Node(int data) {
      this.data = data;
    }

    /**
     * gets the next of this node
     * 
     * @return this.next
     */
    public Node getNext() {
      return this.next;
    }

    /**
     * Adds the next of this node
     * 
     * @param node - next of this node
     */
    public void setNext(Node node) {
      this.next = node;
    }

    /**
     * Gets the data of this node
     * 
     * @return this.data
     */
    public int getData() {
      return this.data;
    }
  }

  /**
   * This class models a linked list
   * 
   * @author Ariel
   *
   */
  static class LinkedList {
    Node head; // the head of the linked list

    public LinkedList(Node node) {
      head = node;
    }

    public void addNode(Node node) {
      head.setNext(node);
    }

    public boolean isInLL(int data) {
      Node currNode = head;
      while (currNode != null) {
        if (currNode.getData() == data) {
          return true;
        } else {
          currNode = currNode.getNext();
        }
      }
      return false;
    }
  }

  public static void main(String[] args) {
    Node head = new Node(1);
    LinkedList list = new LinkedList(head);
    System.out.println(hasCycle(head));
    list.addNode(new Node(8));
    System.out.println(hasCycle(head));
  }

  public static boolean hasCycle(Node head) {

    int[] traversedNodes = new int[100];
    Node currNode = head;
    int index = 0;

    while (currNode != null) {
      // get the currentNode's data to the array
      int data = currNode.data;

      // if we have previously traversed this node, there is a cycle
      if (isInArray(traversedNodes, data)) {
        return true;
      } else {
        // add it to the list of traversed nodes and keep traversing
        traversedNodes[index] = data;
        currNode = currNode.next;
      }
    }

    // there isn't a cycle or head is null
    return false;

  }

  /**
   * Helper method that checks if the current node's data is in the array
   * 
   * @param arr  - list of traversed nodes' data
   * @param data - data of current node
   * @return true if data is in the array already
   */
  private static boolean isInArray(int[] arr, int data) {
    for (int i = 0; i < arr.length; i++) {
      int currData = arr[i];
      if (currData == data) {
        return true;
      }
    }
    return false;
  }

}
