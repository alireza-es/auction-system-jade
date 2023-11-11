package ca.ucalgary.auction.behaviors;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class NotificationBehaviour extends CyclicBehaviour {
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            System.out.println("Received a message: " + msg.getContent());
        } else {
            block();
        }
    }
}