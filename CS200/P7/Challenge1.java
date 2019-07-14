/**
 * @author: Ariel Fu
 * @studentID: 908 168 5910
 * @netID: afu5
 */
		
public class Challenge1 {

	public static void main(String[] args) {
		for(int i=1; i<=100000;i++) {
			System.out.println("The sum of the digits of " + i+" is: " + computeDigitSum(i));
		}
		
		
	}
	
	public static int computeDigitSum(int n) {
		int sum=n%10;
		if(n<10) {
			return n;
		}
		else {
			return sum + computeDigitSum(n/10);
		}
	}

}
