package Browse;

import ProfilePages.Contractor.ContractorProfileContainer;

import javax.swing.*;
import java.awt.*;

public class BrowsePanel extends JPanel{
    private JPanel mainPanel;
    private JPanel searchOrProfilePanel;
    private JButton backToSearchButton;
    private BrowseSearchPanel browseSearchPanel = new BrowseSearchPanel(this);
    private ContractorProfileContainer browseProfilePanel;

    public BrowsePanel () {
        add(mainPanel);
        backToSearchButton.setVisible(false);

        searchOrProfilePanel.add(browseSearchPanel, "search");
        ((CardLayout)searchOrProfilePanel.getLayout()).show(searchOrProfilePanel, "search");
    }

    public void backToSearch() {((CardLayout)searchOrProfilePanel.getLayout()).show(searchOrProfilePanel, "search");}

    public void showSelectedProfile(ContractorProfileContainer selectedContractorProfile) {
        searchOrProfilePanel.add(selectedContractorProfile, "profile");
        ((CardLayout)searchOrProfilePanel.getLayout()).show(searchOrProfilePanel, "profile");
    }
}
