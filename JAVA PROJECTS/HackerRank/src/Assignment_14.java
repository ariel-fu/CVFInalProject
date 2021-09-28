import java.util.ArrayList;
import java.util.HashMap;

public class Assignment_14 {

//  public static void main(String[] args) {
//    String pin = "";
//    ArrayList<String> threeRepeats = new ArrayList<String>();
//
//    int totalNumRepeats = 0;
//    for (int i = 0; i < threeRepeats.size(); i++) {
//      String currString = threeRepeats.get(i);
//
//      HashMap map = repeats(currString);
//
//      if (hasOneRepeat(map, currString)) {
//        totalNumRepeats++;
//      }
//
//    }
//    System.out.println(totalNumRepeats);
//  }
//
//  public static boolean threeRepeat(String temp, int value) {
//    int repeat = 0;
//    for (int i = 0; i < temp.length(); i++) {
//      int currValue = (int) temp.charAt(i);
//      if (currValue == value) {
//        repeat++;
//      }
//    }
//    if (repeat == 3) {
//      return true;
//    } else {
//      return false;
//    }
//  }
//
//  public static HashMap repeats(String str) {
//    //
//    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
//
//    // pre-populate the map
//    for (int i = 0; i < str.length(); i++) {
//      map.put(str.charAt(i), 0);
//    }
//
//    for (int i = 0; i < str.length(); i++) {
//      char currChar = str.charAt(i);
//      // get the curr char's value, ie the number of times that char has shown up
//      int currCharTimes = map.get(currChar);
//      // increment that value
//      currCharTimes++;
//      // add back into the map
//      map.put(currChar, currCharTimes);
//    }
//
//    return map;
//  }
//
//  public static boolean hasOneRepeat(HashMap map, String stringAssociated) {
//
//    int x = 0;
//    for (int i = 0; i < map.size(); i++) {
//      char curr = stringAssociated.charAt(i);
//      int total = (int) map.get(curr);
//      if (total > 1) {
//        x++;
//      }
//    }
//    return x == 1;
//  }
//Java program to distinct permutations of the string

//Returns true if str[curr] does not matches with any of the 
//characters after str[start] 
  static boolean shouldSwap(char str[], int start, int curr) {
    for (int i = start; i < curr; i++) {
      if (str[i] == str[curr]) {
        return false;
      }
    }
    return true;
  }

//Prints all distinct permutations in str[0..n-1] 
  static void findPermutations(char str[], int index, int n) {
    if (index >= n) {
      System.out.println(str);
      return;
    }

    for (int i = index; i < n; i++) {

      // Proceed further for str[i] only if it
      // doesn't match with any of the characters
      // after str[index]
      boolean check = shouldSwap(str, index, i);
      if (check) {
        swap(str, index, i);
        findPermutations(str, index + 1, n);
        swap(str, index, i);
      }
    }
  }

  static void swap(char[] str, int i, int j) {
    char c = str[i];
    str[i] = str[j];
    str[j] = c;
  }

  // Driver code
  public static void main(String[] args) {

    char str[] = { 'W', 'I', 'S', 'C', 'O', 'N', 'S', 'I', 'N' };
    int n = str.length;
    findPermutations(str, 0, n);
  }

}
