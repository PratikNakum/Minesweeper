package com.tag.minesweeper;

public class GameManager {

	Player player = new Player();
	GameBoard gameBoard = new GameBoard();
	GameConditions gameCondition = new GameConditions(gameBoard);

	void startGame() {
		int rowNumber, colNumber, numberOfmoves = 0;
		char flagValue;
		Utility.print("Hello well-come !! please enter player name");
		player.setName(Utility.getString());
		gameBoard.initGameBoard();
		gameBoard.printGameBoard();

		Utility.print("please enter position(ROW & COL)");
		do {
			rowNumber = Utility.getInt("Enter row number :");
			colNumber = Utility.getInt("Enter column number :");
			flagValue = Utility.getChar("Do want to set/remove flag : Y or N ");

			if (flagValue == 'Y' || flagValue == 'y') {
				gameCondition.setFlagAtPosition(rowNumber, colNumber);
			} else {
				gameCondition.unfoldCell(rowNumber, colNumber);
			}
			gameBoard.printGameBoard();
			numberOfmoves++;
		} while (gameCondition.isBomb(rowNumber, colNumber) && gameCondition.isWinner());

		declareScore(numberOfmoves);
	}

	void declareScore(int numberOfmoves) {
		Utility.print(player.getName() + " you take a move : " + numberOfmoves);
	}
}