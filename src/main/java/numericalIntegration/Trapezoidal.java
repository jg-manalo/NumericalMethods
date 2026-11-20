package numericalIntegration;

import calculus.Integral;
import calculus.Derivative;
import userIO.GUIHelper;
import userIO.InputHelper;
import userIO.OutputHelper;

public class Trapezoidal extends numIntegrationConditioner implements Integral, Derivative, InfoSharing, OutputHelper {
    private final String function;
    private final Limits limit;
    private final IntervalWithStep measurements;
    private final double[] table;

    public Trapezoidal(String functionGiven, String upperLimit, String lowerLimit, String intervals) {
        this.function = functionGiven;
        this.limit = new Limits(upperLimit, lowerLimit);
        this.measurements = new IntervalWithStep(upperLimit, lowerLimit, intervals);
        this.table = new double[this.measurements.interval + 1];
    }

    @Override
    public void processOutput() {
        this.loadInfo();
        this.getProof(this.errorLBound(), this.absoluteError(), this.errorUBound());
        while (!InputHelper.isMessagesEmpty()) {
            GUIHelper.send(InputHelper.receive());
        }
    }

    @Override
    public void loadInfo() {
    	try {  
    		if(Double.isNaN(this.directIntegral()) || Double.isNaN(this.applyTrapezoidal())) {
    			throw new Exception("Error!!!\n");
    		}
    		InputHelper.send(String.format("Direct integration: %.6f\n", this.directIntegral()));
    		InputHelper.send(String.format("Trapezoidal integration: %.6f\n", this.applyTrapezoidal()));
    	} catch (Exception e) {
    		InputHelper.send(e.getMessage());
    	}
    }

    private double applyTrapezoidal() {
        try {
        	
        	this.generateTable(this.table, this.limit, this.measurements, this.function);
        	double hOverTwo = measurements.h / 2;
        	double y0 = this.table[0];
        	double yn = this.table[this.table.length - 1];
        	double inBetweenSums = this.sumInBetweens();
        	
        	double It = (hOverTwo) * (y0 + yn + (2 * (inBetweenSums)));
        	
        	if (It == Double.NaN) {
        		throw new Exception("Error");
        	}
        	return It;
        } catch (Exception e) {
        	InputHelper.send(e.getMessage());
        	return Double.NaN;
        }
    }

    private double absoluteError() {
        return Math.abs(this.directIntegral() - this.applyTrapezoidal());
    }

    private double directIntegral() {
        return integrate(function, limit.uLimit, limit.lLimit);
    }

    private double errorUBound() {
        return hSqrOver12() * (this.limit.uLimit - this.limit.lLimit) * sDerMax();
    }

    private double errorLBound() {
        return hSqrOver12() * (this.limit.uLimit - this.limit.lLimit) * sDerMin();
    }

    private double hSqrOver12() {
        return (Math.pow(measurements.h, 2)) / 12;
    }

    private double sDerMin() {
        return sDerivative(this.function, this.limit.uLimit);
    }

    private double sDerMax() {
        return sDerivative(this.function, this.limit.lLimit);
    }

    private double sumInBetweens() {
        double sum = 0;
        for (int i = 1; i < this.table.length - 1; i++) {
            sum += this.table[i];
        }
        return sum;
    }
}
