package commande;

import modele.ModelePrincipal;
import vue.VuePerspective;

import java.io.File;

public class Sauvegarder implements Commande {

    private ModelePrincipal modelePrincipal;

    private VuePerspective vuePerspectiveFinale1;
    private VuePerspective vuePerspectiveFinale2;
    private File pathSavedFile;
    private String nameSavedFile;

    public Sauvegarder(ModelePrincipal modelePrincipal, VuePerspective vuePerspectiveFinale1, VuePerspective vuePerspectiveFinale2, String nameSavedFile, File pathSavedFile) {
        this.modelePrincipal = modelePrincipal;
        this.vuePerspectiveFinale1 = vuePerspectiveFinale1;
        this.vuePerspectiveFinale2 = vuePerspectiveFinale2;
        this.pathSavedFile = pathSavedFile;
        this.nameSavedFile = nameSavedFile;
    }

    @Override
    public boolean execute() {
        modelePrincipal.sauvegarder(vuePerspectiveFinale1, vuePerspectiveFinale2, nameSavedFile, pathSavedFile);
        return true;
    }

}
