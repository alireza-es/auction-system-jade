package ca.ucalgary.auction.ontology.predicates;

import jade.content.Predicate;
import ca.ucalgary.auction.ontology.concepts.Bid;

public class BidAccepted implements Predicate {
    public BidAccepted(Bid bid) {
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