package com.lordmat.blackjacksimulator.statergy;

import com.lordmat.blackjacksimulator.RoundHand;

/**
 * Strategy taken from
 * http://www.gamblingsitesonline.org/images/blackjack-strategy-table.jpg
 *
 * @author mat
 */
public class WatchesDealersCard implements Strategy, DealerCardObserver {

    private int dealerUpCard;

    @Override
    public Move nextMove(RoundHand roundHand) {
        int softHand = roundHand.getFirstHand();

        if (roundHand.hasTwoHands()) {
            // Have ace

            if (softHand <= 6) {
                return Move.HIT;
            } else if (softHand >= 9 && (dealerUpCard >= 9 || dealerUpCard == 1)) {
                return Move.HIT;
            }

        } else {
            if (softHand <= 11) {
                return Move.HIT;
            } else if (softHand == 12 && (dealerUpCard == 2 || dealerUpCard == 3)) {
                return Move.HIT;
            } else if ((softHand >= 12 && softHand <= 16)
                    && (dealerUpCard == 1 || (dealerUpCard >= 7 && dealerUpCard <= 10))) {
                return Move.HIT;
            }
        }

        return Move.STAND;
    }

    @Override
    public void updateDealerCard(int cardValue) {
        dealerUpCard = cardValue;
    }

    @Override
    public String toString() {
        return "WatchesDealersCard";
    }
}
