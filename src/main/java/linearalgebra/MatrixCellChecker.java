package linearalgebra;

interface MatrixCellChecker {
	default boolean isUserNoInput(double[][] matrix) {
		for (double[] rows : matrix) {
			for (double values : rows) {
				if (Double.isNaN(values)) {
					return true;
				}
			}
		}
		return false;
	}
	
	default int determineCellWidth(double[][] matrix) {
		int maxElementWidth = 0;
		for (double[] row : matrix) {
			for (double val : row) {
				maxElementWidth = Math.max(maxElementWidth, String.format("%f", Math.abs(val)).length()); // width
			}
		}
		return maxElementWidth;
	}
}
