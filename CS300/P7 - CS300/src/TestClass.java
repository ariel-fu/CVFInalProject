import java.util.Iterator;

public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SongCollection x = new SongCollection();
		for (int i = 0; i < 10; i++) {
			x.add(new Song("#" + i, "ariel fu"));
		}
//    x.setPlayDirection(false);
//    Iterator<Song> iterator = x.iterator();
//    System.out.print("[ ");
//    Song currSong;
//    while(iterator.hasNext()) {
//      currSong = iterator.next();
//      if(currSong == null) {
//        System.out.print("NULL!");
//      }
//      System.out.print(currSong.toString() + ", " );
//      
//    }
//    System.out.println(" ]");

		analysisMethodC(x);
		System.out.println("Forward playing finished, on to reverse!");
		analyze(x);
	}

	public static void analysisMethodC(SongCollection songs) {
		Iterator<Song> playlist = songs.iterator();
		for (int i = 0; i < 1000; i++)
			if (playlist.hasNext())
				System.out.println(playlist.next());
	}

	public static void analyze(SongCollection song) {
		
	}

}
