/**
 * 
 */
package P2;

/**
 * @author Ariel Fu
 * netID: afu5
 * studentID: 9081685910
 */
public class Money {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Should result in 96.0, results:"  + computeEmployeePaycheck(10, 10.0, 4));
		//test again!
		System.out.println("should result in 108.3, results: " + computeEmployeePaycheck(12, 9.5, 5));
		
		
		//test coins (part b)
		System.out.println("The number of coins given is 20, results: " + computeChangeWithFewestCoins(408));
		//test one more time 
		System.out.println("The number of coins given is 1, results: " + computeChangeWithFewestCoins(25));
		System.out.println("The number of coins given is 1, results: " + computeChangeWithFewestCoins(10));
		System.out.println("The number of coins given is 1, results: " + computeChangeWithFewestCoins(5));
		System.out.println("The number of coins given is 1, results: " + computeChangeWithFewestCoins(1));
		
		System.out.println("The number of coins given is 0, results: " + computeChangeWithFewestCoins(0));
		System.out.println("The number of coins given is 4, results: " + computeChangeWithFewestCoins(41));
	
		
		//test sales (part c)
		System.out.println(("Should result in 16, results: " + computeChangeFromSale(3, 210, 1000)));
		//test another one!
		System.out.println("Should result in 10, results: " + computeChangeFromSale(5, 250, 1500));
		
	}
	
	public static double computeEmployeePaycheck(int numHours, double salary, int tax){
		// pay check should be number of hours multiply by salary and divided by tax converted to a
		//percentage
		//convert tax to a double as a percent
		double taxPercent = tax/100.0;
		// pay check should be number of hours multiply by salary and divided by tax converted to a
		//percentage
		double paycheck= (numHours*salary)*(1-taxPercent);
		//return paycheck
		return paycheck;
	}
	
	
	public static int computeChangeWithFewestCoins(int amount){
		//to keep track of how many coins have been given away :((
		int numCoins =0;
		
		//find the remainder after the quarters
		int amountLeftToChange = amount%25;
		
		//find the number of quarters
		int numQuarters = (amount-amountLeftToChange)/25;
		
		//add on the quarters to number of coins		
		numCoins += numQuarters;
		
		//set amount to the amount after the quarters		
		amount = amountLeftToChange;
		
		//find the new amount left after dimes
		amountLeftToChange = amountLeftToChange%10;
		
		//find the number of dimes used
		int numDimes = (amount-amountLeftToChange)/10;
		
		//add on the dimes to the number of coins
		numCoins+= numDimes;
		
		//set amount to the amount after the dimes
		amount = amountLeftToChange;
		
		//find the amount left after nickels
		amountLeftToChange = amountLeftToChange%5;
		
		//find the number of nickels used
		int numNickels = (amount-amountLeftToChange)/5;
		
		//add the number of nickels to the number of coins
		numCoins += numNickels;
		
		//add on the remaining amount to the number of coins (since it is in ones from pennies)
		numCoins += amountLeftToChange;
	
		
		return numCoins; 
	}
	
	public static int computeChangeFromSale(int numClocks, int clockPrice, int amountPaid){
		//calculate how much he/she needed to pay
		int totalClockPrice = numClocks * clockPrice;
		
		//how much Doc Tok needs to give back
		int amountDue = amountPaid- totalClockPrice;
		
		//call on computeChangeWithFewestCoins to find the fewest amount of coins Doc Tok could give back
		int numCoinsDue = computeChangeWithFewestCoins(amountDue);
		
		//return the fewest number of coins Doc Tok could give back
		return numCoinsDue;
	}
	
	

}
