package Browse;

import MainWindow.MainWindow;
import ProfilePages.Contractor.ContractorProfileContainerPanel;
import Users.Contractor;
import Users.User;
import Users.UserID;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import static Server.UserCredentialsServer.placeholderUserMap;

public class BrowseSearchPanel extends JPanel{
    private JPanel mainPanel;
    private JTextField browseSearchBar;
    private JLabel searchCategoryLabel;
    private JComboBox searchCategorySelectionBox;
    private JButton searchButton;
    private JPanel searchResultsPanel;
    private JComboBox sortCategorySelectionBox;
    private JLabel sortByLabel;
    private JTable searchResultsTable;
    private JButton resetSearchButton;
    private ContractorProfileContainerPanel selectedContractorProfile;

    private Vector<User> contractorList;
    private Vector<User> contractorSearchResults = new Vector<User> ();
    private Vector<String> stringContractorSearchResults;

    public BrowseSearchPanel(BrowsePanel browsePanel) {
        add(mainPanel);

        contractorList = new Vector<User> ();

        // Initialize contractorList with contractors in user credential server
        Iterator userMapIterator = placeholderUserMap.entrySet().iterator();

        while(userMapIterator.hasNext())
        {
            Map.Entry<UserID, User> userElement = (Map.Entry<UserID, User>)userMapIterator.next();
            if (userElement.getValue().getUserType().equals("contractor")) {
                contractorList.addElement(userElement.getValue());
            }
        }

        // Initialise search results table with complete directory
        contractorSearchResults = contractorList;
        displayResults();

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchString = browseSearchBar.getText();
                String searchCategory = (String)searchCategorySelectionBox.getSelectedItem();

                // load data from csv to some kind of array

                if (searchString.equals("")) {
                    //do nothing
                }
                else {
                    // get search category
                    // search array by category selected and update results list
                    searchContractors(searchString, searchCategory);
                    displayResults();
                }
            }
        });

        resetSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contractorSearchResults = contractorList;
                displayResults();
            }
        });

        /*sortCategorySelectionBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // arrange search results list by selected category
            }
        });*/

        searchResultsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = searchResultsTable.rowAtPoint(e.getPoint());
                selectedContractorProfile = new ContractorProfileContainerPanel(browsePanel, (Contractor)contractorSearchResults.get(row));
                selectedContractorProfile.getBackButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        browsePanel.backToSearch();
                    }
                });
                browsePanel.showSelectedProfile(selectedContractorProfile);
            }
        });
    }

    public void searchContractors(String searchString, String searchCategory) {
        // Clear search results vector for new search
        contractorSearchResults = new Vector<User>();

        // Parse through contractorList to get array of contractors matching search to fill contractorSearchResults
        for (int i = 0; i < contractorList.size(); i++) {
            if(searchCategory.equals("Business Name")) {
                if (contractorList.get(i).getBusinessName().contains(searchString)) {
                    contractorSearchResults.addElement(contractorList.get(i));
                }
            }
            else if(searchCategory.equals("Service")) {
                if (contractorList.get(i).getSubUserType().contains(searchString)) {
                    contractorSearchResults.addElement(contractorList.get(i));
                }
            }
        }
    }

    public void displayResults() {
        DefaultTableModel model = new DefaultTableModel(){public boolean isCellEditable(int row, int column){return false;}};
        //model.setColumnIdentifiers(new Object[]{"Business Name", "Rating", "Phone"});
        model.setColumnIdentifiers(new Object[]{"Business Name", "Phone"});

        Object[] row = new Object[2];
        for (int i = 0; i < contractorSearchResults.size(); i++) {
            row[0] = contractorSearchResults.get(i).getBusinessName();
            //row[1] = contractorSearchResults.get(i).getRating();
            row[1] = contractorSearchResults.get(i).getNumber();

            model.addRow(row);
        }

        searchResultsTable.setModel(model);
    }
}
