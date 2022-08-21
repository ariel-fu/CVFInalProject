/**
 * 
 */
package P3_Marian_The_Librarian;

/**
 * @author: Ariel Fu
 * @studentID: 908 168 5910
 * @netID: afu5
 */
public class LateFees {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Return: false --> "+ isAccountBad(true, true));
		System.out.println("Return: true --> "+isAccountBad(false, false));
		System.out.println("Return: true --> "+isAccountBad(true,false));
		System.out.println("Return: true --> "+isAccountBad(false, true));
		
		System.out.println("");
		System.out.println("Return: false --> "+isAccountGood(false, true));
		System.out.println("Return: false --> "+isAccountGood(false, false));
		System.out.println("Return: false --> "+ isAccountGood(true, false));
		System.out.println("Return: true --> "+ isAccountGood(true, true));
		
		
	}
	
	public static boolean isAccountBad(boolean paidLateFee, boolean hasOverDuebook){
		//if the paidLateFee is equal to hasOverDuebook, it should return false
		return (paidLateFee!=hasOverDuebook);
	}
	
	public static boolean isAccountGood(boolean paidLateFee, boolean hasOverdueBook){
		//if they didn't pay the late fee and have overdue books, they are a bad account
		if (!paidLateFee && hasOverdueBook) {
		//bad accounts return false
			return false; 
		}
		//if not, return true (good account)
		return true;
	}
	
	

}
