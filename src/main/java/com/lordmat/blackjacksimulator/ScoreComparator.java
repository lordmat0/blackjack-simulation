package com.lordmat.blackjacksimulator;

/**
 *
 * @author mat
 */
public class ScoreComparator {
    private final RoundHand dealerHand;
    
    public ScoreComparator(RoundHand dealerHand){
        this.dealerHand = dealerHand;
    }
    
    public ScoreOutcome getOutcome(RoundHand playerHand){
        if(dealerHand.isBlackJack() && playerHand.isBlackJack()){
            return ScoreOutcome.PUSH;
        }
        
        if(dealerHand.isBlackJack() && !playerHand.isBlackJack()){
            return ScoreOutcome.LOSE;
        }
        
        if(playerHand.isBlackJack()){
            return ScoreOutcome.BLACKJACK;
        }
        
        if(playerHand.isBust()){
            return ScoreOutcome.LOSE;
        }
        
        if(dealerHand.isBust()){
            return ScoreOutcome.WIN;
        }
        
        return dealerHand.getBestScore() < playerHand.getBestScore() ? ScoreOutcome.WIN : ScoreOutcome.LOSE;
    }
    
}
