/**
 * @author: Ariel Fu
 * @studentID: 908 168 5910
 * @netID: afu5
 */
public class FindCasino {

	public static void main(String[] args) {
		//start with X and Y with 11 and 5 (respectively) 
		int x = 11;
		int y = 5;
		//run the code 365 times for every day of the year
		for (int i = 1; i <= 365; i++) {
			x = findXLocation(x, y);
			y = findYLocation(y);
			System.out.println("Day " + i + ": x=" + x + ", y=" + y);
		}

	}

	public static int findXLocation(int oldX, int oldY) {
		//if the previous X is dividable by the previous Y, then the X location is X/Y
		if (oldX % oldY == 0) {
			return oldX / oldY;
		} else {
			//otherwise the X location is X*Y
			return oldX * oldY;
		}
	}

	public static int findYLocation(int oldY) {
		//if the old Y location is less than 10, the Y location is Y+1
		if (oldY < 10) {
			return oldY + 1;
		} else {
			//otherwise, the new Y location is Y-9
			return oldY - 9;
		}
	}

}
 