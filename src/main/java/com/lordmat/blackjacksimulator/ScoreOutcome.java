package com.lordmat.blackjacksimulator;

/**
 *
 * @author mat
 */
public enum ScoreOutcome {
    // Lose your money
    LOSE(-1), 
    // Keep the same money
    PUSH(0), 
    // Double your money
    WIN(1), 
    // Payout of 2.5 so, £100 -> £250
    BLACKJACK(1.5);
    
    private final double wager;
    
    private ScoreOutcome(double wager){
        this.wager = wager;
    }
    
    public double getWager(){
        return wager;
    }
}
