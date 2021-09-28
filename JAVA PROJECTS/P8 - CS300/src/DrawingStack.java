import java.util.Iterator;

public class DrawingStack
    implements StackADT<DrawingChange>, Iterable<DrawingChange> {
  private LinkedNode<DrawingChange> top; // top of the Stack
  private int size; // number of elements in the Stack

  /**
   * No-arg constructor for the DrawingStack that sets the top to null and the
   * size to 0.
   */
  public DrawingStack() {
    top = null;
    size = 0;
  }

  /**
   * Iterator method from Iterable interface
   * 
   * @return a new DrawingStackIterator that starts at the top of the stack of
   *         DrawingChanges
   */
  @Override
  public Iterator<DrawingChange> iterator() {
    return new DrawingStackIterator<DrawingChange>(top);
  }

  /**
   * Adds a DrawingChange to the Stack of changes made so far
   * 
   * @param element - new DrawingChange made to the board
   */
  @Override
  public void push(DrawingChange element) {
    LinkedNode<DrawingChange> currNode = new LinkedNode<DrawingChange>(element);
    // set the currNode's next to the current top
    currNode.setNext(top);
    // change the current top to be currNode (the newest DrawingChange)
    top = currNode;
    // increment the size
    size++;
    return;
  }

  /**
   * Removes the top of the Stack
   * 
   * @return the data from the former head of the Stack
   */
  @Override
  public DrawingChange pop() {
    if (top == null) {
      return null;
    } else {
      LinkedNode<DrawingChange> prevTop = top;
      top = top.getNext();
      // decrement the size after removing the top of the Stack
      size--;
      return prevTop.getData();
    }
  }

  /**
   * Gets the data of the top of the Stack without removing anything
   * 
   * @return data of the top of the Stack
   */
  @Override
  public DrawingChange peek() {
    if (top == null) {
      return null;
    } else {
      return top.getData();
    }
  }

  /**
   * Returns true if there is nothing in the Stack
   */
  @Override
  public boolean isEmpty() {
    if (this.size == 0) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public int size() {
    return this.size;
  }

}
