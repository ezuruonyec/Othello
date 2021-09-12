package com.atomicobject.othello;

import java.util.Arrays;
import java.util.ListIterator;
import java.util.Random;

public class AI {

	public AI() {
		GameState state = new GameState();
		state.setPlayer(1); //player number value
	}

//	int[][] computeValidMoves(int[][] board, int player) {
//		// do some stuff to figure out what moves you can play
//		// return that list of moves
//	}

	public int[] isMoveValid(int[][] gameBoard) {
		int flips = 0;
		//int rows = 3;
		//int cols = 3;
		//int value = gameBoard[rows][cols];
		boolean valid = false;
		Random rand = new Random();
		int ro = rand.nextInt(8);
		int co = rand.nextInt(8);

		while(valid == false) {
			int value = gameBoard[ro][co];

			while (value != 0) {
				ro = rand.nextInt(8);
				co = rand.nextInt(8);
				value = gameBoard[ro][co];
			}
			int newrow = ro;
			int newcol = co;

			/**
			 * checks rows below
			 * */
			if (newrow != 7 && gameBoard[newrow + 1][newcol] != 0
					&& gameBoard[newrow + 1][newcol] != 1) { // if value = 2
				while (newrow != 7 && gameBoard[newrow +1][newcol] == 2) {
					newrow++;
				}
				if (newrow != 7 && gameBoard[newrow + 1][newcol] == 1) {
					flips(ro, co, gameBoard, +1, 0);
					return new int[]{ro, co};
				}
			}
			newrow = ro;
			newcol = co;

			/**
			 * checks rows above
			 * */
			if (newrow != 0 && gameBoard[newrow - 1][newcol] != 0
					&& gameBoard[newrow - 1][newcol] != 1) { // if value = 2
				while (newrow != 0 && gameBoard[newrow -1][newcol] == 2) {
					newrow--;
				}
				if (newrow != 0 && gameBoard[newrow -1][newcol] == 1) {
					flips(ro, co, gameBoard, -1, 0);
					return new int[]{ro, co};
				}

			}
			newrow = ro;
			newcol = co;

			/**
			 * checks col to the right
			 * */
			if (newcol != 7 && gameBoard[newrow][newcol + 1] != 0
					&& gameBoard[newrow][newcol + 1] != 1) { // if value = 2
				while (newcol != 7 && gameBoard[newrow][newcol + 1] == 2) {
					newcol++;
				}
				if (newcol != 7 && gameBoard[newrow][newcol +1] == 1) {
					flips(ro, co, gameBoard, 0, +1);
					return new int[]{ro, co};
				}
			}
			newrow = ro;
			newcol = co;

			/**
			 * checks col to the left
			 * */
			if (newcol != 0 && gameBoard[newrow][newcol - 1] != 0
					&& gameBoard[newrow][newcol - 1] != 1) { // if value = 2
				while (newcol != 0 && gameBoard[newrow][newcol-1] == 2) {
					newcol--;
				}
				if (newcol != 0 && gameBoard[newrow][newcol-1] == 1) {
					flips(ro, co, gameBoard, 0, -1);
					return new int[]{ro, co};
				}
			}
			newrow = ro;
			newcol = co;

			/**
			 * checks col to the right, rows below
			 * */
			if (newcol != 7 && newrow != 7 && gameBoard[newrow + 1][newcol + 1] != 0
					&& gameBoard[newrow + 1][newcol + 1] != 1) { // if value = 2
				while (newcol != 7 && newrow != 7 && gameBoard[newrow + 1][newcol + 1] == 2 && newcol != 7) {
					newcol++;
					newrow++;
				}
				if (newcol != 7 && newrow != 7 && gameBoard[newrow +1][newcol +1] == 1) {
					flips(ro, co, gameBoard, +1, +1);
					return new int[]{ro, co};
				}
			}
			newrow = ro;
			newcol = co;

			/**
			 * checks col to the left, rows below
			 * */
			if (newcol != 0 && newrow != 7 && gameBoard[newrow + 1][newcol - 1] != 0
					&& gameBoard[newrow + 1][newcol - 1] != 1) { // if value = 2
				while (newcol != 0 && newrow != 7 && gameBoard[newrow + 1][newcol - 1] == 2) {
					newcol--;
					newrow++;
				}
				if (newcol != 0 && newrow != 7 && gameBoard[newrow +1][newcol -1] == 1) {
					flips(ro, co, gameBoard, +1, -1);
					return new int[]{ro, co};
				}
			}
			newrow = ro;
			newcol = co;
			/**
			 * checks col to the left, rows above
			 * */
			if (newcol != 0 && newrow != 0 && gameBoard[newrow - 1][newcol - 1] != 0
					&& gameBoard[newrow - 1][newcol - 1] != 1) { // if value = 2
				while (newcol != 0 && newrow != 0 && gameBoard[newrow - 1][newcol - 1] == 2) {
					newcol--;
					newrow--;
				}
				if (newcol != 0 && newrow != 0 && gameBoard[newrow -1][newcol -1] == 1) {
					flips(ro, co, gameBoard, -1, -1);
					return new int[]{ro, co};
				}
			}
			newrow = ro;
			newcol = co;
			/**
			 * checks col to the right, rows above
			 * */
			if (newcol != 7 && newrow != 0 && gameBoard[newrow - 1][newcol + 1] != 0
					&& gameBoard[newrow - 1][newcol + 1] != 1) { // if value = 2
				while (newcol != 7 && newrow != 0 && gameBoard[newrow - 1][newcol + 1] == 2) {
					newcol++;
					newrow--;
				}
				if (newcol != 7 && newrow != 0 && gameBoard[newrow -1][newcol +1] == 1) {
					flips(ro, co, gameBoard, -1, +1);
					return new int[]{ro, co};
				}
			}
			valid = false;
			ro = rand.nextInt(8);
			co = rand.nextInt(8);
		}
		return new int[]{ro, co};
	}


public void flips(int row, int col, int[][] gameBoard, int rd, int cd){
		gameBoard[row][col] = 1;
		row += rd;
		col += cd;
		while(gameBoard[row][col] != 1 && gameBoard[row][col] != 0 && row != 7
				&& col != 7 && row != 0 && col != 0) {
			gameBoard[row][col] = 1;
			row += rd;
			col += cd;
		}
}


	public int[] computeMove(GameState state) {
		System.out.println("AI returning canned move for game state - " + state);
		int[][] gameBoard = state.getBoard();
		return isMoveValid(gameBoard);
	}
}
