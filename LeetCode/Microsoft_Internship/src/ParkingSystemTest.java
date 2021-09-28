import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParkingSystemTest {
	private ParkingSystem parkTest;
	@BeforeEach
	void setUp() throws Exception {
		parkTest = new ParkingSystem();
	}

	@Test
	void testAddCar() {
		parkTest = new ParkingSystem(1, 1, 0);
		assertTrue(parkTest.addCar(1));
		assertTrue(parkTest.addCar(2));
		assertFalse(parkTest.addCar(3));
		assertFalse(parkTest.addCar(1));
		
	}

}
