package vue;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;


import java.awt.event.ActionEvent;
import java.io.File;

public class MenuFenetre extends JMenuBar {

    private static final String NOM_BTN_FICHIER = "Fichier";
    private static final String NOM_BTN_AIDE = "Aide";
    private static final String NOM_BTN_OUVRIR = "Ouvrir";
    private static final String NOM_BTN_SAUVEGARDER = "Sauvegarder";
    private static final String NOM_BTN_APROPOS = "À propos";

    private final FenetrePrincipale fenetrePrincipale;

    /**
     *
     */
    public MenuFenetre(FenetrePrincipale fenetrePrincipale) {
        this.fenetrePrincipale = fenetrePrincipale;
        initContenu();
    }

    /**
     *
     */
    public void initContenu() {
        JMenu btnFichier = new JMenu(NOM_BTN_FICHIER);
        JMenu btnAide = new JMenu(NOM_BTN_AIDE);
        JMenuItem btnOuvrir = new JMenuItem(NOM_BTN_OUVRIR);
        JMenuItem btnSauvegarder = new JMenuItem(NOM_BTN_SAUVEGARDER);
        JMenuItem btnAPropos = new JMenuItem(NOM_BTN_APROPOS);

        Object[] options1 = { "Oui", "Non",
                "Cancel" };

        btnOuvrir.addActionListener((ActionEvent e) -> {
            boolean ouvrirImage = true;

            if (fenetrePrincipale.getPanneauPrincipal().getImageOuverte()){
                int resultat = JOptionPane.showOptionDialog(null,"Êtes-vous sûr de vouloir ouvrir une autre image sans sauvegarder ?","Alert", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options1, options1[0]);
                ouvrirImage = resultat == 0;
            }

            if (ouvrirImage) {
                JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                fileChooser.setDialogTitle("Sélectionnez un fichier");
                fileChooser.setAcceptAllFileFilterUsed(true);

                // Créer un filtre
                //FileNameExtensionFilter filtreAll = new FileNameExtensionFilter("Tout fichier", "jpg", "png");
                FileNameExtensionFilter filtreJPG = new FileNameExtensionFilter(".jpg", "jpg");
                FileNameExtensionFilter filtrePNG = new FileNameExtensionFilter(".png", "png");
                //fileChooser.addChoosableFileFilter(filtreAll);
                fileChooser.addChoosableFileFilter(filtreJPG);
                fileChooser.addChoosableFileFilter(filtrePNG);

                int returnValue = fileChooser.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    fenetrePrincipale.getPanneauPrincipal().ouvrirImage(selectedFile);
                }
            }
        });

        btnAPropos.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(null,"LOG121 / Laboratoire 3 / Hiver 2022 / ÉTS");
        });

        btnFichier.add(btnOuvrir);
        btnFichier.add(btnSauvegarder);
        btnAide.add(btnAPropos);
        add(btnFichier);
        add(btnAide);
    }
}
