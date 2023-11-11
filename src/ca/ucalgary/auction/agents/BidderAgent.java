package ca.ucalgary.auction.agents;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.Agent;
import ca.ucalgary.auction.ontology.AuctionOntology;
import ca.ucalgary.auction.behaviors.BidderBehaviour;

public class BidderAgent extends Agent {
    private final Codec codec = new SLCodec();
    private final Ontology ontology = AuctionOntology.getInstance();

    protected void setup() {
        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontology);
        addBehaviour(new BidderBehaviour(this));
    }
}