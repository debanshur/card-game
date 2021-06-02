package model;

import java.util.Comparator;

public class Card {
    public static Comparator<Card> cardNumber = new Comparator<Card>() {
        public int compare(Card card1, Card card2) {
            return card2.getNumber() - card1.getNumber();
        }
    };
    char color;
    int number;

    public Card(char color, int number) {
        this.color = color;
        this.number = number;
    }

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    /*@Override
    public String toString() {
        return "Card{" +
                "color=" + color +
                ", number=" + number +
                '}';
    }*/

    @Override
    public String toString() {
        return "Card{" +
                "number=" + number +
                '}';
    }
}
