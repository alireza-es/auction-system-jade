package ca.ucalgary.auction.model;

public class Auction {
    private int id;
    private String item;
    private double startingPrice;
    private double currentBid;
    private int bidsCount;
    private long timeLeft;

    public Auction(int id, String item, double startingPrice, double currentBid, int bidsCount, long timeLeft) {
        this.id = id;
        this.item = item;
        this.startingPrice = startingPrice;
        this.currentBid = currentBid;
        this.bidsCount = bidsCount;
        this.timeLeft = timeLeft;
    }

    public int getId() {
        return id;
    }

    public String getItem() {
        return item;
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

    public void setItem(String item) {
        this.item = item;
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