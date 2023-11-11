package ca.ucalgary.auction.ontology.actions;

import jade.content.AgentAction;
import ca.ucalgary.auction.ontology.concepts.Item;

public class StartAuction implements AgentAction {
    public StartAuction(Item item) {
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