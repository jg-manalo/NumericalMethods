package calculus;

import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.interfaces.IExpr;

import userIO.InputHelper;
import userIO.Parser;

public interface Derivative {
	public default double fDerivative(String expression, double variable) {
		try {

			ExprEvaluator eval = new ExprEvaluator();

			IExpr derivative = eval.eval("D(" + expression + ", x)");
			eval.defineVariable("x", variable);

			return derivative.toDoubleDefault();
		} catch (Exception e) {
			InputHelper.send(e.getMessage());
			System.out.println(e.getMessage());
			return Double.NaN;
		}

	}

	public default double sDerivative(String expression, double variable) {
		try {
			ExprEvaluator eval = new ExprEvaluator();

			IExpr derivative = eval.eval("D(D(" + expression + ", x)" + ",x)");
			eval.defineVariable("x", variable);
			return derivative.toDoubleDefault();
		} catch (Exception e) {
			InputHelper.send(e.getMessage());
			System.out.println(e.getMessage());
			return Double.NaN;
		}
	}

	public default double fourthDerivative(String expression, double variable) {
		try {

			ExprEvaluator eval = new ExprEvaluator();

			IExpr derivative = eval.eval("D(D(D(D(" + expression + ", x), x), x), x)");
			eval.defineVariable("x", variable);
			return derivative.toDoubleDefault();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Double.NaN;
		}
	}

	public default double firstPartialDerX(String expression, double x, double y) {
		try {

			ExprEvaluator eval = new ExprEvaluator();

			IExpr derivative = eval.eval("D(" + expression + ", x)");
			
			return Parser.parseMultiVar(derivative.toString().toLowerCase(), x, y);
		} catch (Exception e) {
			InputHelper.send(e.getMessage());
			System.out.println(e.getMessage());
			return Double.NaN;
		}
	}

	public default double firstPartialDerY(String expression, double x, double y) {
		try {

			ExprEvaluator eval = new ExprEvaluator();

			IExpr derivative = eval.eval("D(" + expression + ", y)");
			eval.defineVariable("y", y);
		
			return Parser.parseMultiVar(derivative.toString().toLowerCase(), x, y);
		} catch (Exception e) {
			InputHelper.send(e.getMessage());
			System.out.println(e.getMessage());
			return Double.NaN;
		}
	}

}
