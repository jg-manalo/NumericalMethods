package errorChecker;

import org.apache.commons.math3.linear.RealVector;

public interface ErrorCheckerMultiVar {
	public boolean isIdealErrorReached(RealVector current, RealVector previous);
}
