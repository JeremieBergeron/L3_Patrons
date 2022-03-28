package commande;

import java.util.LinkedList;

import vue.VueType;

import javax.swing.*;

public class GestionnaireCommande {

    private LinkedList<Commande> listeCommandeGauche;
    private LinkedList<Commande> listeCommandeDroite;
    private static GestionnaireCommande instance = new GestionnaireCommande();

    private GestionnaireCommande(){
        listeCommandeGauche = new LinkedList<>();
        listeCommandeDroite = new LinkedList<>();
    }

    public static GestionnaireCommande getInstance() {
        return instance;
    }

    public void addCommande(Commande commande, VueType vueType) {
        switch (vueType) {
            case GAUCHE:
                listeCommandeGauche.add(commande);
                break;

            case DROITE:
                listeCommandeDroite.add(commande);
                break;
        }

    }

    public Action removeLastCommande(VueType vueType) {

        System.out.println(vueType);

        switch (vueType) {
            case GAUCHE:
                if (listeCommandeGauche.size() != 0) {
                    if (listeCommandeGauche.getLast().unexecute()) {
                        listeCommandeGauche.removeLast();
                    } else {
                        listeCommandeGauche.clear();
                    }
                }
                break;

            case DROITE:
                if (listeCommandeDroite.size() != 0) {
                    if (listeCommandeDroite.getLast().unexecute()) {
                        listeCommandeDroite.removeLast();
                    } else {
                        listeCommandeDroite.clear();
                    }
                }
                break;
        }
        return null;
    }

}
