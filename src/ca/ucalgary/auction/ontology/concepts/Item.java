package ca.ucalgary.auction.ontology.concepts;

import jade.content.Concept;
import static java.lang.System.currentTimeMillis;

public class Item implements Concept {
    public Item() {
    }
    public Item(String name, double startingPrice) {
        this.setName(name);
        this.setStartingPrice(startingPrice);
        this.setId(currentTimeMillis());
    }
    private long id;
    private String name;
    private double startingPrice;

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(double startingPrice) {
        this.startingPrice = startingPrice;
    }
}