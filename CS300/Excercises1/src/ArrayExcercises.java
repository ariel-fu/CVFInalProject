
public class ArrayExcercises {

  public static void main(String[] args) {
    int[] array = new int[] { 1, 2, 5, 9, 31, 8 };
    double x = calculateAverage(array);
    System.out.println("Average of an array: " + x);
    int[] reverseArray = new int[] { 1, 5, 9, 6, 3, 2, 1 };
    int[] reversed = reverseOrder(reverseArray);
    printArray(reverseArray);
    printArray(reversed);

    int[] tensTrue = new int[] { 10, 20, 5, 593, 2 };
    System.out.println(sumOfTens(tensTrue) + " True!");
    System.out.println(sumOfTens(array) + " False!");

    int[] oddEven = new int[] { 12, 81, 5, 823, 21, 48 };
    printArray(sortEvenAndOdd(oddEven));

    int[] even = new int[] { 2, 22, 24, 48, 86, 862 };
    printArray(sortEvenAndOdd(even));

    int[] odd = new int[] { 21, 23, 245, 483, 869, 8627 };
    printArray(sortEvenAndOdd(odd));

  }

  /**
   * Calculates the average value of array elements (int version)
   * 
   * @param args - int array
   * @return average of all elements
   */
  public static double calculateAverage(int[] args) {
    double average = 0.0;
    for(int i = 0; i < args.length; i++) {
      average += args[i];
    }
    average /= args.length;
    return average;
  }

  /**
   * Calculates the average value of array elements (double)
   * 
   * @param args - double array
   * @return average of all elements
   */
  public static double calculateAverage(double[] args) {
    double average = 0.0;
    for(int i = 0; i < args.length; i++) {
      average += args[i];
    }
    average /= args.length;
    return average;
  }

  /**
   * Finds the index of an array element
   * 
   * @param array   that contains a specific element
   * @param element - specific element
   * @return index of element or -1 if no element exists
   */
  public static int findIndexOfElement(int[] array, int element) {
    for(int i = 0; i < array.length; i++) {
      if(array[i] == element) {
        return i;
      }
    }

    return -1;
  }

  /**
   * Reverse an array of integer values
   * 
   * @param array - array to be reversed
   * @return the array with the values reversed
   */
  public static int[] reverseOrder(int[] array) {
    int[] reverse = new int[array.length];
    int currIndex = 0;
    for(int i = array.length - 1; i >= 0; i--) {
      reverse[currIndex] = array[i];
      currIndex++;
    }
    return reverse;
  }

  /**
   * If the sum of all the 10's in the array is 30 return true.
   * 
   * @param array
   * @return true if the sum of all 10's is exactly 30.
   */
  public static boolean sumOfTens(int[] array) {
    int totalTensSum = 0;
    for(int i = 0; i < array.length; i++) {
      if(array[i] % 10 == 0) {
        totalTensSum += array[i];
      }
    }
    if(totalTensSum != 30) {
      return false;
    }
    return true;
  }

  /**
   * Seperates even and odd numbers of a given array of ints. Puts all even
   * numbers first and then odd numbers.
   * 
   * @param array
   * @return array with the even numbers first then odd numbers
   */

  public static int[] sortEvenAndOdd(int[] array) {
    int[] oddArray = new int[array.length];
    int oddIndex = 0;
    int[] evenArray = new int[array.length];
    int evenIndex = 0;
    int[] sortedArray = new int[array.length];
    for(int i = 0; i < array.length; i++) {
      if(array[i] % 2 != 0) {
        oddArray[oddIndex] = array[i];
        oddIndex++;
      } else if(array[i] % 2 == 0) {
        evenArray[evenIndex] = array[i];
        evenIndex++;
      }
    }

    for(int i = 0; i < evenIndex; i++) {
      sortedArray[i] = evenArray[i];
    }

    int addOdd = 0;
    for(int i = evenIndex; i < array.length; i++) {
      sortedArray[i] = oddArray[addOdd];
      addOdd++;
    }

    return sortedArray;
  }

  /**
   * Prints out an array
   * 
   * @param array
   */
  public static void printArray(int[] array) {
    System.out.print("[ ");
    for(int i = 0; i < array.length - 1; i++) {
      System.out.print(array[i] + " , ");
    }
    System.out.println(array[array.length - 1] + " ]");

  }

}
