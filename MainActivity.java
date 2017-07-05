package com.example.nerdy.piggame;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private EditText p1Name;        //display player ones name
    private EditText p2Name;        //display player twos name
    private TextView p1Score;       //display player ones score
    private TextView p2Score;       //display player twos score
    private TextView whoTurn;       //displays whose turn it is
    private TextView turnPts;       //displays accumulated points for one turn
    private ImageView dieImg;       //displays image for die
    private static final String RPS_Game = "WillsFinalComment";
    //Context context = getApplicationContext();
    PigGame myGame = new PigGame(this);

    public void updateUI(){         //this function updates the ui elements
        //Log.d(RPS_Game, "here in updateUI in main");
        if (!(p1Name.getText().toString().equals(""))) {
            myGame.player1.setName(p1Name.getText().toString());
        }
        if (!(p2Name.getText().toString().equals(""))) {
            myGame.player2.setName(p2Name.getText().toString());
        }
        p1Name.setText(myGame.player1.getName());
        p2Name.setText(myGame.player2.getName());
        p1Score.setText(String.valueOf(myGame.player1.getTotScore()));
        p2Score.setText(String.valueOf(myGame.player2.getTotScore()));
        //Log.d(RPS_Game, "here in updateUI in main");

        if (PigPlayer.getIsTurn() == 1) {                               //if it is player 1's turn
            turnPts.setText(String.valueOf(myGame.player1.getTurnPoints()));            //show player 1's single turn accumulated points
            if (myGame.player1.getName().equals("")) {                  //if player one has not entered a name
                whoTurn.setText("Player 1, it is your turn");           //keep default
            }else{
                whoTurn.setText(myGame.player1.getName() + ", it's your turn");    //else display player 1's name
            }
        }else{                                                            //else it is player 2's turn
            //Log.d(RPS_Game, "it is player 2's turn");
            turnPts.setText(String.valueOf(myGame.player2.getTurnPoints()));              //show player 2's single turn accumulated points
            //Log.d(RPS_Game, "here in updateUI in main");

            if (p2Name.getText().toString().equals("")) {                 //if player two has not entered a name
                whoTurn.setText("Player 2, it is your turn");             //keep default
            }else{
                whoTurn.setText(myGame.player2.getName() + ", it's your turn");     //else display player 2's name
            }
        }

    }

    public void showDice(int n){
        //Log.d(RPS_Game, "at beginning of showDice in main");
        if(n == 1) {
            //Log.d(RPS_Game, "n == 1");
            dieImg.setImageResource(R.drawable.die1);//display die
            //Log.d(RPS_Game, "called setImage with 1");
        }
        else if(n == 2)
            dieImg.setImageResource(R.drawable.die2);//display die
        else if(n == 3)
            dieImg.setImageResource(R.drawable.die3);//display die
        else if(n == 4)
            dieImg.setImageResource(R.drawable.die4);//display die
        else if(n == 5)
            dieImg.setImageResource(R.drawable.die5);//display die
        else if(n == 6)
            dieImg.setImageResource(R.drawable.die6);//display die
    }

    public void newGame(View v){
        //Log.d(RPS_Game, "at beginning of newGame in main");
        myGame.clearAll();
        //myGame.player1.clearAll();                                      //clear all relevant player instance variables
        //myGame.player2.clearAll();
        updateUI();                                                     //clear UI
        dieImg.setImageResource(R.drawable.blank);//display blank die
        //Log.d(RPS_Game, "at end of newGame in main");
    }
    public void rollDie(View v){
        ///Log.d(RPS_Game, "at beginning of rollDie in main");
        Random rand = new Random();                 // generate a random 1-6 for the die
        int n = rand.nextInt(6);                    // Gives n such that 0 <= n < 6
        n = n + 1;
        /////////////////////////////////////////////////////////n = 1;
        showDice(n);

        myGame.handleRoll(n);
        //Log.d(RPS_Game, "returned from handleRoll");
        updateUI();
        //Log.d(RPS_Game, "returned from updateUI");

    }
    public void endTurn(View v){
        //Log.d(RPS_Game, "in endTurn");
        myGame.endSingleTurn();
        //Log.d(RPS_Game, "in endTurn returned from endSingleTurn");
        updateUI();
        dieImg.setImageResource(R.drawable.blank);//display blank die
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Log.d(RPS_Game, "in onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p1Name = (EditText) findViewById(R.id.textView3);
        p2Name = (EditText) findViewById(R.id.textView4);
        p1Score = (TextView) findViewById(R.id.textView7);
        p2Score = (TextView) findViewById(R.id.textView8);
        whoTurn = (TextView) findViewById(R.id.textView9);
        turnPts = (TextView) findViewById(R.id.textView11);
        dieImg = (ImageView) findViewById(R.id.imageView);
        //p1Name.setInputType(EditText.TYPE_CLASS_TEXT);
        //p2Name.setInputType(EditText.TYPE_CLASS_TEXT);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(RPS_Game, "in onPause");
    }
}
