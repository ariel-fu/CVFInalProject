
public class SWE_Fall {

	public static int constructRectangles(int[] A) {
		// construct an array that is size of A / 4 -- max number of rectangles
		int[] rectangles = new int[1000];
		
		for(int i = 0; i <A.length; i++) {
			// add matching ones to the rectangleSizes, wehre each index = number of appearances
			rectangles[A[i]-1]+=1;
		}
		
		int first = -1;
		// max
		int difference = 1001;
		
		for(int i = 0; i < rectangles.length; i++) {
			if(rectangles[i] >= 4) {
				System.err.println("ERR " + rectangles[i] + " " + i);
				return 0;
			}
			
			if(rectangles[i] >= 2) {
				// 
				if(first == -1) {
					first = i;
					continue;
				} else {
					int currDifference = rectangles[i] - rectangles[first];
					if(difference > currDifference) {
						difference = currDifference;
					}
					first = i;
				}
			}
			
		}
		
		if(difference == 1001) {
			return -1;
		}
		return difference;
	}
	
	
	public static int insertChars(String S) {
		if(S == null) {
			// insert a if null
			return 0;
		}
		
		int num = 0;
		
		for(int i = 0; i < S.length(); i++) {
			char curr = S.charAt(i);
			
			if(i == 0) {
				// curr = a, disregard
				if(curr == 'b') {
					num += 1;	
				} else if(curr == 'c'){	// curr = c
					num += 2;
				}
			} else {
				if (S.length() < 2) {
					break;
				}
				char prev = S.charAt(i-1);
				if(curr == 'a') {
					// next = a, b, or c
					// System.err.println("curr " + curr + " next " + next);
					
					if(prev == 'a') {
						// add b, c
						num += 2;
					} else if(prev == 'b'){
						// add b						
						num += 1;
					} 
				} else if(curr == 'b') {
					if(prev == 'b') {
						// add a, c
						num += 2;
					} else if(prev == 'c'){
						// add b
						num += 1;
					}				
				} else {	// curr = c
					if(prev == 'c') {
						// add a, b
						num += 2;
					} else if(prev == 'a'){
						// add a
						num += 1;
					}				
				}
			}			
		}
		
		return num;
	}
}
