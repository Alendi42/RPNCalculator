package rpn.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Stack;

import rpn.exception.InsucientParameterException;
import rpn.exception.InvalidDecimalNumberException;

public class ElementFactory {
	
	private Stack<Element> calculationStack;
	private MathContext mathContext;

	public ElementFactory(Stack<Element> stack, MathContext mathContext) {
		this.calculationStack = stack;
		this.mathContext = mathContext;
	}
	
	public Element createElement(String arg) {
		
		switch(arg) {
		case "+": 
			return new ADD(tryFetch(2));
		case "-":
			return new SUB(tryFetch(2));
		case "*":
			return new MUL(tryFetch(2));
		case "/":
			return new DIV(tryFetch(2));
		case "sqrt":
			return new SQRT(tryFetch(1));
		default: // treat it as a decimal number
			try {
				return new Origin(new BigDecimal(arg), mathContext);
			}catch (NumberFormatException e) {
				throw new InvalidDecimalNumberException();
			}
		}
	}
	
	/**
	 * Fetch arguments from stack.
	 * @param number, the number of arguments to fetch
	 * @return
	 */
	private Element[] tryFetch(int number) {
		if(calculationStack.size() < number) {
			throw new InsucientParameterException();
		}else {
			Element[] arguments = new Element[number];
			for(int i = number -1; i>=0; i--) {
				arguments[i] = calculationStack.pop();
			}
			return arguments;
		}
	}

}
