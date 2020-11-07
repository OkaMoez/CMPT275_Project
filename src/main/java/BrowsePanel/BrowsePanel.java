package BrowsePanel;

import javax.swing.*;

public class BrowsePanel extends JPanel{
    private JTextField browseSearchBar;
    private JPanel searchResultsPanel;
    private JPanel searchCategoryButtonsPanel;
    private JButton searchByNameButton;
    private JButton searchByLocationButton;
    private JButton searchByRatingsButton;
    private JPanel mainPanel;

    public BrowsePanel() {
        add(mainPanel);
        searchCategoryButtonsPanel.setVisible(true);
        searchByNameButton.setVisible(true);
        searchResultsPanel.setVisible(true);
    }
}
