package numericalIntegration;

import userIO.InputHelper;

public interface InfoSharing {
    default void getProof(double errorLowerBound, double absError, double errorUpperBound) {
    	try {
    		if (Double.isNaN(errorLowerBound) || Double.isNaN(absError) || Double.isNaN(errorUpperBound)) {
    			throw new Exception("\nError: Can't Distinguish The Result");
    		} 
    			
			String firstComparator = errorLowerBound < absError ? "<" : ">";
			String secondComparator = absError < errorUpperBound ? "<" : ">";
			String response = firstComparator.equals("<") && secondComparator.equals("<") ? "The answer is acceptable!" : "The answer is unacceptable!";
			
			InputHelper.send(String.format("%.10f %s %.10f %s %.10f\n", errorLowerBound, firstComparator, absError, secondComparator, errorUpperBound));
			InputHelper.send(response);
		
    	} catch (Exception e) {
    		InputHelper.send(e.getMessage());
    	}
    }
    
    void loadInfo();
}
