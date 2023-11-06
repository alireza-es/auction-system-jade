package com.auctions.behaviors;

import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.core.AID;

public class WinnerNotificationBehavior extends OneShotBehaviour {
    private AID winnerAID;
    private int highestBid;

    public WinnerNotificationBehavior(AID winnerAID, int highestBid) {
        this.winnerAID = winnerAID;
        this.highestBid = highestBid;
    }

    public void action() {
        // Send a notification to the winner
        ACLMessage winnerMsg = new ACLMessage(ACLMessage.INFORM);
        winnerMsg.addReceiver(winnerAID);
        winnerMsg.setContent("Congratulations! You have won the auction with a bid of $" + highestBid);
        myAgent.send(winnerMsg);

        // Send a notification to the seller (implement sellerAID logic)
        AID sellerAID = new AID("seller", AID.ISLOCALNAME); // Example seller agent name
        ACLMessage sellerMsg = new ACLMessage(ACLMessage.INFORM);
        sellerMsg.addReceiver(sellerAID);
        sellerMsg.setContent("Your item has been sold for $" + highestBid);
        myAgent.send(sellerMsg);
    }
}
