package MainWindow;

import Booking.SchedulePanel;
import Browse.BrowsePanel;
import Login.Panel.LoginPanel;
import ProfilePages.Customer.CustomerProfileContainerPanel;
import ProfilePages.Contractor.ContractorProfileContainerPanel;
import Messaging.MessagingPanel;
import Server.ServerConnection;

import Users.User;
import Users.UserID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    // References to databases/servers
    private ServerConnection serverConnection = new ServerConnection();

    // Current user data
    private String username;
    private String password;
    private String clientOrContractor;
    static boolean IsContractor = true;
    private User currentUser;

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

    // Content panel, children, and settings
    enum LocalPanelNames {
        LOGIN,
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
    private SchedulePanel schedulePanel = new SchedulePanel(this);
    private BrowsePanel browsePanel = new BrowsePanel(this);

    public MainWindow(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(mainPanelMinSize);
        this.setContentPane(mainPanel);
        this.pack();

        clientOrContractor = new String();

        // Populate the content panel with all the different panels we plan on using
        // Using a cardLayout we can then switch between them easily
        contentPanel.add(loginPanel, LocalPanelNames.LOGIN.toString());
        contentPanel.add(browsePanel, LocalPanelNames.BROWSE.toString());
        contentPanel.add(schedulePanel, LocalPanelNames.SCHEDULE.toString());
        contentPanel.add(messagingPanel, LocalPanelNames.MESSAGING.toString());
        contentPanel.add(new JPanel(), LocalPanelNames.HISTORY.toString());


        // Home page is login screen
        navigationPanel.setVisible(false);
        ((CardLayout)contentPanel.getLayout()).show(contentPanel, LocalPanelNames.LOGIN.toString());

        // Buttons switch contentPanel between previously added panels
        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setEnterKeyAction(null);
                ((CardLayout)contentPanel.getLayout()).show(contentPanel, LocalPanelNames.BROWSE.toString());
            }
        });
        calendarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setEnterKeyAction(null);
                ((CardLayout)contentPanel.getLayout()).show(contentPanel, LocalPanelNames.SCHEDULE.toString());
            }
        });
        messagingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changePageToMessaging();
            }
        });
        transactionHistoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setEnterKeyAction(null);
                ((CardLayout)contentPanel.getLayout()).show(contentPanel, LocalPanelNames.HISTORY.toString());
            }
        });
        profileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setEnterKeyAction(null);

                //(amon) I think when the person logs in we should set a bool variable true or false
                //depending on if they are a customer or contractor, so the right profile can be displayed
                //and also so the right edit screen is given. I just made one here as placeholder until.
                //myProfile = true;
                if(clientOrContractor.equals("client")) {
                    //customerProfile.myprofile(); //called because we clicked "my profile"

                    CustomerProfileContainerPanel customerProfileContainer = null;
                    customerProfileContainer = new CustomerProfileContainerPanel(MainWindow.this);
                    contentPanel.add(customerProfileContainer, LocalPanelNames.PROFILE_CLIENT.toString());
                    ((CardLayout) contentPanel.getLayout()).show(contentPanel, LocalPanelNames.PROFILE_CLIENT.toString());
                }
                else{
                    //contractorProfile.myprofile(); //called because we clicked "my profile"
                    ContractorProfileContainerPanel contractorProfileContainer = new ContractorProfileContainerPanel(MainWindow.this);
                    contentPanel.add(contractorProfileContainer, LocalPanelNames.PROFILE_CONTRACTOR.toString());
                    ((CardLayout) contentPanel.getLayout()).show(contentPanel, LocalPanelNames.PROFILE_CONTRACTOR.toString());
                }

            }
        });
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setEnterKeyAction(null);
                // end login session and destruct current user
                currentUser = null;
                navigationPanel.setVisible(false);
                loginPanel.setblank();
                ((CardLayout)contentPanel.getLayout()).show(contentPanel, LocalPanelNames.LOGIN.toString());
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
            currentUser = serverConnection.getUserFromCredentials(new UserID(username), password);
            browseButton.setVisible(false);
            ((CardLayout)contentPanel.getLayout()).show(contentPanel, LocalPanelNames.SCHEDULE.toString());
        }
        else if(userType.equals("client"))
        {
            currentUser = serverConnection.getUserFromCredentials(new UserID(username), password);
            browseButton.setVisible(true);
            ((CardLayout)contentPanel.getLayout()).show(contentPanel, LocalPanelNames.BROWSE.toString());
        }

        schedulePanel = new SchedulePanel(this);

        // Output user name and user type to console just to confirm who is logged in
        //System.out.println(currentUser.getUserID().toString() + " is logged in as a " + currentUser.getUserType());
    }

    public void changePageToMessaging(){
        messagingPanel.pageSelected();
        setEnterKeyAction(messagingPanel.getDefaultButton());
        ((CardLayout)contentPanel.getLayout()).show(contentPanel, LocalPanelNames.MESSAGING.toString());
    }

    public void changePageToMessaging(UserID chatRecipient){
        messagingPanel.pageSelected(chatRecipient);
        setEnterKeyAction(messagingPanel.getDefaultButton());
        ((CardLayout)contentPanel.getLayout()).show(contentPanel, LocalPanelNames.MESSAGING.toString());
    }

    // Successful Sign Up (does same thing as login)
    //          maybe this should take in the new user credentials
    public void successfulSignUp(String userType){
        // get username and password for login
        // using credentials create user and then...
        navigationPanel.setVisible(false);
        ((CardLayout)contentPanel.getLayout()).show(contentPanel, LocalPanelNames.LOGIN.toString());

    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public String getUsername() { return this.username; }

    public String getClientOrContractor() { return this.clientOrContractor; }

    public ServerConnection getServerConnection() {
        return serverConnection;
    }

    public void setEnterKeyAction(JButton button) {
        // Pass in null or a button to set what the enter/return key does
        this.getRootPane().setDefaultButton(button);
    }

    public static void main(String[] args) {
        JFrame frame = new MainWindow("CMPT 275 Project PoC - Group 20");
        frame.setVisible(true);

    }
}
