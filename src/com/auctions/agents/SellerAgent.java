package com.auctions.agents;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.core.AID;
import jade.core.behaviours.TickerBehaviour;
import java.util.Random;

public class SellerAgent extends Agent {
    private Random random = new Random();

    protected void setup() {
        System.out.println("Seller Agent " + getAID().getName() + " is ready.");

        // Schedule an auction initiation task at random intervals
        addBehaviour(new RandomAuctionInitiationBehavior());
    }

    private class RandomAuctionInitiationBehavior extends TickerBehaviour {
        public RandomAuctionInitiationBehavior() {
            super(SellerAgent.this, random.nextInt(20000) + 5000); // Random interval between 5 to 25 seconds
        }

        protected void onTick() {
            // Initiate a new auction with a random starting price
            int startingPrice = random.nextInt(1000) + 100; // Random starting price between 100 to 1100
            AID auctioneerAID = new AID("auctioneer", AID.ISLOCALNAME); // Example auctioneer agent name

            // Send a new auction initiation message to the auctioneer
            ACLMessage initiateAuctionMsg = new ACLMessage(ACLMessage.REQUEST);
            initiateAuctionMsg.setContent(String.valueOf(startingPrice));
            initiateAuctionMsg.addReceiver(auctioneerAID);
            send(initiateAuctionMsg);

            System.out.println("Seller Agent " + getAID().getName() + " initiated a new auction with starting price: $" + startingPrice);
        }
    }
}
