package rpn.model;

import java.math.BigDecimal;

public class SQRT extends Result {
	
	public SQRT(Element[] args) {
		super(args);
	}

	@Override
	public void calculate() {
		this.value = new BigDecimal(Math.sqrt(args[0].getValue().doubleValue()), mathContext);
	}

}
