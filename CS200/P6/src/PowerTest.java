import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PowerTest {

	@Test
	void testPower() {

		assertTrue(Power.power(1)==2);
		assertTrue(Power.power(2)==4);
		assertTrue(Power.power(3)==8);
		assertTrue(Power.power(4)==16);
		assertTrue(Power.power(5)==32);
		assertTrue(Power.power(8)==256);
	}

}
