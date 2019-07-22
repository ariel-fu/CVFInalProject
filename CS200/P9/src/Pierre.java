/*
 * @author: Ariel Fu
 * @studentID:  908 168 5910
 * @netID: afu5
 */
public class Pierre {

	public static void main(String[] args) {
		int[] x = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };
		System.out.println("Is it in order? " + isInOrder(x));
		// out of order
		int[] x1 = new int[] { 2, 1, 93, 153, 21, 236, 9 };
		System.out.println("Is it in order? " + isInOrder(x1));
		// kinda out of order
		int[] x2 = new int[] { 1, 2, 3, 5, 8, 4 };
		System.out.println("Is it in order? " + isInOrder(x2));
		// backward order
		int[] x3 = new int[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		System.out.println("Is it in order? " + isInOrder(x3));

	}

	public static boolean isInOrder(int[] pages) {
		boolean x = true;
		for (int i = 1; i < pages.length; i++) {
			if (pages[i] < pages[i - 1]) {
				x = false;
			}
		}
		return x;
	}
	
	public static void order(int[] pages) {
		   int temp;
		   for (int i = 1; i < pages.length; i++) {
		    for (int j = i; j > 0; j--) {
		     if (pages[j] < pages[j - 1]) {
		      temp = pages[j];
		      pages[j] = pages[j - 1];
		      pages[j - 1] = temp;
		     }
		    }
		   }
	}

}
