package ca.ucalgary.auction.ontology.concepts;

import jade.content.Concept;
import jade.core.AID;

public class Bid implements Concept {
    private AID bidder;
    private int price;

    public AID getBidder() {
        return bidder;
    }

    public void setBidder(AID bidder) {
        this.bidder = bidder;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}