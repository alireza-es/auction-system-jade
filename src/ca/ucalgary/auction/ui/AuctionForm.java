package ca.ucalgary.auction.ui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import ca.ucalgary.auction.model.Auction;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

import java.awt.*;

public class AuctionForm extends JFrame {
    JTable table;
    DefaultTableModel model;
    JButton placeBidButton;
    AgentContainer agentContainer;
    AgentController auctioneerAgentController;

    public AuctionForm(AgentContainer _agentContainer) {
        agentContainer = _agentContainer;
        this.startAuctioneerAgent();
        model = new DefaultTableModel(new Object[]{"Id", "Item", "Starting Price", "Current Bid", "Bids Count", "Time Left"}, 0);
        table = new JTable(model);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                placeBidButton.setEnabled(table.getSelectedRow() != -1);
            }
        });

        JButton startAuctionButton = new JButton("Start Auction");
        startAuctionButton.addActionListener(e -> startAuction());

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
    }

    public void populateTable(Auction[] auctions) {
        for (Auction auction : auctions) {
            model.addRow(new Object[]{auction.getId(), auction.getItem(), auction.getStartingPrice(), auction.getCurrentBid(), auction.getBidsCount(), auction.getTimeLeft()});
        }
    }

    private void startAuctioneerAgent() {
        try {
            auctioneerAgentController = agentContainer.createNewAgent("auctioneer", "ca.ucalgary.auction.agents.AuctioneerAgent", null);
            auctioneerAgentController.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startAuction() {
        // implement start auction logic
    }

    private void placeBid() {
        // implement place bid logic
    }
}