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

    public void addCommande(Commande commande, Perspective perspective) {

        if (!commande.unexecute()){
            listeCommandeGauche.clear();
            listeCommandeDroite.clear();
        } else {
            switch (perspective.getVueType()) {
                case GAUCHE:
                    listeCommandeGauche.add(originator.saveToMemento(perspective));
                    break;

                case DROITE:
                    listeCommandeDroite.add(originator.saveToMemento(perspective));
                    break;
            }
        }

    }

    public void removeLastCommande(ControleurPerspective controleurPerspective) {
        Perspective perspective = null;
        switch (controleurPerspective.getPerspective().getVueType()) {
            case GAUCHE:
                if (listeCommandeGauche.size() != 0) {
                    MementoIF mementoIF = listeCommandeGauche.getLast();
                    perspective = originator.restoreFromMemento(mementoIF);
                    controleurPerspective.setPerspective(perspective);
                    listeCommandeGauche.removeLast();

                }
                break;

            case DROITE:
                if (listeCommandeDroite.size() != 0) {
                    MementoIF mementoIF = listeCommandeDroite.getLast();
                    perspective = originator.restoreFromMemento(mementoIF);
                    controleurPerspective.setPerspective(perspective);

                    listeCommandeDroite.removeLast();

                }
                break;
        }
    }

}
