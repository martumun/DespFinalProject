package minesweeper;

public class MineSweeperImp implements MineSweeper{
	
	Matrix matrix=new Matrix(4, 4);
	public void uncover(int row, int col) {
		matrix.uncover(row, col);
		matrix.Print();
	}

	public void flagAsMine(int row, int col) {
		matrix.Flag(row, col);	
		matrix.Print();
	}

	public void clearFlag(int row, int col) {
		Cell c=matrix.GetCell(row, col);
		c.setIsFlaged(false);
		matrix.Print();
		
	}
	
	public int GetRows(){
		return matrix.GetNumRows();
	}
	
	public int GetCol(){
		return matrix.GetNumCol();
	}
	public boolean isGameOver() {
		return matrix.GameOver();
	}

	public boolean isWinningGame() {
		return matrix.HasWin();
	}

	public void display() {
		matrix.Print();
	}

	public void displayInternal() {
		matrix.PrintInternal();
	}

	public void displayRaw() {
		matrix.PrintRaw();
		
	}

}
