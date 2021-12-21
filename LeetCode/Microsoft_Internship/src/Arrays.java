import java.util.ArrayList;
import java.util.List;
// Array problems from leetcode
public class Arrays {
	
  public int trap(int[] height) {
  	if(height.length == 0) {
  		return 0;
  	}
  	int totalTrapped = 0;
  	int size = height.length; 
  	int[] leftMax = new int[size];
  	int[] rightMax = new int[size];
  	
  	leftMax[0] = height[0];
  	for(int i = 1; i<size; i++) {
  		leftMax[i] = (height[i] > leftMax[i-1]) ? height[i] : leftMax[i-1];
  	}
  	rightMax[size-1] = height[size-1];
  	for(int i = size-2; i>=0; i--) {
  		rightMax[i] = (height[i] > rightMax[i+1]) ? height[i] : rightMax[i+1];
  	}
  	for(int i =1; i<size-1; i++) {
  		int min = (leftMax[i] > rightMax[i]) ? rightMax[i] : leftMax[i];
  		totalTrapped += min - height[i];
  	}
  	return totalTrapped;
  	
//	for(int i = 0; i<height.length; i++) {
//	int currHeight = height[i];
//	int maxRight = 0;
//	int maxIndex = i;
//	int runner = height.length-1;
//	while(i < runner) {
//		int runnerHeight = height[runner];
//		if(runnerHeight >= currHeight && runnerHeight >= maxRight) {
//			maxRight = runnerHeight;
//			maxIndex = runner;
//		}
//		runner--;
//	}
//	// choose the smaller height
//	int maxHeight = (currHeight < maxRight) ? currHeight : maxRight;
//	int length = (maxIndex-i-1);
//	int area = maxHeight * length;
//	if(area != 0) {
//		for(int j=i+1; j<maxIndex; j++) {
//			area-=height[j];
//		}
//	}
//	
//	totalTrapped += area;
//}
//
//  	int totalTrapped = 0;
//  	for(int i = 0; i<height.length; i++) {
//  		int currHeight = height[i];
//  		int nextHeightIndex = i+1;
//  		int length = 0;
//  		int blocked = 0;
//  		while(nextHeightIndex < height.length && currHeight > height[nextHeightIndex]) {
//  			length++;
//  			if(height[nextHeightIndex] != 0) {
//  				blocked += height[nextHeightIndex];
//  			}
//  			nextHeightIndex++;
//  		}
//  		i = nextHeightIndex;
//  		// calc total area from that point
//  		int totalArea = (currHeight * length) - blocked;
//  		// add to total trapped water area
//  		totalTrapped += totalArea;
//  	}
//  	
//  	return totalTrapped;
  }
	
  public String restoreString(String s, int[] indices) {
  	char[] arr = new char[indices.length];
  	for(int i = 0; i<indices.length; i++) {
  		int index = indices[i];
  		arr[index] = s.charAt(i);
  	}
  	String ans = "";
  	for(int i = 0; i<arr.length; i++) {
  		ans += arr[i] + "";
  	}
  	
  	return ans;
  	
  }
	
  public int[] smallerNumbersThanCurrent(int[] nums) {
   int[] ans = new int[nums.length];
   int max = 0;
   for(int i = 0; i<nums.length; i++) {
  	 if(nums[i] > max) {
  		 max = nums[i];
  	 }
   }
   int[] hash = new int[max+1];
   for(int i = 0; i<nums.length; i++) {
  	 int curr = nums[i];
  	 hash[curr]++;
   }
   
   for(int i = 0; i<nums.length; i++) {
  	 int totalLessThan = 0; 
  	 int currNum = nums[i];
  	 if(currNum == hash.length) {
  		 totalLessThan = nums.length-1;
  	 } else {
  		 for(int j = 0; j<currNum; j++) {
    		 totalLessThan += hash[j];
    	 }
 
  	 }
  	   	 ans[i] = totalLessThan;
   }
   return ans;
  }
	
  public int[] getConcatenation(int[] nums) {
  	int n = nums.length;
  	int[] ans = new int[n*2];
    for(int i = 0; i<n; i++) {
    	ans[i] = nums[i];
    	ans[i+n] = nums[i];
    }
  	return ans;
  }
	
	public int maximumWealth(int[][]accounts) {
		int[] wealth = new int[accounts.length];
		
		for(int i = 0; i<accounts.length; i++) {
			for(int j = 0; j<accounts[i].length; j++) {
				wealth[i]+=accounts[i][j];
			}
		}
		
		int max = 0;
		for(int i = 0; i<wealth.length; i++) {
			if(wealth[i] > max) {
				max = wealth[i];
			}
		}
		
		return max;
		
	}
	public int missingNumber(int[] nums) {
		int totalSum = (nums.length)*(nums.length+1)/2;
		for(int i = 0; i<nums.length; i++) {
			totalSum-=nums[i];
		}
		
		return totalSum;
	}
	public int[] intersect(int[] nums1, int[] nums2) {
		int[] intersect = new int[Math.max(nums1.length, nums2.length)];
		int numOfIntersectElements = 0;
		for(int i = 0; i<nums1.length; i++) {
			for(int j = 0; j<nums2.length; j++) {
				if(nums1[i] == nums2[j]) {
					intersect = new int[Math.max(nums1.length, nums2.length)];
					int index1 = i;
					int index2 = j;
					int intersectIndex = 0;
					numOfIntersectElements = 0;
					while(index1 < nums1.length || index2 < nums2.length) {
						// if they are equal, add to the intersect array and keep moving
						if(nums1[index1] == nums2[index2]) {
							intersect[intersectIndex] = nums1[index1];
							index1++;
							index2++;
							numOfIntersectElements++;
						} else {
							// otherwise, get out of here
							break;
						}
					}
				}
			}
		}
		
		int[] temp = intersect;
		intersect = new int[numOfIntersectElements];
		for(int i = 0; i<numOfIntersectElements; i++) {
			intersect[i] = temp[i];
		}
		// trim off the end of intersect
		return intersect;
	}
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> ret = new ArrayList<>();
		// add the empty set
		ret.add(new ArrayList<Integer>());

		// add all possible combinations
		for (int i = 0; i < nums.length; i++) {
			List<Integer> oldSubset = new ArrayList<Integer>();
			for (int j = i; j < nums.length; j++) {
				List<Integer> newSubset = new ArrayList<Integer>(oldSubset);
				newSubset.add(nums[j]);
				ret.add(newSubset);
				oldSubset = newSubset;
			}
		}

		return ret;
	}

	public int numJewelsInStones(String jewels, String stones) {
		// create a hashmap with all the types of stones inside
		int[] typesOfStones = new int[59];
		for (int i = 0; i < stones.length(); i++) {
			int stoneAscii = stones.charAt(i) - 65;
			typesOfStones[stoneAscii] += 1;
		}

		// go through all the jewels and add up the total number of stones that are
		// jewels
		int numJewels = 0;
		for (int i = 0; i < jewels.length(); i++) {
			int jewelAscii = jewels.charAt(i) - 65;
			numJewels += typesOfStones[jewelAscii];
		}

		return numJewels;
	}

	public void rotate(int[] nums, int k) {
		// mod k for max efficiency
		k %= nums.length;

		// reverse entire array
		reverse(nums, 0, nums.length - 1);
		// reverse the part that was shifted
		reverse(nums, k, nums.length - 1);
		// reverse the part that came from the end to the front
		reverse(nums, 0, k - 1);
	}

	private void reverse(int[] nums, int startIndex, int endIndex) {
		while (startIndex < endIndex) {
			int temp = nums[startIndex];
			nums[startIndex] = nums[endIndex];
			nums[endIndex] = temp;
			startIndex++;
			endIndex--;
		}
	}

//	public void rotate(int[] nums, int k) {
//		if(k%nums.length == 0) {
//			return;
//		}
//		for(int i = 0; i<(k%nums.length); i++) {
//			int pushIn = nums[0];
//			for(int j = 1; j<nums.length; j++) {
//				int pullOut = nums[j];
//				nums[j] = pushIn;
//				pushIn = pullOut;
//			}
//			// set [0] to the value at .length-1
//			nums[0] = pushIn;
//		}
//	}
	public int maxProfit(int[] prices) {
		int profit = 0;

		for (int i = 1; i < prices.length; i++) {
			if (prices[i - 1] < prices[i]) {
				profit += (prices[i] - prices[i - 1]);
			}
		}
		return profit;
	}

	public List<String> fizzBuzz(int n) {
		String fizz = "Fizz";
		String buzz = "Buzz";
		String fizzbuzz = "FizzBuzz";
		List<String> output = new ArrayList<String>();
		for (int i = 1; i < n + 1; i++) {
			if (i % 3 == 0 && i % 5 == 0) {
				output.add(fizzbuzz);
			} else if (i % 3 == 0) {
				output.add(fizz);
			} else if (i % 5 == 0) {
				output.add(buzz);
			} else {
				output.add(i + "");
			}
		}
		return output;
	}

	public int singleNumber(int[] nums) {
		int xor = 0;
		for (int i = 0; i < nums.length; i++) {
			xor ^= nums[i];
		}
		return xor;
	}

	public int removeDuplicates(int[] nums) {
		if (nums.length < 3) {
			return nums.length;
		}

		int count = 1;
		int nextIndex = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i - 1] == nums[i]) {
				count++;
			} else {
				count = 1;
			}

			if (count <= 2) {
				// add the curr element
				nums[nextIndex] = nums[i];
				nextIndex += 1;
			}
		}

		return nextIndex;
	}

	public void moveZeroes(int[] nums) {
		if (nums.length == 0 || nums.length == 1) {
			return;
		}
		int nonZeroIndex = 0;
		for (int i = 0; i < nums.length; i++) {
			int curr = nums[i];
			if (curr != 0) {
				// non-zero value
				// populate the first non-populated index with the curr value
				nums[nonZeroIndex] = curr;
				if (nonZeroIndex != i) {
					nums[i] = 0; // set the now-empty spot at i to 0
				}
				nonZeroIndex++;
			}
		}
	}

	public int removeDuplicates2(int[] nums) {
		int nextIndex = 0;
		for (int i = 1; i < nums.length; i++) {
			if (nums[nextIndex] != nums[i]) {
				nextIndex++;
				nums[nextIndex] = nums[i];

			}
		}
		return nextIndex + 1;
	}

//	public int singleNumber(int[] nums) {
//		int notDupl = nums[0];
//		for (int i = 0; i < nums.length; i++) {
//			int curr = nums[i];
//			boolean duplicate = false;
//			for (int j = i + 1; j < nums.length; j++) {
//				int runner = nums[j];
//				if (curr == runner) {
//					duplicate = true;
//					break;
//				}
//			}
//			if (!duplicate) {
//				notDupl = curr;
//			}
//		}
//		return notDupl;
//	}

	public int[] createTargetArray(int[] nums, int[] index) {
		// init the ret array to hold values of -1
		int[] ret = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			ret[i] = -1;
		}

		for (int i = 0; i < index.length; i++) {
			int addValue = nums[i];
			// this spot has alreayd been occupied
			if (ret[index[i]] != -1) {
				ret = shift(ret, index[i]);
			}
			// add to index[i]
			ret[index[i]] = nums[i];
		}

		return ret;
	}

	private int[] shift(int[] ret, int index) {
		int pushIn = ret[index];
		for (int i = index + 1; i < ret.length; i++) {
			int pullOut = ret[i];
			ret[i] = pushIn;
			pushIn = pullOut;
			// end of meaningful nums
			if (pushIn == -1) {
				break;
			}
		}
		return ret;
	}

	public int searchInsert(int[] nums, int target) {

		for (int i = 0; i < nums.length; i++) {
			int currValue = nums[i];
			if (currValue >= target) {
				return i;
			}
		}
		return nums.length;

	}

	public int numIdenticalPairs(int[] nums) {
		int numGoodPairs = 0;
		for (int i = 0; i < nums.length; i++) {
			int currElement = nums[i];
			for (int j = i + 1; j < nums.length; j++) {
				int runner = nums[j];
				if (currElement == runner) {
					numGoodPairs++;
				}
			}
		}
		return numGoodPairs;

	}

	public int removeElement(int[] nums, int val) {
		int numElements = 0;
		int swapIndex = 0;

		for (int i = 0; i < nums.length; i++) {
			int curr = nums[i];
			if (curr != val) {
				// swap [i] and [swapIndex]
				nums[i] = nums[swapIndex];
				nums[swapIndex] = curr;
				swapIndex++;
				numElements++;
			}
		}

		return numElements;
	}

	public int[] removeElement2(int[] nums, int val) {
		int swapIndex = 0;

		for (int i = 0; i < nums.length; i++) {
			int curr = nums[i];
			if (curr != val) {
				// swap [i] and [swapIndex]
				nums[i] = nums[swapIndex];
				nums[swapIndex] = curr;
				swapIndex++;
			}
		}

		return nums;
	}

	public List<String> commonChars(String[] A) {
		if (A.length == 0) {
			return null;
		}
		int[] numCharCount = new int[26];
		for (int i = 0; i < A.length; i++) {
			String currString = A[i];
			for (int j = 0; j < currString.length(); j++) {
				char currChar = currString.charAt(j);
				int hashIndex = (int) currChar - 'a';
				// increment the count at numCharCount
				numCharCount[hashIndex]++;
			}
		}

		List<String> ret = new ArrayList<>();
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < numCharCount[i]; j++) {
				char convertChar = 'a';
				convertChar += i;
				ret.add(Character.toString(convertChar));
			}
		}

		return ret;
	}

}
