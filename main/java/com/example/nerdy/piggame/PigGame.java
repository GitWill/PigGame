package com.example.nerdy.piggame;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Nerdy on 7/3/2017.
 */

class PigGame {
    private static final String RPS_Game = "WillsFinalComment";
    PigPlayer player1 = new PigPlayer();
    PigPlayer player2 = new PigPlayer();
    Context context;

    PigGame(Context a){
        context = a;
    }

    public void endGame(){
        //Log.d(RPS_Game, "in endGame ");

        String playerName;
        if (player1.getTotScore() > player2.getTotScore()){         //if player 1 won
            if (player1.getName().equals("")){
                playerName = "Player 1";
            }else {
                playerName = player1.getName();
            }
            Toast.makeText(context, playerName + " won the game", Toast.LENGTH_LONG).show();
        }else if(player1.getTotScore() < player2.getTotScore()){    //else if player 2 won
            if (player2.getName().equals("")){
                playerName = "Player 2";
            }else {
                playerName = player2.getName();
            }
            Toast.makeText(context, playerName + " won the game", Toast.LENGTH_LONG).show();
        }else{                                                      //else its a tie
            if (player2.getName().equals("")){
                playerName = "Player 2";
            }else {
                playerName = player2.getName();
            }
            Toast.makeText(context, "Tie game... shame on "+ playerName, Toast.LENGTH_LONG).show();
        }
        clearAll();
    }

    public void clearAll(){
        player1.setTotScore(0);
        player1.setTurnPoints(0);
        player2.setTotScore(0);
        player2.setTurnPoints(0);
        PigPlayer.setIsTurn(1);
    }

    public void endSingleTurn(){
        //Log.d(RPS_Game, "in endSingleTurn ");
        player1.setTotScore(player1.getTotScore() + player1.getTurnPoints());
        player2.setTotScore(player2.getTotScore() + player2.getTurnPoints());
        if (PigPlayer.getIsTurn() == 2 && (player2.getTotScore() > 19 || player1.getTotScore() > 19)){
            endGame();
        }else if (PigPlayer.getIsTurn() == 1){      //if it is player 1's turn
            //player1.setTotScore(player1.getTurnPoints() + player1.getTotScore());//add player 1's turn points to their total
            player1.setTurnPoints(0);               //reset player 1's turn points
            PigPlayer.setIsTurn(2);                 //now it will be player 2's turn
        } else {                                    //else it is player 2's turn
            //player2.setTotScore(player2.getTurnPoints() + player2.getTotScore());//add player 2's turn points to their total
            player2.setTurnPoints(0);               //reset player 2's turn points
            PigPlayer.setIsTurn(1);                 //now it will be player 1's turn
        }
    }

    public void handleRoll(int die){
        if(PigPlayer.getIsTurn() == 1 && die == 1 && player2.getTotScore() > 19){
            endGame();
        }else if(PigPlayer.getIsTurn() == 2 && die ==1 && player1.getTotScore() > 19)//if player 2 rolled a 1 while player 1 has 100+ points
            endGame();                              //then player one wins
        else if(die == 1) {
            if(die == 1) {
                if (PigPlayer.getIsTurn() == 1) {       //if it is player ones turn
                    player1.setTurnPoints(0);         //reset turn total
                    PigPlayer.setIsTurn(2);           //change to player twos turn
                } else {                                 //if it is player twos turn
                    player2.setTurnPoints(0);         //reset turn total
                    PigPlayer.setIsTurn(1);           //change to player ones turn
                }
            }
        }else{                          //any other number just adds to the accumulated turn points
                if(PigPlayer.getIsTurn() == 1) {
                    player1.accumulate(die);
                }else{
                    player2.accumulate(die);
                }
            }
    }
}
