import java.util.Iterator;
import java.util.NoSuchElementException;

public class DrawingStackIterator<DrawingChange>
    implements Iterator<DrawingChange> {
  private LinkedNode<DrawingChange> next;

  public DrawingStackIterator(LinkedNode<DrawingChange> head) {
    this.next = head;
  }

  @Override
  public boolean hasNext() {
    if (next.getNext() == null) {
      return false;
    }
    return true;
  }

  @Override
  public DrawingChange next() {
    // TODO Auto-generated method stub
    if (next == null) {
      throw new NoSuchElementException("Empty stack");
    } else if (next.getNext() == null) {
      throw new NoSuchElementException("There isn't a next drawing change");
    } else {
      LinkedNode<DrawingChange> currDrawingChange = next;
      next = next.getNext();
      return currDrawingChange.getData();
    }
  }
}
