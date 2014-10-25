package com.lordmat.blackjacksimulator;

/**
 * Holds the suits
 *
 * @author mat
 */
public enum Suit {

    DIAMOND("Diamonds"), HEART("Hearts"), CLUB("Clubs"), SPADE("Spades");

    private final String name;

    private Suit(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;

    }
}
