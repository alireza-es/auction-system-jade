package ca.ucalgary.auction.agents;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.Agent;
import ca.ucalgary.auction.ontology.AuctionOntology;
import ca.ucalgary.auction.ontology.concepts.Item;
import ca.ucalgary.auction.utils.Constants;
import ca.ucalgary.auction.behaviors.SellerBehaviour;

public class SellerAgent extends Agent {
    private final Codec codec = new SLCodec();
    private final Ontology ontology = AuctionOntology.getInstance();

    protected void setup() {
        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontology);
        Object[] args = getArguments();
        Item item = null;
        if (args != null && args.length > 0) {
            item = (Item) args[0];
        }
        addBehaviour(new SellerBehaviour(this, item));
    }

    public static String generateName(Item item) {
        return String.format(Constants.SELLER_AGENT + "-%s-%d", item.getName().toLowerCase().replace(" ", "-"), item.getId());
    }
}