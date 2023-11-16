package ca.ucalgary.auction.agents;

import ca.ucalgary.auction.behaviors.AuctioneerBehaviour;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.Agent;
import ca.ucalgary.auction.ontology.AuctionOntology;

public class AuctioneerAgent extends Agent {
    private Codec codec = new SLCodec();
    private Ontology ontology = AuctionOntology.getInstance();

    protected void setup() {
        System.out.println(">>>> 1) Auctioneer successfully started.");
        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontology);
        addBehaviour(new AuctioneerBehaviour(this));
    }
}