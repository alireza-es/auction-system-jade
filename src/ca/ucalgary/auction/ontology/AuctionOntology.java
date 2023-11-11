package ca.ucalgary.auction.ontology;

import jade.content.onto.BeanOntology;
import jade.content.onto.BeanOntologyException;
import jade.content.onto.Ontology;

public class AuctionOntology extends BeanOntology {
    private static AuctionOntology theInstance = new AuctionOntology();

    public static AuctionOntology getInstance() {
        return theInstance;
    }

    private AuctionOntology() {
        super("AuctionOntology");
        try {
            add("ca.ucalgary.auction.ontology.actions");
            add("ca.ucalgary.auction.ontology.predicates");
            add("ca.ucalgary.auction.ontology.concepts");
        } catch (BeanOntologyException e) {
            e.printStackTrace();
        }
    }
}