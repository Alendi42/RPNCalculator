package rpn;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rpn.RPNCommandLine;

class CalculatorTest {
	
	private RPNCommandLine cmd = new RPNCommandLine();

	@BeforeEach
	void setUp() throws Exception {
		cmd.process("clear");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAdd() {
		String result = cmd.process("2 3 +");
		String expected = "stack: 5";
		assertEquals(expected, result);
	}
	
	@Test
	void testSub() {
		String result = cmd.process("2 3 -");
		String expected = "stack: -1";
		assertEquals(expected, result);
	}
	
	@Test
	void testMul() {
		String result = cmd.process("2 3 *");
		String expected = "stack: 6";
		assertEquals(expected, result);
	}
	
	@Test
	void testDiv() {
		String result = cmd.process("2 3 /");
		String expected = "stack: 0.6666666667";
		assertEquals(expected, result);
		
		result = cmd.process("3 *");
		expected = "stack: 2";
		assertEquals(expected, result);
	}
	
	@Test
	void testSqrt() {
		String result = cmd.process("2 sqrt");
		String expected = "stack: 1.4142135624";
		assertEquals(expected, result);
		
		result = cmd.process("2 sqrt *");
		expected = "stack: 2";
		assertEquals(expected, result);
	}
	
	@Test
	void testCombined() {
		cmd.process("2 3 /");
		cmd.process("3 *");
		cmd.process("1 +");
		String result = cmd.process("6 -");
		cmd.process("clear");
		
		String expected = cmd.process("2 3 / 3 * 1 + 6 -");
		assertEquals(expected, result);
		
	}
	
	@Test
	void testClear() {
		cmd.process("2  3  3 *  1 +  6 -");
		String result = cmd.process("clear");
		String expected = "stack:";
		assertEquals(expected, result);
		
		result = cmd.process("2  3 *");
		expected = "stack: 6";
		assertEquals(expected, result);
	}
	
	@Test
	void testUndo() {
		String result = cmd.process("2 3 / 3 * 1 + 6 sqrt");
		String expected = "stack: 3 2.4494897428";
		assertEquals(expected, result);
		result = cmd.process("undo");
		expected = "stack: 3 6";
		assertEquals(expected, result);
		result = cmd.process("undo");
		expected = "stack: 3";
		assertEquals(expected, result);
		result = cmd.process("undo");
		expected = "stack: 2 1";
		assertEquals(expected, result);
		result = cmd.process("undo");
		expected = "stack: 2";
		assertEquals(expected, result);
		result = cmd.process("undo");
		expected = "stack: 0.6666666667 3";
		assertEquals(expected, result);
		result = cmd.process("undo");
		expected = "stack: 0.6666666667";
		assertEquals(expected, result);
		result = cmd.process("undo");
		expected = "stack: 2 3";
		assertEquals(expected, result);
		result = cmd.process("undo");
		expected = "stack: 2";
		assertEquals(expected, result);
		result = cmd.process("undo");
		expected = "stack:";
		assertEquals(expected, result);
		result = cmd.process("undo");
		expected = "stack:";
		assertEquals(expected, result);
	}
	
	@Test
	void testEmptyInput() {
		cmd.process("2");
		cmd.process("");
		cmd.process("3");
		cmd.process("   ");
		String result = cmd.process("*");
		
		String expected = "stack: 6";
		assertEquals(expected, result);
	}
	
	@Test
	void testInsucientParameters() {
		
		String result = cmd.process("+");
		String expected = "stack:";
		assertEquals(expected, result);
		
		result = cmd.process("1 + ");
		expected = "stack: 1";
		assertEquals(expected, result);
		
		result = cmd.process("1 + + 2 3");
		expected = "stack: 2";
		assertEquals(expected, result);
		
	}
	
	@Test
	void testDividedByZero(){
		String result = cmd.process("2 0 /");
		String expected = "stack:";
		assertEquals(expected, result);
	}
	
	@Test
	void testSqrtForNegative() {
		String result = cmd.process("-1 sqrt");
		String expected = "stack:";
		assertEquals(expected, result);
	}
	
	@Test
	void testUnexpectedInput() {
		String result = cmd.process("1 2 3 unexpected 4 5");
		String expected = "stack: 1 2 3";
		assertEquals(expected, result);
	}


}
