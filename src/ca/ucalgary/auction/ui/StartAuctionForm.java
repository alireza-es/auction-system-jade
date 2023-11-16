package ca.ucalgary.auction.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;

public class StartAuctionForm extends JFrame {
    private JTextField itemField;
    private JFormattedTextField priceField;
    private AuctionForm auctionForm;

    public StartAuctionForm(AuctionForm auctionForm) {
        this.auctionForm = auctionForm;

        setTitle("Start Auction");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel itemLabel = new JLabel("Item:");
        itemField = new JTextField();

        JLabel priceLabel = new JLabel("Starting Price:");
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Double.class);
        formatter.setMinimum(0.0);
        formatter.setMaximum(Double.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        priceField = new JFormattedTextField(formatter);

        panel.add(itemLabel);
        panel.add(itemField);
        panel.add(priceLabel);
        panel.add(priceField);

        JButton startButton = new JButton("Start Auction");
        startButton.addActionListener(e -> startAuction());

        panel.add(startButton);

        getContentPane().add(panel, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    private void startAuction() {
        String itemName = itemField.getText();
        double startingPrice = ((Number)priceField.getValue()).doubleValue();
        auctionForm.startSellerAgent(itemName, startingPrice);
        dispose();
    }
}