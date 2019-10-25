import static org.junit.Assert.*;

import org.junit.Test;

public class TestP07 {

  @Test
  public void test() {
    
  }

  @Test
  public void testSong() {
    Song test = new Song("elmo", "ariel");
    Song other = new Song("elmo", "ariel");
    Song test1 = new Song("2elmo", "ariel");
    assertTrue(test.toString().equals("elmo by ariel"));
    assertTrue(test.equals(test));
    assertTrue(!test.equals(null));
    assertTrue(test.equals(other));
    assertTrue(!test.equals(test1));
  }
  
  
  @Test 
  public void testDoublyLinkedNode() {
    DoublyLinkedNode<Integer> x = new DoublyLinkedNode<Integer>(19);
    DoublyLinkedNode<String> previous = new DoublyLinkedNode<String>("19");
    DoublyLinkedNode<String> previous1 = new DoublyLinkedNode<String>("1");
    DoublyLinkedNode<String> next = new DoublyLinkedNode<String>("20");
    DoublyLinkedNode<String> next1 = new DoublyLinkedNode<String>("21");
    DoublyLinkedNode<String> y = new DoublyLinkedNode<String>(previous, "connect", next);
    assertTrue(x.getData() == 19);
    assertTrue(y.getNext().equals(next));
    assertTrue(y.getData().equals("connect"));
    assertTrue(y.equals(y));
    assertTrue(previous.getPrevious()==null);
    assertTrue(next.getNext() == null);
    assertTrue(next.getPrevious() == null);
    y.setNext(next1);
    assertTrue(y.getNext().equals(next1));
    y.setPrevious(previous1);
    assertTrue(y.getPrevious().equals(previous1));
  }
  
  @Test
  public void testSongCollection() {
    
    
    
  }

}
