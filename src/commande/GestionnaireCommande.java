package commande;

import java.util.LinkedList;

import controleur.ControleurPerspective;
import memento.MementoIF;
import memento.Originator;
import modele.Perspective;
import vue.VueType;
import javax.swing.*;

public class GestionnaireCommande {

    private Originator originator = new Originator();
    private LinkedList<MementoIF> listeCommandeGauche;
    private LinkedList<MementoIF> listeCommandeDroite;

    private static GestionnaireCommande instance = new GestionnaireCommande();

    private GestionnaireCommande(){
        listeCommandeGauche = new LinkedList<>();
        listeCommandeDroite = new LinkedList<>();
    }

    public static GestionnaireCommande getInstance() {
        return instance;
    }

    public boolean isEmpty(){
        // Inutile de regarder les 2 listes
        return listeCommandeGauche.isEmpty();
    }

    /**
     *
     * @param commande :
     * @param perspective :
     */
    public void addCommande(Commande commande, Perspective perspective) {

        switch (perspective.getVueType()) {
            case GAUCHE:
                listeCommandeGauche.add(originator.saveToMemento(perspective));
                break;

            case DROITE:
                listeCommandeDroite.add(originator.saveToMemento(perspective));
                break;
        }

    }

    /**
     *
     * @param controleurPerspective :
     */
    public void removeLastCommande(ControleurPerspective controleurPerspective) {
        switch (controleurPerspective.getPerspective().getVueType()) {
            case GAUCHE:
                removeLastCommande(controleurPerspective, listeCommandeGauche);
                break;

            case DROITE:
                removeLastCommande(controleurPerspective, listeCommandeDroite);
                break;
        }
    }

    /**
     *
     * @param controleurPerspective :
     * @param liste :
     */
    private void removeLastCommande(ControleurPerspective controleurPerspective, LinkedList<MementoIF> liste){
        if (liste.size() != 0) {
            MementoIF mementoIF = liste.getLast();
            Perspective perspective = originator.restoreFromMemento(mementoIF);
            controleurPerspective.setPerspective(perspective);

            liste.removeLast();
        }
    }

    /**
     *
     */
    public void effacerHistoriqueCmd() {
        listeCommandeGauche.clear();
        listeCommandeDroite.clear();
    }

}
