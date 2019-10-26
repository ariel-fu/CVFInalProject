import static org.junit.Assert.*;

import java.util.NoSuchElementException;

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
    assertTrue(previous.getPrevious() == null);
    assertTrue(next.getNext() == null);
    assertTrue(next.getPrevious() == null);
    y.setNext(next1);
    assertTrue(y.getNext().equals(next1));
    y.setPrevious(previous1);
    assertTrue(y.getPrevious().equals(previous1));
  }

  @Test
  public void testSongCollection() {
    // test constructor - head is null, tail is null, play direction is true
    // (Playlist.)
    SongCollection test = new SongCollection();
//    assertTrue(test.getHead() == null);
//    assertTrue(test.getTail() == null);
//    assertTrue(test.getPlayDirection());
//    // test setPlayDirection - true -> true, false -> false
//    test.setPlayDirection(false);
//    assertTrue(test.getPlayDirection() == false);
//    test.setPlayDirection(true);
//    assertTrue(test.getPlayDirection());

    // test add - adds to end of collection, if list is empty -> both head and tail
    // become the song added.
    Song song1 = new Song("ew", "poep");
    test.add(song1);
//    assertTrue(test.getHead().getData().equals(song1));
//    assertTrue(test.getTail().getData().equals(song1));
//    assertTrue(test.getSize() == 1);
//    Song song2 = new Song("talking to the moon", "trying to get to you");
//    test.add(song2);
//    assertTrue(test.getSize() == 2);
//    assertTrue(test.getHead().getData().equals(song1));
//    assertTrue(test.getTail().getData().equals(song2));

    // test remove - removes from head of the collection, if list size = 1 head &
    // tail should be equal to null.

    test.remove();
//    assertTrue(test.getHead().getData().equals(song2));
//    assertTrue(test.getTail().getData().equals(song2));
//    assertTrue(test.getSize() == 1);
//    test.remove();
//
//    assertTrue(test.getHead() == null);
//    assertTrue(test.getTail() == null);
//    assertTrue(test.getSize() == 0);

    // test iterator, return Playlist if play direction is true, return
    // ReversePlaylist if play direction is false.

    assertTrue(test.iterator() instanceof Playlist);
    test.setPlayDirection(false);
    assertTrue(test.iterator() instanceof ReversePlaylist);

  }

  @Test
  public void testPlaylist() {
    Song other = new Song("tay", "afterglow");
    DoublyLinkedNode<Song> head = new DoublyLinkedNode<Song>(other);
    Playlist test = new Playlist(head);
    // test Constructor sets current to head.
//    assertTrue(test.getCurrent().equals(head));
    assertTrue(test.hasNext());
    assertTrue(test.next().equals(other));
    // test song out of collection bounds!
    try {
      test.next();
      System.out.println("didn't work.");
    } catch (NoSuchElementException e) {
      System.out.println("exception was thrown!");
    }
  }

  @Test
  public void testReversePlaylist() {
    Song other = new Song("twenty years", "go where you go");
    DoublyLinkedNode<Song> tail = new DoublyLinkedNode<Song>(other);
    ReversePlaylist test = new ReversePlaylist(null);
//    assertTrue(test.getCurrent() == null);
    assertTrue(test.hasNext() == false);
    try {
      test.next();
    } catch (NoSuchElementException e) {
      System.out.println("null exception was thrown!");
    }

    test = new ReversePlaylist(tail);
//    assertTrue(test.getCurrent().equals(tail));
    assertTrue(test.hasNext());
    assertTrue(test.next().equals(other));
    try {
      test.next();
    } catch (NoSuchElementException e) {
      System.out.println("out of bounds !");
    }

  }

}
