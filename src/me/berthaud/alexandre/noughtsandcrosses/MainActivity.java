package me.berthaud.alexandre.noughtsandcrosses;

import java.util.ArrayList;
import me.berthaud.alexandre.noughtsandcrosses.models.Board;
import me.berthaud.alexandre.noughtsandcrosses.models.AI;
import me.berthaud.alexandre.noughtsandcrosses.util.Misc;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	Board board;
	ArrayList<Button> buttons = new ArrayList<Button>();
	private AI AI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        OnClickListener gameButtonsListener = new OnClickListener() {
        	public void onClick(View v) {
        		Button button = (Button) findViewById(v.getId());
        		if(button.getText().equals("") && !board.isGameover()) {
        			button.setText("X");
        			board.setBox(Integer.parseInt((String) button.getTag()), 'X');
        			if(handleAfterTurn(board)) {
        				CPUTurn(board);
        			}
        		} else if(board.isGameover()) {
        			Misc.displayToast(getApplicationContext(), "Game is over");
        		} else {
        			Misc.displayToast(getApplicationContext(), "You can't play there");
        		}
        		
        	}
        };
        
    	this.buttons.add((Button) findViewById(R.id.button1));
    	this.buttons.add((Button) findViewById(R.id.button2));
    	this.buttons.add((Button) findViewById(R.id.button3));
    	this.buttons.add((Button) findViewById(R.id.button4));
    	this.buttons.add((Button) findViewById(R.id.button5));
    	this.buttons.add((Button) findViewById(R.id.button6));
    	this.buttons.add((Button) findViewById(R.id.button7));
    	this.buttons.add((Button) findViewById(R.id.button8));
    	this.buttons.add((Button) findViewById(R.id.button9));
    	
    	for(Button btn : this.buttons) {
    		btn.setOnClickListener(gameButtonsListener);
    	}
    	
    	((Button) findViewById(R.id.newgame)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = getIntent();
				finish();
				startActivity(intent);
			}
		});
    	
    	this.board = new Board(this.buttons);
    	
    	this.AI = new AI(this.board);
    	
        if(Math.floor(Math.random() * 2) == 1) {
        	CPUTurn(board);
        	Misc.displayToast(getApplicationContext(), "CPU played first, your turn");
        } else {
        	Misc.displayToast(getApplicationContext(), "Your turn");
        }
    }
    
    public boolean handleAfterTurn(Board board) {
    	switch(board.getWinner()) {
			case ' ':
				return true;
			case 'X':
				Misc.displayToast(getApplicationContext(), "You win!");
				return false;
			case 'O':
				Misc.displayToast(getApplicationContext(), "CPU wins!");
				return false;
			case '#':
				Misc.displayToast(getApplicationContext(), "It's a draw :(");
				return false;
			default:
				Misc.displayToast(getApplicationContext(), "wut?");
				return true;
    	}
    }
    
    public void CPUTurn(Board board) {
    	int nextMove = this.AI.getNextMove();
    	this.board.setBox(nextMove, 'O');
    	this.buttons.get(nextMove).setText("O");
    	this.handleAfterTurn(board);
    }
}
