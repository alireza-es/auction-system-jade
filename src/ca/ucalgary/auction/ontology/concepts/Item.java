package ca.ucalgary.auction.ontology.concepts;

import jade.content.Concept;

public class Item implements Concept {
    private String name;
    private int startingPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(int startingPrice) {
        this.startingPrice = startingPrice;
    }
}