package calculus;

import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.interfaces.IExpr;
import userIO.Parser;

public interface Integral {
    default double integrate(String func, double upperLimit, double lowerLimit){
        try {        	
        	ExprEvaluator evaluator = new ExprEvaluator();
        	
        	String function = "Integrate" + "[" + func + ", {x, " + String.format("%.10f", lowerLimit) + ", " + String.format("%.10f", upperLimit) + "}]";
        	
        	IExpr result = evaluator.eval(function);        
        	return Parser.parse(result.toString(), 1);
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        	return Double.NaN;
        }
    }
}
