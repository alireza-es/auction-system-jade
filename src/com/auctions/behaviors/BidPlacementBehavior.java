package com.auctions.behaviors;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.Map;
import jade.core.AID;

public class BidPlacementBehavior extends CyclicBehaviour {
    private Map<AID, Integer> bids;

    public BidPlacementBehavior(Map<AID, Integer> bids) {
        this.bids = bids;
    }

    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null && msg.getPerformative() == ACLMessage.PROPOSE) {
            int bidAmount = Integer.parseInt(msg.getContent());
            AID bidder = msg.getSender();

            // Validate the bid amount (implement your validation logic here)
            boolean isValidBid = validateBid(bidAmount);

            if (isValidBid) {
                bids.put(bidder, bidAmount);
                System.out.println("Received bid: $" + bidAmount + " from " + bidder.getName());
                // Send acknowledgment to the bidder
                ACLMessage reply = msg.createReply();
                reply.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                reply.setContent("Bid accepted: $" + bidAmount);
                myAgent.send(reply);
            } else {
                // Bid is invalid, send rejection to the bidder
                ACLMessage reply = msg.createReply();
                reply.setPerformative(ACLMessage.REJECT_PROPOSAL);
                reply.setContent("Invalid bid: $" + bidAmount);
                myAgent.send(reply);
            }
        } else {
            block();
        }
    }

    // Implement your bid validation logic here
    private boolean validateBid(int bidAmount) {
        // Add your validation criteria (e.g., minimum bid amount, allowed increments, etc.)
        // Return true if the bid is valid, false otherwise
        return bidAmount >= 0;  // For simplicity, considering non-negative bids as valid
    }
}
