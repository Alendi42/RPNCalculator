package rpn.model;

public class SUB extends Result {
	
	public SUB(Element[] args) {
		super(args);
	}

	@Override
	public void calculate() {
		this.value = args[0].getValue().subtract(args[1].getValue(), mathContext); 
	}

}
