/**
 * @author: Ariel Fu
 * @studentID: 908 168 5910
 * @netID: afu5
 */
public class OrganizeBookshelf {


	public static void main(String[] args) {
		System.out.println("Return 10--> " + getRightmostBookID(5, 10));
		System.out.println("Return -2--> " + getRightmostBookID(-2, -3));
		System.out.println("Return 0--> " + getRightmostBookID(0, 0));
		
		
		System.out.println("Return 9--> " + spaceToLeave(5, 15));
		System.out.println("Return 1--> " + spaceToLeave(1, 3));
		System.out.println("Return 0--> " + spaceToLeave(0, 1));
		System.out.println("Return 12--> " + spaceToLeave(-4, 9));

		
		
	}
	
	public static int getRightmostBookID(int id_1, int id_2){
		//assume the first book has a greater id than the second book
		int rightMostBookID = id_1;
		//test if the first book is indeed greater
		if(id_2>id_1){
			//if not, then return the second book's id
			rightMostBookID = id_2;
		}
		//return the greater id!
		return rightMostBookID;
	}
	
	public static int spaceToLeave(int id_1, int id_2){
		//set a variable to be the distance between the two books
		int space = 0;
		//create a condition where id_1 is greater than id_2
		if(id_1>id_2){
			//the space would be the first book minus the second book then minus one!
			space = (id_1 - id_2)-1;
		}
		//create a condition where id_1 is less than id_2
		if(id_1<id_2){
			//the space would be the second book minus the first book then minus one!
			space = (id_2-id_1)-1;
		}
		//return the space between the two books!
		return space;
	}
	
	

}
