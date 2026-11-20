package linearalgebra;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import userIO.GUIHelper;
import userIO.InputHelper;
import userIO.OutputHelper;
import userIO.Parser;

public class GaussJordan implements MatrixCellChecker, OutputHelper {

	private final int matrixSize;
	private final double[][] left;
	private final double[] constants;
	private final double[][] AI;
	private final double[][] I;

	public GaussJordan(String[][] coefficients, String[] constants) {
		this.matrixSize = constants.length;
		this.left = this.loadCoefficients(coefficients);
		this.constants = this.loadConstants(constants);
		this.I = this.IdentityMatrix(this.matrixSize);
		this.AI = this.amalgamate(this.left, this.I);
	}

	// GUI communication system
	@Override
	public void processOutput() {
		this.finalAnsSendMessage(this.calculate());

		while (!InputHelper.isMessagesEmpty()) {
			GUIHelper.send(InputHelper.receive());
		}
	}

	private void sendAmalgamatedMatrix() {
		InputHelper.send("Augmented Matrix:\n\n");
		for (double[] row : AI) {
			String formattedRow = "";
			for (double val : row) {
				formattedRow += String.format("%" + this.determineCellWidth(AI) + "f ", val);
			}
			InputHelper.send(formattedRow + "\n");
		}
		InputHelper.send("\n");
	}

	private void sendInverseMatrix(RealMatrix inverse) {
		double precisionPointForZero = 1e-10;
		InputHelper.send("Inversed Matrix:\n\n");
		double[][] inverseDemonstration = this.amalgamate(inverse, this.IdentityMatrix(matrixSize));
		for (double[] AI1 : inverseDemonstration) {
			String formattedRow = "";
			for (double val : AI1) {
				if (Math.abs(val) < precisionPointForZero) {
					val = 0.0;
				}
				formattedRow += String.format("%.10f ", val);
			}
			InputHelper.send(formattedRow + "\n");
		}
		InputHelper.send("\n");
	}

	private void finalAnsSendMessage(double[] vector) {
		InputHelper.send("The value of the unknowns:\n");
		for (int i = 0; i < vector.length; i++) {
			InputHelper.send(String.format("x[%d]: %.10f\n", i + 1, vector[i]));
		}
	}

	// Matrix calculation
	private RealMatrix findInverse(RealMatrix A) {
		RealMatrix inverseA = MatrixUtils.inverse(A);
		this.sendInverseMatrix(inverseA);
		return inverseA;
	}

	private double[] calculate() {
		try {
			RealMatrix coeffMatrix = new Array2DRowRealMatrix(this.left);
			LUDecomposition detStarter = new LUDecomposition(coeffMatrix);
			double determinant = detStarter.getDeterminant();
			
			if (isUserNoInput(this.left)) {
				throw new RuntimeException("Cells are not filled properly\n");
			}
			
			if (determinant == 0.0) {
				throw new RuntimeException("Cannot Proceed: Matrix is singular\n");
			}

			this.sendAmalgamatedMatrix();
			RealMatrix A = new Array2DRowRealMatrix(this.left);
			RealVector x = MatrixUtils.createRealVector(this.constants);
			RealMatrix Ainv = this.findInverse(A);
			RealVector answer = Ainv.operate(x);
			return answer.toArray();
		} catch (RuntimeException e) {
			InputHelper.send("[Error] " + e.getMessage());
		}
		return this.arrayIsNaN();
	}

	private double[] arrayIsNaN() {
		double[] array = new double[this.matrixSize];
		for (int i = 0; i < array.length; i++) {
			array[i] = Double.NaN;
		}
		return array;
	}

	private double[][] amalgamate(double[][] left, double[][] I) {
		double[][] amalgamated = new double[this.matrixSize][this.matrixSize * 2];
		for (int i = 0; i < this.matrixSize; i++) {
			for (int j = 0; j < this.matrixSize * 2; j++) {
				amalgamated[i][j] = j < this.matrixSize ? left[i][j] : I[i][j - this.matrixSize];
			}
		}
		return amalgamated;
	}

	private double[][] amalgamate(RealMatrix left, double[][] I) {
		double[][] amalgamated = new double[this.matrixSize][this.matrixSize * 2];
		for (int i = 0; i < this.matrixSize; i++) {
			for (int j = 0; j < this.matrixSize * 2; j++) {
				amalgamated[i][j] = j < this.matrixSize ? I[i][j] : left.getEntry(i, j - this.matrixSize);
			}
		}
		return amalgamated;
	}

	private double[][] IdentityMatrix(int matDim) {
		double[][] iMat = new double[matDim][matDim];
		for (int i = 0; i < matDim; i++) {
			for (int j = 0; j < matDim; j++) {
				iMat[i][j] = i == j ? 1 : 0;
			}
		}
		return iMat;
	}

	private double[][] loadCoefficients(String[][] coefficients) {
		double[][] temp = new double[this.matrixSize][this.matrixSize];

		for (int i = 0; i < this.matrixSize; i++) {
			for (int j = 0; j < this.matrixSize; j++) {
				temp[i][j] = Parser.parseConstant(coefficients[i][j]);
			}
		}
		return temp;
	}

	private double[] loadConstants(String[] constants) {
		double[] temp = new double[this.matrixSize];

		for (int i = 0; i < this.matrixSize; i++) {
			temp[i] = Parser.parseConstant(constants[i]);
		}
		return temp;
	}
}
