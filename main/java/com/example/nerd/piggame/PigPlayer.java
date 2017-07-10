package com.example.nerd.piggame;

/**
 * Created by Nerdy on 7/3/2017.
 */

public class PigPlayer {
    private int totalScore = 0;                  //the players total score
    private int turnPoints = 0;                  //number of pts player has accumulated so far this turn
    private static int isTurn = 1;               //equals 1 when it's player ones turn, 2 when its player two's
    private static boolean isDouble = false;     //equals 1 when it's player ones turn, 2 when its player two's
    private String name = "";

    //declare and define he class mutators
    public void accumulate(int in){
        turnPoints = turnPoints + in;
    }

    public void setTotScore(int in){
        totalScore = in;
    }

    public void setTurnPoints(int in){
        turnPoints = in;
    }

    public static void setIsTurn(int in){
        isTurn = in;
    }

    public void setName(String in){ name = in; }

    //declare and define the class accessors
    public int getTotScore(){
        return totalScore;
    }

    public int getTurnPoints(){ return turnPoints; }

    public static int getIsTurn(){
        return isTurn;
    }

    public String getName(){
        return name;
    }
}