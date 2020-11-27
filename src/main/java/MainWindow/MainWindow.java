package MainWindow;

import Booking.SchedulePanel;
import Browse.BrowsePanel;
import Login.Panel.LoginPanel;
import ProfilePages.Customer.CustomerProfileContainer;
import ProfilePages.Contractor.ContractorProfileContainer;
import Messaging.MessagingPanel;
import Users.Client;
import Users.Contractor;
import Users.User;
import Users.UserID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    // Current user data
    private String username;
    private String password;
    private String clientOrContractor;

    private User currentUser;

    // (Uchechi) login session timer attribute to be implemented

    // Main panel and settings
    private JPanel mainPanel;
    private Dimension mainPanelMinSize = new Dimension(800, 500);

    // Navigation bar, children, and settings
    private JPanel navigationPanel;
    private JButton browseButton;
    private JButton transactionHistoryButton;
    private JButton messagingButton;
    private JButton calendarButton;
    private JButton logoutButton;
    private JButton profileButton;
    static public boolean myProfile = true;
    static boolean IsContractor = true;
    // Content panel, children, and settings
    enum LocalPanelNames {
        BROWSE,
        SCHEDULE,
        MESSAGING,
        HISTORY,
        PROFILE_CLIENT,
        PROFILE_CONTRACTOR
    }

    private JPanel contentPanel;
    private LoginPanel loginPanel = new LoginPanel(this);
    private MessagingPanel messagingPanel = new MessagingPanel(this);
    private SchedulePanel schedulePanel = new SchedulePanel();
    private BrowsePanel browsePanel = new BrowsePanel();
    private CustomerProfileContainer customerProfileContainer = new CustomerProfileContainer();
    private ContractorProfileContainer contractorProfileContainer = new ContractorProfileContainer();


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
        contentPanel.add(messagingPanel, MainWindow.LocalPanelNames.MESSAGING.toString());
        contentPanel.add(new JPanel(), MainWindow.LocalPanelNames.HISTORY.toString());
        contentPanel.add(customerProfileContainer, MainWindow.LocalPanelNames.PROFILE_CLIENT.toString());
        contentPanel.add(contractorProfileContainer, MainWindow.LocalPanelNames.PROFILE_CONTRACTOR.toString());

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
        messagingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((CardLayout)contentPanel.getLayout()).show(contentPanel, MainWindow.LocalPanelNames.MESSAGING.toString());
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



                if(!IsContractor) {
                    //CustomerProfile.myprofile(); //called because we clicked "my profile"
                if(clientOrContractor.equals("client")) {
                    //customerProfile.myprofile(); //called because we clicked "my profile"
                    ((CardLayout) contentPanel.getLayout()).show(contentPanel, MainWindow.LocalPanelNames.PROFILE_CLIENT.toString());
                }
                else{
                    //contractorProfile.myprofile(); //called because we clicked "my profile"
                    ((CardLayout) contentPanel.getLayout()).show(contentPanel, MainWindow.LocalPanelNames.PROFILE_CONTRACTOR.toString());
                }

            }}
        });
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // end login session and destruct current user
                currentUser = null;
                navigationPanel.setVisible(false);
                ((CardLayout)contentPanel.getLayout()).show(contentPanel,"login");
            }
        });

    }

    // Successful Login
    //          this should take in the login credentials
    public void successfulLogin(String userType){
        username = loginPanel.getUsername();
        password = loginPanel.getPassword();
        clientOrContractor = userType;
        navigationPanel.setVisible(true);
        if(userType.equals("contractor"))
        {
            currentUser = new Contractor(new UserID(username), password);
            browseButton.setVisible(false);
            ((CardLayout)contentPanel.getLayout()).show(contentPanel, MainWindow.LocalPanelNames.SCHEDULE.toString());
        }
        else if(userType.equals("client"))
        {
            currentUser = new Client(new UserID(username), password);
            ((CardLayout)contentPanel.getLayout()).show(contentPanel, MainWindow.LocalPanelNames.BROWSE.toString());
        }

        // Output user name and user type to console just to confirm who is logged in
        System.out.println(currentUser.getUserID() + " is logged in as a " + currentUser.getUserType());
    }

    // Successful Sign Up (does same thing as login)
    //          maybe this should take in the new user credentials
    public void successfulSignUp(String userType){
        // get username and password for login
        // using credentials create user and then...
        successfulLogin(userType);
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public static void main(String[] args) {
        JFrame frame = new MainWindow("CMPT 275 Project PoC - Group 20");
        frame.setVisible(true);
    }
}
