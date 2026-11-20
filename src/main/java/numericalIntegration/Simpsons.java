package numericalIntegration;

import calculus.Derivative;
import calculus.Integral;
import userIO.GUIHelper;
import userIO.InputHelper;
import userIO.OutputHelper;

public class Simpsons extends numIntegrationConditioner implements Integral, Derivative, InfoSharing, OutputHelper {

    private final String function;
    private final Limits limit;
    private final IntervalWithStep measurements;
    private final double[] table;

    public Simpsons(String functionGiven, String upperLimit, String lowerLimit, String intervals) {
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
        InputHelper.clear();
    }

    @Override
    public void loadInfo() {
    	try {
    		if (Double.isNaN(this.directIntegral())) {
    			throw new RuntimeException("Possible syntax error\n");
    		}
    		InputHelper.send(String.format("Direct integration: %.6f\n", this.directIntegral()));
    		InputHelper.send(String.format("Simpson\'s integration: %.6f\n", this.applySimpsons()));
    	} catch (Exception e) {
    		InputHelper.send(e.getMessage());
    	}
    }
    
    
    private boolean isIntervalOdd() {
    	return this.measurements.interval % 2 != 0;
    }
    private double applySimpsons() {
    	try {
    		
    		if (this.isIntervalOdd()) {
    			throw new RuntimeException("Odd Intervals Are Not Allowed!");
    		}
    		this.generateTable(this.table, this.limit, this.measurements, this.function);
    		double hOverThree = measurements.h / 3;
    		double y0 = this.table[0];
    		double yn = this.table[this.table.length - 1];
    		double evenInBetweens = this.evenSumInBetween();
    		double oddInBetweens = this.oddSumInBetween();
    		
    		double It = (hOverThree) * (y0 + yn + (4 * oddInBetweens) + (2 * evenInBetweens));
    		return It;
    		
    	} catch (Exception e) {
    		InputHelper.send(e.getMessage());
    		return Double.NaN;
    	}
    }

    private double absoluteError() {
        return Math.abs(this.directIntegral() - this.applySimpsons());
    }

    private double directIntegral() {
        return this.isIntervalOdd()? Double.NaN : integrate(function, limit.uLimit, limit.lLimit);
    }

    private double errorUBound() {
        return this.hFourthOver180() * (this.limit.uLimit - this.limit.lLimit) * fourthDerMax();
    }

    private double errorLBound() {
        return this.hFourthOver180() * (this.limit.uLimit - this.limit.lLimit) * fourthDerMin();
    }

    private double hFourthOver180() {
        return (Math.pow(measurements.h, 4)) / 180;
    }

    private double fourthDerMin() {
        return fourthDerivative(this.function, this.limit.uLimit);
    }

    private double fourthDerMax() {
        return fourthDerivative(this.function, this.limit.lLimit);
    }

    private double evenSumInBetween() {
        double sum = 0;
        for (int i = 1; i < this.table.length - 2; i++) {
            sum += i % 2 == 0 ? this.table[i] : 0;
        }
        return sum;
    }

    private double oddSumInBetween() {
        double sum = 0;
        for (int i = 1; i < this.table.length - 1; i += 2) {
            sum += i % 2 != 0 ? this.table[i] : 0;
        }
        return sum;
    }
}
