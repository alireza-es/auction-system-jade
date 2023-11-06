package com.auctions.agents;

import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import com.auctions.behaviors.BidPlacementBehavior;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BidderAgent extends Agent {
    private Map<AID, Integer> bids = new HashMap<>();
    private Random random;

    protected void setup() {
        System.out.println("Bidder Agent " + getAID().getName() + " is ready.");

        // Add BidPlacementBehavior to handle bid placement requests
        addBehaviour(new BidPlacementBehavior(bids));

        // Schedule a bidding task at random intervals
        addBehaviour(new RandomBiddingBehavior());
    }

    private class RandomBiddingBehavior extends TickerBehaviour {
        public RandomBiddingBehavior() {
            super(BidderAgent.this, random.nextInt(10000) + 1000); // Random interval between 1 to 10 seconds
        }

        protected void onTick() {
            int bidAmount = random.nextInt(500) + 1; // Random bid amount between 1 to 500
            AID auctioneerAID = new AID("auctioneer", AID.ISLOCALNAME); // Example auctioneer agent name

            // Send a random bid to the auctioneer
            ACLMessage bidMsg = new ACLMessage(ACLMessage.PROPOSE);
            bidMsg.setContent(String.valueOf(bidAmount));
            bidMsg.addReceiver(auctioneerAID);
            send(bidMsg);

            System.out.println("Bidder Agent " + getAID().getName() + " placed a bid: $" + bidAmount);
        }
    }
}
