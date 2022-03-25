package vue;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;


import java.awt.event.ActionEvent;
import java.io.File;

public class MenuFenetre extends JMenuBar {

    private static final String NOM_BTN_MENU = "Menu";
    private static final String NOM_BTN_OUVRIR = "Ouvrir";
    private static final String NOM_BTN_SAUVGARDER = "Sauvgarder";

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
        JMenuItem btnSauvgarder = new JMenuItem(NOM_BTN_SAUVGARDER);

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

        btnMenu.add(btnOuvrir);
        btnMenu.add(btnSauvgarder);
        add(btnMenu);
    }
}
