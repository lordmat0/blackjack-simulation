package com.lordmat.blackjacksimulator;

import com.lordmat.blackjacksimulator.spending.Spending;
import com.lordmat.blackjacksimulator.statergy.Move;
import com.lordmat.blackjacksimulator.statergy.Strategy;

/**
 *
 * @author mat
 */
public class AI_Player implements Player {

    private static int idIndex = 1;
    private int id = idIndex++;
    
    private RoundHand roundHand;
    private final Strategy strategy;
    private final Spending spending;

    public AI_Player(Strategy strategy, Spending spending) {
        this.strategy = strategy;
        this.spending = spending;

    }

    @Override
    public Move getMove() {
        return strategy.nextMove(roundHand);
    }

    @Override
    public int betAmount() {
        int betAmount = spending.betAmount();
        
        if(betAmount > 0){
            roundHand.setBetAmount(betAmount);
        }
        
        return betAmount;
    }

    @Override
    public void changeAmount(int amount) {
        spending.changeAmount(amount);
    }

    @Override
    public void setRoundHand(RoundHand roundHand) {
        this.roundHand = roundHand;
    }
    
    @Override
    public RoundHand getHand(){
        return roundHand;
    }

    @Override
    public void drawCard(Card card) {
        roundHand.drawCard(card);
    }

    @Override
    public int getBestScore() {
        return roundHand.getBestScore();
    }

    @Override
    public int lastBestAmount() {
        return roundHand.getBetAmount();
    }

    @Override
    public String toString(){
        return "id: " +  id + " " + strategy.toString() + " " + spending.toString();
    }

}
