package Browse;

import MainWindow.MainWindow;
import ProfilePages.Contractor.ContractorProfileContainerPanel;

import javax.swing.*;
import java.awt.*;

public class BrowsePanel extends JPanel{
    // Owner window
    MainWindow mainWindow;

    private JPanel mainPanel;
    private JPanel searchOrProfilePanel;
    private JButton backToSearchButton;
    private BrowseSearchPanel browseSearchPanel = new BrowseSearchPanel(this);

    public BrowsePanel (MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        add(mainPanel);
        backToSearchButton.setVisible(false);

        searchOrProfilePanel.add(browseSearchPanel, "search");
        ((CardLayout)searchOrProfilePanel.getLayout()).show(searchOrProfilePanel, "search");
    }

    public void backToSearch() {((CardLayout)searchOrProfilePanel.getLayout()).show(searchOrProfilePanel, "search");}

    public void showSelectedProfile(ContractorProfileContainerPanel selectedContractorProfile) {
        searchOrProfilePanel.add(selectedContractorProfile, "profile");
        ((CardLayout)searchOrProfilePanel.getLayout()).show(searchOrProfilePanel, "profile");
    }

    public MainWindow getMainWindowObj() {
        return mainWindow;
    }
}
