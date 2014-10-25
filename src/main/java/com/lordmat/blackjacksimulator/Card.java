package com.lordmat.blackjacksimulator;

/**
 *
 * @author mat
 */
public class Card {

    private final Suit suit;
    private final Value value;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    public boolean isAce() {
        return value.isAce();
    }
    
    public int getValue(){
        return value.getValue();
    }

    @Override
    public String toString() {
        return value.toString() + " of " + suit.toString();
    }
}
