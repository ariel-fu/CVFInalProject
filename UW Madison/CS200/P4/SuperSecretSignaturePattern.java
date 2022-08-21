package P4;
/**
 * @author: Ariel Fu
 * @studentID:  908 168 5910
 * @netID: afu5
 */
public class SuperSecretSignaturePattern {

	public static void main(String[] args) {
		//run 340 times since starting at 1
		for(int i =1; i<=369; i++){
			System.out.println(i+") "+colorChange(i));
		}
	}
	
	//x is the number of loops in the for loop
	public static String colorChange(int x){

		//check special types first i.e. gold, silver, ruby, ends in 1, etc.
		if(x%10==1){
			return color10(x);
		}
		//check for 3, 5, or 15, copied from FancySubstitutionPattern.java
		else if (x%5==0 || x%3==0) {
			return color3or5(x);
		}
		
		//now check for the base pattern, which repeats every 7
		else {
			return colorBase(x);
		}
	}
	
	public static String color10(int x) {
		if(x%15==0){
			return "pink";
		}

		//if it is dividable by three, print out mauve
		else if(x%3 ==0){
			return "mauve";
		}
		//if it is dividable by 5, print out purple, we don't 
		//even have to test for this case
		//there are no whole numbers that end in 1 and is dividable by 5
		else if(x%5==0){
			return "purple";
		}
		//if it isn't dividable by any other number, print out violet
		else {
			return "violet";
		}		
	}
	
	public static String color3or5(int x){
		//the following copied over from FancySubstitutionPattern.java
		if(x%15==0){
			return "ruby";
		}
		//if it is dividable b three, print out silver
		else if(x%3 ==0){
			return "silver";
		}
		//if it is dividable by 5, print out gold
		else if(x%5==0){
			return "gold";
		}

		//this should never happen
		return "";
	}
	
	//check for the base pattern, which repeats every 7
	public static String colorBase(int x) {
		int modBy7=x%7;
		
		// therefore, mod by 7
		if(modBy7==1){
			//if x mod 7 ==1, print out red
			return "red";
		}
		//if remainder = 2, print out yellow
		else if(modBy7==2){
			return "yellow";
		}
		//if remainder is 3, print out green
		else if(modBy7 ==3){
			return "green";
		}
		//if remainder is 4, print out brown
		else if(modBy7==4){
			return "brown";
		}
		//if remainder is 5, print out olive
		else if(modBy7 ==5){
			return "olive";
		}
		//if remainder is 6, print out chocolate
		else if(modBy7 == 6){
			return "chocolate";
		}
		//if remainder is 0, print out peach
		else if (modBy7 == 0){
			return "peach";
		}
		
		//this should never happen
		return "";
	}

}
