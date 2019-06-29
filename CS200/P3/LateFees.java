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
		//if one of the account's variable of paid or overdue is true
		boolean accountStatus = true;
		if(paidLateFee==true && hasOverDuebook==true){
			//test if both are true, if so change account status to false
			accountStatus= false;
		}
		//return the account status
		return accountStatus;
	}
	
	public static boolean isAccountGood(boolean paidLateFee, boolean hasOverdueBook){
		//call on previous function (isAccountBad) 
		boolean accountStatus = isAccountBad(paidLateFee, hasOverdueBook);
		if(accountStatus == true){
			//if the account status is true, meaning that they are a bad account, return the opposite
			accountStatus = !accountStatus;
		}
		else {
			//if the account status is false, meaning they had a good account, return the opposite
			accountStatus = !accountStatus;
		}
		//return the account status		
		return accountStatus;
	}
	
	

}
