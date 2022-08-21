import static org.junit.Assert.*;

import org.junit.Test;


public class BoatBestBetTest {

	@Test
	public void testComputeNumberOfChanges() {
		assertTrue(BoatBestBet.computeNumberOfChanges(8)==3);
		assertTrue(BoatBestBet.computeNumberOfChanges(3)==7);
		
		
	}

}
