package com.lordmat.blackjacksimulator.statergy;

import com.lordmat.blackjacksimulator.RoundHand;

/**
 * The dealer plays until 17 or higher
 *
 * @author mat
 */
public class DealerStrategy implements Strategy {

    @Override
    public Move nextMove(RoundHand roundHand) {

        if ((roundHand.hasTwoHands() && roundHand.getSecondHand() >= 17)) {
            return Move.STAND;
            
        } else if (roundHand.getFirstHand() >= 17) {
            return Move.STAND;
        }

        return Move.HIT;
    }

}
