package linearalgebra;

import userIO.OutputHelper;
import userIO.GUIHelper;
import userIO.InputHelper;

public class GaussianElimination implements OutputHelper, MatrixCellChecker {

	private final double[][] mat;
	private final double[] vector;
	private final int matSize;

	public GaussianElimination(double[][] matrix, int matSize) {
		this.matSize = matSize;
		this.mat = matrix;
		this.vector = new double[this.matSize];
	}

	@Override
	public void processOutput() {
		this.calculate();
		while (!InputHelper.isMessagesEmpty()) {
			GUIHelper.send(InputHelper.receive());
		}
	}

	private void sendIterationOutputToGUI() {
		for (int i = 0; i < this.matSize; i++, InputHelper.send("\n")) {
			for (int j = 0; j <= this.matSize; j++) {
				InputHelper.send(String.format("%." + this.determineCellWidth(this.mat) + "f ", this.mat[i][j]));
			}
		}
		InputHelper.send("\n");
	}

	private void calculate() {
		try {
			if (isUserNoInput(this.mat)) {
				throw new RuntimeException("Cells are not filled properly\n");
			}

			int isSingularSignal = this.forwardElimination();
			switch (isSingularSignal) {
			case -1:
				this.backSubstitution();
				break;
			default:
				throw new RuntimeException("Singular Matrix or May Infinitely Have Many Solutions\n");
			}

			this.helpDisplayFinalAns();
		} catch (Exception e) {
			InputHelper.send("[Error]" + e.getMessage());
		}
	}

	private void helpDisplayFinalAns() {
		InputHelper.send("\nThe values of the unknowns are:\n");
		for (int i = 0; i < vector.length; i++) {
			InputHelper.send(String.format("u[%d]: %.10f\n", i + 1, vector[i]));
		}
	}

	private double[] backSubstitution() {
		InputHelper.send("Backward Substitution:\n");
		for (int i = this.matSize - 1; i >= 0; i--) {
			this.vector[i] = this.mat[i][this.matSize];

			for (int j = i + 1; j < this.matSize; j++) {
				vector[i] -= this.mat[i][j] * vector[j];
			}

			InputHelper.send(String.format("u[%d] = %.10f / %.10f\n", i + 1, this.vector[i], this.mat[i][i]));
			this.vector[i] = vector[i] / this.mat[i][i];
		}
		return vector;
	}

	private void rowSwap(int i, int j) {
		for (int k = 0; k <= this.matSize; k++) {
			double temp = this.mat[i][k];
			mat[i][k] = mat[j][k];
			mat[j][k] = temp;
		}
	}

	private int forwardElimination() {
		InputHelper.send("Forward Elimination:\n");
		for (int k = 0; k < this.matSize - 1; k++) {
			InputHelper.send(String.format("Step %d:\n", k + 1));
			int maxIndex = k;
			int maxValue = (int) this.mat[maxIndex][k];

			for (int i = k + 1; i < this.matSize; i++) {
				if (Math.abs(this.mat[i][k]) > maxValue) {
					maxValue = (int) this.mat[i][k];
					maxIndex = i;
				}
			}

			if (this.mat[k][maxIndex] == 0) {
				return k;
			}

			if (maxIndex != k) {
				this.rowSwap(k, maxIndex);
			}

			for (int i = k + 1; i < this.matSize; i++) {
				double factor = this.mat[i][k] / this.mat[k][k];
				for (int j = k + 1; j <= this.matSize; j++) {
					this.mat[i][j] -= this.mat[k][j] * factor;
					this.mat[i][k] = 0;
				}
			}
			this.sendIterationOutputToGUI();
		}
		this.sendIterationOutputToGUI();
		return -1;
	}

}
