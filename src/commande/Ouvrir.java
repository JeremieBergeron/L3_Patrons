package commande;


import controleur.ControleurPerspective;
import modele.ModelePrincipal;
import modele.Perspective;

import java.io.File;

public class Ouvrir implements Commande{

    private ModelePrincipal modelePrincipal;
    private File selectedFile;

    public Ouvrir (ModelePrincipal modelePrincipal, File selectedFile) {
        this.modelePrincipal = modelePrincipal;
        this.selectedFile = selectedFile;
    }

    @Override
    public boolean execute() {
        modelePrincipal.ouvrir(selectedFile);
        GestionnaireCommande.getInstance().addCommande(this, null);
        return true;
    }

    @Override
    public boolean unexecute() {
        return false;
    }

}
