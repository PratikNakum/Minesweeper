package com.tag.minesweeper;

public class GameBoard {

	Cell cell[][] = new Cell[Constants.ROWS][Constants.COLS];

	public Cell[][] getCell() {
		return cell;
	}

	void initGameBoard() {
		for (int i = 0; i < cell.length; i++) {
			for (int j = 0; j < cell[i].length; j++) {
				cell[i][j] = new Cell();
			}
		}
		initBombInGameBoard();
		initNumbersInGameBoard();
	}

	void initBombInGameBoard() {
		int randomNumber1 = 0, randomNumber2 = 0;
		for (int i = 0; i < (Constants.ROWS * Constants.COLS) / 4; i++) {
			randomNumber1 = Utility.randomNumber(Constants.ROWS);
			randomNumber2 = Utility.randomNumber(Constants.COLS);

			cell[randomNumber1][randomNumber2].setValue("B");
			cell[randomNumber1][randomNumber2].setBomb(true);
		}
	}

	void initNumbersInGameBoard() {
		int numberOfBomb = 0;
		String value;
		for (int i = 0; i < cell.length; i++) {
			for (int j = 0; j < cell[i].length; j++) {
				if (cell[i][j].isBomb() == false) {
					numberOfBomb = calculateBombInSurround(i, j);
					value = String.valueOf(numberOfBomb);
					cell[i][j].setValue(value);
				}
			}
		}
	}

	int calculateBombInSurround(int i, int j) {
		int numberOfBomb = 0;
		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				int rowNumber = i + x, colNumber = j + y;
				if (rowNumber > -1 && rowNumber < Constants.ROWS && colNumber > -1 && colNumber < Constants.COLS) {
					// it check rowNumber & colNumber is on GameBoard
					if (cell[rowNumber][colNumber].isBomb()) {
						numberOfBomb++;
					}
				}
			}
		}
		return numberOfBomb;
	}

	void printGameBoard() {
		System.out.print("    ");
		for (int i = 0; i < cell[0].length; i++) {
			System.out.print(i + "    ");
		}
		System.out.println();
		System.out.println();
		for (int i = 0; i < cell.length; i++) {
			System.out.print(i + " ");
			for (int j = 0; j < cell[i].length; j++) {
				if (cell[i][j].isOpen()) {
					if (cell[i][j].isFlag()) {
						System.out.print("  " + "F" + "  ");
					} else {
						System.out.print("  " + cell[i][j].getValue() + "  ");
					}
				} else {
					System.out.print("  " + "-" + "  ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}
