package ca.ucalgary.auction.ui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import ca.ucalgary.auction.agents.SellerAgent;
import ca.ucalgary.auction.model.Auction;
import ca.ucalgary.auction.ontology.concepts.Item;
import ca.ucalgary.auction.utils.Constants;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class AuctionForm extends JFrame {
    JTable table;
    DefaultTableModel model;
    JButton placeBidButton;
    AgentContainer agentContainer;
    AgentController auctioneerAgentController;
    AgentController auctionUpdateAgentController;
    private Map<Long, AgentController> sellerAgents;

    private List<Auction> auctions;

    public AuctionForm(AgentContainer _agentContainer) {
        agentContainer = _agentContainer;
        this.sellerAgents = new HashMap<>();
        this.auctions = new ArrayList<>();
        this.auctions.add(new Auction(1, "Item 1", 10.0, 12.0, 2, 60));
        this.startAuctioneerAgent();
        this.startAuctionUpdateAgent();
        model = new DefaultTableModel(new Object[]{"Id", "Item", "Starting Price", "Current Bid", "Bids Count", "Time Left"}, 0);
        table = new JTable(model);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                placeBidButton.setEnabled(table.getSelectedRow() != -1);
            }
        });

        JButton startAuctionButton = new JButton("Start Auction");
        startAuctionButton.addActionListener(e -> openStartAuctionForm());

        placeBidButton = new JButton("Place Bid");
        placeBidButton.addActionListener(e -> placeBid());
        placeBidButton.setEnabled(false);

        JPanel panel = new JPanel();
        panel.add(startAuctionButton);
        panel.add(placeBidButton);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
        this.populateTable();
    }

    public void populateTable() {
        for (Auction auction : this.auctions) {
            model.addRow(new Object[]{auction.getId(), auction.getItemName(), auction.getStartingPrice(), auction.getCurrentBid(), auction.getBidsCount(), auction.getTimeLeft()});
        }
    }

    private void startAuctioneerAgent() {
        try {
            auctioneerAgentController = agentContainer.createNewAgent(Constants.AUCTIONEER_AGENT, "ca.ucalgary.auction.agents.AuctioneerAgent", null);
            auctioneerAgentController.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startAuctionUpdateAgent() {
          try {
            Object[] args = new Object[1];
            args[0] = this;
            auctionUpdateAgentController = agentContainer.createNewAgent(Constants.AUCTION_UPDATE_AGENT, "ca.ucalgary.auction.agents.AuctionUpdateAgent", args);
            auctionUpdateAgentController.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openStartAuctionForm() {
        StartAuctionForm form = new StartAuctionForm(this);
        form.setVisible(true);
    }

    public void startSellerAgent(String itemName, double startingPrice) {
        try {
            Item item = new Item(itemName, startingPrice);
            AgentController sellerAgentController = agentContainer.createNewAgent(SellerAgent.generateName(item), "ca.ucalgary.auction.agents.SellerAgent", new Object[]{item});
            sellerAgentController.start();
            sellerAgents.put(item.getId(), sellerAgentController);

        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
    public void addAuction(Auction auction) {
        this.auctions.add(auction);
        this.populateTable();
    }

    private void placeBid() {
        // implement place bid logic
    }
}