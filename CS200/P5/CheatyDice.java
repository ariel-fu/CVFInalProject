
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
		int numRolls=0;
		//run the for loops 20 times for each of the sides
		//of the three dices
		for(int i =1; i<=20; i++){
			for(int j=1; j<=20; j++){
				for(int k=1; k<=20; k++){
					//check if there is a winning pair for all three dices
					//if there is, add one to numWins
					numWins+= winOrNot(i, j, k);
					//add one to numRolls regardless of a winning pair or not 
					numRolls++;
					
				}
			}
		}
		double percent =(numWins*1.0/numRolls)*100.0;
		System.out.println("Sky has a " + percent + "% chance of winning.");

	}
	
	public static int winOrNot (int firstDice, int secondDice, int thirdDice){
		//keep track if we won or not
		int numWins =0;
		//check if the first and second dices are three or apart
		if(absValue(firstDice, secondDice)<=3){
			numWins++;
		}
		//if the first and second dices are a winning pair,
		//check if the first and third dices are three or apart
		else if(absValue(firstDice, thirdDice)<=3){
			numWins++;
		}
		//if the first and second or the first and third dices 
		//aren't a winning pair, check if the second and the third
		//are a winning pair
		else if(absValue(secondDice, thirdDice)<=3){
			numWins++;
		}
		//if there is a winning pair, return one
		return numWins;
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
