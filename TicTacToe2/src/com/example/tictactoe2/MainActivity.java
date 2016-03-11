package com.example.tictactoe2;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	
	ImageView felt1, felt2, felt3, felt4, felt5, felt6, felt7, felt8, felt9;
	ImageView[] iArray;
	Button bNewGame;
	
	boolean gamefinish = false;
	
	boolean turn = true; // X = true; O = false
	int turn_count =0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//links up ImageViews to the layout file.
		felt1 = (ImageView) findViewById(R.id.felt1);
		felt2 = (ImageView) findViewById(R.id.felt2);
		felt3 = (ImageView) findViewById(R.id.felt3);
		felt4 = (ImageView) findViewById(R.id.felt4);
		felt5 = (ImageView) findViewById(R.id.felt5);
		felt6 = (ImageView) findViewById(R.id.felt6);
		felt7 = (ImageView) findViewById(R.id.felt7);
		felt8 = (ImageView) findViewById(R.id.felt8);
		felt9 = (ImageView) findViewById(R.id.felt9);
		
		bNewGame = (Button) findViewById(R.id.bNewGame);
	
		felt1.setTag(" ");
		felt2.setTag(" ");
		felt3.setTag(" ");
		felt4.setTag(" ");
		felt5.setTag(" ");
		felt6.setTag(" ");
		felt7.setTag(" ");
		felt8.setTag(" ");
		felt9.setTag(" ");
		
		//creating the array and put all the imageviews into it.
		iArray = new ImageView[]{felt1, felt2, felt3, felt4, felt5, felt6, felt7, felt8, felt9};
		
		//registering event listerners
		for(ImageView i : iArray){
			//for every imageview i in the array.
			//you set the click listerner to this
			
			i.setOnClickListener(this);
			//'this' makes this class as the handler for the event
			//so this class should implement an interface called 
			//OnClickListerner which contains the function OnClick
			//which is called when a registered imageview is clicked
		}
		
		//anonymous inner class concept
		bNewGame.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				//reset turn and turn_count
				//re enable all buttons
				
				turn = true;
				turn_count = 0;
				enabledisableAllImageViews(true);
				gamefinish = false;
			}	
		});
	}
	
	@Override
	public void onClick(View v) {
		//this method will be called on every click
		if (gamefinish){
			return; 
		}
		ImageView i = (ImageView) v;
		ImageViewClicked(i);
	}

	private void ImageViewClicked(ImageView i) {
		//variables to the game..
		//at the start turn is true. so game almays starts with X's turn.
		
		if(turn){
			// X's turn
			i.setImageResource(R.drawable.kryds);
			i.setTag("Kryds");
			
		}else{
			// O's turn7
			i.setImageResource(R.drawable.bolle);
			i.setTag("Bolle");
			
		}
		i.setClickable(false);
		turn = !turn;
		
		checkForWinner();
		
	}
	
	private void checkForWinner(){
		
		boolean there_is_a_winner = false;
		// isClickable is necessary for the game to be played, else the game is over after the first move.
		//check the horizontal
		if(felt1.getTag().toString() == felt2.getTag().toString() && felt2.getTag().toString()== felt3.getTag().toString() && !felt1.isClickable())
		there_is_a_winner = true;
		
		else if(felt4.getTag().toString() == felt5.getTag().toString() && felt5.getTag().toString()== felt6.getTag().toString() && !felt5.isClickable())
		there_is_a_winner = true;
		
		else if(felt7.getTag().toString() == felt8.getTag().toString() && felt8.getTag().toString()== felt9.getTag().toString() && !felt9.isClickable())
		there_is_a_winner = true;
		
		//check the verticals
		else if(felt1.getTag().toString() == felt4.getTag().toString() && felt4.getTag().toString()== felt7.getTag().toString() && !felt1.isClickable())
		there_is_a_winner = true;
			
		else if(felt2.getTag().toString() == felt5.getTag().toString() && felt5.getTag().toString()== felt8.getTag().toString() && !felt5.isClickable())
		there_is_a_winner = true;
				
		else if(felt3.getTag().toString() == felt6.getTag().toString() && felt6.getTag().toString()== felt9.getTag().toString() && !felt9.isClickable())
		there_is_a_winner = true;
			
		//check the diagonals
		else if(felt1.getTag().toString() == felt5.getTag().toString() && felt5.getTag().toString()== felt9.getTag().toString() && !felt1.isClickable())
		there_is_a_winner = true;
				
		else if(felt3.getTag().toString() == felt5.getTag().toString() && felt5.getTag().toString()== felt7.getTag().toString() && !felt5.isClickable())
		there_is_a_winner = true;
		
		
		if(there_is_a_winner){
			gamefinish = true;
			if(!turn){
				toast("X wins!!");
			}else{
				toast("O wins!!");
			}
		}	
	}
	
	private void enabledisableAllImageViews(boolean enable) {
		
		//false to disable
		for(ImageView i: iArray){
			i.setClickable(enable);
			
			if(enable){
				i.setImageResource(R.drawable.blanks);
			}
		}
	}

	private void toast(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
		
	}
}

