package P3_Marian_The_Librarian;

import static org.junit.Assert.*;

import org.junit.Test;

public class LateFeesTest {

	@Test
	public void testIsAccountBad() {
	
		assertTrue("no paidLateFee, no OverDuebook",LateFees.isAccountBad(false, false)==false);
		assertTrue("no paidLateFee, OverDuebook",LateFees.isAccountBad(false, true)==true);
		assertTrue("paidLateFee, no OverDuebook",LateFees.isAccountBad(true, false)==true);
		assertTrue("paidLateFee, OverDuebook",LateFees.isAccountBad(true, true)==false);
	}

	@Test
	public void testIsAccountGood() {
		assertTrue("no paidLateFee, no OverDuebook",LateFees.isAccountGood(false, false)==true);
		assertTrue("no paidLateFee, OverDuebook",LateFees.isAccountGood(false, true)==false);
		assertTrue("paidLateFee, no OverDuebook",LateFees.isAccountGood(true, false)==true);
		assertTrue("paidLateFee, OverDuebook",LateFees.isAccountGood(true, true)==true);
	}

}
