import java.util.Iterator;

public class TestClass {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    SongCollection x = new SongCollection();
    for(int i=0; i<10; i++) {
      x.add(new Song("#" + i, "ariel fu"));
    }
    x.setPlayDirection(false);
    Iterator<Song> iterator = x.iterator();
    System.out.print("[ ");
    Song currSong;
    while(iterator.hasNext()) {
      currSong = iterator.next();
      if(currSong == null) {
        System.out.print("NULL!");
      }
      System.out.print(currSong.toString() + ", " );
      
    }
    System.out.println(" ]");
  }

}
