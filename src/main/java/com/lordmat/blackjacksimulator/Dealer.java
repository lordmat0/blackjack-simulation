package com.lordmat.blackjacksimulator;

import com.lordmat.blackjacksimulator.spending.DealerSpending;
import com.lordmat.blackjacksimulator.statergy.Move;
import com.lordmat.blackjacksimulator.statergy.Strategy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Dealer {

    private Deck cardDeck;
    
    private Player dealerAI;

    private List<Player> players;

    private int numberOfRounds;

    private int roundPot;

    public Dealer(Strategy dealerStrategy) {
        this(dealerStrategy, new ArrayList<>(), new Deck());
    }

    public Dealer(Strategy dealerStrategy, List<Player> players) {
        this(dealerStrategy, players, new Deck());
    }

    public Dealer(Strategy dealerStrategy, List<Player> players, Deck cardDeck) {
        this.players = players;
        this.cardDeck = cardDeck;
        
        dealerAI = new AI_Player(dealerStrategy, new DealerSpending());
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
        return dealerAI.getTotalMoney();
    }

    public int getRoundCount() {
        return numberOfRounds;
    }

    public String playRound() {
        StringBuilder details = new StringBuilder();
        roundPot = 0;
        RoundHand dealerHand = new RoundHand();
        
        dealerAI.setRoundHand(dealerHand);

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
        
        dealerAI.drawCard(card1);
        dealerAI.drawCard(card2);
        
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

            details.append(player).append(" stands with: ").append(player.getHand().toString()).append("\n");
        }

        details.append("\n");
        return details.toString();
    }

    private String dealerPlays() {
        StringBuilder details = new StringBuilder();

        // Dealer always plays last
        while (dealerAI.getMove() == Move.HIT) {
            Card card = cardDeck.drawNextCard();

            dealerAI.drawCard(card);

            details.append(dealerAI).append(" draws ").append(card).append("\n");
        }

        details.append("Dealer stands");

        details.append("\n");
        return details.toString();
    }

    private String checkAllScores() {
        StringBuilder details = new StringBuilder();

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
        details.append("Income: ").append(getHouseIncome())
                .append(" roundPot: ").append(roundPot);
        
        
        details.append("\n");
        return details.toString();
    }
}
