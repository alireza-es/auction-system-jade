package ca.ucalgary.auction.behaviors;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import ca.ucalgary.auction.ontology.AuctionOntology;
import ca.ucalgary.auction.ontology.actions.PlaceBid;
import ca.ucalgary.auction.ontology.predicates.AuctionStarted;
import ca.ucalgary.auction.ontology.predicates.BidAccepted;
import ca.ucalgary.auction.ontology.concepts.Bid;

public class BidderBehaviour extends CyclicBehaviour {
    private Codec codec = new SLCodec();
    private Ontology ontology = AuctionOntology.getInstance();

    public BidderBehaviour(Agent a) {
        super(a);
    }

    public void action() {
        MessageTemplate mt = MessageTemplate.and(
                MessageTemplate.MatchLanguage(codec.getName()),
                MessageTemplate.MatchOntology(ontology.getName())
        );
        ACLMessage msg = myAgent.receive(mt);
        if (msg != null) {
            if (msg.getPerformative() == ACLMessage.INFORM) {
                try {
                    Object content = myAgent.getContentManager().extractContent(msg);
                    if (content instanceof AuctionStarted) {
                        // Place a bid
                        ACLMessage reply = msg.createReply();
                        reply.setPerformative(ACLMessage.PROPOSE);
                        Bid bid = new Bid();
                        bid.setBidder(myAgent.getAID());
                        bid.setPrice(((AuctionStarted) content).getItem().getStartingPrice() + 10);
                        myAgent.getContentManager().fillContent(reply, new PlaceBid(bid));
                        myAgent.send(reply);
                    } else if (content instanceof BidAccepted) {
                        // Print a message
                        System.out.println("I won the auction with a bid of " + ((BidAccepted) content).getBid().getPrice());
                    }
                } catch (Codec.CodecException | OntologyException e) {
                    e.printStackTrace();
                }
            }
        } else {
            block();
        }
    }
}