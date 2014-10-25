package com.lordmat.blackjacksimulator;

/**
 *
 * @author mat
 */
public class FaceDownCard {
    private Card card;
    
    public FaceDownCard(Card card){
        this.card = card;
    }
    
    public Card flipCard(){
        return card;
    }
}
