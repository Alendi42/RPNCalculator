package rpn.model;

public class ADD extends Result {
	
	public ADD(Element[] args) {
		super(args);
	}

	@Override
	public void calculate() {
		this.value = args[0].getValue().add(args[1].getValue(), mathContext);
	}

}
