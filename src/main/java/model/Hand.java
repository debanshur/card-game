package model;

import java.util.ArrayList;
import java.util.Collections;

public class Hand {
    public Card highCard;
    ArrayList<Card> cards = new ArrayList<>();
    int handRank;

    public void addCard(Card card) {
        this.cards.add(card);
        Collections.sort(this.cards, Card.cardNumber);
    }

    public int getHandRank() {
        return handRank;
    }

    private int getCommonNum(int a, int b, int c) {
        return a == b ? a : c;
    }

    private int getTrialValue(int card1Num, int card2Num, int card3Num) {
        if (card1Num == card2Num && card2Num == card3Num) {
            return card1Num;
        }
        return 0;
    }

    private int getSequenceValue(int card1Num, int card2Num, int card3Num) {
        if (card1Num == 14) {
            card1Num = 1;
            if (card2Num - 1 == card3Num && card3Num - 1 == card1Num) {
                return card2Num;
            }
        } else {
            if (card1Num - 1 == card2Num && card2Num - 1 == card3Num) {
                return card1Num;
            }
        }
        return 0;
    }

    private int getPairValue(int card1Num, int card2Num, int card3Num) {
        if (card1Num == card2Num || card1Num == card3Num) {
            return card1Num;
        } else if (card2Num == card3Num) {
            return card2Num;
        }
        return 0;
    }

    public int calculateHandRank() {
        //Trial > Sequence > Pair > High Card
        this.highCard = cards.get(0);

        int card1Num = cards.get(0).getNumber();
        int card2Num = cards.get(1).getNumber();
        int card3Num = cards.get(2).getNumber();

        //Random prime numbers for weight
        int[] primeNum = {17, 1741, 174761};

        int trialWeight = getTrialValue(card1Num, card2Num, card3Num) * primeNum[2];
        int sequenceWeight = getSequenceValue(card1Num, card2Num, card3Num) * primeNum[1];
        int pairWeight = getPairValue(card1Num, card2Num, card3Num) * primeNum[0];
        this.handRank = trialWeight + sequenceWeight + pairWeight + this.highCard.getNumber();

        return this.handRank;
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + cards +
                ", handRank=" + handRank +
                '}';
    }
}
