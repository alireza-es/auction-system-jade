package ca.ucalgary.auction.model;

import ca.ucalgary.auction.ontology.concepts.Item;

public class Auction {
    private long id;
    private String itemName;
    private double startingPrice;
    private double currentBid;
    private int bidsCount;
    private long timeLeft;

    public Auction(Item item) {
        this(item.getId(), item.getName(), item.getStartingPrice(), 0, 0, 0);
    }

    public Auction(long id, String itemName, double startingPrice, double currentBid, int bidsCount, long timeLeft) {
        this.id = id;
        this.itemName = itemName;
        this.startingPrice = startingPrice;
        this.currentBid = currentBid;
        this.bidsCount = bidsCount;
        this.timeLeft = timeLeft;
    }

    public long getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public double getStartingPrice() {
        return startingPrice;
    }

    public double getCurrentBid() {
        return currentBid;
    }

    public int getBidsCount() {
        return bidsCount;
    }

    public long getTimeLeft() {
        return timeLeft;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setItemName(String item) {
        this.itemName = item;
    }

    public void setStartingPrice(double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public void setCurrentBid(double currentBid) {
        this.currentBid = currentBid;
    }

    public void setBidsCount(int bidsCount) {
        this.bidsCount = bidsCount;
    }

    public void setTimeLeft(long timeLeft) {
        this.timeLeft = timeLeft;
    }
}