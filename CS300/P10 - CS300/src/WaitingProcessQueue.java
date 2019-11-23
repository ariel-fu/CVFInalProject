import java.util.NoSuchElementException;
import java.util.Arrays;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           WaitingProcessQueue
// Files:           CustomProcess, WaitingProcessQueue 
// Course:          300, Fall, and 2019
//
// Author:          (Ariel Fu)
// Email:           afu5@wisc.edu
// Lecturer's Name: Mouna Ayari Ben Hadj Kacem
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         NONE
// Online Sources:  NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/**
 * This class represents the priority queue
 * 
 * @author Ariel
 *
 */
public class WaitingProcessQueue implements WaitingQueueADT<CustomProcess> {
  private static final int INITIAL_CAPACITY = 20; // the initial capacity of this waiting process
                                                  // queue
  private CustomProcess[] data; // min heap-array storing the CustomProcesses inserted in this
                                // WaitingProcessQueue. data is an oversize array
  private int size; // number of CustomProcesses stored in this WaitingProcessQueue

  /**
   * No-argument constructor that creates an empty waiting process queue whose
   * initial capacity is INITIAL_CAPACITY
   */
  public WaitingProcessQueue() {
    data = new CustomProcess[INITIAL_CAPACITY];
  }

  /**
   * Inserts a new process into the queue
   */
  @Override
  public void insert(CustomProcess newObject) {
    if(size >= data.length) {
      arraySizeDoubler();
    }

    // if the array is empty: set index 0 then quit.
    if(isEmpty()) {
      data[0] = newObject;
      size++;
      return;
    } else {
      // increment the size and set the data at size to the new process
      data[size] = newObject;
      int current = size;

      // while the data at the current index is less than the data at the parent's
      // index
      while (data[current].compareTo(data[getParent(current)]) < 0) {
        // push the current data upwards
        minHeapPercolateUp(current);
        // change current to the parent's index/the current's index
        current = getParent(current);
      }
    }
    size++;

  }

  /**
   * Removes the process that has the smallest value
   * 
   * @return the process that has been removed
   * @throws NoSuchElementException - if this waiting queue is empty
   */
  @Override
  public CustomProcess removeBest() {
    // if it empty, throw a NoSuchElementException
    if(isEmpty()) {
      throw new NoSuchElementException("Queue is empty");
    }
    // the best is at the root
    CustomProcess currentBest = data[0];
    // set the root to the last element in the heap
    data[0] = data[size - 1];
    data[size - 1] = null; // remove it from the heap
    int currentIndex = 0;
    // while it is not a leaf
    while (!isLeaf(currentIndex)) {
      minHeapPercolateDown(currentIndex);
      currentIndex = getHighestPriorityChild(currentIndex);
    }
    size--;
    return currentBest;
  }

  /**
   * Returns the smallest process without removing it
   * 
   * @return the process at the top of the queue
   * @throws java.util.NoSuchElementException - if this waiting queue is empty
   * 
   */
  @Override
  public CustomProcess peekBest() {
    if(size == 0) {
      throw new NoSuchElementException("The waiting is queue is empty!");
    }
    return data[0]; // smallest value, highest priority, is at the root
  }

  /**
   * Accessor of the size
   * 
   * @return size of the queue
   */
  @Override
  public int size() {
    // change back to size
    return size;
  }

  /**
   * If the queue is empty, it returns true
   * 
   * @return true if size == 0
   */
  @Override
  public boolean isEmpty() {

    return size == 0;
  }

  /**
   * Helps percolate up the element at the given index in the data min-heap array
   * 
   * @param index - element to percolate up
   */
  protected void minHeapPercolateUp(int index) {
    CustomProcess currElement = data[index];
    int parentOfCurrent = getParent(index);
    CustomProcess temp = null;
    // swap the parent and the current
    temp = currElement;
    // set the data at the index given to the process at the parent
    data[index] = data[parentOfCurrent];
    // set the data at the index of the parent to the process at the given index
    data[parentOfCurrent] = temp;
  }

  /**
   * Helper method: percolate down the element at the given index in the data
   * min-heap array
   * 
   * @param index - element to percolate down
   */
  protected void minHeapPercolateDown(int index) {
    CustomProcess temp = data[index]; // data at index.

    if(data[getLeftChild(index)].compareTo(data[getRightChild(index)]) > 0) {
      // right child is smaller than the left child
      data[index] = data[getRightChild(index)];
      data[getRightChild(index)] = temp;
    } else if(data[getLeftChild(index)].compareTo(data[getRightChild(index)]) < 0) {
      // left child is smaller than the right child
      data[index] = data[getLeftChild(index)];
      data[getLeftChild(index)] = temp;
    } else {
      // both are equal, swap with the right branch
      data[index] = data[getRightChild(index)];
      data[getRightChild(index)] = temp;
    }

  }

  /**
   * Returns a String representation of all the non-null elements, if it empty,
   * return " "
   * 
   * @return each value of the array seperated by " "
   */
  @Override
  public String toString() {
    if(isEmpty()) {
      return " ";
    }
    String value = "";
    // add to the String all the values with a space after it until the last one
    for(int i = 0; i < size - 1; i++) {
      value += data[i] + " ";
    }
    value += data[size - 1]; // add the last value without a space
    return value;
  }

  /**
   * Array size doubler, copies over all the data from the current data to a new
   * array with double the size. Then sets data's pointer to the new array
   */
  protected void arraySizeDoubler() {
    data = Arrays.copyOf(data, data.length * 2);
  }

  /**
   * Helper method for getting left child
   * 
   * @param i - index of the parent
   * @return index of left child of the parent
   */
  protected int getLeftChild(int i) {
    return 2 * i + 1;
  }

  /**
   * Helper method for getting right child
   * 
   * @param i - index of the parent
   * @return index of the right child of the parent
   */
  protected int getRightChild(int i) {
    return 2 * i + 2;
  }

  /**
   * Helper method that gets the child with the highest priority
   * 
   * @param i - index of current node
   * @return index of the child with the highest priority
   */
  protected int getHighestPriorityChild(int i) {
    if(data[getLeftChild(i)].compareTo(data[getRightChild(i)]) < 0) {
      return getLeftChild(i);
    } else {
      return getRightChild(i);
    }
  }

  /**
   * Helper method for getting parent of either right or left child
   * 
   * @param i - index of child
   * @return index of parent of this child
   */
  protected int getParent(int i) {
    return (i - 1) / 2;
  }

  /**
   * Helper method that returns true if int is a leaf node
   * 
   * @param i - index of current
   * @return true if the node is a leaf
   */
  protected boolean isLeaf(int i) {
    if(i >= (size / 2) && i <= size) {
      return true;
    }
    return false;
  }

  // DEBUG
  protected CustomProcess getRightNode(int index) {
    return data[2 * index + 2];
  }

  protected CustomProcess getLeftNode(int index) {
    return data[2 * index + 1];
  }

}
