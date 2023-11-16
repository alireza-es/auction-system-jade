package ca.ucalgary.auction.ontology.concepts;

import jade.content.Concept;
import jade.core.AID;

public class Bid implements Concept {
    private AID bidder;
    private double price;

    public AID getBidder() {
        return bidder;
    }

    public void setBidder(AID bidder) {
        this.bidder = bidder;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}