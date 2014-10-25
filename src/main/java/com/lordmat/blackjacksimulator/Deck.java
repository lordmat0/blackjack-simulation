package com.lordmat.blackjacksimulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author mat
 */
public class Deck {

    private final Stack<Card> cards;
    private int numberOfDecks;

    public Deck() {
        this(5);
    }

    public Deck(int numberOfDecks) {
        cards = new Stack<>();
        this.numberOfDecks = numberOfDecks;

        cards.addAll(getNewDecks(numberOfDecks));

        shuffle();
    }

    private Stack<Card> getNewDecks(int numberOfDecks) {
        Stack<Card> newDeck = new Stack<>();

        numberOfDecks = numberOfDecks > 0 ? numberOfDecks : 1;

        for (int i = 0; i < numberOfDecks; i++) {
            for (Suit suit : Suit.values()) {
                for (Value value : Value.values()) {
                    newDeck.push(new Card(suit, value));
                }
            }
        }

        return newDeck;
    }

    public Card drawNextCard() {
        handleEmptyDeck();

        Card card = cards.pop();
        card.flipCard();

        return card;
    }

    public Card drawFaceDownCard() {
        handleEmptyDeck();
        return cards.pop();
    }

    private void handleEmptyDeck() {
        if (cards.isEmpty()) {
            cards.addAll(getNewDecks(numberOfDecks));
            shuffle();
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    @Override
    public String toString() {
        List<Card> remainingCards = new ArrayList<>(cards);

        return remainingCards.toString();
    }
}
