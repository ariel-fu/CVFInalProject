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

		int[] x1 = new int[] {1,3,98,2,5,6,2,7};
		System.out.println("Is it in order? " + isInOrder(x1));
		order(x1);
		printArray(x1);
		System.out.println();
		System.out.println("Is it in order? " + isInOrder(x1));
		// kinda out of order
	//	int[] x2 = new int[] { 1, 2, 3, 5, 8, 4 };
//		System.out.println("Is it in order? " + isInOrder(x2));
		// sort x2
	//	order(x2);
	//	System.out.println("Is it in order? " + isInOrder(x2));
		//// backward order
		//int[] x3 = new int[] { 100000000, 9, 5, 7, 3, 5, 3, 3, 2, 0 };
	//	System.out.println("Is it in order? " + isInOrder(x3));
		// sort x3
//		order(x3);
	//	System.out.println("Is it in order? " + isInOrder(x3));

//		int[] xe = new int[] { 1, 5, 91, 563, 22569 };
	//	System.out.println("Is it in order? " + isInOrder(xe));

		// bucket(xe);
//		System.out.println("Is it in order? " + isInOrder(xe));

		// TODO: test cases for order

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
		pages = sort(pages);
	}
	
	
	public static int[] sort (int[] pages){
		int arrLength = pages.length;
		for(int i=0; i<arrLength; i++){
			for(int j=0; j<arrLength-1; j++){
				if(pages[j+1]<pages[j]){
					int replace = pages[j+1];
					pages[j+1] = pages[j];
					pages[j] = replace;
				}
			}
			}
		return pages;	
	}
	public static int[] sort1(int[] pages) {
		int max = 0;
		for (int i = 0; i < pages.length; i++) {
			if (pages[i] > max) {
				max = pages[i];
			}
		}
		int[] substitueArray = new int[max + 1];
		for (int i = 0; i < pages.length; i++) {
			substitueArray[pages[i]]++;
		}
		int currValue = 0;
		for (int i = 0; i < substitueArray.length; i++) {
			for (int j = 0; j < substitueArray[i]; j++) {
				pages[currValue] = i;
				currValue++;
			}
		}

		return pages;
	}

	public static void printArray(int[] a) {
		System.out.print("{");
		for (int i = 0; i < a.length - 1; i++) {
			System.out.print(a[i] + ", ");
		}
		System.out.print(a[a.length - 1] + "}");
	}
}
