package com.lordmat.blackjacksimulator;

import com.lordmat.blackjacksimulator.statergy.Move;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author mat
 */
public class Round {

    private int roundPot;
    private final Player dealerAI;
    private final List<Player> players;
    private final Deck cardDeck;

    private StringBuilder details;

    public Round(Player dealerAI, List<Player> players, Deck cardDeck) {
        this.dealerAI = dealerAI;
        this.players = players;
        this.cardDeck = cardDeck;
    }

    public String playRound() {
        details = new StringBuilder();

        RoundHand dealerHand = new RoundHand();

        dealerAI.setRoundHand(dealerHand);

        // Set up round hands
        for (Player player : players) {
            RoundHand roundHand = new RoundHand();
            player.setRoundHand(roundHand);
        }

        takeBets();

        dealCards();
        playerPlays();
        checkAllScores();

        return details.toString();
    }

    private void takeBets() {
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
    }

    private void dealCards() {
        for (int i = 0; i < 2; i++) {
            boolean faceDown = (i % 2 == 1);
            for (Player player : players) {
                Card card = null;

                if (faceDown) {
                    card = cardDeck.drawFaceDownCard();
                } else {
                    card = cardDeck.drawNextCard();
                }

                player.drawCard(card);

                details.append(player).append(" drew ").append(card).append("\n");
            }
            details.append("\n");
        }

        // Dealer deals his cards
        Card card1 = cardDeck.drawNextCard();
        Card card2 = cardDeck.drawFaceDownCard();

        dealerAI.drawCard(card1);
        dealerAI.drawCard(card2);

        details.append("Dealer drew ").append(card1).append("\n");
        details.append("Dealer drew ").append(card2).append("\n");

        details.append("\n");
    }

    private void playerPlays() {

        for (Player player : players) {
            playerPlayRound(player);
        }

        //Now the dealer can play
        playerPlayRound(dealerAI);

        details.append("\n");
    }

    private void playerPlayRound(Player player) {
        while (player.getMove() == Move.HIT) {
            Card card = cardDeck.drawNextCard();

            player.drawCard(card);

            details.append(player).append(" draws ").append(card).append("\n");
        }

        details.append(player).append(" stands");

        details.append("\n");
    }

    private void checkAllScores() {
        int dealerScore = dealerAI.getBestScore();

        details.append(dealerAI).append(" score is ").append(dealerScore).append("\n");

        for (Player player : players) {

            details.append(player);

            int betAmount = player.lastBestAmount();

            ScoreComparator scoreComparator = new ScoreComparator(dealerAI.getHand());

            ScoreOutcome outcome = scoreComparator.getOutcome(player.getHand());

            // Sets correct wager, on loss betAmount is mutplied by a negitive number
            int winAmount = (int) (betAmount * outcome.getWager());
            //roundPot += winAmount;

            player.changeAmount(winAmount);

            switch (outcome) {
                case WIN:
                case BLACKJACK:
                case PUSH:
                    //TODO change to doubles or some other class for currency
                    details.append(" wins ").append(winAmount);

                    // Money is taken into the pot at the start
                    // need to pay back the orginal amount + the win amount
                    roundPot -= betAmount + winAmount;

                    if (outcome == ScoreOutcome.BLACKJACK) {
                        details.append(" by blackjack! ");
                    }

                    break;

                case LOSE:

                    details.append(" loses ").append(betAmount);

                    break;
            }

            details.append(" with a score of ")
                    .append(player.getBestScore()).append("\n");
        }

        dealerAI.changeAmount(roundPot);
        details.append("Income: ").append(dealerAI.getTotalMoney())
                .append(" roundPot: ").append(roundPot);

        details.append("\n");
    }
}
