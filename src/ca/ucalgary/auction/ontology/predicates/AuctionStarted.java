package ca.ucalgary.auction.ontology.predicates;

import jade.content.Predicate;
import ca.ucalgary.auction.ontology.concepts.Item;

public class AuctionStarted implements Predicate {
    public AuctionStarted(Item item) {
        this.setItem(item);
    }
    private Item item;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}