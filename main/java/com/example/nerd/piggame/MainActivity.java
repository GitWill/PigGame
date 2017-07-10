package com.example.nerd.piggame;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //get references to the UI elements
    private EditText p1Name;        //display player ones name
    private EditText p2Name;        //display player twos name
    private TextView p1Score;       //display player ones score
    private TextView p2Score;       //display player twos score
    private TextView whoTurn;       //displays whose turn it is
    private TextView turnPts;       //displays accumulated points for one turn
    private ImageView dieImg;       //displays image for die
    private boolean blueDie;        //decides which dice to display
    private int myRandom;           //used for the die roll
    private SharedPreferences savedValues;
    PigGame myGame = new PigGame(this);

    public void newGame(View v) {
        myGame.clearAll();                                                  //reset the back end
        savedValues = PreferenceManager.getDefaultSharedPreferences(this);  //get the saved vals
        blueDie = savedValues.getBoolean("die_color_checkbox_key", false);  //get die color from saved vals
        myGame.setWinCond(Integer.parseInt(savedValues.getString("score_key", "100")));//set win condition from saved vals
        updateHandi();                                                      //see if there is a selected handicap
        updateUI();                                                         //reset front end
        dieImg.setImageResource(R.drawable.blank);                          //display blank die
    }

    public void endTurn(View v) {
        myGame.endSingleTurn();                     //call the back end method to end a single turn
        updateHandi();                              //in case the game ended
        updateUI();                                 //display the new scores etc
        dieImg.setImageResource(R.drawable.blank);  //display blank die
    }

    public void rollDie(View v) {
        Random rand = new Random();                                                     // generate a random for the die
        savedValues = PreferenceManager.getDefaultSharedPreferences(this);              //use the dice size setting to assign the random range
        int die_size = Integer.parseInt(savedValues.getString("die_size_key", "6"));
        myRandom = rand.nextInt(die_size);  // Gives n such that 0 <= n < 6
        myRandom = myRandom + 1;
        showDice(myRandom);                 //display the die
        myGame.handleRoll(myRandom);        //send the roll value to the back end
        updateHandi();                      //in case the game ended
        updateUI();                         //pudate UI
    }

    public void showDice(int n) {   //this method displays the dice according to the random generated in rollDie()
        if(blueDie){                //if the blue dice option is sel
            if (n == 1) {dieImg.setImageResource(R.drawable.blue1);}
            else if (n == 2)dieImg.setImageResource(R.drawable.blue2);//display die
            else if (n == 3)dieImg.setImageResource(R.drawable.blue3);//display die
            else if (n == 4)dieImg.setImageResource(R.drawable.blue4);//display die
            else if (n == 5)dieImg.setImageResource(R.drawable.blue5);//display die
            else if (n == 6)dieImg.setImageResource(R.drawable.blue6);//display die
            else if (n == 7)dieImg.setImageResource(R.drawable.blue7);//display die
            else if (n == 8)dieImg.setImageResource(R.drawable.blue8);//display die
            else if (n == 9)dieImg.setImageResource(R.drawable.blue9);//display die
        }else {
            if (n == 1) {dieImg.setImageResource(R.drawable.die1);}
            else if (n == 2)dieImg.setImageResource(R.drawable.die2);//display die
            else if (n == 3)dieImg.setImageResource(R.drawable.die3);//display die
            else if (n == 4)dieImg.setImageResource(R.drawable.die4);//display die
            else if (n == 5)dieImg.setImageResource(R.drawable.die5);//display die
            else if (n == 6)dieImg.setImageResource(R.drawable.die6);//display die
            else if (n == 7)dieImg.setImageResource(R.drawable.die7);//display die
            else if (n == 8)dieImg.setImageResource(R.drawable.die8);//display die
            else if (n == 9)dieImg.setImageResource(R.drawable.die9);//display die
        }
    }

    public void updateHandi(){                                                  //this method will set the
        savedValues = PreferenceManager.getDefaultSharedPreferences(this);      //get the saved values
        int handi = Integer.parseInt(savedValues.getString("handi_key", "0"));  //check the selected handicap
        if (myGame.player1.getTotScore() < handi) {                             //if player one has less than the handicap
            myGame.player1.setTotScore(handi);                                  //then set player ones score to the handicap
        }
    }

    public void updateUI() {                                        //this method updates the ui elements
        if (!(p1Name.getText().toString().equals(""))) {            //if player 1 has entered a name
            myGame.player1.setName(p1Name.getText().toString());    //then display it
        }
        if (!(p2Name.getText().toString().equals(""))) {            //if player 2 has entered a name
            myGame.player2.setName(p2Name.getText().toString());    //then display it
        }
        p1Name.setText(myGame.player1.getName());                       //update the player name elements of the ui
        p2Name.setText(myGame.player2.getName());
        p1Score.setText(String.valueOf(myGame.player1.getTotScore()));  //update the player score elemets of the ui
        p2Score.setText(String.valueOf(myGame.player2.getTotScore()));

        if (PigPlayer.getIsTurn() == 1) {                                       //if it is player 1's turn
            turnPts.setText(String.valueOf(myGame.player1.getTurnPoints()));    //show player 1's single turn accumulated points
            if (myGame.player1.getName().equals("")) {                          //if player one has not entered a name
                whoTurn.setText("Player 1, it is your turn");                   //show that it is player 1's turn on the ui
            } else {
                whoTurn.setText(myGame.player1.getName() + ", it's your turn");  //else display player 1's name
            }
        } else {                                                                 //else it is player 2's turn
            turnPts.setText(String.valueOf(myGame.player2.getTurnPoints()));     //show player 2's single turn accumulated points
            if (p2Name.getText().toString().equals("")) {                        //if player two has not entered a name
                whoTurn.setText("Player 2, it is your turn");                    //keep default
            } else {
                whoTurn.setText(myGame.player2.getName() + ", it's your turn");   //else display player 2's name
            }
        }
        showDice(myRandom);                                                       //display the dice
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        p1Name = (EditText) findViewById(R.id.textView3);   //define references to ui elements which we will change
        p2Name = (EditText) findViewById(R.id.textView4);
        p1Score = (TextView) findViewById(R.id.textView7);
        p2Score = (TextView) findViewById(R.id.textView8);
        whoTurn = (TextView) findViewById(R.id.textView9);
        turnPts = (TextView) findViewById(R.id.textView11);
        dieImg = (ImageView) findViewById(R.id.imageView);
        updateHandi();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = savedValues.edit();   //get the sharedpreferences editor
        editor.putString("p1n", myGame.player1.getName());      //use the editor to save our instance vars
        editor.putString("p2n", myGame.player2.getName());
        editor.putInt("p1s", myGame.player1.getTotScore());
        editor.putInt("p2s", myGame.player2.getTotScore());
        editor.putInt("ran", myRandom);
        editor.putInt("who", PigPlayer.getIsTurn());
        editor.putInt("w", myGame.getWinCond());
        editor.putBoolean("bd", blueDie);

        if(PigPlayer.getIsTurn() == 1) {//if it is player ones turn
            editor.putInt("turnPts", myGame.player1.getTurnPoints());
        } else {
            editor.putInt("turnPts", myGame.player2.getTurnPoints());
        }
        editor.commit();                                        //save our preferences
    }

    @Override
    public void onResume() {
        super.onResume();
        myGame.player1.setName(savedValues.getString("p1n",""));    //retrieve the instance variables
        myGame.player2.setName(savedValues.getString("p2n",""));    //from the SharedPreferences object
        myGame.player1.setTotScore(savedValues.getInt("p1s", 0));   //and set them as current instance vars
        myGame.player2.setTotScore(savedValues.getInt("p2s", 0));
        myRandom = savedValues.getInt("ran", 6);
        PigPlayer.setIsTurn(savedValues.getInt("who", 0));
        myGame.setWinCond(savedValues.getInt("w", 100));
        blueDie = savedValues.getBoolean("bd", false);
        showDice(myRandom);
        if(PigPlayer.getIsTurn() == 1){
            myGame.player1.setTurnPoints(savedValues.getInt("turnPts", 0));
        }else{
            myGame.player2.setTurnPoints(savedValues.getInt("turnPts", 0));
        }

        updateUI();                                                         //show loaded vars in ui
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {                   //decide what happens when the
        switch (item.getItemId()) {                                         //user selects a menu item
            case R.id.menu_settings:
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class)); //start the settings activity
                return true;
            case R.id.menu_about:
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {                         //this will be used to inflate
        getMenuInflater().inflate(R.menu.activity_main, menu);              //the menu so it is visiblwe in the UI
        return true;
    }
}