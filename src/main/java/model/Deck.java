package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    final int TOP = 0;
    char[] deckColors = {'H', 'C', 'S', 'D'};
    int[] cardNum = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
    ArrayList<Card> cards = new ArrayList<>();

    public Card getTopCard() {
        return cards.remove(TOP);
    }

    public void createShuffledDeck() {
        Random r = new Random();
        Card[] deckCards = new Card[(deckColors.length * cardNum.length)];

        int count = 0;
        for (char c : deckColors) {
            for (int s : cardNum) {
                deckCards[count++] = new Card(c, s);
            }
        }

        Card[] shuffledDeck = new Card[deckCards.length];
        count = 0;
        while (count < deckCards.length) {
            int randomNum = r.nextInt((deckCards.length));
            Card card = deckCards[randomNum];

            if (card != null) {
                shuffledDeck[count++] = card;
                deckCards[randomNum] = null;
            }
        }
        this.cards.clear();
        Collections.addAll(this.cards, shuffledDeck);
    }
}
