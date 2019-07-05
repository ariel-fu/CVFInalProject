/**
 * @author: Ariel Fu
 * @studentID: 908 168 5910
 * @netID: afu5
 */
public class FindCasino {

	public static void main(String[] args) {
		int x = 11;
		int y = 5;
		for (int i = 1; i <= 365; i++) {
			x = findXLocation(x, y);
			y = findYLocation(y);
			System.out.println("Day " + i + ": x=" + x + ", y=" + y);
		}

	}

	public static int findXLocation(int oldX, int oldY) {
		if (oldX % oldY == 0) {
			return oldX / oldY;
		} else {
			return oldX * oldY;
		}
	}

	public static int findYLocation(int oldY) {
		if (oldY < 10) {
			return oldY + 1;
		} else {
			return oldY - 9;
		}
	}

}
