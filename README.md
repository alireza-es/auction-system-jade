# Multi-Agent Online Auction System

## Business Scenario

The Multi-Agent Online Auction System simulates an online marketplace where users can buy and sell items through auctions. The system utilizes multi-agent technology to create a decentralized, efficient, and secure platform for conducting auctions.

## Features

- **Decentralized Auctions:** Auctions are managed by individual auctioneer agents, allowing for decentralized and parallel processing.
- **Bid Management:** Bidder agents can place bids, withdraw bids, and receive real-time updates on bidding status.
- **Seller Listings:** Sellers can create new auctions, set starting prices, and monitor the progress of their auctions.
- **Notification System:** Notification agents inform bidders and sellers about bid updates, auction closing, and winner determination.
- **Dynamic Interactions:** Agents interact dynamically, adjusting to changing bid statuses and auction conditions.

## List of Agents

1. **Auctioneer Agents:**
   - *Description:* Manage individual auctions, track bids, and determine winners.

2. **Bidder Agents:**
   - *Description:* Place bids, withdraw bids, and receive notifications about bidding status.

3. **Seller Agents:**
   - *Description:* Create new auctions, monitor auction listings, and receive notifications about winning bids.

4. **Notification Agents:**
   - *Description:* Notify bidders and sellers about bid updates, auction closing, and winner determination.

## Agent Interactions

1. **Bid Placement:**
   - *Description:* Bidder agents place bids on auctioneer agents. Auctioneer agents manage incoming bids, track the highest bid, and notify bidders if they have been outbid.

2. **Auction Closing:**
   - *Description:* Auctioneer agents monitor the duration of the auction and close the auction when the specified time limit is reached. They determine the winning bid and the winning bidder.

3. **Winner Notification:**
   - *Description:* Notification agents inform the winning bidder and the seller about the auction outcome. They provide details about the winning bid amount and the next steps for payment and item transfer.

## Additional Documentation

### Installation

1. Clone the repository:
   ```bash
   git clone git@github.com:alireza-es/auction-system-jade.git

2. Compile the Java files:
   ```bash
   cd MultiAgentAuctionSystem
   javac *.java
   

### Usage 
1. Run the JADE platform:
   ```bash
   java -cp path/to/jade.jar jade.Boot -gui
2. Start the auction system agents:
   ```bash
   java -cp .:path/to/jade.jar Main
   
3. Follow the console prompts to create auctions, place bids, and monitor auction outcomes.

### Contribution Guidelines
Contributions are welcome! Please fork the repository and create a pull request with your changes.


