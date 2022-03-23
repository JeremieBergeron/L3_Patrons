package vue;

import modele.ModelePrincipale;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;

public class FenetrePrincipale extends JFrame implements Runnable{

    private static final String TITRE_FENETRE = "Laboratoire 1 : LOG121 - Simulation";
    private static final Dimension DIMENSION = new Dimension(700, 700);

    private PanneauPrincipale panneauPrincipale;
    private ModelePrincipale modelePrincipale;
    private MenuFenetre menuFenetre;

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

    public FenetrePrincipale(ModelePrincipale modelePrincipale){
        this.modelePrincipale = modelePrincipale;
    }

    @Override
    public void run() {
        initFenetre();
        initContenu();
    }

    /**
     *
     */
    private void initFenetre() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(10, 10);
    }

    /**
     *
     */
    private void initContenu() {
        panneauPrincipale = new PanneauPrincipale(modelePrincipale);
        //add(panneauPrincipale);

        setContentPane(panneauPrincipale);


    }
}
