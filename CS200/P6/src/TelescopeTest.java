import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TelescopeTest {

	@Test
	void testDegrees() {
		assertTrue(Telescope.degrees(4)==5);
		assertTrue(Telescope.degrees(5)==7);
		assertTrue(Telescope.degrees(6)==11);
		assertTrue(Telescope.degrees(7)==17);
		assertTrue(Telescope.degrees(8)==25);
		assertTrue(Telescope.degrees(9)==37);
		assertTrue(Telescope.degrees(10)==55);
	}

}
