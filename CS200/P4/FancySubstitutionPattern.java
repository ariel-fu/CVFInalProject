package P4;

import com.sun.org.apache.xerces.internal.impl.dv.dtd.NMTOKENDatatypeValidator;

/**
 * @author: Ariel Fu
 * @studentID: 908 168 5910
 * @netID: afu5
 */
public class FancySubstitutionPattern {

	public static void main(String[] args) {
		for (int i = 1; i <= 251; i++) {
			switchColors(i);
		}

	}

	public static void switchColors(int numLoops) {
		// if it dividable by both 3 and 5
		if (numLoops % 15 == 0) {
			System.out.println(numLoops + ") ruby");
		}
		// if it is dividable b three, print out silver
		else if (numLoops % 3 == 0) {
			System.out.println(numLoops + ") silver");
		}
		// if it is dividable by 5, print out gold
		else if (numLoops % 5 == 0) {
			System.out.println(numLoops + ") gold");
		}
		else {
			lessSimplePattern(numLoops);
		}
	}
	
	//copied from lessSimplePattern
	public static void lessSimplePattern(int numLoops) {
		int modFour = numLoops%4;
		// since it starts at 1, every "red" pattern moded by 4 is equal to one!
		if (modFour == 1) {
			// print loop number and red
			System.out.println(numLoops + ") red");

		}

		// every yellow pattern moded by 4 is equal to two
		if (modFour == 2) {
			// print out loop number and yellow
			System.out.println(numLoops + ") yellow");
		}

		// every green pattern moded by 4 is equal to 3!
		if (modFour == 3) {
			System.out.println(numLoops + ") green");
		}

		// every brown pattern is a multiple of 4 so %4 = 0
		if (modFour == 0) {
			System.out.println(numLoops + ") brown");
		}
	}
}
