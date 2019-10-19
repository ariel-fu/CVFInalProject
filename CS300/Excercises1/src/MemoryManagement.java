import java.util.ArrayList;

public class MemoryManagement {

  public static void sampleObjectsInHeap() {
    // How many objects are at least in the heap after the following segment of code
    // runs?
    Character[] chars = new Character[] { 'C', 'a', null, 'R', new Character('s') };
    // 1 the array of elements of type Character which reference is stored in chars
    // 3 objects instances of the Character class result of auto-boxing operations
    // 'C', 'a', and 'R'
    // 1 object instance of Character added to the heap after calling the
    // constructor of the class Character using new Character('s')
    // Total: 5 objects (one array and four instances of Character class) are at
    // least in the heap.

  }

  public static void sampleArrayListOperations() {
    // what would the following
    ArrayList<Integer> digits = new ArrayList<>();
    digits.add(1); // digits --> 1
    digits.add(0, 2); // digits --> 2 1
    digits.add(1, 8); // digits --> 2 8 1
    digits.remove(0); // digits --> 8 1
    digits.add(5); // --> digits --> 8 1 5
    digits.set(digits.size() - 1, 3); // --> 8 1 3
    for(int i = 0; i < digits.size(); i++) {
      System.out.print(digits.get(i));
    }
    // 813 printed out to the console
  }

  public static int sumNumbers(String str) {
    int sum = 0;
    char test;
    String add = "";

    for(int i = 0; i < str.length(); i++) {

      test = str.charAt(i);
      if(Character.isDigit(test)) {

        add += test;

      } else {
        if(!add.equals("")) {
          sum += Integer.parseInt(add);
        }
        add = "";
      }
    }
    if(!add.equals("")) {
      sum += Integer.parseInt(add);
    }
    return sum;
  }

  public static void main(String[] args) {
    /**
     * Memory Management in Java // The JVM consists mainly of the following memory
     * areas: // (1) The stack (that represents the stack of method calls and also
     * the space where all // primitive type variables defined in your program are
     * stored). // (2) The heap (largest memory space in a JVM). All objects created
     * in your program are stored // in the heap. Objects which are no more
     * addressed (not referenced any more) are automatically // discarded (garbage
     * collected). // (3) Static memory space (aka METASPACE) (small memory area)
     * where static variables and static // memory are stored
     * 
     * // instance fields and instance methods belong to objects. They are loaded
     * within every object // instance in the heap
     * 
     * // static variables, static constants, and static methods are stored in the
     * METASPACE which // belongs to the class and are shared among all objects
     * 
     */
    sampleArrayListOperations(); // 813
    System.out.println();

    String x = "abc123xyz";
    int sum = sumNumbers(x);
    System.out.println(sum);
  }
}