package ca.ucalgary.auction.agents;

import jade.core.Agent;
import ca.ucalgary.auction.behaviors.NotificationBehaviour;

public class NotificationAgent extends Agent {
    protected void setup() {
        addBehaviour(new NotificationBehaviour());
    }
}