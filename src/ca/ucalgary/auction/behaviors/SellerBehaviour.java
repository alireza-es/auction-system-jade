package ca.ucalgary.auction.behaviors;

import ca.ucalgary.auction.agents.AuctionUpdateAgent;
import ca.ucalgary.auction.ontology.predicates.AuctionStarted;
import ca.ucalgary.auction.utils.Constants;
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
    private final Item item;

    public SellerBehaviour(Agent a, Item item) {
        super(a);
        this.item = item;
    }

    public void action() {
        // Start an auction
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.addReceiver(new AID(Constants.AUCTIONEER_AGENT, AID.ISLOCALNAME));
        msg.setLanguage(codec.getName());
        msg.setOntology(ontology.getName());
        msg.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
        try {
            // Create the content of the message
            StartAuction sa = new StartAuction(this.item);
            myAgent.getContentManager().fillContent(msg, new Action(new AID(Constants.AUCTIONEER_AGENT, AID.ISLOCALNAME), sa));
            // Add a behaviour to handle the responses to the request
            myAgent.addBehaviour(new AchieveREInitiator(myAgent, msg) {
                protected void handleInform(ACLMessage inform) {
                    System.out.println("Auction successfully started.");

                    // Send update message to AuctionUpdateAgent
                    ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                    msg.addReceiver(new AID(Constants.AUCTION_UPDATE_AGENT, AID.ISLOCALNAME));
                    msg.setLanguage(codec.getName()); // Set the language of the message
                    try {
                        myAgent.getContentManager().fillContent(msg, new Action(new AID(Constants.AUCTIONEER_AGENT, AID.ISLOCALNAME), sa));
                        myAgent.send(msg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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