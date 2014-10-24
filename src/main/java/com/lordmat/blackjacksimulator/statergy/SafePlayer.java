package com.lordmat.blackjacksimulator.statergy;

import com.lordmat.blackjacksimulator.RoundHand;

/**
 *
 * @author mat
 */
public class SafePlayer implements Strategy{

    @Override
    public Move nextMove(RoundHand roundHand) {
        
        if(roundHand.hasTwoHands()){
            if(roundHand.getSecondHand() >= 18){
                return Move.STAND;
            }
        }
        
        if(roundHand.getFirstHand() <= 15){
            return Move.HIT;
        }
        
        
        return Move.STAND;
    }

    
    @Override
    public String toString(){
        return "Safe player";
    }

}
