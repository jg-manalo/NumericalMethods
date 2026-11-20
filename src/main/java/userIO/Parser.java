package userIO;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;


public class Parser {

    public static double parse(String function, double variable) {
        Argument x = new Argument("x", variable);
        Expression data = new Expression(function, x);
        return data.calculate();

    }
    
    public static double parseMultiVar(String function, double var1, double var2) {
    	Argument x = new Argument("x", var1);
        Argument y = new Argument("y", var2);
        Expression data = new Expression(function,x ,y);
        return data.calculate();
    }
    
    public static double parseMultiVar(String function, double var1, double var2, double var3) {
    	Argument x = new Argument("x", var1);
    	Argument y = new Argument("y", var2);
    	Argument z = new Argument("z", var3);
    	Expression data = new Expression(function,x ,y,z);
    	return data.calculate();
    }
    
    public static double parseConstant(String constant) {
    	Expression constToEval = new Expression(constant);
    	return constToEval.calculate();
    }
    
}
