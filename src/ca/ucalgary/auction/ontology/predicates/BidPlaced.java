package ca.ucalgary.auction.ontology.predicates;

import jade.content.Predicate;
import ca.ucalgary.auction.ontology.concepts.Bid;

public class BidPlaced implements Predicate {
    public BidPlaced(Bid bid) {
        this.setBid(bid);
    }
    private Bid bid;

    public Bid getBid() {
        return bid;
    }

    public void setBid(Bid bid) {
        this.bid = bid;
    }
}