package com.auctions.agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class NotificationAgent extends Agent {
    protected void setup() {
        System.out.println("Notification Agent " + getAID().getName() + " is ready.");

        // Add behavior for handling notifications
        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    System.out.println("Received notification: " + msg.getContent());
                } else {
                    block();
                }
            }
        });
    }
}

