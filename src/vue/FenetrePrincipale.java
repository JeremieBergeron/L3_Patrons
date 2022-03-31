package vue;

import modele.ModelePrincipal;
import observateur.Observer;

import javax.swing.JFrame;
import java.awt.*;

public class FenetrePrincipale extends JFrame implements Runnable, Observer {

    private static final String TITRE_FENETRE = "L3 - Patrons";
    private static final Dimension DIMENSIONS = Toolkit.getDefaultToolkit().getScreenSize();

    private ModelePrincipal modelePrincipal;
    private PanneauPrincipal panneauPrincipal;
    private MenuFenetre menuFenetre;

    /**
     *
     * @param modelePrincipal :
     */
    public FenetrePrincipale (ModelePrincipal modelePrincipal) {
        this.modelePrincipal = modelePrincipal;
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

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        // Mettre la fenêtre au centre de l'écran
        setLocationRelativeTo(null);
    }

    /**
     *
     */
    private void initContenu() {
        menuFenetre = new MenuFenetre(this);
        panneauPrincipal = new PanneauPrincipal(modelePrincipal);

        add(menuFenetre, BorderLayout.NORTH);
        add(panneauPrincipal);
    }

    /**
     *
     * @return :
     */
    public PanneauPrincipal getPanneauPrincipal() {
        return this.panneauPrincipal;
    }

    /**
     *
     */
    @Override
    public void update() {


    }
}
