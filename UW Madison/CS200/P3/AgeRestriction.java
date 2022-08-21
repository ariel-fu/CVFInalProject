//author: Ariel Fu
//Net ID: afu5
//Student ID: 908 168 5910
public class AgeRestriction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//System.out.println("Expected: true --> " + isOver13(14));
	//	System.out.println("Expected: false --> " + isOver13(12));
    	//System.out.println("Expected: true --> " + isOver13(13));
	//	System.out.println("Expected: false --> " + isOver13(0));
		
		System.out.println("Expected: INVALID AGE -->" + testCase(103));
		System.out.println("You can borrow any books! --> "+ testCase(18));
		System.out.println("You cannot borrow any books. --> " + testCase(12));
		System.out.println("You cannot borrow sensitive material ..--> "+ testCase(13));
		

		
	}
	
	public static boolean isOver13(int age) {
		//make a variable to change due to a condition and return this var
		boolean oldEnough = false;
		if(age>=13) {
			//if their age is 13 or older, they are old enough to borrow books
			oldEnough = true;
		}
		//return if their are old enough or not
		return oldEnough;
	}
	
	public static void whatCanIBorrow(int age) {
		if(age<13) {
			//if age is under 13, they cannot borrow any books
			System.out.println("You cannot borrow any books.");
		
		}
		
		else if (age>=13&&age<18) {
			//if they are 13 or older but under 18, they can borrow specific books
			System.out.println("You cannot borrow sensitive material.");
		
		}
		
		else if(age>=18&&age<=100) {
			//if they are older than 18, they can borrow all books
			System.out.println("You can borrow any books!");
			
		}
		
		else if(age>100 || age<0) {
			//if they have an age that is not possible print out INVALID AGE!
			System.out.println("INVALID AGE");
			}
		
	}
    //test case method for void method
	public static int testCase(int age) {
		whatCanIBorrow(age);
		return 0;
	}
}
