package ca.ucalgary.auction;

import ca.ucalgary.auction.ui.AuctionForm;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

public class Main {
    public static void main(String[] args) {
        // Get a hold on JADE runtime
        Runtime runtime = Runtime.instance();

        // Create a default profile
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        profile.setParameter(Profile.MAIN_PORT, "1099");
        profile.setParameter(Profile.GUI, "true");

        // Create a new non-main container, connecting to the default
        // main container (i.e., on this host, port 1099)
        AgentContainer agentContainer = runtime.createMainContainer(profile);
        AuctionForm form = new AuctionForm(agentContainer);
        form.setVisible(true);
    }
}