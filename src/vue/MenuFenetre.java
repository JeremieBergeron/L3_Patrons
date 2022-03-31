package vue;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class MenuFenetre extends JMenuBar {

    private static final String NOM_BTN_MENU = "Menu";
    private static final String NOM_BTN_OUVRIR = "Ouvrir";
    private static final String NOM_BTN_SAUVEGARDER = "Sauvegarder";

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
        JMenu btnMenu= new JMenu(NOM_BTN_MENU);
        JMenuItem btnOuvrir = new JMenuItem(NOM_BTN_OUVRIR);
        JMenuItem btnSauvegarder = new JMenuItem(NOM_BTN_SAUVEGARDER);

        btnOuvrir.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fileChooser.setDialogTitle("Sélectionnez un fichier");
            fileChooser.setAcceptAllFileFilterUsed(false);

            // Créer un filtre
            FileNameExtensionFilter filtreJPG = new FileNameExtensionFilter(".jpg", "jpg");
            FileNameExtensionFilter filtrePNG = new FileNameExtensionFilter(".png", "png");
            fileChooser.addChoosableFileFilter(filtreJPG);
            fileChooser.addChoosableFileFilter(filtrePNG);

            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                fenetrePrincipale.getPanneauPrincipal().ouvrirImage(selectedFile);
            }
        });

        btnSauvegarder.addActionListener((ActionEvent e) -> {
            int retour = JOptionPane.showConfirmDialog(null,
                    "Voulez-vous vraiment sauvegarder?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            if(retour==0) {//si le bouton cliqué est "oui"
                //fenetrePrincipale.getPanneauPrincipal().SauvegarderImage();
                JFileChooser fileSave = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                fileSave.setDialogTitle("Sélectionnez un emplacement pour la sauvegarde");
                fileSave.setAcceptAllFileFilterUsed(false);

                // Créer un filtre
                FileNameExtensionFilter filtreSER = new FileNameExtensionFilter(".ser", "ser");
                fileSave.addChoosableFileFilter(filtreSER);
            }

        });

        btnMenu.add(btnOuvrir);
        btnMenu.add(btnSauvegarder);
        add(btnMenu);
    }
}
