package MainWindow;

import ContractorProfile.ContractorProfile;
import CustomerProfile.CustomerProfile;
import Messaging.InquiryPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    // Main panel and settings
    private JPanel mainPanel;
    private Dimension mainPanelMinSize = new Dimension(800, 500);

    // Navigation bar, children, and settings
    private JPanel navigationPanel;
    private JButton browseButton;
    private JButton transactionHistoryButton;
    private JButton inquiriesButton;
    private JButton calendarButton;
    private JButton logoutButton;
    private JButton profileButton;

    // Content panel, children, and settings
    private JPanel contentPanel;

    private InquiryPanel inquiryPanel = new InquiryPanel();
    private CustomerProfile customerProfile = new CustomerProfile();
    private ContractorProfile contractorProfile = new ContractorProfile();

    public MainWindow(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(mainPanelMinSize);
        this.setContentPane(mainPanel);
        this.pack();

        // Populate the content panel with all the different panels we plan on using
        // Using a cardLayout we can then switch between them easily
        contentPanel.add(inquiryPanel, "inquiry");
        contentPanel.add(new JPanel(), "calendar");
        contentPanel.add(new JPanel(), "browse");
        contentPanel.add(new JPanel(), "history");
        contentPanel.add(customerProfile, "customerprofile");
        contentPanel.add(contractorProfile, "contractorprofile");

        // Buttons switch contentPanel between previously added panels
        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)contentPanel.getLayout()).show(contentPanel,"browse");
            }
        });
        calendarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)contentPanel.getLayout()).show(contentPanel,"calendar");
            }
        });
        inquiriesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)contentPanel.getLayout()).show(contentPanel,"inquiry");
            }
        });
        transactionHistoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)contentPanel.getLayout()).show(contentPanel,"history");
            }
        });
        profileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //(amon) I think when the person logs in we should set a bool variable true or false
                //depending on if they are a customer or contractor, so the right profile can be displayed
                //and also so the right edit screen is given. I just made one here as placeholder until.

                boolean IsContractor = false;
                if(IsContractor) {
                    customerProfile.myprofile(); //called because we clicked "my profile"
                    ((CardLayout) contentPanel.getLayout()).show(contentPanel, "customerprofile");
                }
                else{
                    contractorProfile.myprofile(); //called because we clicked "my profile"
                    ((CardLayout) contentPanel.getLayout()).show(contentPanel, "contractorprofile");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new MainWindow("CMPT 275 Project PoC - Group 20");
        frame.setVisible(true);
    }
}
