blackjack-simulation
====================

Java simulation of a blackjack game to test different strategies

Still a work in progress, expect massive changes to class structure.

You can implement your own strategy and spending by implementing the interfaces provided and see how long your bot can last

The interfaces to implement are Strategy and Spending which must be passed in to an AI_Player object. Check the Simulation class on how to do this.


TODO
========
How new decks being introduced (currently when cards run out it will just add 5 more decks in)

Add more Spending behaviours
* Over confident
* Small bets, increasing when on a winning streak

Add more Strategy behaviours
* Card counting (I think all the interfaces are there)
* Betting big amounts

Add joint Spending + Strategy behaviours (playing safe when you have less money)

Simulation
* Multi-threading
* Capture what bot lasted the longest


Implement all of blackjack's player options:
* split
* surrender
* double
