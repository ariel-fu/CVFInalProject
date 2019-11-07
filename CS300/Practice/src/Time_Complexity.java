import java.util.LinkedList;

public class Time_Complexity {

  public static int counter(int n) {
    int count = 0;
    for(int i = n; i > 0; i--) {
      for(int j = i; j < n; j++) {
        System.out.println("i: " + i + " j: " + j);
        count += 1;
      }
    }
    return count;
  }

  public static void testMethod(Character[] input) {
    LinkedList<Character> allChars = new LinkedList<Character>();
    for(int i = 0; i < input.length; i++) {
      if(input[i].equals('D')) {
        allChars.add(input[i]);
      } else {
        System.out.println("After " + i + " iterations, break out of loop!");
        break;
      }
    }

    if(allChars.size() < 1000) {
      for(int i = 0; i < allChars.size(); i++) {
        System.out.println(allChars.get(i));
      }
    }
  }

  public static void main(String[] args) {
    Character[] input = new Character[] { 'D', 'D', 'D', 'D', 'D' };

    testMethod(input);
    System.out.println("---------------");
    System.out.println(counter(10));
  }

}
