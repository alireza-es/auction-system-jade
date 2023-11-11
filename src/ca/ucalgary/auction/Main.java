package ca.ucalgary.auction;

import ca.ucalgary.auction.ui.AuctionForm;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

public class Main {
    public static void main(String[] args) {
        AuctionForm form = new AuctionForm();
        form.setVisible(true);

//        // Get a hold on JADE runtime
//        Runtime rt = Runtime.instance();
//
//        // Create a default profile
//        Profile p = new ProfileImpl();
//        p.setParameter(Profile.MAIN_HOST, "localhost");
//        p.setParameter(Profile.MAIN_PORT, "1099");
//        p.setParameter(Profile.GUI, "true");
//
//        // Create a new non-main container, connecting to the default
//        // main container (i.e., on this host, port 1099)
//        ContainerController cc = rt.createMainContainer(p);
//
//        try {
//            // Create a new agent, a copy of the original auctioneer
//            AgentController ac = cc.createNewAgent("auctioneer", "ca.ucalgary.auction.AuctioneerAgent", null);
//            ac.start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}