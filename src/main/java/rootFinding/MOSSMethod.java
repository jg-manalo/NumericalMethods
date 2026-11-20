package rootFinding;

import userIO.Parser;
import userIO.GUIHelper;
import userIO.InputHelper;
import userIO.InputHelperExt;
import userIO.OutputHelper;
import calculus.Derivative;
import errorChecker.ErrorChecker;
import iterators.NumMethIterator;

import java.util.Stack;

public class MOSSMethod extends NumMethIterator implements RootFinder, OutputHelper,Derivative, ErrorChecker {
    private final String fSimplified;
    private final double threshold;
    private double x;
    private final Stack<Double> xValueHistory;

    public MOSSMethod(String fSimplified, String xk, String threshold) {
        this.xValueHistory = new Stack<>();
        this.fSimplified = fSimplified;
        this.x = Parser.parseConstant(xk);
        this.threshold = Parser.parseConstant(threshold);
    }

    @Override
    public void processOutput() {
    	this.convertFinalAnsAsMsg();
    	while (!InputHelperExt.isMessagesEmpty()) {
    		GUIHelper.send(InputHelperExt.receive());
    	}
    	while (!InputHelper.isMessagesEmpty()) {
    		GUIHelper.send(InputHelper.receive());
    	}
    }
    
    @Override
    public double findRoot() {
        try {
            boolean notConvergent = this.gx() >= 1;
            boolean areDoublesNaN = Double.isNaN(g(this.x)) || Double.isNaN(gx());

            if (areDoublesNaN) {
            	throw new RuntimeException("[Please verify your inputs]\n");
            } else if (notConvergent){
                throw new RuntimeException(String.format("[Not convergent] g(x): %.10f >= 1",this.gx()));
            }

            if (xValueHistory.isEmpty()) {
                xValueHistory.push(this.x);
            }

            InputHelper.send(String.format("\nThreshold: E < %f\n\n", this.threshold));
            InputHelper.send(String.format("x[%d]: %.10f\n", 0, this.x));

            for (short k = 1; k <= this.maxIteration; k++) {
                this.x = g(this.x);
                InputHelper.send(String.format("x[%d]: %.10f Error %.10f\n", k, this.x, this.getErrorLevel(this.x, this.xValueHistory.peek())));
                
                boolean isIdealErrorReached = this.getErrorLevel(this.x, this.xValueHistory.peek()) < this.threshold;
                if (isIdealErrorReached) {
                	break;
                }
                xValueHistory.push(this.x);
            }
            return this.xValueHistory.peek();
        } catch (RuntimeException e) {
            InputHelper.send("Error: " + e.getMessage() + "\n");
            return Double.NaN;
        }
    }
    
    @Override
   	protected void convertFinalAnsAsMsg() {
   		double finalAns = this.findRoot();
   		InputHelperExt.send(String.format("Approximate root: %.10f\n", finalAns));
   		
   	}
    
    private double g(double x) {
        return Parser.parse(this.fSimplified, x);
    }

    private double gx() {
    	return fDerivative(this.fSimplified, this.x);
    }
    
}
