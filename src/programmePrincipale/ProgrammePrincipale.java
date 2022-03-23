package programmePrincipale;

import modele.ModelePrincipale;
import vue.FenetrePrincipale;

import javax.swing.SwingUtilities;

public class ProgrammePrincipale {
    public static void main(String[] args) {

        ModelePrincipale modelePrincipale = new ModelePrincipale();

        // Affiche l'interface
        SwingUtilities.invokeLater(new FenetrePrincipale(modelePrincipale));
    }
}
