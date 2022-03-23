package vue;

import modele.ModelePrincipale;

import javax.swing.JFrame;
import java.awt.Dimension;

public class FenetrePrincipale extends JFrame implements Runnable{

    private static final String TITRE_FENETRE = "L3 - Patrons";
    private static final Dimension DIMENSIONS = new Dimension(700, 700);

    private ModelePrincipale modelePrincipale;

    private PanneauPrincipale panneauPrincipale;
    private MenuFenetre menuFenetre;

    /**
     *
     * @param modelePrincipale :
     */
    public FenetrePrincipale (ModelePrincipale modelePrincipale) {
        this.modelePrincipale = modelePrincipale;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        initFenetre();
        initContenu();

    }

    /**
     *
     */
    private void initFenetre() {
        setTitle(TITRE_FENETRE);
        setSize(DIMENSIONS);

        setVisible(true);

        // Mettre la fenêtre au centre de l'écran
        setLocationRelativeTo(null);

        // Empêcher la redimensionne de la fenêtre
        setResizable(false);
    }

    /**
     *
     */
    private void initContenu() {

        PanneauPrincipale panneauPrincipale = new PanneauPrincipale(modelePrincipale);

    }
}
