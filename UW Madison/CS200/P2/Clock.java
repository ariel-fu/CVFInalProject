/**
 * 
 */
package P2;

/**
 * author: Ariel Fu
 * netID: afu5
 * studentID: 9081685910
 */
public class Clock {

	
	public static void main(String[] args) {
		System.out.println("Should return 0, results: " + computeSecondHandAngle(0));
		System.out.println("Should return 6, results: " + computeSecondHandAngle(1));
		System.out.println("Should return 60, results: " + computeSecondHandAngle(10));
		System.out.println("Should return 0, results: " + computeSecondHandAngle(60));
		System.out.println("Should return 90, results: " + computeSecondHandAngle(75));
		
		//test minutes!!
		System.out.println("Should return 0, results: " + computeMinuteHandAngle(0));
		System.out.println("Should return 6, results: " + computeMinuteHandAngle(1));
		System.out.println("Should return 60, results: " + computeMinuteHandAngle(10));
		System.out.println("Should return 0, results: " + computeMinuteHandAngle(60));
		System.out.println("Should return 90, results: " + computeMinuteHandAngle(75));
		
		//and also hours 
		System.out.println("Should return 0, results: " + computeHourHandAngle(0));
		System.out.println("Should return 60, results: " + computeHourHandAngle(2));
		//and test more...
		System.out.println("Should return 0, results: " + computeHourHandAngle(12));
		System.out.println("Should return 270, results: " + computeHourHandAngle(21));	
	}
	
	
	public static int computeSecondHandAngle(int seconds){
		return timeToAngle(seconds, 60);
	}
	
	public static int computeMinuteHandAngle(int minutes){
		return timeToAngle(minutes, 60);
	}
	
	public static int computeHourHandAngle(int hours){
		return timeToAngle(hours, 12);
	}
	
	
	/**
	 * @param time - time
	 * @param unit - unit of time that is a full circle
	 * @return angle
	 */
	public static int timeToAngle(int time, int unit){
		int timeMod = time%unit;
		int angle = timeMod*(360/unit);
		return angle;
	}

}
