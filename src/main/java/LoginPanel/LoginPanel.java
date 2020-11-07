package LoginPanel;

import MainWindow.MainWindow;
import SignupPanel.SignupPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel{
    private JPanel loginPanel;
    private JLabel loginLabel;
    private JTextField contractorUsernameTextField;
    private JTextField clientUsernameTextField;
    private JPasswordField contractorPasswordField;
    private JPasswordField clientPasswordField;
    private JButton contractorLoginButton;
    private JButton clientLoginButton;
    private JButton showContractorSignUpButton;
    private JButton showClientSignUpButton;
    private JLabel contractorLoginLabel;
    private JLabel contractorUsernameLabel;
    private JLabel contractorPasswordLabel;
    private JLabel createAccountLabel;
    private JLabel clientLoginLabel;
    private JLabel clientUsernameLabel;
    private JLabel clientPasswordLabel;
    private JLabel welcomeLabel;
    private JLabel contractorLoginWarningLabel;
    private JLabel clientLoginWarningLabel;
    private JPanel signUpOrLoginSelectButtonPanel;
    private JPanel signUpOrLoginContentPanel;
    private JButton signUpButton;

    private JButton backToLoginButton = new JButton("Back to Login");

    private JPanel loginContentPanel;
    private SignupPanel contractorSignupPanel = new SignupPanel("contractor");
    private SignupPanel clientSignupPanel = new SignupPanel("client");
    private String userType;

    // Login credentials
    private String username;
    private String password;

    // Sign up details
    private String signUpUsername;
    private String signUpName;
    private String signUpPassword;

    public LoginPanel(final MainWindow mainWindow) {

        add(loginPanel);
        // Hide login warning labels
        contractorLoginWarningLabel.setVisible(false);
        clientLoginWarningLabel.setVisible(false);

        // Hide sign up button
        signUpButton.setVisible(false);
        backToLoginButton.setVisible(false);
        signUpOrLoginContentPanel.setVisible(true);

        // Add login and sign up panels to card
        signUpOrLoginContentPanel.add(loginContentPanel, "login");
        signUpOrLoginContentPanel.add(clientSignupPanel, "contractorSignup");
        signUpOrLoginContentPanel.add(clientSignupPanel, "clientSignup");


        ((CardLayout)signUpOrLoginContentPanel.getLayout()).show(signUpOrLoginContentPanel,"login");

        // Contractor Login
        contractorLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clientLoginWarningLabel.setVisible(false);
                contractorLoginWarningLabel.setVisible(false);
                // Check that username and password are filled
                userType = "contractor";
                username = contractorUsernameTextField.getText();
                password = contractorPasswordField.getPassword().toString();
                if(username.equals("") ||  password.equals("") )
                {
                    contractorLoginWarningLabel.setText("Error: Please enter all fields.");
                    contractorLoginWarningLabel.setVisible(true);
                }
                else
                {
                    // UNWASIKETEST check that username and password are in database if not set warning label. If so, proceed
                    // From main window, swap this panel for the contractor "home page" ie bookings panel
                    mainWindow.successfulLogin("contractor");
                }
            }
        });

        // Client Login
        clientLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clientLoginWarningLabel.setVisible(false);
                contractorLoginWarningLabel.setVisible(false);
                // Check that username and password are filled
                userType = "client";
                username = clientUsernameTextField.getText();
                password = clientPasswordField.getPassword().toString();
                if(username.equals("") || password.equals("") )
                {
                    clientLoginWarningLabel.setText("Error: Please enter all fields.");
                    clientLoginWarningLabel.setVisible(true);
                }
                else
                {
                    // UNWASIKETEST check that username and password are in database if not set warning label. If so, proceed
                    // From main window, swap this panel for the client "home page"
                    mainWindow.successfulLogin("client");
                }
            }
        });

        // Sign Up Button
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(userType.equals("contractor")){
                    signUpUsername = contractorSignupPanel.getUsername();
                    signUpName = contractorSignupPanel.getName();
                    signUpPassword = contractorSignupPanel.getPassword();
                }
                else if(userType.equals("client")){
                    signUpUsername = clientSignupPanel.getUsername();
                    signUpName = clientSignupPanel.getName();
                    signUpPassword = clientSignupPanel.getPassword();
                }
                if (signUpUsername.equals("") || signUpName.equals("") || signUpPassword.equals(""))
                {
                    // error message
                    return;
                }
                // pass credentials into this sign up function
                mainWindow.successfulSignUp(userType);
            }
        });

        // Show Contractor Sign Up Button
        showContractorSignUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userType = "contractor";
                signUpButton.setVisible(true);
                backToLoginButton.setVisible(true);
                signUpOrLoginSelectButtonPanel.setVisible(false);
                ((CardLayout)signUpOrLoginContentPanel.getLayout()).show(signUpOrLoginContentPanel,"contractorSignup");
            }
        });

        // Show Client Sign Up Button
        showClientSignUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userType = "client";
                signUpButton.setVisible(true);
                backToLoginButton.setVisible(true);
                signUpOrLoginSelectButtonPanel.setVisible(false);
                ((CardLayout)signUpOrLoginContentPanel.getLayout()).show(signUpOrLoginContentPanel,"clientSignup");
            }
        });

        // Back to Login Button
        backToLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signUpButton.setVisible(false);
                backToLoginButton.setVisible(false);
                signUpOrLoginSelectButtonPanel.setVisible(true);
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
