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
    assertTrue(test.toString().equals("elmo by ariel"));

    assertTrue(test.equals(test));
    assertTrue(!test.equals(null));
    Song other = new Song("elmo", "ariel");
    assertTrue(test.equals(other));
    Song test1 = new Song("2elmo", "ariel");
    assertTrue(!test.equals(test1));
    assertTrue(!test.equals(test.toString()));
  }

  @Test
  public void testDoublyLinkedNode() {
    DoublyLinkedNode<Integer> x = new DoublyLinkedNode<Integer>(19);
    DoublyLinkedNode<String> previous = new DoublyLinkedNode<String>("19");
    DoublyLinkedNode<String> next = new DoublyLinkedNode<String>("20");
    assertTrue(x.getData() == 19);
    assertTrue(previous.getPrevious() == null);
    assertTrue(next.getNext() == null);
    assertTrue(next.getPrevious() == null);

    //prev > y > next
    DoublyLinkedNode<String> y = new DoublyLinkedNode<String>(previous, "connect", next);
    assertTrue(y.getData().equals("connect"));
    assertTrue(y.equals(y));

    assertTrue(y.getNext().equals(next));
    //TODO - test get prev

    
    
    //set next,prev (override existing value)
    DoublyLinkedNode<String> next1 = new DoublyLinkedNode<String>("21");
    DoublyLinkedNode<String> previous1 = new DoublyLinkedNode<String>("1");
    y.setNext(next1);
    assertTrue(y.getNext().equals(next1));
    y.setPrevious(previous1);
    assertTrue(y.getPrevious().equals(previous1));
    //TODO - override with null
  }

  @Test
  public void testSongCollection() {
    // test constructor - head is null, tail is null, play direction is true
    // (Playlist.)
    SongCollection test = new SongCollection();
    assertTrue(getHead(test) == null);
    assertTrue(getTail(test) == null);

    // test add - adds to end of collection, if list is empty -> both head and tail
    // become the song added.
    test = new SongCollection();
    Song song1 = new Song("ew", "poep");
    test.add(song1);
    assertTrue(getHead(test).equals(song1));
    assertTrue(getTail(test).equals(song1));

    //TODO - not add song, how did this pass
    Song song2 = new Song("talking to the moon", "trying to get to you");
    test.add(song2);
    assertTrue(getHead(test).equals(song1));
    assertTrue(getTail(test).equals(song2));

    // test remove - removes from head of the collection, if list size = 1 head &
    // tail should be equal to null.
    test = new SongCollection();
    test.add(song2);

    // test remove when size == 1.
    test.remove();
    assertTrue(getHead(test) == null);
    assertTrue(getTail(test) == null);

    // test iterator, return Playlist if play direction is true, return
    // ReversePlaylist if play direction is false.
    test = new SongCollection();
    assertTrue(test.iterator() instanceof Playlist);
   
    test.setPlayDirection(false);
    assertTrue(test.iterator() instanceof ReversePlaylist);
    
    //TODO - make sure the list is built correctly when there are multiple songs

  }

  private static Song getHead(SongCollection list) {
    list.setPlayDirection(true);
    if(list.iterator().hasNext()) {
      return list.iterator().next();
    } else {
      return null;
    }
  }

  private static Song getTail(SongCollection list) {
    list.setPlayDirection(false);
    if(list.iterator().hasNext()) {
      return list.iterator().next();
    } else {
      return null;
    }
  }

  @Test
  public void testPlaylist() {
    Song other = new Song("tay", "afterglow");
    DoublyLinkedNode<Song> head = new DoublyLinkedNode<Song>(other);
    
    Playlist test = new Playlist(head);
    assertTrue(test.hasNext());
    assertTrue(test.next().equals(other));

    try {
      test.next();
      assertTrue(false);
    } catch (NoSuchElementException e) {
    	assertTrue(true);
    }
    
    //TODO - test playlist with multiple songs
    Song[] songs=new Song[3];
    for (int i=0; i<songs.length; i++) {
    	songs[i]=new Song("Song"+i,"author"+i);
    }
    head = new DoublyLinkedNode<Song>(songs[0]);
    DoublyLinkedNode<Song> one = new DoublyLinkedNode<Song>(songs[1]);
    head.setNext(one);
    DoublyLinkedNode<Song> two = new DoublyLinkedNode<Song>(songs[2]);
    one.setNext(two);
    //add test cases

    //TODO - test empty playlist
    test=new Playlist(null);
  }

  @Test
  public void testReversePlaylist() {
	  //TODO - add same test cases as the testPlaylist
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
