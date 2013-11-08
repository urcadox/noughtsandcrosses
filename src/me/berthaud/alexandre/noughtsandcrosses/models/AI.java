package me.berthaud.alexandre.noughtsandcrosses.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AI {
	Board board;
	
	public AI(Board board) {
		this.board = board;
	}
	
	public int getNextMove() {
		int winBox = this.getWinBoxId();
		if(winBox != -1)
			return winBox;
		int blockOtherBox = this.getBlockOtherBoxId();
		if(blockOtherBox != -1)
			return blockOtherBox;
		int centerBox = this.getCenterBoxId();
		if(centerBox != -1)
			return centerBox;
		int oppositeCornerBox = this.getOppositeCornerBoxId();
		if(oppositeCornerBox != -1)
			return oppositeCornerBox;
		int cornerBox = this.getCornerBoxId();
		if(cornerBox != -1)
			return cornerBox;
		return this.getRandomBoxId();
	}
	
	private int getWinBoxOfPlayerId(char p) {
		int count, lastEmpty;
		
		for(int i = 0; i < 3; i++) {
			count = 0;
			lastEmpty = -1;
			for(int j = 0; j < 3; j++) {
				int current = this.board.board[i][j];
				if(current == ' ')
					lastEmpty = i * 3 + j;
				else if(current == p)
					count++;
			}
			if(count == 2 && lastEmpty != -1)
				return lastEmpty;
		}
		
		for(int j = 0; j < 3; j++) {
			count = 0;
			lastEmpty = -1;
			for(int i = 0; i < 3; i++) {
				int current = this.board.board[i][j];
				if(current == ' ')
					lastEmpty = i * 3 + j;
				else if(current == p)
					count++;
			}
			if(count == 2 && lastEmpty != -1)
				return lastEmpty;
		}
		
		count = 0;
		lastEmpty = -1;
		for(int i = 0; i < 3; i++) {
			int current = this.board.board[i][i];
			if(current == ' ')
				lastEmpty = i * 3 + i;
			else if(current == p)
				count++;
		}
		if(count == 2 && lastEmpty != -1)
			return lastEmpty;
		
		count = 0;
		lastEmpty = -1;
		for(int i = 0; i < 3; i++) {
			int current = this.board.board[2 - i][i];
			if(current == ' ')
				lastEmpty = (2 - i) * 3 + i;
			else if(current == p)
				count++;
		}
		if(count == 2 && lastEmpty != -1)
			return lastEmpty;
		
		return -1;
	}
	
	private int getWinBoxId() {
		return this.getWinBoxOfPlayerId('O');
	}
	
	private int getBlockOtherBoxId() {
		return this.getWinBoxOfPlayerId('X');
	}
	
	private int getCenterBoxId() {
		if(this.board.getBox(4) == ' ')
			return 4;
		else
			return -1;
	}
	
	private int getOppositeCornerBoxId() {
		if(this.board.getBox(0) == 'X' && this.board.getBox(8) == ' ')
			return 8;
		if(this.board.getBox(2) == 'X' && this.board.getBox(6) == ' ')
			return 6;
		if(this.board.getBox(6) == 'X' && this.board.getBox(2) == ' ')
			return 2;
		if(this.board.getBox(8) == 'X' && this.board.getBox(0) == ' ')
			return 0;
		return -1;
	}
	
	private int getCornerBoxId() {
		List<Integer> corners = Arrays.asList(0, 2, 6, 8);
		ArrayList<Integer> availableCorners = new ArrayList<Integer>();
		for(Integer c : corners) {
			if(this.board.getBox(c) == ' ')
				availableCorners.add(c);
		}
		if(availableCorners.size() != 0)
			return availableCorners.get((int) (Math.random() * (availableCorners.size() - 1)));
		else
			return -1;
	}
	
	private int getRandomBoxId() {
		int box = -1;
    	while(box == -1) {
    		int id = (int) (Math.random() * 8);
    		if(this.board.getBox(id) == ' ') {
    			box = id;
    		}
    	}
    	return box;
	}
}
