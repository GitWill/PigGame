package com.example.nerd.piggame;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Nerdy on 7/3/2017.
 */

    //the PigGame class will handle the back end of the game
class PigGame extends AppCompatActivity {
    PigPlayer player1 = new PigPlayer();    //each game has two players
    PigPlayer player2 = new PigPlayer();
    Context context;                        //we need the context so we can toast the winner
    private int winCond;                    //this will store the selected win condition

    PigGame(Context a){                     //constructor takes in a context for toasting
        context = a;
    }

    public void setWinCond(int in){ winCond = in; }//sets our private var winCond

    public int getWinCond(){ return winCond;}      //accesses our privat var winCond

    public void endGame(){                          //this method handles the end of the game for the back end
        String playerName;                          //this will store the name of the winning playert
        if (player1.getTotScore() > player2.getTotScore()){         //if player 1 won
            if (player1.getName().equals("")){                      //get their name and toast them
                playerName = "Player 1";
            }else {
                playerName = player1.getName();
            }
            Toast.makeText(context, playerName + " won the game", Toast.LENGTH_LONG).show();
        }else if(player1.getTotScore() < player2.getTotScore()){    //else if player 2 won
            if (player2.getName().equals("")){                      //get their name and toast them
                playerName = "Player 2";
            }else {
                playerName = player2.getName();
            }
            Toast.makeText(context, playerName + " won the game", Toast.LENGTH_LONG).show();
        }else{                                                      //else its a tie
            if (player2.getName().equals("")){                      //get player 2's name so you
                playerName = "Player 2";                            //can shame them for quitting
            }else {                                                 //they win 5/6 times if they rolled
                playerName = player2.getName();
            }
            Toast.makeText(context, "Tie game... shame on "+ playerName, Toast.LENGTH_LONG).show();
        }
        clearAll();
    }

    public void clearAll(){         //resets the backend instance vars
        player1.setTotScore(0);
        player1.setTurnPoints(0);
        player2.setTotScore(0);
        player2.setTurnPoints(0);
        PigPlayer.setIsTurn(1);
    }

    public void endSingleTurn(){    //handles when a player ends their own turn
        player1.setTotScore(player1.getTotScore() + player1.getTurnPoints());   //if a player has accumulated pts,
        player2.setTotScore(player2.getTotScore() + player2.getTurnPoints());   //give them their points
        if (PigPlayer.getIsTurn() == 2 && (player2.getTotScore() >= winCond || player1.getTotScore() >= winCond)){  //if player 2 ends turn while either player has enough to win
            endGame();                                                          //then the game is over
        }else if (PigPlayer.getIsTurn() == 1){      //if it is player 1's turn
            player1.setTurnPoints(0);               //reset player 1's turn points
            PigPlayer.setIsTurn(2);                 //now it will be player 2's turn
        } else {                                    //else it is player 2's turn
            player2.setTurnPoints(0);               //reset player 2's turn points
            PigPlayer.setIsTurn(1);                 //now it will be player 1's turn
        }
    }

    public void handleRoll(int die){                //handle the back end actins for a roll of the die

        if(PigPlayer.getIsTurn() == 2 && die ==1 && player1.getTotScore() >= winCond)//if player 2 rolled a 1 while player 1 has 100+ points
            endGame();                              //then player one wins
        else if(die == 1) {                         //if the die is a 1
            if (PigPlayer.getIsTurn() == 1) {       //if it is player ones turn
                player1.setTurnPoints(0);           //reset turn total
                PigPlayer.setIsTurn(2);             //change to player twos turn
            } else {                                //if it is player twos turn
                player2.setTurnPoints(0);           //reset turn total
                PigPlayer.setIsTurn(1);             //change to player ones turn
            }
        }else{                                      //any other number rolled on the die
            if(PigPlayer.getIsTurn() == 1) {        //will be accumulated to the players turnPoints
                player1.accumulate(die);
            }else{
                player2.accumulate(die);
            }
        }
    }
}