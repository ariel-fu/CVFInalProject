

/**
 * @author: Ariel Fu
 * @studentID:  908 168 5910
 * @netID: afu5
 */
public class FairDice {

	public static void main(String[] args) {
		//keep track of the number of wins
		int numWins=0;
		//keep track of the number of rolls
		int numRolls=0;
		//run 20 times for both for loops bc they are 20 sided dices.
		for(int i =1; i<=20; i++){
			for(int j=1; j<=20; j++){
				//add one to number of rolls, regardless of win or not
				numRolls++;
				//test if it is a winning roll
				if(winOrNot(i,j) == true){
					//if it is a winning roll, add one to number of wins
					numWins++;
				}
				//otherwise, don't do anything
			}
		}
		//print out his percentage of winning
		System.out.println("Sky has a " + percentageOf(numWins, numRolls) + "% chance of winning.");

	}
	
	public static double percentageOf(int numberOfWins, int numberOfRollsTotal){
		double percent = (double)numberOfWins/numberOfRollsTotal*100;
		//cast the ints to doubles and multiply by 100
		return percent;
	}
	
	public static boolean winOrNot (int firstDice, int secondDice){
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
	
	//make the difference between the two dices positive
	public static int absValue(int one,int two){
		int absoluteValue=one-two;
		if(absoluteValue<0){
			absoluteValue*=-1;
		}
		return absoluteValue;
	}
	
	
	
	

}
