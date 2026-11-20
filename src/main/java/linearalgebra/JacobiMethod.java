package linearalgebra;

import java.util.Stack;
import java.util.Arrays;
import java.util.HashSet;

import userIO.GUIHelper;
import userIO.GUIHelperExt;
import userIO.InputHelper;
import userIO.OutputHelper;
import userIO.Parser;
import userIO.InputHelperExt;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.JacobiPreconditioner;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import errorChecker.ErrorCheckerMultiVar;
import iterators.NumMethIterator;

public class JacobiMethod extends NumMethIterator implements OutputHelper, ErrorCheckerMultiVar {

	private final double[][] lhsMat;
	private final double[] rhsVector;
	private final double[] unknowns;
	private final int matSize;
	private final Stack<RealVector> xHistory;
	private final Stack<Double> errorHistory;

	public JacobiMethod( String[][] lhsMat, String[] rhsVector, String[] assumption, String threshold) {
		this.matSize = lhsMat.length;
		this.threshold = Double.parseDouble(threshold);
		this.xHistory = new Stack<>();
		this.lhsMat = loadLHSMat(lhsMat);
		this.rhsVector = loadRHSVec(rhsVector);
		this.unknowns = loadUnknowns(assumption);
		this.errorHistory = new Stack<>();
	}

	@Override
	public void processOutput() {
		this.convertFinalAnsAsMsg();;
		while (!InputHelper.isMessagesEmpty()) {
			GUIHelper.send(InputHelper.receive());
		}

		while (!InputHelperExt.isMessagesEmpty()) {
			GUIHelperExt.send(InputHelperExt.receive());
		}
	}

	@Override
	protected void convertFinalAnsAsMsg() {
		RealVector finalAnswer = this.calculateJacobi();
		InputHelper.send("\n\nApproximate Answers With Respect To The Threshold:\n");
		for (int i = 0; i < this.matSize; i++) {
			InputHelper.send(String.format("u[%d]: %.10f\n", i + 1 ,finalAnswer.getEntry(i)));
		}
	}

	private RealVector calculateJacobi() {
		try {
			if (!isDiagonallyDominant(this.lhsMat)) {
				throw new RuntimeException(
						"The LHS Matrix is not diagonally dominant. Jacobi Method would not work...");
			}
			
			RealVector b = new ArrayRealVector(this.rhsVector);
			RealVector x = new ArrayRealVector(this.unknowns);
			JacobiPreconditioner D_inversed = new JacobiPreconditioner(getDiagonals(this.lhsMat), true);
			RealMatrix N = new Array2DRowRealMatrix(nonDiagonals(this.lhsMat));

			short i = 1;
			boolean isIterating = true;

			if (this.xHistory.isEmpty()) {
				this.xHistory.push(x);
			}
			
			InputHelperExt.send(String.format("Threshold: E < %f", this.threshold));
			InputHelperExt.send("\n");
			InputHelper.send(String.format("i[%d]: ", 0));
			for (int l = 0; l < this.matSize; l++) {
				InputHelper.send(String.format("%.10f ", x.getEntry(l)));
			}

			InputHelper.send("\n");
			
			while (isIterating) {
				RealVector prod = N.operate(this.xHistory.peek());
				RealVector ans = D_inversed.operate(b.subtract(prod));
				InputHelper.send(String.format("i[%d]: ", i));
				boolean wantsToHalt = (i == this.maxIteration || isIdealErrorReached(ans, this.xHistory.peek()));
				if (wantsToHalt) {
					isIterating = false;
				}

				for (int j = 0; j < x.getDimension(); j++) {
					double newX = ans.getEntry(j);
					x.setEntry(j, newX);

					InputHelper.send(String.format("%.10f ", x.getEntry(j)));
				}

				InputHelper.send("\n");
				this.xHistory.push(ans);

				i++;
			}
			return this.xHistory.pop();
		} catch (RuntimeException e) {
			InputHelper.send(String.format("Error: %s\n", e.getMessage()));
		}
		return this.allUnknownsAreNaN();
	}
	
	private RealVector allUnknownsAreNaN() {
		double[] tempArr = new double[this.matSize];
		for (int i = 0; i < tempArr.length; i++) {
			tempArr[i] = Double.NaN;
		}
		return new ArrayRealVector(tempArr);
	}
	private boolean isDiagonallyDominant(double[][] lhsMat) {
		boolean matIsDominant = true;
		for (int i = 0; i < this.matSize; i++) {
			double sum = 0;
			for (int j = 0; j < this.matSize; j++) {
				sum += Math.abs(lhsMat[i][j]);
			}
			sum -= Math.abs(lhsMat[i][i]);

			if (Math.abs(lhsMat[i][i]) < sum) {
				matIsDominant = false;
			}
		}
		return matIsDominant;
	}

	private double[][] loadLHSMat(String[][] rawLHS) {
		double[][] leftMat = new double[this.matSize][this.matSize];
		for (int i = 0; i < this.matSize; i++) {
			for (int j = 0; j < this.matSize; j++) {
				leftMat[i][j] = Parser.parseConstant(rawLHS[i][j]);
			}
		}
		return leftMat;
	}

	private double[] loadRHSVec(String[] rawRHSVec) {
		double[] rhsVec = new double[this.matSize];
		for (int i = 0; i < this.matSize; i++) {
			rhsVec[i] = Parser.parseConstant(rawRHSVec[i]);
		}
		return rhsVec;
	}

	private double[] loadUnknowns(String[] assume) {
		double[] guess = new double[this.matSize];
		for (int i = 0; i < matSize; i++) {
			guess[i] = Parser.parseConstant(assume[i]);
		}
		return guess;
	}

	@Override
	public boolean isIdealErrorReached(RealVector current, RealVector previous) {
		HashSet<Boolean> signals = new HashSet<>(); 
		boolean isCloseEnough = false;
		boolean binaryFlag = false;
				
		for (int i = 0; i < this.matSize; i++) {
			double diff = Math.abs(current.getEntry(i) - previous.getEntry(i));
			double e = Math.abs(diff / current.getEntry(i));
			this.errorHistory.push(e);
			InputHelperExt.send(String.format("%.10f ", this.errorHistory.peek()));
			
			binaryFlag = e >= this.threshold ? false : true;
			signals.add(binaryFlag);
		}
		
		isCloseEnough = (signals.size() == 1 && signals.contains(true)) ?  true : false;
		
		InputHelperExt.send("\n");
		return isCloseEnough;
	}
	
	private double[] getDiagonals(double[][] rawMat) {
		double[] vec = new double[rawMat.length];
		for (int i = 0; i < rawMat.length; i++) {
			for (int j = 0; j < rawMat.length; j++) {
				if (i == j) {
					vec[i] = rawMat[i][j];
				}
			}
		}
		return vec;
	}

	private double[][] nonDiagonals(double[][] rawMat) {
		double[][] N = Arrays.copyOfRange(rawMat, 0, rawMat.length);
		for (int i = 0; i < rawMat.length; i++) {
			for (int j = 0; j < rawMat.length; j++) {
				if (i == j) {
					N[i][j] = 0.0;
				}
			}
		}
		return N;
	}
}
