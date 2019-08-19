package me.berthaud.alexandre.noughtsandcrosses.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.widget.Button;

public class Board {
	public char[][] board = new char[3][3];
	private boolean gameOver;
	public ArrayList<Button> buttons;
	
	public Board(ArrayList<Button> buttons) {
		for (int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				this.board[i][j] = ' ';
		this.gameOver = false;
		this.buttons = buttons;
	}
	
	public char getBox (int id) {
		return this.board[id / 3][id % 3];
	}
	
	public void setBox(int tag, char value) {
		this.board[tag / 3][tag % 3] = value;
	}
	
	public Button getButton(int x, int y) {
		return this.buttons.get(x * 3 + y);
	}
	
	public boolean isGameover() {
		return this.gameOver;
	}
	
	private char calcWinner() {
		if(this.board[0][0] != ' ' &&
		   this.board[0][0] == this.board[0][1] &&
		   this.board[0][0] == this.board[0][2]) {
			this.changeToWinColor(Arrays.asList(this.getButton(0, 0), this.getButton(0, 1), this.getButton(0, 2)));
			return this.board[0][0];
		}			
		if(this.board[1][0] != ' ' &&
		   this.board[1][0] == this.board[1][1] &&
		   this.board[1][0] == this.board[1][2]) {
			this.changeToWinColor(Arrays.asList(this.getButton(1, 0), this.getButton(1, 1), this.getButton(1, 2)));
			return this.board[1][0];
		}
		if(this.board[2][0] != ' ' &&
		   this.board[2][0] == this.board[2][1] &&
		   this.board[2][0] == this.board[2][2]) {
			this.changeToWinColor(Arrays.asList(this.getButton(2, 0), this.getButton(2, 1), this.getButton(2, 2)));
			return this.board[2][0];
		}
		if(this.board[0][0] != ' ' &&
		   this.board[0][0] == this.board[1][0] &&
		   this.board[0][0] == this.board[2][0]) {
			this.changeToWinColor(Arrays.asList(this.getButton(0, 0), this.getButton(1, 0), this.getButton(2, 0)));
			return this.board[0][0];
		}
		if(this.board[0][1] != ' ' &&
		   this.board[0][1] == this.board[1][1] &&
		   this.board[0][1] == this.board[2][1]) {
			this.changeToWinColor(Arrays.asList(this.getButton(0, 1), this.getButton(1, 1), this.getButton(2, 1)));
			return this.board[0][1];
		}
		if(this.board[0][2] != ' ' &&
		   this.board[0][2] == this.board[1][2] &&
		   this.board[0][2] == this.board[2][2]) {
			this.changeToWinColor(Arrays.asList(this.getButton(0, 2), this.getButton(1, 2), this.getButton(2, 2)));
			return this.board[0][2];
		}
		if(this.board[0][0] != ' ' &&
		   this.board[0][0] == this.board[1][1] &&
		   this.board[0][0] == this.board[2][2]) {
			this.changeToWinColor(Arrays.asList(this.getButton(0, 0), this.getButton(1, 1), this.getButton(2, 2)));
			return this.board[0][0];
		}
		if(this.board[0][2] != ' ' &&
		   this.board[0][2] == this.board[1][1] &&
		   this.board[0][2] == this.board[2][0]) {
			this.changeToWinColor(Arrays.asList(this.getButton(0, 2), this.getButton(1, 1), this.getButton(2, 0)));
			return this.board[0][2];
		}
		if(this.isFull()) {
			return '#';
		}
		return ' ';
	}
	
	public char getWinner() {
		char winner = this.calcWinner();
		if(winner != ' ') {
			this.gameOver = true;
		}
		return winner;
	}
	
	public void changeToWinColor(List<Button> buttons) {
		for(Button btn : buttons) {
			btn.setBackgroundColor(0xFF000000);
			btn.setTextColor(0xFFFFFFFF);
		}
	}
	
	public boolean isFull() {
		int count = 0;
		for(int i = 0; i < 9; i++) {
			if(this.getBox(i) != ' ') {
				count++;
			}
		}
		return count >= 9;
	}
}
