package com.auctions.agents;

import com.auctions.behaviors.AuctionClosingBehavior;
import com.auctions.behaviors.BidPlacementBehavior;
import jade.core.AID;
import jade.core.Agent;

import java.util.HashMap;
import java.util.Map;

public class AuctioneerAgent extends Agent {
    private Map<AID, Integer> bids = new HashMap<>();

    protected void setup() {
        System.out.println("Auctioneer Agent " + getAID().getName() + " is ready.");

        // Add BidPlacementBehavior to handle bid placement requests
        addBehaviour(new BidPlacementBehavior(bids));

        // Add AuctionClosingBehavior to handle auction closing
        addBehaviour(new AuctionClosingBehavior(bids, getAID(), this));
    }
}
