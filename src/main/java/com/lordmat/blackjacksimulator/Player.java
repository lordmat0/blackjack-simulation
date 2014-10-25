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
    RoundHand getRoundHand();
    
    int betAmount();
    int lastBestAmount();
    int getTotalMoney();
    
    void changeAmount(int amount);

}
