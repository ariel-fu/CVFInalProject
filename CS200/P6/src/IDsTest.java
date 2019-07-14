import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IDsTest {

	@Test
	void testCountIDs() {
		assertTrue(IDs.countIDs(2)==66);
		assertTrue(IDs.countIDs(3)==542);
		assertTrue(IDs.countIDs(4)==4454);
		assertTrue(IDs.countIDs(5)==36598);
	}

}
