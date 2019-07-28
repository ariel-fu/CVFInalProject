import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DefinitelyNotACalculatorTest {

	@Test
	void testIsNumeric() {
		double[] result= new double [1];
		assertTrue(DefinitelyNotACalculator.isNumeric("1", result));
		assertTrue(result[0]==1);
		assertTrue(DefinitelyNotACalculator.isNumeric("1.5", result));
		assertTrue(result[0]==1.5);
		assertTrue(!DefinitelyNotACalculator.isNumeric("F", result));
		assertTrue(!DefinitelyNotACalculator.isNumeric("", result));
	}

	@Test
	void testAddNumberInt() {
		String[] inputs = {"","1","2"};
		assertTrue(DefinitelyNotACalculator.addNumber(inputs)==3);
	}

	@Test
	void testAddNumberDbl() {
		String[] inputs = {"","1.5","2.3"};
		assertTrue(DefinitelyNotACalculator.addNumber(inputs)==3.8);
	}

	@Test
	void testAddNumberFail() {
		String[] inputs = {"","F","2.3"};
		assertTrue(DefinitelyNotACalculator.addNumber(inputs)==2.3);
		inputs = new String[] {"","2.3","F"};
		assertTrue(DefinitelyNotACalculator.addNumber(inputs)==2.3);
		inputs = new String[] {"","F","F"};
		assertTrue(DefinitelyNotACalculator.addNumber(inputs)==0);
	}

	@Test
	void testIsTwoNumbers() {
		String[] inputs = {"","1","2"};
		assertTrue(DefinitelyNotACalculator.isTwoNumbers(inputs));
	}

	@Test
	void testIsValidArguments() {
		String[] inputs = {DefinitelyNotACalculator.PLUS,"1","2"};
		assertTrue(DefinitelyNotACalculator.isValidArgument(inputs));
		inputs = new String[]{DefinitelyNotACalculator.SUBSTRACT,"1","2"};
		assertTrue(DefinitelyNotACalculator.isValidArgument(inputs));
		inputs = new String[]{DefinitelyNotACalculator.MULTIPLY,"1","2"};
		assertTrue(DefinitelyNotACalculator.isValidArgument(inputs));
		inputs = new String[]{DefinitelyNotACalculator.DIVIDE,"1","2"};
		assertTrue(DefinitelyNotACalculator.isValidArgument(inputs));
		inputs = new String[]{DefinitelyNotACalculator.MAX,"1","2"};
		assertTrue(DefinitelyNotACalculator.isValidArgument(inputs));
		inputs = new String[]{DefinitelyNotACalculator.POW,"1","2"};
		assertTrue(DefinitelyNotACalculator.isValidArgument(inputs));
		
		inputs = new String[] {DefinitelyNotACalculator.ABS,"1"};
		assertTrue(DefinitelyNotACalculator.isValidArgument(inputs));

		inputs = new String[] {DefinitelyNotACalculator.LOG,"1"};
		assertTrue(DefinitelyNotACalculator.isValidArgument(inputs));

		inputs = new String[] {DefinitelyNotACalculator.SQRT,"1"};
		assertTrue(DefinitelyNotACalculator.isValidArgument(inputs));
	}

	@Test
	void testIsValidArgumentsFail() {
		String[] inputs = {DefinitelyNotACalculator.PLUS,"2"};
		assertTrue(!DefinitelyNotACalculator.isValidArgument(inputs));
		inputs = new String[]{DefinitelyNotACalculator.SUBSTRACT,"1"};
		assertTrue(!DefinitelyNotACalculator.isValidArgument(inputs));
		inputs = new String[]{DefinitelyNotACalculator.MULTIPLY,"1"};
		assertTrue(!DefinitelyNotACalculator.isValidArgument(inputs));
		inputs = new String[]{DefinitelyNotACalculator.DIVIDE,"1"};
		assertTrue(!DefinitelyNotACalculator.isValidArgument(inputs));
		inputs = new String[]{DefinitelyNotACalculator.MAX,"1"};
		assertTrue(!DefinitelyNotACalculator.isValidArgument(inputs));
		inputs = new String[]{DefinitelyNotACalculator.POW,"1"};
		assertTrue(!DefinitelyNotACalculator.isValidArgument(inputs));
		
		inputs = new String[] {DefinitelyNotACalculator.ABS,"1","2"};
		assertTrue(!DefinitelyNotACalculator.isValidArgument(inputs));
		inputs = new String[] {DefinitelyNotACalculator.LOG,"1","2"};
		assertTrue(!DefinitelyNotACalculator.isValidArgument(inputs));
		inputs = new String[] {DefinitelyNotACalculator.SQRT,"1","2"};
		assertTrue(!DefinitelyNotACalculator.isValidArgument(inputs));
	}

	@Test
	void testIsTwoNumbersFail() {
		String[] inputs = {"","F","2.3"};
		assertTrue(!DefinitelyNotACalculator.isTwoNumbers(inputs));
		inputs = new String[] {"","2.3","F"};
		assertTrue(!DefinitelyNotACalculator.isTwoNumbers(inputs));
		inputs = new String[] {"","F","F"};
		assertTrue(!DefinitelyNotACalculator.isTwoNumbers(inputs));
		inputs = new String[] {"","1","2","3"};
		assertTrue(!DefinitelyNotACalculator.isTwoNumbers(inputs));
		inputs = new String[] {"","1"};
		assertTrue(!DefinitelyNotACalculator.isTwoNumbers(inputs));

		inputs = new String[]{"Quit"};
		assertTrue(!DefinitelyNotACalculator.isTwoNumbers(inputs));
		inputs = new String[]{"exit"};
		assertTrue(!DefinitelyNotACalculator.isTwoNumbers(inputs));

		inputs = new String[]{"quit"};
		assertTrue(!DefinitelyNotACalculator.isTwoNumbers(inputs));

		//inputs = new String[]{"quit","2","3"};
		//assertTrue(!DefinitelyNotACalculator.isValidArguments(inputs));
	
	}

	@Test
	void testIsValidOpt() {
		String input="+";
		assertTrue(DefinitelyNotACalculator.isValidOperator(input));
		assertTrue(DefinitelyNotACalculator.isValidOperator("-"));
		assertTrue(DefinitelyNotACalculator.isValidOperator("*"));
		assertTrue(DefinitelyNotACalculator.isValidOperator("/"));

		
		assertTrue(!DefinitelyNotACalculator.isValidOperator("quit"));
		assertTrue(!DefinitelyNotACalculator.isValidOperator("Quit"));
		assertTrue(!DefinitelyNotACalculator.isValidOperator("QUIT"));
		assertTrue(!DefinitelyNotACalculator.isValidOperator(""));
	}

	@Test
	void testRandArguments() {
		String[] inputs = {"","F","2.3"};
		assertTrue(!DefinitelyNotACalculator.isValidRandArgument(inputs));
		inputs = new String[] {"","2.3"};
		assertTrue(DefinitelyNotACalculator.isValidRandArgument(inputs));
		inputs = new String[] {"","2.3","5"};
		assertTrue(!DefinitelyNotACalculator.isValidRandArgument(inputs));
	}

	@Test
	void testIsInt() {
		assertTrue(DefinitelyNotACalculator.isInt(1));
		assertTrue(DefinitelyNotACalculator.isInt(-1));
		assertTrue(DefinitelyNotACalculator.isInt(0));
		assertTrue(!DefinitelyNotACalculator.isInt(1.5));
		assertTrue(!DefinitelyNotACalculator.isInt(-1.5));
	}
	
	@Test 
	void testIsEndlessArg() {
		String[] splitString = {"", "2","3","5","23","5"};
		assertTrue(DefinitelyNotACalculator.isValidEndlessArgument(splitString));
	}
}
