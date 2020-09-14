package com.tag.minesweeper;

public class GameConditions {

	Cell cell[][];

	GameConditions(GameBoard gameBoard) {
		cell = gameBoard.getCell();
	}

	// check is there bomb???
	boolean isBomb(int rowNumber, int colNumber) {
		if (cell[rowNumber][colNumber].isBomb() && !cell[rowNumber][colNumber].isFlag()) {
			if (cell[rowNumber][colNumber].isOpen()) {
				System.out.println("please try again !! it's bomb...");
				cell[rowNumber][colNumber].setOpen(true);
				return false;
			}
		}
		return true;
	}

	// open a cell
	public void unfoldCell(int rowNumber, int colNumber) {
		if (!cell[rowNumber][colNumber].isFlag()) // open if their is no flag
		{
			if (cell[rowNumber][colNumber].getValue().equals("0")) {
				findSurroundOpenSpace(rowNumber, colNumber);
			} else {
				cell[rowNumber][colNumber].setOpen(true);
			}
		}
	}

	// open all near by black spaces
	public void findSurroundOpenSpace(int rowNumber, int colNumber) {
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				int row = rowNumber + x, col = colNumber + y;
				if (row > -1 && row < Constants.ROWS && col > -1 && col < Constants.COLS) {
					if (cell[row][col].getValue().equals("0") && !cell[row][col].isOpen()) {
						cell[row][col].setOpen(true);

						// recursively called method to open all near by blank spaces
						findSurroundOpenSpace(row, col);
					} else if (!cell[row][col].getValue().equals("0")) {
						cell[row][col].setOpen(true);
					}
				}
			}
		}
	}

	// check all the place open notInclude bomb
	public boolean isWinner() {
		for (int i = 0; i < cell.length; i++) {
			for (int j = 0; j < cell[i].length; j++) {
				if (cell[i][j].isFlag() && !cell[i][j].isBomb())
					return true;
				if (!cell[i][j].isBomb() && !cell[i][j].isOpen())
					return true;
			}
		}
		System.out.println("yehheee !! Congratulations you win game");
		return false;
	}

	// set flag at entered number of row and column
	public void setFlagAtPosition(int rowNumber, int colNumber) {
		if (cell[rowNumber][colNumber].isFlag()) {
			cell[rowNumber][colNumber].setFlag(false);
			cell[rowNumber][colNumber].setOpen(false);

		} else if (!cell[rowNumber][colNumber].isOpen()) {
			cell[rowNumber][colNumber].setFlag(true);
			cell[rowNumber][colNumber].setOpen(true);
		} else {
			System.out.println("its Already open");
		}
	}
}
