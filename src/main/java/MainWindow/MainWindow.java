package MainWindow;

import Booking.SchedulePanel;
import BrowsePanel.BrowsePanel;
import LoginPanel.LoginPanel;
import ContractorProfile.ContractorProfile;
import CustomerProfile.CustomerProfile;
import Messaging.InquiryPanel;
import SignupPanel.SignupPanel;
import sun.rmi.runtime.Log;

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
    enum LocalPanelNames {
        BROWSE,
        SCHEDULE,
        INQUIRY,
        HISTORY,
        PROFILE_CLIENT,
        PROFILE_CONTRACTOR
    }

    private JPanel contentPanel;
    private LoginPanel loginPanel = new LoginPanel(this);
    private InquiryPanel inquiryPanel = new InquiryPanel();
    private SchedulePanel schedulePanel = new SchedulePanel();
    private BrowsePanel browsePanel = new BrowsePanel();
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
        contentPanel.add(loginPanel, "login");
        contentPanel.add(browsePanel, MainWindow.LocalPanelNames.BROWSE.toString());
        contentPanel.add(schedulePanel, MainWindow.LocalPanelNames.SCHEDULE.toString());
        contentPanel.add(inquiryPanel, MainWindow.LocalPanelNames.INQUIRY.toString());
        contentPanel.add(new JPanel(), MainWindow.LocalPanelNames.HISTORY.toString());
        contentPanel.add(customerProfile, MainWindow.LocalPanelNames.PROFILE_CLIENT.toString());
        contentPanel.add(contractorProfile, MainWindow.LocalPanelNames.PROFILE_CONTRACTOR.toString());

        // Home page is login screen
        navigationPanel.setVisible(false);
        ((CardLayout)contentPanel.getLayout()).show(contentPanel,"login");

        // Buttons switch contentPanel between previously added panels
        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)contentPanel.getLayout()).show(contentPanel, MainWindow.LocalPanelNames.BROWSE.toString());
            }
        });
        calendarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)contentPanel.getLayout()).show(contentPanel, MainWindow.LocalPanelNames.SCHEDULE.toString());
            }
        });
        inquiriesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)contentPanel.getLayout()).show(contentPanel, MainWindow.LocalPanelNames.INQUIRY.toString());
            }
        });
        transactionHistoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)contentPanel.getLayout()).show(contentPanel, MainWindow.LocalPanelNames.HISTORY.toString());
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
                    ((CardLayout) contentPanel.getLayout()).show(contentPanel, MainWindow.LocalPanelNames.PROFILE_CLIENT.toString());
                }
                else{
                    contractorProfile.myprofile(); //called because we clicked "my profile"
                    ((CardLayout) contentPanel.getLayout()).show(contentPanel, MainWindow.LocalPanelNames.PROFILE_CONTRACTOR.toString());
                }

            }
        });

    }

    // Successful Login
    //          this should take in the login credentials
    public void successfulLogin(String userType){
        navigationPanel.setVisible(true);
        if(userType.equals("contractor"))
        {
            browseButton.setVisible(false);
            ((CardLayout)contentPanel.getLayout()).show(contentPanel, MainWindow.LocalPanelNames.SCHEDULE.toString());
        }
        else if(userType.equals("client"))
        {
            ((CardLayout)contentPanel.getLayout()).show(contentPanel, MainWindow.LocalPanelNames.BROWSE.toString());
        }
    }

    // Successful Sign Up (does same thing as login)
    //          maybe this should take in the new user credentials
    public void successfulSignUp(String userType){
        // using credentials create user and then...
        successfulLogin(userType);
    }

    public static void main(String[] args) {
        JFrame frame = new MainWindow("CMPT 275 Project PoC - Group 20");
        frame.setVisible(true);
    }
}
