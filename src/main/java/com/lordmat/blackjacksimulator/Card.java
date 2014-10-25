package com.lordmat.blackjacksimulator;

/**
 *
 * @author mat
 */
public class Card {

    private boolean faceDown;
    private final Suit suit;
    private final Value value;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;

        faceDown = true;
    }

    public int getValue() {
        if (faceDown) {
            return 0;
        }
        return value.getValue();
    }

    public boolean isFaceDown() {
        return faceDown;
    }

    public void flipCard() {
        faceDown = false;
    }

    @Override
    public String toString() {
        if(faceDown){
            return "face down";
        }
        
        return value.toString() + " of " + suit.toString();
    }
}
