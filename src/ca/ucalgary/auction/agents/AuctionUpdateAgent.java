package ca.ucalgary.auction.agents;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.content.onto.basic.Action;
import ca.ucalgary.auction.ontology.concepts.Item;
import ca.ucalgary.auction.ui.AuctionForm;
import ca.ucalgary.auction.model.Auction;
import ca.ucalgary.auction.ontology.AuctionOntology;

public class AuctionUpdateAgent extends Agent {
    private Codec codec = new SLCodec();
    private Ontology ontology = AuctionOntology.getInstance();
    private AuctionForm auctionForm;

    protected void setup() {
        Object[] args = getArguments();
        if (args != null && args.length > 0) {
            auctionForm = (AuctionForm) args[0];
        }

        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontology);
        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
                if (msg != null) {
                    try {
                        Action act = (Action) getContentManager().extractContent(msg);
                        Item item = (Item) act.getAction();
                        auctionForm.addAuction(new Auction(item));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    block();
                }
            }
        });
    }

}