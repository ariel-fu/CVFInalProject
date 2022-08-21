import static org.junit.Assert.*;

import org.junit.Test;


public class FindCasinoTest {

	@Test
	public void testFindXLocation() {
		assertTrue(FindCasino.findXLocation(15, 3)==5);

		assertTrue(FindCasino.findXLocation(9,4 )==36);
	}

	@Test
	public void testFindYLocation() {

		assertTrue(FindCasino.findYLocation(9)==10);
		assertTrue(FindCasino.findYLocation(13)==4);
		assertTrue(FindCasino.findYLocation(10)==1);
		
	}

}
