package Login.Panel;

import Login.ContentPanel.LoginContentPanel;
import MainWindow.MainWindow;
import Login.SignupContentPanel.SignupContentPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginPanel extends JPanel{
    private JPanel loginPanel;

    // login database to verify login details
    private HashMap<String, String> loginDatabase;

    // card layout to show login or signup screens
    private JPanel signUpOrLoginContentPanel;
    private LoginContentPanel loginContentPanel = new LoginContentPanel();
    private SignupContentPanel contractorSignupContentPanel = new SignupContentPanel("contractor");
    private SignupContentPanel clientSignupContentPanel = new SignupContentPanel("client");

    // Content present during login screen
    private JPanel loginButtonsPanel;
    private JButton contractorLoginButton;
    private JButton clientLoginButton;
    private JLabel contractorLoginWarningLabel;
    private JLabel clientLoginWarningLabel;
    private JButton showContractorSignUpButton;
    private JButton showClientSignUpButton;

    // Content present during signup screen
    private JPanel signUpButtonsPanel;
    private JButton signUpButton;
    private JLabel signUpWarningLabel;
    private JButton backToLoginButton;

    private String userType;

    // Login credentials
    private String username;
    private String password;
    public String getUsername(){return username;}
    public String getPassword(){return password;}

    // Sign up details
    private String signUpUsername;
    private String signUpName;
    private String signUpPassword;

    public LoginPanel(final MainWindow mainWindow) {

        add(loginPanel);

        // initialise loginDatabase attribute here
        loginDatabase = new HashMap<String, String>();
        loginDatabase.put("Amon", "password1");
        loginDatabase.put("Brigham", "password2");
        loginDatabase.put("Uchechi", "password3");
        loginDatabase.put("user", "pass");

        signUpOrLoginContentPanel.setVisible(true);

        //Add login and sign up panels to card
        signUpOrLoginContentPanel.add(loginContentPanel, "login");
        signUpOrLoginContentPanel.add(contractorSignupContentPanel, "contractorSignup");
        signUpOrLoginContentPanel.add(clientSignupContentPanel, "clientSignup");

        // Show login content panel and login buttons panel during startup
        ((CardLayout)signUpOrLoginContentPanel.getLayout()).show(signUpOrLoginContentPanel,"login");
        signUpButtonsPanel.setVisible(false);
        loginButtonsPanel.setVisible(true);

        // Contractor Login
        contractorLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clientLoginWarningLabel.setText("");
                contractorLoginWarningLabel.setText("");
                // Check that username and password are filled
                userType = "contractor";
                username = loginContentPanel.getContractorUsername();
                password = loginContentPanel.getContractorPassword();
                if(username.equals("") ||  password.equals("") )
                {
                    contractorLoginWarningLabel.setText("Error: Please enter all fields.");
                }
                else
                {
                    // check that username and password are in database if not set warning label. If so, proceed
                    // From main window, swap this panel for the contractor "home page" ie bookings panel
                    if (loginDatabase.containsKey(username)){
                        if (loginDatabase.get(username).equals(password)){
                            mainWindow.successfulLogin("contractor");
                        }
                        else{
                            contractorLoginWarningLabel.setText("Invalid credentials.");
                        }
                    }
                    else{
                        contractorLoginWarningLabel.setText("User does not exist. Please sign up below.");
                    }
                }
            }
        });

        // Client Login
        clientLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clientLoginWarningLabel.setText("");
                contractorLoginWarningLabel.setText("");
                // Check that username and password are filled
                userType = "client";
                username = loginContentPanel.getClientUsername();
                password = loginContentPanel.getClientPassword();
                if(username.equals("") || password.equals("") )
                {
                    clientLoginWarningLabel.setText("Error: Please enter all fields.");
                }
                else
                {
                    // check that username and password are in database if not set warning label. If so, proceed
                    // From main window, swap this panel for the contractor "home page" ie bookings panel
                    if (loginDatabase.containsKey(username)){
                        if (loginDatabase.get(username).equals(password)){
                            mainWindow.successfulLogin("client");
                        }
                        else{
                            clientLoginWarningLabel.setText("Invalid credentials.");
                        }
                    }
                    else{
                        clientLoginWarningLabel.setText("User does not exist. Please sign up below.");
                    }
                }
            }
        });

        // Sign Up Button
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signUpWarningLabel.setText("");
                if(userType.equals("contractor")){
                    signUpUsername = contractorSignupContentPanel.getUsername();
                    signUpName = contractorSignupContentPanel.getName();
                    signUpPassword = contractorSignupContentPanel.getPassword();
                }
                else if(userType.equals("client")){
                    signUpUsername = clientSignupContentPanel.getUsername();
                    signUpName = clientSignupContentPanel.getName();
                    signUpPassword = clientSignupContentPanel.getPassword();
                }

                if (signUpUsername.equals("") || signUpName.equals("") || signUpPassword.equals("")){
                    signUpWarningLabel.setText("Please enter all fields.");
                }
                else {
                    // execute a sign up function
                    mainWindow.successfulSignUp(userType);
                }
            }
        });

        // Show Contractor Sign Up Button
        showContractorSignUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signUpWarningLabel.setText("");
                userType = "contractor";
                signUpButtonsPanel.setVisible(true);
                loginButtonsPanel.setVisible(false);
                ((CardLayout)signUpOrLoginContentPanel.getLayout()).show(signUpOrLoginContentPanel,"contractorSignup");
            }
        });

        // Show Client Sign Up Button
        showClientSignUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signUpWarningLabel.setText("");
                userType = "client";
                signUpButtonsPanel.setVisible(true);
                loginButtonsPanel.setVisible(false);
                ((CardLayout)signUpOrLoginContentPanel.getLayout()).show(signUpOrLoginContentPanel,"clientSignup");
            }
        });

        // Back to Login Button
        backToLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contractorLoginWarningLabel.setText("");
                clientLoginWarningLabel.setText("");
                signUpButtonsPanel.setVisible(false);
                loginButtonsPanel.setVisible(true);
                ((CardLayout)signUpOrLoginContentPanel.getLayout()).show(signUpOrLoginContentPanel,"login");
            }
        });
    }


    public static void main(String[] args) {
        /*JFrame randWindow = new JFrame("Login Screen");
        randWindow.setVisible(true);
        randWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LoginPanel panel1 = new LoginPanel(new MainWindow("Login Panel Demo"));
        randWindow.add(panel1);
        randWindow.pack();*/
    }
}
