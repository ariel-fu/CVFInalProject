import java.util.Iterator;
import java.util.ArrayList;

public class P07_PreAssignment_Quiz {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    ArrayList<String> list = new ArrayList<>();
    list.add("a");

    list.add("b");

    list.add("c");
    Iterator<String> iterator = list.iterator();
    for(int i = 0; i < 3; i++) {
      
      System.out.print(iterator.next());
    }
  }

}
