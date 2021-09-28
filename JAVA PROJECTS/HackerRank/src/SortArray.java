
public class SortArray {
  public int[] sortArray(int[] nums) {
    if (nums == null) {
      return null;
    }
    // use a selection sort method
    for (int i = 0; i < nums.length; i++) {
      // first element of unsorted portion
      int minIndex = i;
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[minIndex] > nums[j]) {
          minIndex = j;
        }
      }
      // swap the values at minIndex and i
      int temp = nums[i];
      nums[i] = nums[minIndex];
      nums[minIndex] = temp;
    }

    return nums;
  }

  public int[] sortArrayInsertion(int[] nums) {

    for (int i = 1; i < nums.length; i++) {
      int currIndex = i;
      int prevIndex = currIndex - 1;
      int currValue = nums[currIndex];
      int prevValue = nums[prevIndex];
      while (currValue < prevValue) {

        nums = swap(nums, currIndex, prevIndex);
        currIndex--;
        prevIndex = currIndex - 1;
        if (prevIndex >= 0) {
          currValue = nums[currIndex];
          prevValue = nums[prevIndex];
        } else {
          break;
        }
      }
    }
    return nums;
  }

  private int[] swap(int[] nums, int index1, int index2) {
    int temp = nums[index1];
    nums[index1] = nums[index2];
    nums[index2] = temp;
    return nums;
  }
}
