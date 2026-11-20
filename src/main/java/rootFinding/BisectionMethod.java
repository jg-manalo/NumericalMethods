package rootFinding;

import userIO.Parser;
import userIO.InputHelper;
import userIO.InputHelperExt;
import userIO.OutputHelper;
import userIO.GUIHelper;

import java.util.Stack;

import iterators.NumMethIterator;
import errorChecker.ErrorChecker;
public class BisectionMethod extends NumMethIterator implements RootFinder, OutputHelper, ErrorChecker {

	private final String expr;
	private double a;
	private double b;
	private double c;
	private final double threshold;
	private final Stack<Double> midPointHistory;

	public BisectionMethod(String function, String a, String b, String threshold) {
		this.expr = function;
		this.threshold = Parser.parseConstant(threshold);
		this.a = Parser.parseConstant(a);
		this.b = Parser.parseConstant(b);
		this.midPointHistory = new Stack<>();
		this.c = this.midpoint(this.a, this.b);
	}

	@Override
	public double findRoot() {

		try {
			double fA = this.fA(this.a);
			double fB = this.fA(this.b);
			boolean isAssumptionWrong = !(fA * fB < 0);
			boolean areDoublesNaN = Double.isNaN(fA(this.a)) || Double.isNaN(fB(this.b)) || Double.isNaN(fC());

			if (areDoublesNaN) {
				throw new RuntimeException("[Please verify your inputs]\n");
			} else if (isAssumptionWrong) {
				throw new RuntimeException(String.format(
						"[Try a different guess] fA(x): %.10f * fB(x): %.10f = %.10f > 0\n", fA, fB, (fA * fB)));
			}

			InputHelper.send(String.format("\nThreshold: E < %f\n\n", this.threshold));

			if (this.midPointHistory.isEmpty()) {
				this.midPointHistory.push(this.c);
			}
			this.midPointHistory.push(this.c);
			for (short i = 1; i <= this.maxIteration; i++) {
				this.c = midpoint(this.a, this.b);
				double diffOfAB = Math.abs(this.a - this.b);
				boolean atBestCase = fC() == 0.0;
				boolean isBelowTolerance = diffOfAB < this.threshold;

				// actual implementation
				InputHelper.send(String.format("i[%d] a: %.10f b: %.10f c: %.10f f(c): %f Error: %.10f\n", i, this.a,
						this.b, this.c, this.fC(), diffOfAB));

				if (atBestCase) {
					return this.c;
				}
				
				if (isBelowTolerance) {
					break;
				} else if (fC() < 0) {
					this.a = this.c;
				} else {
					this.b = this.c;
				}

				this.midPointHistory.push(this.c);
			}
			return this.midPointHistory.pop();
		} catch (RuntimeException e) {
			InputHelper.send("Error: " + e.getMessage());
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

	private double midpoint(double a, double b) {
		return (a + b) / 2;
	}

	private double fA(double a) {
		return Parser.parse(this.expr, a);
	}

	private double fB(double b) {
		return Parser.parse(this.expr, b);
	}

	private double fC() {
		return Parser.parse(this.expr, c);
	}
}
