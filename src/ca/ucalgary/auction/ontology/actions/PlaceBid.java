package ca.ucalgary.auction.ontology.actions;

import jade.content.AgentAction;
import ca.ucalgary.auction.ontology.concepts.Bid;

public class PlaceBid implements AgentAction {
    public PlaceBid() {
    }
    public PlaceBid(Bid bid) {
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