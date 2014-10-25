package com.lordmat.blackjacksimulator;

import com.lordmat.blackjacksimulator.spending.DealerSpending;
import com.lordmat.blackjacksimulator.statergy.CardObserver;
import com.lordmat.blackjacksimulator.statergy.DealerCardObserver;
import com.lordmat.blackjacksimulator.statergy.Move;
import com.lordmat.blackjacksimulator.statergy.Strategy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BlackJackTable {

    private Deck cardDeck;

    private Player dealer;

    private List<Player> players;

    private List<CardObserver> cardObservers;
    private List<DealerCardObserver> dealerCardObservers;

    private int numberOfRounds;

    private StringBuilder details;
    private int roundPot;

    public BlackJackTable(Strategy dealerStrategy) {
        this(dealerStrategy, new ArrayList<>(), new Deck());
    }

    public BlackJackTable(Strategy dealerStrategy, List<Player> players) {
        this(dealerStrategy, players, new Deck());
    }

    public BlackJackTable(Strategy dealerStrategy, List<Player> players, Deck cardDeck) {
        this.players = players;
        this.cardDeck = cardDeck;

        dealer = new AI_Player(dealerStrategy, new DealerSpending());

        cardObservers = new ArrayList<>();
        dealerCardObservers = new ArrayList<>();
    }

    /**
     * Register player
     *
     * @param player
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Remove Player
     *
     * @param player
     */
    public void removePlayer(Player player) {
        players.remove(player);
    }

    public void addCardObserver(CardObserver cardObserver) {
        cardObservers.add(cardObserver);
    }

    public void removeCardObserver(CardObserver cardObserver) {
        cardObservers.remove(cardObserver);
    }

    private void notifyCardObservers(int cardValue) {
        for (CardObserver cardObserver : cardObservers) {
            cardObserver.updateCard(cardValue);
        }
    }
    

    public void addDealerCardObserver(DealerCardObserver dealerCardObserver) {
        dealerCardObservers.add(dealerCardObserver);
    }

    public void removeDealerCardObserver(DealerCardObserver dealerCardObserver) {
        dealerCardObservers.remove(dealerCardObserver);
    }

    private void notifyDealerCardObservers(int cardValue) {
        for (DealerCardObserver cardObserver : dealerCardObservers) {
            cardObserver.updateDealerCard(cardValue);
        }
    }

    /**
     * Removes players that have no money
     *
     * @return
     */
    public String cleanUpPlayers() {
        StringBuilder sb = new StringBuilder();

        Iterator<Player> iterPlayer = players.iterator();

        while (iterPlayer.hasNext()) {
            Player player = iterPlayer.next();

            if (player.getTotalMoney() <= 0) {
                sb.append(player).append(" has no money and was kicked from the table\n");
                iterPlayer.remove();
            }
        }

        return sb.toString();
    }

    /**
     *
     * @return if there are players, may need to clean up first
     */
    public boolean hasPlayers() {
        return !players.isEmpty();
    }

    /**
     * Get income from this table
     *
     * @return
     */
    public int getHouseIncome() {
        return dealer.getTotalMoney();
    }

    /**
     * Get last rounds income, will return 0 if there has not been any rounds
     *
     * @return
     */
    public int getRoundCount() {
        return numberOfRounds;
    }

    /**
     * Play a round of blackjack
     *
     * @return
     */
    public String playRound() {
        details = new StringBuilder();
        roundPot = 0;

        details.append(cleanUpPlayers());

        if (hasPlayers()) {
            RoundHand dealerHand = new RoundHand();

            dealer.setRoundHand(dealerHand);

            // Set up round hands
            for (Player player : players) {
                RoundHand roundHand = new RoundHand();
                player.setRoundHand(roundHand);
            }

            takeBets();

            dealCards();
            playerPlays();
            checkAllScores();

            numberOfRounds++;
        } else {
            details.append("No more players");
        }
        return details.toString();
    }

    /**
     * Handles taking bets
     */
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

    /**
     * Handles dealing the cards. The dealer is given one face up and one face
     * down. Players are given two face up cards.
     */
    private void dealCards() {
        for (int i = 0; i < 2; i++) {

            for (Player player : players) {
                Card card = cardDeck.drawNextCard();

                player.drawCard(card);

                details.append(player).append(" drew ").append(card).append("\n");
                
                notifyCardObservers(card.getValue());
            }
            details.append("\n");
        }

        // Dealer deals his cards
        Card firstCard = cardDeck.drawNextCard();
        Card hiddenCard = cardDeck.drawFaceDownCard();

        dealer.drawCard(firstCard);
        dealer.drawCard(hiddenCard);
        
        notifyDealerCardObservers(firstCard.getValue());

        details.append("Dealer drew ").append(firstCard).append("\n");
        details.append("Dealer drew ").append(hiddenCard).append("\n");

        details.append("\n");
    }

    /**
     * Loops through letting the players play
     */
    private void playerPlays() {

        for (Player player : players) {
            playerPlayRound(player);
        }

        Card card = dealer.getRoundHand().flipCard();
        
        details.append("\n").append(dealer).append(" flipped over ").append(card).append("\n");
        notifyCardObservers(card.getValue());
        
        //Now the dealer can play
        playerPlayRound(dealer);

        details.append("\n");
    }

    /**
     * Lets an individual player play
     *
     * @param player The player to play
     */
    private void playerPlayRound(Player player) {
        while (player.getMove() == Move.HIT) {
            Card card = cardDeck.drawNextCard();

            player.drawCard(card);
            
            notifyCardObservers(card.getValue());

            details.append(player).append(" draws ").append(card).append("\n");
        }

        details.append(player).append(" stands");

        details.append("\n");
    }

    /**
     * Checks all scores and payouts if necessary
     */
    private void checkAllScores() {
        int dealerScore = dealer.getBestScore();

        details.append(dealer).append(" score is ").append(dealerScore).append("\n");

        for (Player player : players) {

            details.append(player);

            int betAmount = player.lastBestAmount();

            ScoreComparator scoreComparator = new ScoreComparator(dealer.getRoundHand());

            ScoreOutcome outcome = scoreComparator.getOutcome(player.getRoundHand());

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

        dealer.changeAmount(roundPot);
        details.append("Income: ").append(dealer.getTotalMoney())
                .append(" roundPot: ").append(roundPot);

        details.append("\n");
    }
}
