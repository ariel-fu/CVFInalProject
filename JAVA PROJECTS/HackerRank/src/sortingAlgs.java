import java.util.Random;

public class sortingAlgs {

  private static int[] arr;

  public static void main(String[] args) {
    arr = new int[50];
    for (int i = 0; i < arr.length; i++) {
      Random rand = new Random();
      int value = rand.nextInt(1000);
      arr[i] = value;
    }
    print(arr);
    arr = insertionSort(arr);
    System.out.println("****** sorting *******");
    print(arr);
  }

  /**
   * Sorts an int array by insertion sort (hopefully)
   * 
   * @param arr - int array
   * @return a sorted array
   */
  private static int[] insertionSort(int[] arr) {

    for (int i = 1; i < arr.length; i++) {
      // get the current indices
      int index1 = i;
      int index2 = i - 1;

      // element at index1
      int currElement = arr[index1];
      // element at index2
      int prevElement = arr[index2];

      // while the element at index1 < element at index2
      while (currElement < prevElement) {

        arr = swap(arr, index1, index2);

        if (index2 >= 1) {
          // subtract index1 and index2
          index1--;
          index2--;
          prevElement = arr[index2];
          currElement = arr[index1];
        } else {
          break;
        }

      }
      prevElement = currElement;
    }
    return arr;
  }e

  /**
   * This takes in an array and swaps two index
   * 
   * @param arr    - array
   * @param index1 - index 1
   * @param index2 - index2
   * @return an array with the elements at index1 and index2 swapped
   */
  private static int[] swap(int[] arr, int index1, int index2) {
    int ele1 = arr[index1]; // holds the value at index1 before swap
    int ele2 = arr[index2]; // holds the value at index2 before swap
    arr[index1] = ele2;
    arr[index2] = ele1;

    return arr;
  }

  private static void print(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }
}
