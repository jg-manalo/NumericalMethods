package errorChecker;

import userIO.InputHelper;

public interface ErrorChecker {
	 default public double getErrorLevel(double current, double previous) {
	    	try {
	    		double getErrorLevel = Math.abs((current - previous) / current);
	    		return getErrorLevel;
	    	} catch (Exception e) {
	    		InputHelper.send(e.getMessage());
	    		return Double.NaN;
	    	}
	    }
}
