/*
 * @author: Ariel Fu
 * @studentID:  908 168 5910
 * @netID: afu5
*/

public class Pierre {

	public static void main(String[] args) {
		int[] test = new int[100000];
		for (int i = 0; i < test.length; i++) {
			test[i] = (int) (Math.random() * 1000000);
		}
	}

	public static boolean isInOrder(int[] pages) {
		//look for one mistake, because if there is one out of order the whole array is out of order
		for (int i = 1; i < pages.length; i++) {
			if (pages[i] < pages[i - 1]) {
				//if one is out of order, return false and quit
				return false;
			}
		}
		//if no mistakes were found, return true
		return true;
	}

	public static void order(int[] pages) {
		int arrLength = pages.length;
		//if the array is already in order, return back to main!
		if(isInOrder(pages)) {
			return;
		}
		//otherwise look through every array element to see which one is out of order
		for (int i = 0; i < arrLength; i++) {
			for (int j = 0; j < arrLength - 1; j++) {
				//when we find an element out of order, swap it with the one smaller than it!
				if (pages[j + 1] < pages[j]) {
					int replace = pages[j + 1];
					pages[j + 1] = pages[j];
					pages[j] = replace;
				}
			}
		}
	}
}
