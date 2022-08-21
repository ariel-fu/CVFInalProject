import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Example of code showing how to iterate over a List collection with String Object items using
 * for-each loop
 */
public class TestIterator {

  /**
   * Use of an Iterator over an example of Collection: ArrayList
   * 
   * @param args
   */
  public static void main(String[] args) {

    // Declare the list of String object items
    List<String> stringList = new ArrayList<String>();
    stringList.add("cs300-P01");
    stringList.add("cs300-P02");
    stringList.add("cs300-P03");

    // Use of classic for( ; ; ) loop to iterate over the stringList
    System.out.print("List content: ");

    for (int i = 0; i < stringList.size(); i++)
      System.out.print(stringList.get(i) + "; ");

    System.out.println();

    // Use of an Iterator to iterate over the stringList
    Iterator<String> iter = stringList.iterator();
    System.out.print("List content: ");

    while (iter.hasNext()) {
      System.out.print(iter.next() + "; ");
    }

    System.out.println();

    // Use of for-each loop to iterate over the stringList
    System.out.print("List content: ");
    for (String s : stringList)
      System.out.print(s + "; ");

    int[] arr = new int[] {1, 10, 20, 30, 40, 50};
    System.out.println();
    System.out.print("Array content: ");
    for (int x : arr)
      System.out.print(x + " ");

    String[] sList = new String[] {"cs300-P01", "cs300-P02", "cs300-P03"};
    System.out.print("\nList content: ");
    for (String s : sList)
      System.out.print(s + "; ");

    System.out.println();
    System.out.print("List content: ");
    for (String s : stringList) {
      System.out.print(s + "; ");
      s = null; // this statement has no effect. You cannot change the contents of a list using a
                // for-each loop
    }

    System.out.println();
    System.out.print("List content: ");
    for (String s : stringList)
      System.out.print(s + "; ");


  }

}
