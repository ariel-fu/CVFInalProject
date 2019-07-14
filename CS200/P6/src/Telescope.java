/**
 * @author: Ariel Fu
 * @studentID: 908 168 5910
 * @netID: afu5
 */
public class Telescope {

	public static void main(String[] args) {
		System.out.println("5--> "+degrees(4));
		System.out.println("1 --> "+degrees(1));
		System.out.println("1--> "+degrees(2));

	}
	
	public static int degrees(int day) {
		//since using two recursive cases, use two base cases: 1,2
		if(day ==1) {
			return 1;
		}
		else if(day ==2) {
			return 1;
		}
		//the curr day is yesterday plus the day three days ago, plus one
		else {
			return degrees(day-1)+degrees(day-2)+1;
		}
	}

}
