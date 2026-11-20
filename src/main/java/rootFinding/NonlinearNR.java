package rootFinding;

import java.util.HashSet;
import java.util.Stack;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import calculus.Derivative;
import errorChecker.ErrorCheckerMultiVar;
import iterators.NumMethIterator;
import userIO.GUIHelper;
import userIO.GUIHelperExt;
import userIO.InputHelper;
import userIO.InputHelperExt;
import userIO.OutputHelper;
import userIO.Parser;

public class NonlinearNR extends NumMethIterator implements Derivative, OutputHelper, ErrorCheckerMultiVar {
	private Stack<RealVector> calcHistory;
	private Stack<Double> errorHistory;
	private final double[] assumptions;
	private final FunctionCollection functions;
	private final int matrixSize;

	public NonlinearNR(FunctionCollection functions, String[] assumptions, String threshold) {
		this.threshold = Parser.parseConstant(threshold);
		this.functions = functions;
		this.matrixSize = 2;
		this.calcHistory = new Stack<>();
		this.errorHistory = new Stack<>();
		this.assumptions = this.loadAssumptions(assumptions);
	}

	@Override
	public void processOutput() {
		this.convertFinalAnsAsMsg();
		while (!InputHelper.isMessagesEmpty()) {
			GUIHelper.send(InputHelper.receive());

		}
		while (!InputHelperExt.isMessagesEmpty()) {
			GUIHelperExt.send(InputHelperExt.receive());
		}
	}

	@Override
	protected void convertFinalAnsAsMsg() {
		RealVector finalAnswer = this.calculateMatrixEqn();
		InputHelper.send("Final Answer: ");
		for (short i = 0; i < this.matrixSize; i++) {
			InputHelper.send(String.format("%.10f ", finalAnswer.getEntry(i)));
		}
	}

	
	private RealVector calculateMatrixEqn() {
		try {
			
			RealVector currentVectors = new ArrayRealVector(this.assumptions);
			
			if (this.calcHistory.isEmpty()) {
				this.calcHistory.push(currentVectors);
			}
			
			InputHelperExt.send(String.format("Threshold: E < %f", this.threshold));
			
			this.iterativeProcess(currentVectors);
			return this.calcHistory.pop();
		} catch (Exception e) {
			InputHelper.send(e.getMessage());
			return this.allUnknownsAreNaN();
		}
	}

	private void iterativeProcess(RealVector currentVectors) {
		short i = -1;
		boolean iterating = true;
		while (iterating) {
			currentVectors = this.calcHistory.peek();
			RealVector funcEval = new ArrayRealVector(
					this.functionEvaluation(currentVectors.getEntry(0), currentVectors.getEntry(1)));
			RealMatrix jacobian = new Array2DRowRealMatrix(
					this.partialDerivatives(currentVectors.getEntry(0), currentVectors.getEntry(1)));
			RealMatrix inverseJacobian = MatrixUtils.inverse(jacobian);
			RealVector ans = currentVectors.subtract(inverseJacobian.operate(funcEval));

			InputHelper.send(String.format("k[%d] ", i + 1));
			boolean wantsToHalt = this.isIdealErrorReached(ans, this.calcHistory.peek()) || i == (this.maxIteration - 1);
			this.sendIterativeValuesAsMsg(ans);
			if (wantsToHalt) {
				iterating = false;
			}

			this.calcHistory.push(ans);
			i++;
		}
	}

	private void sendIterativeValuesAsMsg(RealVector currentVectorValues) {
		for (short i = 0; i < this.matrixSize; i++) {
			InputHelper.send(String.format("%.10f ", currentVectorValues.getEntry(i)));
		}
		InputHelper.send("\n");
	}

	@Override
	public boolean isIdealErrorReached(RealVector current, RealVector previous) {
		HashSet<Boolean> signals = new HashSet<>(); 
		boolean isCloseEnough = false;
		boolean binaryFlag = false;
				
		for (int i = 0; i < this.matrixSize; i++) {
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
	
	private RealVector allUnknownsAreNaN() {
		double[] tempArr = new double[this.matrixSize];
		for (int i = 0; i < tempArr.length; i++) {
			tempArr[i] = Double.NaN;
		}
		return new ArrayRealVector(tempArr);
	}

	private double[][] partialDerivatives(double x, double y) {
		double[][] matrixDraft = new double[2][2];
		matrixDraft[0][0] = firstPartialDerX(this.functions.function1, x, y);
		matrixDraft[0][1] = firstPartialDerY(this.functions.function1, x, y);
		matrixDraft[1][0] = firstPartialDerX(this.functions.function2, x, y);
		matrixDraft[1][1] = firstPartialDerY(this.functions.function2, x, y);
		return matrixDraft;
	}

	private double[] loadAssumptions(String[] assumptions) {
		double[] vector = new double[this.matrixSize];
		for (int i = 0; i < assumptions.length; i++) {
			vector[i] = Parser.parseConstant(assumptions[i]);
		}
		return vector;
	}

	private double[] functionEvaluation(double x, double y) {
		double[] vector = new double[this.matrixSize];
		vector[0] = Parser.parseMultiVar(this.functions.function1, x, y);
		vector[1] = Parser.parseMultiVar(this.functions.function2, x, y);
		return vector;
	}
}
