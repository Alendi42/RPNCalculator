package rpn.model;

public class MUL extends Result {
	
	public MUL(Element[] args) {
		super(args);
	}

	@Override
	public void calculate() {
		this.value = args[1].getValue().multiply(args[0].getValue(), mathContext);
	}

}
