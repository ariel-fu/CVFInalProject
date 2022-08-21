package P4;

import static org.junit.Assert.*;

import org.junit.Test;

public class SuperSecretSignaturePatternTest {

	@Test
	public void testColorChange() {
		assertTrue(SuperSecretSignaturePattern.colorChange(1)=="violet");
		assertTrue(SuperSecretSignaturePattern.colorChange(21)=="mauve");
		
		assertTrue(SuperSecretSignaturePattern.colorChange(3)=="silver");
		assertTrue(SuperSecretSignaturePattern.colorChange(5)=="gold");
		assertTrue(SuperSecretSignaturePattern.colorChange(15)=="ruby");

		assertTrue(SuperSecretSignaturePattern.colorChange(8)=="red");
		assertTrue(SuperSecretSignaturePattern.colorChange(2)=="yellow");
		assertTrue(SuperSecretSignaturePattern.colorChange(17)=="green");
		assertTrue(SuperSecretSignaturePattern.colorChange(4)=="brown");
		assertTrue(SuperSecretSignaturePattern.colorChange(19)=="olive");
		assertTrue(SuperSecretSignaturePattern.colorChange(13)=="chocolate");
		assertTrue(SuperSecretSignaturePattern.colorChange(7)=="peach");
		
		//impossible
		for (int i=1; i<370; i++) {
			assertTrue(SuperSecretSignaturePattern.colorChange(i)!="purple");
			assertTrue(SuperSecretSignaturePattern.colorChange(i)!="pink");
		}
	}

}
