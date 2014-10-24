package com.lordmat.blackjacksimulator;

import com.lordmat.blackjacksimulator.statergy.Move;

/**
 *
 * @author mat
 */
public interface Player {
    
    void setRoundHand(RoundHand roundHand);
    void drawCard(Card card);
    Move getMove();
    int getBestScore();
    RoundHand getHand();
    
    int betAmount();
    int lastBestAmount();
    
    void changeAmount(int amount);
}
