/**
 * @author: Ariel Fu
 * @studentID: 908 168 5910
 * @netID: afu5
 */
public class Power {

	public static void main(String[] args) {
		System.out.println("4-->" + power(2) );
		System.out.println("8-->" + power(3) );
		System.out.println("16-->" + power(4));
		System.out.println("32-->" + power(5) );
		System.out.println("256-->" + power(8) );
		System.out.println("4-->" + power(2) );
		

	}
	
	public static int power(int day) {
		//if day is one, return 2 since her strength started at 2
		if(day==1) {
			return 2;
		}
		else
		{
			//take the previous number and multiply by 2, doubleling it
			return 2*power(day-1);
		}
	}

}
