package ProfilePages.Contractor;

import Browse.BrowsePanel;
import MainWindow.MainWindow;
import Users.Contractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContractorProfileContainerPanel extends JPanel {
    private MainWindow mainWindow;
    private JPanel contractorContainer;
    private JButton editButton;
    private JButton backButton;
    private JPanel content;
    CardLayout cardLayout = new CardLayout();

    private BrowsePanel browsePanel;

    private ContractorProfileEdit contractorProfileEdit = new ContractorProfileEdit();

    public ContractorProfileContainerPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;

        ContractorProfile contractorProfile = new ContractorProfile(mainWindow);
        content.setLayout(cardLayout);

        add(contractorContainer);
        content.add(contractorProfile, "contractorProfile");
        content.add(contractorProfileEdit, "contractorProfileEdit");
        backButton.setVisible(false);
        editButton.setVisible(true);

        ((CardLayout) content.getLayout()).show(content, "contractorProfile");

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contractorProfile.updateVales();
                contractorProfileEdit.clearLabel();
                ((CardLayout) content.getLayout()).show(content, "contractorProfile");
                backButton.setVisible(false);
                editButton.setVisible(true);

            }
        });
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) content.getLayout()).show(content, "contractorProfileEdit");
                backButton.setVisible(true);
                editButton.setVisible(false);
            }
        });
    }

    public ContractorProfileContainerPanel(BrowsePanel browsePanel, Contractor contractor) {
        this.browsePanel = browsePanel;
        this.mainWindow = browsePanel.getMainWindowObj();

        ContractorProfile contractorProfile = new ContractorProfile(mainWindow, contractor);
        content.setLayout(cardLayout);

        add(contractorContainer);
        content.add(contractorProfile, "contractorProfile");
        backButton.setVisible(true);
        editButton.setVisible(false);

        ((CardLayout) content.getLayout()).show(content, "contractorProfile");

        // (Uchechi) Redefined for Browse Panel within BrowseSearchPanel.java. This might be redundant here
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contractorProfile.updateVales();
                contractorProfileEdit.clearLabel();
                ((CardLayout) content.getLayout()).show(content, "contractorProfile");
                backButton.setVisible(false);
                editButton.setVisible(false);
            }
        });
    }

    public JButton getBackButton() { return backButton; }
}
