package minesweeper;

public class Matrix {
	private int nrows;
    private int ncols;

    public Matrix(int rows,int cols) {
        this.nrows = rows;
        this.ncols = cols;
    }

    public Matrix(int nrow, int ncol) {
        this.nrows = nrow;
        this.ncols = ncol;
        data = new double[nrow][ncol];
    }
}
