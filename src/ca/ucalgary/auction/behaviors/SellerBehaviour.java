package ca.ucalgary.auction.behaviors;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREInitiator;
import ca.ucalgary.auction.ontology.AuctionOntology;
import ca.ucalgary.auction.ontology.actions.StartAuction;
import ca.ucalgary.auction.ontology.concepts.Item;

public class SellerBehaviour extends OneShotBehaviour {
    private final Codec codec = new SLCodec();
    private final Ontology ontology = AuctionOntology.getInstance();

    public SellerBehaviour(Agent a) {
        super(a);
    }

    public void action() {
        // Start an auction
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.addReceiver(new AID("auctioneer", AID.ISLOCALNAME));
        msg.setLanguage(codec.getName());
        msg.setOntology(ontology.getName());
        msg.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
        try {
            // Create the content of the message
            StartAuction sa = new StartAuction(null);
            Item item = new Item();
            item.setName("item1");
            item.setStartingPrice(100);
            sa.setItem(item);
            myAgent.getContentManager().fillContent(msg, new Action(new AID("auctioneer", AID.ISLOCALNAME), sa));
            // Add a behaviour to handle the responses to the request
            myAgent.addBehaviour(new AchieveREInitiator(myAgent, msg) {
                protected void handleInform(ACLMessage inform) {
                    System.out.println("Auction successfully started.");
                }
                protected void handleFailure(ACLMessage failure) {
                    System.out.println("Failed to start auction.");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}