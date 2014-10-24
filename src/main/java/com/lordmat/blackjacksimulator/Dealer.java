package com.lordmat.blackjacksimulator;

import com.lordmat.blackjacksimulator.statergy.Move;
import com.lordmat.blackjacksimulator.statergy.Strategy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Dealer {

    private Deck cardDeck;

    private RoundHand dealerHand;

    private List<Player> players;

    private int numberOfRounds;
    
    private int income;
    private int roundPot;

    private final Strategy dealerStrategy;

    public Dealer(Strategy dealerStrategy) {
        this(dealerStrategy, new ArrayList<>(), new Deck());
    }

    public Dealer(Strategy dealerStrategy, List<Player> players) {
        this(dealerStrategy, players, new Deck());
    }

    public Dealer(Strategy dealerStrategy, List<Player> players, Deck cardDeck) {
        this.dealerStrategy = dealerStrategy;
        this.players = players;
        this.cardDeck = cardDeck;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public boolean hasPlayers() {
        return !players.isEmpty();
    }

    public int getHouseIncome() {
        return income;
    }
    
    public int getRoundCount(){
        return numberOfRounds;
    }

    public String playRound() {
        StringBuilder details = new StringBuilder();
        roundPot = 0;
        dealerHand = new RoundHand();

        // Set up round hands
        for (Player player : players) {
            RoundHand roundHand = new RoundHand();
            player.setRoundHand(roundHand);
        }

        details.append(takeBets());

        if (hasPlayers()) {

            details.append(dealCards());
            details.append(playerPlays());
            details.append(dealerPlays());
            details.append(checkAllScores());
            numberOfRounds++;
        } else {
            details.append("No players left!");
        }

        return details.toString();
    }

    private String takeBets() {
        StringBuilder details = new StringBuilder();

        Iterator<Player> iterPlayer = players.iterator();

        while (iterPlayer.hasNext()) {
            Player player = iterPlayer.next();

            int betAmount = player.betAmount();
            if (betAmount != -1) {
                roundPot += betAmount;

                details.append(player);
                details.append(" bets ");
                details.append(betAmount);

            } else {
                // Can no longer play as they have no money
                details.append(player).append(" leaves the game");
                iterPlayer.remove();
            }

            details.append("\n");
        }
        details.append("\n");
        return details.toString();
    }

    private String dealCards() {
        StringBuilder details = new StringBuilder();

        for (int i = 0; i < 2; i++) {

            for (Player player : players) {
                Card card = cardDeck.drawNextCard();
                player.drawCard(card);

                details.append(player).append(" drew ").append(card).append("\n");
            }
            details.append("\n");
        }

        // Dealer deals his cards
        Card card1 = cardDeck.drawNextCard();
        Card card2 = cardDeck.drawNextCard();
        dealerHand.drawCard(card1);
        dealerHand.drawCard(card2);

        details.append("Dealer drew ").append(card1).append("\n");
        details.append("Dealer drew ").append(card2).append("\n");

        details.append("\n");
        return details.toString();
    }

    private String playerPlays() {
        StringBuilder details = new StringBuilder();

        for (Player player : players) {
            while (player.getMove() == Move.HIT) {
                Card card = cardDeck.drawNextCard();

                player.drawCard(card);

                details.append(player).append(" draws ").append(card).append("\n");
            }

            details.append(player).append(" stands\n");
        }

        details.append("\n");
        return details.toString();
    }

    private String dealerPlays() {
        StringBuilder details = new StringBuilder();

        // Dealer always plays last
        while (dealerStrategy.nextMove(dealerHand) == Move.HIT) {
            Card card = cardDeck.drawNextCard();

            dealerHand.drawCard(card);

            details.append("Dealer draws ").append(card).append("\n");
        }

        details.append("Dealer stands");

        details.append("\n");
        return details.toString();
    }

    private String checkAllScores() {
        StringBuilder details = new StringBuilder();

        int dealerScore = dealerHand.getBestScore();

        details.append("Dealer score is ").append(dealerScore).append("\n");

        for (Player player : players) {

            details.append(player);

            int betAmount = player.lastBestAmount();
            int playerBestScore = player.getBestScore();

            if (dealerScore < playerBestScore) {
                int winAmount = betAmount * 2;

                roundPot -= winAmount;

                player.winAmount(winAmount);

                details.append(" wins ").append(winAmount);
            } else {
                details.append(" loses ").append(betAmount);
            }
            details.append(" with a score of ").append(playerBestScore).append("\n");
        }

        income += roundPot;

        details.append("\n");
        return details.toString();
    }
}
