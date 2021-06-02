import model.Card;
import model.Deck;
import model.Player;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

import static constant.Constants.*;

public class CardGameApplication {
    Deck deck;
    Player[] players;

    CardGameApplication() {
        deck = new Deck();
        players = new Player[NUM_OF_PLAYERS];
    }

    private void makePlayersReady() {
        for (int i = 0; i < NUM_OF_PLAYERS; i++) {
            players[i] = new Player("Player " + (i + 1));
        }

        //Distribute cards to each players
        for (int i = 0; i < NUM_OF_CARDS; i++) {
            for (int j = 0; j < NUM_OF_PLAYERS; j++) {
                players[j].getHand().addCard(deck.getTopCard());
            }
        }
    }

    private void calculatePlayerHandRank() {
        for (int i = 0; i < NUM_OF_PLAYERS; i++) {
            players[i].getHand().calculateHandRank();
            System.out.println(players[i]);
        }
    }

    private ArrayList<Player> findPotentialWinners() {
        ArrayList<Player> contenders = new ArrayList<>();
        contenders.add(players[0]);
        int maxRank = players[0].getHand().getHandRank();
        for (int i = 1; i < NUM_OF_PLAYERS; i++) {
            if (players[i].getHand().getHandRank() > maxRank) {
                contenders.clear();
                contenders.add(players[i]);
                maxRank = players[i].getHand().getHandRank();
            } else if (players[i].getHand().getHandRank() == maxRank) {
                contenders.add(players[i]);
            }
        }
        return contenders;
    }

    Player resolveTie(ArrayList<Player> contenders) {
        while (contenders.size() != 1) {
            System.out.println("\nContenders : ");
            for (Player contender : contenders) {
                Card newCard = deck.getTopCard();
                contender.getHand().highCard = newCard;
                System.out.println(contender + "  => " + newCard.getNumber());
            }
            int highCard = contenders.get(0).getHand().highCard.getNumber();
            ArrayList<Player> newContenders = new ArrayList<>();
            newContenders.add(contenders.get(0));
            for (int i = 1; i < contenders.size(); i++) {
                if (contenders.get(i).getHand().highCard.getNumber() > highCard) {
                    newContenders.clear();
                    newContenders.add(contenders.get(i));
                    highCard = contenders.get(i).getHand().highCard.getNumber();
                } else if (contenders.get(i).getHand().highCard.getNumber() == highCard) {
                    newContenders.add(contenders.get(i));
                }
            }

            contenders = newContenders;
        }
        return contenders.get(0);
    }

    public void startGame() {
        System.out.println("Reading Properties.");
        System.out.println("Starting Game for " + NUM_OF_PLAYERS);

        int N = NUM_OF_GAMES;
        while (NUM_OF_GAMES > 0) {
            System.out.println("\n----------------------------------");
            System.out.println("Game : " + (N - NUM_OF_GAMES + 1));
            deck.createShuffledDeck();

            makePlayersReady();
            calculatePlayerHandRank();

            ArrayList<Player> potentialWinners = findPotentialWinners();

            Player winner = resolveTie(potentialWinners);
            System.out.println("\nWinner : " + winner);
            NUM_OF_GAMES--;
        }

    }
}
