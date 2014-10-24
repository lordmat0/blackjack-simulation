package com.lordmat.blackjacksimulator;

/**
 *
 * @author mat
 */
public class Card {

    private final Suit suit;
    private final int value;
    private final String name;

    public Card(Suit suit, int value, String name) {
        this.suit = suit;
        this.value = value;
        this.name = name;
    }

    public boolean isAce() {
        return value == 1;
    }
    
    public int getValue(){
        return value;
    }

    @Override
    public String toString() {
        return name + " of " + suit.toString();
    }
}
