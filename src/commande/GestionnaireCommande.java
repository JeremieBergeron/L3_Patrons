package commande;

import java.util.LinkedList;
import commande.Commande;

public class GestionnaireCommande {

    private LinkedList<Commande> listeCmdPersp1;
    private LinkedList<Commande> listeCmdPersp2;
    private static GestionnaireCommande instance;

    private gestionnaireCommande() {
        instance = new GestionnaireCommande();
    }

    public static GestionnaireCommande getInstance() {
        return instance;
    }

}
