package rpn;

import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Stack;

import rpn.model.Element;
import rpn.model.ElementFactory;

public class Calculator {

	private Stack<Element> calculationStack;
	private DecimalFormat decimalFormat;
	private ElementFactory factory;
	
	private static final DecimalFormat defaultDecimalFormat = new DecimalFormat("#.##########");//decimal places on external display, default as 10
	static {
		defaultDecimalFormat.setRoundingMode(RoundingMode.HALF_UP);
	}
	private static final MathContext defaultMathContext = new MathContext(15, RoundingMode.HALF_UP);//decimal places in internal precision(scale), default as 15
	
	
	public Calculator() {
		this.calculationStack = new Stack<>();
		this.decimalFormat=defaultDecimalFormat;
		
		factory = new ElementFactory(calculationStack, defaultMathContext);
	}
	
	public Calculator(int precision, int display) {
		this.calculationStack = new Stack<>();
		
		StringBuffer buffer = new StringBuffer("#.");
		for(int i = 0; i < display ; i++)
			buffer.append('#');
		this.decimalFormat = new DecimalFormat(buffer.toString());
		this.decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
		MathContext mathContext = new MathContext(precision, RoundingMode.HALF_UP);
		
		factory = new ElementFactory(calculationStack, mathContext);
	}
	
	public void process(String arg) {
		
		switch(arg) {
		//undo and clear, the two commands will not create new Element to stack
		case "undo":
			undo();
			break;
		case "clear":
			clear();
			break;
		// Otherwise, call factory to create Element and push to stack.	
		default: 
			calculationStack.push(factory.createElement(arg));
		}
		
	}
	
	public String outputStack() {
		StringBuffer buffer = new StringBuffer("stack:");
		
		for(Element e : calculationStack) {
			buffer.append(" " + e.outputValue(decimalFormat));
		}
		return buffer.toString();
		
	}
	
	public void clear() {
		calculationStack.clear();
	}
	
	public void undo() {
		if(calculationStack.size() > 0) {
			Element[] args = calculationStack.pop().restore();
			for(Element e : args) {
				calculationStack.push(e);
			}
		}//otherwise, there are no operations to undo.
	}
	
}
