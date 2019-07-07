import static org.junit.Assert.*;

import org.junit.Test;


public class BoatLevelsTest {

	@Test
	public void testChangeValueOfANumber() {
		assertTrue(BoatLevels.changeValueOfANumber(2)==1);
		assertTrue(BoatLevels.changeValueOfANumber(3)==10);
	
	}

}
