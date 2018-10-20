package rpn.model;

/**
 * Represent decimals in the stack which are the results of prior calculation(add/sub/mul/div/sqrt). 
 * @author Nan
 *
 */
abstract public class Result extends Element {

	protected Element[] args;
	
	protected Result(Element[] args) {
		this.args = args;
		this.mathContext = args[0].mathContext;
		calculate();
	}
	
	abstract public void calculate();
	
	public Element[] restore() {
		return args;
	}
	
}
