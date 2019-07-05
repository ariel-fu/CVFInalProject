

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
				//if it is a winning pair, add one to numWins
				numWins+=winOrNot(i, j);
				//add one to number of rolls, regardless of win or not
				numRolls++;
			}
		}
		//cast numWins to a double to avoid int division ;))
		double percent =((double)numWins/numRolls)*100;
		//print out his percentage of winning
		System.out.println("Sky has a " + percent + "% chance of winning.");

	}
	
	public static int winOrNot (int firstDice, int secondDice){
		//a variable to return if the two dices are three or less apart
		int numWins =0;
		//if the absolute value between the two dice is three or less
		//add one to the number of possible wins
		if(absValue(firstDice, secondDice)<=3){
			numWins++;
		}
		//adds one to the number of possible wins
		return numWins;
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
