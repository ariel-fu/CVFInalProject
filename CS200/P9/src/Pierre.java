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
		boolean isTheArrayInOrder = true;

		for (int i = 1; i < pages.length; i++) {
			if (pages[i] < pages[i - 1]) {
				isTheArrayInOrder = false;
			}
		}
		return isTheArrayInOrder;
	}

	public static void order(int[] pages) {
		int arrLength = pages.length;
		for (int i = 0; i < arrLength; i++) {
			for (int j = 0; j < arrLength - 1; j++) {
				if (pages[j + 1] < pages[j]) {
					int replace = pages[j + 1];
					pages[j + 1] = pages[j];
					pages[j] = replace;
				}
			}
		}
	}
}
