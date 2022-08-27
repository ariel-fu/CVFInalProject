import java.util.ArrayList;
import java.util.List;

class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
 

public class August21 {

    public int[] runningSum(int[] nums) {
    	// get list of running sum
    	int[] runningSum = new int[nums.length];
    	runningSum[0] = nums[0];
    	for(int i = 1; i<nums.length; i++) {
    		runningSum[i] = runningSum[i-1] + nums[i];
    	}
    	
    	return runningSum;
    }
    
    public int pivotIndex(int[] nums) {
    	// run through every combination?
    	for(int i = 0; i<nums.length; i++) {
    		int leftSum = 0;
    		for(int j = 0; j<i; j++) {
    			leftSum += nums[j];
    		}
    		int rightSum = 0;
    		for(int j = i+1; j<nums.length; j++) {
    			rightSum += nums[j];
    		}
    		if(leftSum == rightSum) {
    			return i;
    		}
    	}
    	return -1;
    }
    
    public ListNode middleNode(ListNode head) {
        int length = 0;
        ListNode current = head;
        while(current != null) {
        	length++;
        	current = current.next;
        }
        
        int middleIndex = length/2;
        current = head;
        for(int i = 0; i<middleIndex; i++) {
        	current = current.next;
        }
        return current;
    }
    
    public int balancedStringSplit(String s) {
        int numBalanced = 0;
        char[] sArr = s.toCharArray();
        int numL = 0;
        int numR = 0;
        for(int i = 0; i < sArr.length; i++) {
        	if(sArr[i] == 'L') {
        		numL += 1;
        	} else {
        		numR += 1;
        	}
        	if(numL == numR) {
        		numBalanced += 1;
        		numL = 0;
        		numR = 0;
        	}
        }
        return numBalanced;
    }
    
    public boolean isIsomorphic(String s, String t) {
    	char[] hashmap = new char[128];
    	char[] trackVals = new char[128];
    	for(int i = 0; i < t.length(); i++) {
    		// for every t char, check the hashmap for the s map
    		char test = hashmap[s.charAt(i)];
    		int testInt = hashmap[s.charAt(i)];
    		
    		int tTest = t.charAt(i);
    		if(hashmap[s.charAt(i)] == 0) {
    			// test for dups
    			if(trackVals[t.charAt(i)] != 0) {
    				return false;
    			}
    			// if the hashmap doesn't contain the char, set it to s's curr char
    			hashmap[s.charAt(i)] = t.charAt(i);
    			trackVals[t.charAt(i)] = t.charAt(i);
    		} else {
    			// check if hashmap s char = curr s char
    			if(hashmap[s.charAt(i)] != t.charAt(i)) {
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    public boolean isSubsequence(String s, String t) {
    	if(s == null || s.equals("")) {
    		return true;
    	}
    	if(t == "" && !s.equals("") ) {
    		return false;
    	}
        int sCounter = 0;
        char sValue = s.charAt(sCounter);
        for(int i = 0; i < t.length(); i++) {

        	if(sValue == t.charAt(i)) {
        		sCounter ++;
            	// check if we reached the end of s
            	if(sCounter == s.length()) {
            		return true;
            	}
        		sValue = s.charAt(sCounter);
        	}
        }
        return sCounter == s.length();
    } 
    
    public int findMiddleIndex(int[] nums) {
    	int index;
    	int leftSum = nums[0];
    	int rightSum = 0;
    	for(int i = 2; i<nums.length; i++) {
    		rightSum += nums[i];
    	}
    	
    	if(rightSum == 0) {
    		return 1;
    	}
    	
    	for(int i = 1; i <nums.length-1; i++) {
    		index = i;
    		if(leftSum == rightSum) {
    			return index;
    		} else {
    			leftSum += nums[index];
    			rightSum -= nums[index + 1];
    		}
    	}
    	
    	// test total
    	int total = 0;
    	for(int i = 0; i < nums.length-1; i++) {
    		total += nums[i];
    	}
    	
    	if(total == 0) {
    		return nums.length - 1;
    	}
    	
    	return -1;
    }
    
    
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
    	List<List<Integer>> groups = new ArrayList<List<Integer>>();
    	
    	int i = 0;
    	//for(int i = 0; i < groupSizes.length; i++) {
    	while(i < groupSizes.length) {
    		// get curr group size
    		int currGroupSize = groupSizes[i];
    		if(currGroupSize == -1) {
    			i++;
    			continue;
    		}
    		// remove curr val
    		groupSizes[i] = -1;
    		ArrayList<Integer> currGroup = new ArrayList<Integer>();
    		currGroup.add(i);
    		int counter = 1;
    		for(int j = 0; j < groupSizes.length; j++) {
    			if(groupSizes[j] == currGroupSize) {
    				if(counter == currGroupSize) {
    					break;
    				}
    				currGroup.add(j);
    				// remove curr val
    	    		groupSizes[j] = -1;
    	    		counter++;
    			}
    		}
    		groups.add(currGroup);
    		i++;
    	}
    	return groups;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
