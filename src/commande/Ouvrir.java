package commande;


import modele.ModelePrincipal;

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
        return true;
    }

}
