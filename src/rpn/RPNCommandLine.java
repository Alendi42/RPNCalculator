package rpn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import rpn.exception.InsucientParameterException;
import rpn.exception.InvalidDecimalNumberException;

public class RPNCommandLine {
	
	private Calculator calculator;
	
	public RPNCommandLine() {
		calculator = new Calculator();
	}
	
	public RPNCommandLine(int precision, int display) {
		calculator = new Calculator(precision, display);
	}
	
	public void run() {
		
		System.out.println("RPN calculator starts.");
		System.out.println(calculator.outputStack());
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String buffer = null;
		try {
			while((buffer = br.readLine()) != null) {
				
				if("exit".equals(buffer))
					break;
				
				String status = process(buffer);
				System.out.println(status);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("RPN calculator stops.");
		}
	}
	
	public String process(String input) {
		int pos = 0;
		StringTokenizer tokenizer = new StringTokenizer(input);
		while(tokenizer.hasMoreTokens()) {
			pos++;//record the sequence of current processing items
			String token = tokenizer.nextToken();
			try{
				calculator.process(token);
			}catch(InsucientParameterException e) {
				System.out.println("operator " + token + "(position: " + pos + ") insucient parameters");
				break;
			}catch(InvalidDecimalNumberException e) {
				System.out.println("argument " + token + "(position: " + pos + ") is not a valid decimal number");
				break;
			}catch(NumberFormatException e) {
				System.out.println("Invalid result on operator " + token + "(position: " + pos + "): " + e.getMessage() + ". Clear the stack.");
				calculator.clear();
				break;
			}catch(ArithmeticException e) {
				System.out.println("Invalid result on operator " + token + "(position: " + pos + "): " + e.getMessage() + ". Clear the stack.");
				calculator.clear();
				break;
			}
		}
		return calculator.outputStack();
	}
	

	public static void main(String[] args) {
		
		RPNCommandLine cmd;

		if(args.length == 2) {
			cmd = new RPNCommandLine(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		}else {
			cmd = new RPNCommandLine();
		}
		cmd.run();
		
	}

}
