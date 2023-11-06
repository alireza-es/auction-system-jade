package com.auctions.behaviors;

import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.core.AID;
import jade.core.Agent;

import java.util.Map;

public class AuctionClosingBehavior extends OneShotBehaviour {
    private Map<AID, Integer> bids;
    private AID auctioneerAID;
    private Agent myAgent;

    public AuctionClosingBehavior(Map<AID, Integer> bids, AID auctioneerAID, Agent agent) {
        this.bids = bids;
        this.auctioneerAID = auctioneerAID;
        this.myAgent = agent;
    }

    public void action() {
        int highestBid = 0;
        AID winner = null;

        // Determine the winner based on the highest bid
        for (Map.Entry<AID, Integer> entry : bids.entrySet()) {
            if (entry.getValue() > highestBid) {
                highestBid = entry.getValue();
                winner = entry.getKey();
            }
        }

        // Notify the winner and the seller (assuming the seller's AID is known)
        if (winner != null) {
            // Notify the winner
            WinnerNotificationBehavior winnerNotificationBehavior = new WinnerNotificationBehavior(winner, highestBid);
            myAgent.addBehaviour(winnerNotificationBehavior);

            // Notify the seller (replace "sellerAID" with the actual seller's AID)
            AID sellerAID = new AID("seller", AID.ISLOCALNAME); // Example seller agent name
            ACLMessage sellerMsg = new ACLMessage(ACLMessage.INFORM);
            sellerMsg.addReceiver(sellerAID);
            sellerMsg.setContent("Your item has been sold for $" + highestBid);
            myAgent.send(sellerMsg);
        }

        // Notify other bidders about the result (similar logic as before)
        // ...

        // Inform the auctioneer about the completion of the auction
        ACLMessage completionMsg = new ACLMessage(ACLMessage.INFORM);
        completionMsg.addReceiver(auctioneerAID);
        completionMsg.setContent("Auction closed. Winner: " + (winner != null ? winner.getName() : "No winner"));
        myAgent.send(completionMsg);
    }
}
