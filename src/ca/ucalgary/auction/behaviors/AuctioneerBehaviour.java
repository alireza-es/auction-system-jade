package ca.ucalgary.auction.behaviors;

import jade.content.ContentElement;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import ca.ucalgary.auction.ontology.AuctionOntology;
import ca.ucalgary.auction.ontology.actions.StartAuction;
import ca.ucalgary.auction.ontology.actions.PlaceBid;
import ca.ucalgary.auction.ontology.predicates.AuctionStarted;
import ca.ucalgary.auction.ontology.predicates.BidPlaced;
import ca.ucalgary.auction.ontology.concepts.Bid;

public class AuctioneerBehaviour extends CyclicBehaviour {
    private AID highestBidder;
    private double highestBid;

    public AuctioneerBehaviour(Agent a) {
        super(a);
    }

    public void action() {
        Codec codec = new SLCodec();
        Ontology ontology = AuctionOntology.getInstance();
        MessageTemplate mt = MessageTemplate.and(
                MessageTemplate.MatchLanguage(codec.getName()),
                MessageTemplate.MatchOntology(ontology.getName())
        );
        ACLMessage msg = myAgent.receive(mt);
        if (msg != null) {
            if (msg.getPerformative() == ACLMessage.REQUEST) {
                try {
                    Action actionContent = (Action) myAgent.getContentManager().extractContent(msg);
                    StartAuction sa = (StartAuction) actionContent.getAction();
                    // Start a new auction
                    highestBid = sa.getItem().getStartingPrice();
                    highestBidder = null;

                    // Notify that the auction has started
                    ACLMessage reply = msg.createReply();
                    reply.setPerformative(ACLMessage.INFORM);
                    myAgent.getContentManager().fillContent(reply, new AuctionStarted(sa.getItem()));
                    myAgent.send(reply);
                } catch (Codec.CodecException | OntologyException e) {
                    e.printStackTrace();
                }
            } else if (msg.getPerformative() == ACLMessage.PROPOSE) {
                try {
                    PlaceBid pb = (PlaceBid) myAgent.getContentManager().extractContent(msg);
                    // Place a bid
                    Bid bid = pb.getBid();
                    if (bid.getPrice() > highestBid) {
                        highestBid = bid.getPrice();
                        highestBidder = bid.getBidder();
                    }

                    // Notify that the bid has been placed
                    ACLMessage reply = msg.createReply();
                    reply.setPerformative(ACLMessage.INFORM);
                    myAgent.getContentManager().fillContent(reply, new BidPlaced(bid));
                    myAgent.send(reply);
                } catch (Codec.CodecException | OntologyException e) {
                    e.printStackTrace();
                }
            }
        } else {
            block();
        }
    }
}