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

    /**
     * Returns value of card or zero if card is faced down
     *
     * @return
     */
    public int getValue() {
        if (faceDown) {
            return 0;
        }
        return value.getValue();
    }

    /**
     * Returns true is the card is face down otherwise false
     *
     * @return
     */
    public boolean isFaceDown() {
        return faceDown;
    }

    /**
     * Flips the card so it can be read
     */
    public void flipCard() {
        faceDown = false;
    }

    @Override
    public String toString() {
        if (faceDown) {
            return "face down";
        }
        return value.toString() + " of " + suit.toString();
    }
}
