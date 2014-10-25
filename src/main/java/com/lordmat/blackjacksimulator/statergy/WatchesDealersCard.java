/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lordmat.blackjacksimulator.statergy;

import com.lordmat.blackjacksimulator.RoundHand;

/**
 *
 * @author mat
 */
public class WatchesDealersCard implements Strategy, DealerCardObserver{

    private int dealerCard;
    
    @Override
    public Move nextMove(RoundHand roundHand) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateDealerCard(int cardValue) {
        dealerCard = cardValue;
        
        System.out.println("It works! dealer card is: " + dealerCard);
    }
    
    @Override
    public String toString(){
        return "WatchesDealersCard";
    }
}
