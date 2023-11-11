package ca.ucalgary.auction.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuctionForm extends JFrame {
    private JTextField itemField;
    private JTextField priceField;

    public AuctionForm() {
        setTitle("Start Auction");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel itemLabel = new JLabel("Item:");
        itemField = new JTextField();
        JLabel priceLabel = new JLabel("Starting Price:");
        priceField = new JTextField();

        panel.add(itemLabel);
        panel.add(itemField);
        panel.add(priceLabel);
        panel.add(priceField);

        JButton startButton = new JButton("Start Auction");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startAuction();
            }
        });

        panel.add(startButton);

        getContentPane().add(panel, BorderLayout.CENTER);
    }

    private void startAuction() {
        String item = itemField.getText();
        int price = Integer.parseInt(priceField.getText());
        // Start the auction with the entered item and price
        System.out.println("Starting auction for item: " + item + " with starting price: " + price);
    }
}