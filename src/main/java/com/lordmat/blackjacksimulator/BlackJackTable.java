package com.lordmat.blackjacksimulator;

import com.lordmat.blackjacksimulator.spending.DealerSpending;
import com.lordmat.blackjacksimulator.statergy.Strategy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BlackJackTable {

    private Deck cardDeck;

    private Player dealer;

    private List<Player> players;

    private int numberOfRounds;

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
        StringBuilder details = new StringBuilder();

        details.append(cleanUpPlayers());

        if (hasPlayers()) {
            Round round = new Round(dealer, players, cardDeck);
            details.append(round.playRound());

            numberOfRounds++;
        } else {
            details.append("No more players");
        }
        return details.toString();
    }
}
