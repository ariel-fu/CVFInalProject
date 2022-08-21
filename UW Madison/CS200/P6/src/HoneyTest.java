import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HoneyTest {

	@Test
	void testNumMoves() {
		assertTrue(Honey.numMoves(2)==6);
		assertTrue(Honey.numMoves(3)==12);
		assertTrue(Honey.numMoves(4)==20);
		assertTrue(Honey.numMoves(5)==30);
		assertTrue(Honey.numMoves(6)==42);
		assertTrue(Honey.numMoves(7)==56);
	}

}
