package programmePrincipale;

import modele.ModelePrincipal;
import vue.FenetrePrincipale;

import javax.swing.SwingUtilities;

public class ProgrammePrincipale {
    public static void main(String[] args) {

        ModelePrincipal modelePrincipale = new ModelePrincipal();

        // Affiche l'interface
        SwingUtilities.invokeLater(new FenetrePrincipale(modelePrincipale));
    }
}
