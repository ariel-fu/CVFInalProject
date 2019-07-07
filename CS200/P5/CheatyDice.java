/**
 * @author: Ariel Fu
 * @studentID:  908 168 5910
 * @netID: afu5
 */
public class CheatyDice {
	public static void main(String[] args) {
		//keep track of the number of wins
		int numWins=0;
		//keep track of the number of rolls
		int numRollsTotal=0;
		//run the for loops 20 times for each of the sides
		//of the three dices
		for(int i =1; i<=20; i++){
			for(int j=1; j<=20; j++){
				for(int k=1; k<=20; k++){
					//add one to total number of rolls
					numRollsTotal++;
					//check if it is a winning combo
					if(winOrNot(i, j, k)==true){
						numWins++;
					}
				}
			}
		}
		System.out.println("Sky has a " + percentageOf(numWins, numRollsTotal) + "% chance of winning.");

	}
	
	//use winOrNot from fairDice?
	public static boolean winOrNot (int firstDice, int secondDice, int thirdDice){
		//a variable to return if the two dices are three or less apart
		
		//if the absolute value between the two dice is three or less
		//aka a win, return true
		if(winOrNot2(firstDice, secondDice)){
			return true;
		}
		//if the first and second dices are a winning pair,
		//check if the first and third dices are three or apart
		//if yes, return true
		else if(winOrNot2(firstDice, thirdDice)){
			return true;
		}
		//if the first and second or the first and third dices 
		//aren't a winning pair, check if the second and the third dices
		//are a winning pair
		//if yes, return true
		else if(winOrNot2(secondDice, thirdDice)){
			return true;
		} else{
			//if none of the cases work out, return false
			return false;
		}
	}
	
	//under is copied from FairDice.java
	public static boolean winOrNot2 (int firstDice, int secondDice){
		//a variable to return if the two dices are three or less apart
		
		//if the absolute value between the two dice is three or less
		//aka a win, return true
		if(absValue(firstDice, secondDice)<=3){
			return true;
		} else{
		//if there is no win, return false
		return false;
		}
	}
	public static double percentageOf(int numberOfWins, int numberOfRollsTotal){
		//cast the ints to doubles and multiply by 100
		double percent = (double)numberOfWins/numberOfRollsTotal*100;
		return percent;
	}
	
	//again, make the difference between the two dices positive
	public static int absValue(int one,int two){
		int absoluteValue=one-two;
		if(absoluteValue<0){
			absoluteValue*=-1;
		}
		return absoluteValue;
	}
	
	
	

}
