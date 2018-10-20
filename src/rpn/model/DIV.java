package rpn.model;

public class DIV extends Result {
	
	public DIV(Element[] args) {
		super(args);
	}

	@Override
	public void calculate() {
		this.value = args[0].getValue().divide(args[1].getValue(), mathContext);
	}

}
