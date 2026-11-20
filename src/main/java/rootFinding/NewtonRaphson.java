package rootFinding;

import userIO.Parser;
import java.util.Stack;

import userIO.GUIHelper;
import userIO.InputHelper;
import userIO.InputHelperExt;
import userIO.OutputHelper;
import calculus.Derivative;
import errorChecker.ErrorChecker;
import iterators.NumMethIterator;

public class NewtonRaphson extends NumMethIterator implements RootFinder, OutputHelper, Derivative, ErrorChecker {

	private final String expr;
	private double x;
	private final double threshold;
	private final Stack<Double> xValueHistory;

	public NewtonRaphson(String expr, String initialGuess, String threshold) {
		this.expr = expr;
		this.x = Parser.parseConstant(initialGuess);
		this.threshold = Parser.parseConstant(threshold);
		this.xValueHistory = new Stack<>();
	}

	@Override
	public double findRoot() {

		try {
			boolean convergent = (fOfX() * fDoublePrime()) < Math.pow(fPrime(), 2);
			boolean areDoublesNaN = Double.isNaN(fOfX()) || Double.isNaN(fPrime())
					|| Double.isNaN(fDoublePrime());

			double fOfXTimesFDoublePrime = fOfX() * fDoublePrime();
			double fPrimeSquared = Math.pow(fPrime(), 2);

			if (xValueHistory.isEmpty()) {
				xValueHistory.add(this.x);
			}

			if (areDoublesNaN) {
				throw new RuntimeException("[Please verify your inputs]\n");
			} 
			
			if (!convergent) {
				throw new RuntimeException(String.format("[Not convergent] (f(x) *f\"(x)) = %.10f >=  f'(x): %.10f\n",
						fOfXTimesFDoublePrime, fPrimeSquared));
			}

			InputHelper.send(String.format("\nThreshold: E < %f\n\n", this.threshold));
			this.sendCalculationsToGUI(String.format("x[%d]: %.10f\n", 0, this.x));
			for (short i = 1; i <= this.maxIteration; i++) {
				this.x = this.x - (this.fOfX() / this.fPrime());
				this.sendCalculationsToGUI(String.format("x[%d]: %.10f Error: %.10f\n", i, this.x,
						getErrorLevel(this.x, this.xValueHistory.peek())));

				boolean isIdealErrorReached = getErrorLevel(this.x, this.xValueHistory.peek()) < this.threshold;
				
				if (isIdealErrorReached) {
					break;
				} else if (Double.isNaN(this.getErrorLevel(this.x, this.xValueHistory.peek()))) {
					throw new RuntimeException("Unsolvable!\n");
				}
				this.xValueHistory.push(this.x);
			}
			return this.xValueHistory.peek();
		} catch (Exception e) {
			InputHelper.send("Error: " + e.getMessage() + "\n");
			return Double.NaN;
		}
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
		InputHelper.clear();
		InputHelperExt.clear();
	}

	@Override
	protected void convertFinalAnsAsMsg() {
		double finalAns = this.findRoot();
		InputHelperExt.send(String.format("Approximate root: %.10f\n", finalAns));

	}

	private double fOfX() {
		return Parser.parse(this.expr, this.x);
	}

	private double fPrime() {
		return fDerivative(this.expr, this.x);
	}

	private double fDoublePrime() {
		return sDerivative(this.expr, this.x);
	}

	private void sendCalculationsToGUI(String query) {
		InputHelper.send(query);
	}
}
