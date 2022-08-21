/**
 * 
 */
package P3_Marian_The_Librarian;

/**
 * @author Ariel
 * @studentID: 908 168 5910
 * @netID: afu5
 */
public class Combinations {

	public static void main(String[] args) {
		System.out.println("Return N --> " + findCombination(12, 3, 8));
		System.out.println("Return: * --> " + findCombination(0,0 , 0));
		System.out.println("Return + --> " + findCombination(-5, 0, -5));
		System.out.println("Return + --> " + findCombination(1, 2, 3));
		System.out.println("Return + --> " + findCombination(1, 2, -1));
		System.out.println("Return: * --> " + findCombination(1,2 ,2 ));
		System.out.println("Return: * --> " + findCombination(3,24,8));
		System.out.println("Return: * --> " + findCombination(54,6, 9));
		
		System.out.println("");
		
		System.out.println("Return N--> "+ findOppositeCombination(3, 5, 1));
		System.out.println("Return - --> " + findOppositeCombination(3, 1, 2));
		System.out.println("Return - --> " + findOppositeCombination(5,7 ,2 ));
		
		
	}
	
	public static char findCombination(int x, int y, int z){
		//set the default to no combinations
		char combo = 'N';
		if((x*y ==z) || (x*z ==y) || (z*y ==x)){
			//if any of these three combos check out to true, then the two inputs were multiplied 
			combo = '*';
		}
		else if((x+y==z) || (x+z ==y) || (z+y == x)){
			//if any of these three combos check out to true, then the two inputs were added
			combo= '+';
		}
		//return the character
		return combo;
	}
	
	public static char findOppositeCombination(int x, int y, int z){
		//find out if it is using *, +, or N
		char combo = findCombination(x, y, z);
		if(combo == '*'){
			//if the combo char is multiply, change it to divide
			combo ='/';
		}
		else if(combo=='+'){
			//if the combo used addition, change combo to subtract
			combo = '-';
		}
		else{
			//the last case would be changing it to N
			combo ='N';
		}
		//return the char
		return combo;
	}
	
	public static char findColorCombination(int x, int y, int z, boolean isRed, boolean isBlue) {
		//see if it needs to change the value of x and z (first and third)
		if (isRed) {
			x=3*x;
			z=3*z;
		}
		//see if it needs to change the value of y (second)
		if (isBlue) {
			y=2*y;
		}
		//return using findCombination
		return findCombination(x, y, z);
	}

}
