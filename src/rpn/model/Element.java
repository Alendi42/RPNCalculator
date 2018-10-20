package rpn.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;

/**
 * Elements used in calculator stack
 * @author Nan
 *
 */
public abstract class Element {

	protected BigDecimal value;
	protected MathContext mathContext;

	public BigDecimal getValue() {
		return value;
	}
	
	public String outputValue(DecimalFormat format) {
		return format.format(value);
	}
	
	abstract public Element[] restore();
	
}
