package rpn.model;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Represent decimals in the stack which are from cmd input directly. 
 * @author Nan
 *
 */
public class Origin extends Element {

	public Origin(BigDecimal value, MathContext mathContext) {
		this.value = value;
		this.mathContext = mathContext;
	}

	@Override
	public Element[] restore() {
		return new Element[0];
	}
	
}
