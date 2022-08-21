package P2;
/*
 * Name: Ariel Fu
 * studentID: 9081685910
 * netID: afu5
 */

public class Paint {

	public static void main(String[] args) {
		// testing cases!
		System.out.println(computeClockArea(0.0));

		System.out.println(computeClockArea(1.0));
		//computeClockArea(1.0) should return 3.14
		//testing for circles!
		
		System.out.println(computeClockArea(4.0));
		//computeClockArea(4.0) should return 50.24
		//testing for circles!
		
		System.out.println(computeWallArea(0.0,0.0));
		System.out.println(computeWallArea(2.0,0.0));
		System.out.println(computeWallArea(0.0,2.0));

		System.out.println(computeWallArea(2.0,4.0));
		//computeWallArea(2.0,4.0,) should return 8.0
		//testing for wall area!
		
		System.out.println(computeWallArea(3.0, 3.0));
		//computeWallArea(3.0,3.0) should return 9.0
		//testing for wall area!
		
		System.out.println(computeExposedWallArea(0.0, 0, 0.0, 0.0, 0));
		System.out.println(computeExposedWallArea(4.0, 0, 3.0, 2.0, 0));
		System.out.println(computeExposedWallArea(4.0, 0, 3.0, 2.0, 1));

		System.out.println(computeExposedWallArea(1.0, 2, 4.0, 4.0, 2));
		//test computeExposedWallArea with the example numbers 
		//should be equal to 25.72
		
		System.out.println(computeExposedWallArea(2.0, 4, 3.0, 3.0, 4));
		//test again! never too much testing ig :))
		//should be equal to -14.24 (approximately) 
		//too many clocks haha oops :))
	}
	
	public static double computeClockArea(double radius){
		//area of a circle is (pi)*(r^2)
		double clockArea = (radius*radius)*3.14;
		//return the area of the clock
		return clockArea;
	}
	
	
	public static double computeWallArea(double width, double height){
		//area of a rectangle is a width X height
		double rectArea = width*height;
		//return the area of the wall
		return rectArea;
		
	}
	public static double computeExposedWallArea(double radius, int numClocks, double width, double height, int numWalls){
		 //calculate the area that the clocks take up!
		double clockArea = computeClockArea(radius)*numClocks;
		//calculate how much wall space there actually is!
		double wallArea = computeWallArea(width, height)*numWalls;
		//subtract the area that the clocks take up from the wall area to get the exposed area	
		double exposedArea=wallArea-clockArea;
		//return the exposed area
		return exposedArea;
	}

}
