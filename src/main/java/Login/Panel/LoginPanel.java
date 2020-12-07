package Login.Panel;

import Login.ContentPanel.LoginContentPanel;
import MainWindow.MainWindow;
import Login.SignupContentPanel.SignupContentPanel;
import Server.UserCredentialServerInterface;
import Server.UserCredentialsServer;
import Users.Client;
import Users.Contractor;
import Users.CsvSearch;
import Users.UserID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
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
    private String[] signUpUser= new String[9];
    private CsvSearch csvSearch = new CsvSearch();
    private Client tempClient;
    private Contractor tempContractor;

    UserCredentialServerInterface loginServer;

    public LoginPanel(final MainWindow mainWindow) throws IOException {
        loginServer = mainWindow.getServerConnection();

        add(loginPanel);

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
                    UserCredentialServerInterface.LoginResult loginResult = loginServer.tryLoginCredentials(new UserID(username), password);
                    if (loginResult.equals(UserCredentialServerInterface.LoginResult.BAD_USERNAME)){
                        contractorLoginWarningLabel.setText("User does not exist. Please sign up below.");
                        return;
                    }
                    if (loginResult.equals(UserCredentialServerInterface.LoginResult.BAD_PASSWORD)){
                        contractorLoginWarningLabel.setText("Invalid credentials.");
                        return;
                    }
                    if (loginResult.equals(UserCredentialServerInterface.LoginResult.SUCCESS_CONTRACTOR)) {
                        mainWindow.successfulLogin("contractor");
                    }
                    if (loginResult.equals(UserCredentialServerInterface.LoginResult.SUCCESS_CLIENT)){
                        contractorLoginWarningLabel.setText("Wrong user type, sign in as a client");
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
                else {
                    // check that username and password are in database if not set warning label. If so, proceed
                    // From main window, swap this panel for the contractor "home page" ie bookings panel
                    UserCredentialServerInterface.LoginResult loginResult = loginServer.tryLoginCredentials(new UserID(username), password);
                    if (loginResult.equals(UserCredentialServerInterface.LoginResult.BAD_USERNAME)) {
                        clientLoginWarningLabel.setText("User does not exist. Please sign up below.");
                        return;
                    }
                    if (loginResult.equals(UserCredentialServerInterface.LoginResult.BAD_PASSWORD)) {
                        clientLoginWarningLabel.setText("Invalid credentials.");
                        return;
                    }
                    if(loginResult.equals(UserCredentialServerInterface.LoginResult.SUCCESS_CLIENT)){
                    mainWindow.successfulLogin("client");
                    }
                    if (loginResult.equals(UserCredentialServerInterface.LoginResult.SUCCESS_CONTRACTOR)){
                        contractorLoginWarningLabel.setText("Wrong user type, sign in as a contractor");
                    }
                }
            }
        });

        // Sign Up Button
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signUpWarningLabel.setText("");

                if(userType.equals("contractor")){
                    signUpUser[0] = contractorSignupContentPanel.getUsername();
                    signUpUser[1] ="contractor";
                    signUpUser[2] =contractorSignupContentPanel.getPassword();
                    signUpUser[3] =contractorSignupContentPanel.getName();
                    signUpUser[4] ="null";
                    signUpUser[5] =contractorSignupContentPanel.getNumber();
                    signUpUser[6] =contractorSignupContentPanel.getAddressOrBusiness();
                    signUpUser[7]= "no ratings";
                    signUpUser[8]=contractorSignupContentPanel.getSubUserType();
                    if(signUpUser[2].equals("badPassword")){
                        signUpWarningLabel.setText("Passwords do not match");
                        return;
                    }
                    for(int i=0;i<8;i++){
                        if(signUpUser[i].contains(",")){
                            signUpWarningLabel.setText("Please enter values without including commas");
                            return;
                        }
                    }

                    tempContractor = new Contractor(new UserID(signUpUser[0]), signUpUser[2]);
                    if(UserCredentialsServer.placeholderUserMap.containsKey(tempContractor.getUserID())){
                        signUpWarningLabel.setText("Username already exists");
                        return;
                    }
                    tempContractor.setName(signUpUser[3]);
                    tempContractor.setBusinessName(signUpUser[6]);
                    tempContractor.setNumber(signUpUser[5]);
                    tempContractor.setSubUserType(signUpUser[8]);
                    UserCredentialsServer.placeholderUserMap.put(tempContractor.getUserID(),tempContractor);

                }
                else if(userType.equals("client")){
                    signUpUser[0] = clientSignupContentPanel.getUsername();
                    signUpUser[1] ="client";
                    signUpUser[2] =clientSignupContentPanel.getPassword();
                    signUpUser[3] =clientSignupContentPanel.getName();
                    signUpUser[4] =clientSignupContentPanel.getAddressOrBusiness();
                    signUpUser[5] =clientSignupContentPanel.getNumber();
                    signUpUser[6] ="null";
                    signUpUser[7] ="null";
                    signUpUser[8] ="null";
                    if(signUpUser[2].equals("badPassword")){
                        signUpWarningLabel.setText("Passwords do not match");
                        return;
                    }
                    for(int i=0;i<8;i++){
                        if(signUpUser[i].contains(",")){
                            signUpWarningLabel.setText("Please enter values without including commas");
                            return;
                        }
                    }
                    tempClient = new Client(new UserID(signUpUser[0]), signUpUser[2]);
                    if(UserCredentialsServer.placeholderUserMap.containsKey(tempClient.getUserID())){
                        signUpWarningLabel.setText("Username already exists");
                        return;
                    }
                    tempClient.setName(signUpUser[3]);
                    tempClient.setAddress(signUpUser[4]);
                    tempClient.setNumber(signUpUser[5]);
                    UserCredentialsServer.placeholderUserMap.put(tempClient.getUserID(),tempClient);

                }

                if (Arrays.asList(signUpUser).contains("")){
                    signUpWarningLabel.setText("Please enter all fields.");
                }
                else {
                    // execute a sign up function
                    try {
                        csvSearch.addUser(signUpUser);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    clientSignupContentPanel.setBlank();
                    contractorSignupContentPanel.setBlank();
                    signUpWarningLabel.setText("Sign up Successful: Please login");
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
                contractorSignupContentPanel.setBlank();
                clientSignupContentPanel.setBlank();
                setblank();
                ((CardLayout)signUpOrLoginContentPanel.getLayout()).show(signUpOrLoginContentPanel,"login");
            }
        });
    }
    public void setblank(){
        loginContentPanel.setBlank();
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
