package MainWindow;

import LoginPanel.LoginPanel;
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

    // Content panel, children, and settings
    private JPanel contentPanel;
    private LoginPanel loginPanel = new LoginPanel(this);
    private InquiryPanel inquiryPanel = new InquiryPanel();

    public MainWindow(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(mainPanelMinSize);
        this.setContentPane(mainPanel);
        this.pack();

        // Populate the content panel with all the different panels we plan on using
        // Using a cardLayout we can then switch between them easily
        contentPanel.add(loginPanel, "login");
        contentPanel.add(inquiryPanel, "inquiry");
        contentPanel.add(new JPanel(), "calendar");
        contentPanel.add(new JPanel(), "browse");
        contentPanel.add(new JPanel(), "history");

        // Home page is login screen
        navigationPanel.setVisible(false);
        ((CardLayout)contentPanel.getLayout()).show(contentPanel,"login");

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
    }

    // Successful Login
    //          this should take in the login credentials
    public void successfulLogin(String userType){
        navigationPanel.setVisible(true);
        if(userType.equals("contractor"))
        {
            ((CardLayout)contentPanel.getLayout()).show(contentPanel,"bookings");
        }
        else if(userType.equals("client"))
        {
            ((CardLayout)contentPanel.getLayout()).show(contentPanel,"browse");
        }
    }

    // Successful Sign Up (does same thing as login)
    //          maybe this should take in the new user credentials
    public void successfulSignUp(String userType){
        // using credentials create user and then...
        successfulLogin(userType);
    }

    // Called when user goes back to login screen from signup screen
    public void backToLogin(){
        loginPanel = new LoginPanel(this);
    }

    public static void main(String[] args) {
        JFrame frame = new MainWindow("CMPT 275 Project PoC - Group 20");
        frame.setVisible(true);
    }
}
