package com.lordmat.blackjacksimulator;

import com.lordmat.blackjacksimulator.spending.DealerSpending;
import com.lordmat.blackjacksimulator.statergy.Strategy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Dealer {

    private Deck cardDeck;

    private Player dealerAI;

    private List<Player> players;

    private int numberOfRounds;

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

        details.append(cleanUpPlayers());

        if (hasPlayers()) {
            Round round = new Round(dealerAI, players, cardDeck);
            details.append(round.playRound());
            
            numberOfRounds++;
        } else {
            details.append("No more players");
        }
        return details.toString();
    }
}
