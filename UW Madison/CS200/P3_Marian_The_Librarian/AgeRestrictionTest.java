package P3_Marian_The_Librarian;

import static org.junit.Assert.*;

import org.junit.Test;

public class AgeRestrictionTest {

	@Test
	public void testIsOver13() {
		assertTrue("> 13", AgeRestriction.isOver13(100));
		assertTrue("= 13", AgeRestriction.isOver13(13));
		assertTrue("< 13", !AgeRestriction.isOver13(6));
		assertTrue("< 13", !AgeRestriction.isOver13(0));
		assertTrue("< 13", !AgeRestriction.isOver13(-6));
	}

	@Test
	public void testIsInvalid() {
		assertTrue("-2",!AgeRestriction.isValid(-2));
	}

	@Test
	public void testWhatCanIBorrow() {
		assertTrue("-2, "+AgeRestriction.whatCanIBorrowString(-2), AgeRestriction.whatCanIBorrowString(-2)==AgeRestriction.INVALID);
		
		assertTrue("0", AgeRestriction.whatCanIBorrowString(0)==AgeRestriction.NOBOOK);
		assertTrue("12", AgeRestriction.whatCanIBorrowString(12)==AgeRestriction.NOBOOK);
		
		assertTrue("=13", AgeRestriction.whatCanIBorrowString(13)==AgeRestriction.SOMEBOOK);
		assertTrue("15", AgeRestriction.whatCanIBorrowString(15)==AgeRestriction.SOMEBOOK);

		assertTrue("18", AgeRestriction.whatCanIBorrowString(18)==AgeRestriction.ANYBOOK);
		assertTrue("50", AgeRestriction.whatCanIBorrowString(50)==AgeRestriction.ANYBOOK);
		assertTrue("100", AgeRestriction.whatCanIBorrowString(100)==AgeRestriction.ANYBOOK);

		assertTrue("101", AgeRestriction.whatCanIBorrowString(101)==AgeRestriction.INVALID);
	}

}
