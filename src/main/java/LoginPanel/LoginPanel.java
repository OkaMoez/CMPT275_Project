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
    private JButton contractorSignUpButton;
    private JButton clientSignUpButton;
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

    //private JPanel homePagePanels;
    private SignupPanel signupPanel;

    public LoginPanel(final MainWindow mainWindow) {
        // Hide login warning labels
        contractorLoginWarningLabel.setVisible(false);
        clientLoginWarningLabel.setVisible(false);

        /*homePagePanels.add(loginPanel, "login");
        homePagePanels.add(signupPanel, "signup");

        ((CardLayout)homePagePanels.getLayout()).show(homePagePanels,"login");*/

        add(loginPanel);

        // Contractor Login
        contractorLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Check that username and password are filled
                if(contractorUsernameTextField.getText().equals("") || contractorPasswordField.getPassword() == null )
                {
                    contractorLoginWarningLabel.setText("Error: Please enter all fields.");
                    contractorLoginWarningLabel.setVisible(true);
                    return;
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
                // Check that username and password are filled
                if(clientUsernameTextField.getText().equals("") || clientPasswordField.getPassword() == null )
                {
                    contractorLoginWarningLabel.setText("Error: Please enter all fields.");
                    clientLoginWarningLabel.setVisible(true);
                    return;
                }
                else
                {
                    // UNWASIKETEST check that username and password are in database if not set warning label. If so, proceed
                    // From main window, swap this panel for the client "home page"
                    mainWindow.successfulLogin("client");
                }
            }
        });

        // Contractor Sign Up
        contractorSignUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signupPanel = new SignupPanel(mainWindow, "contractor");
                loginPanel.setVisible(false);
                add(signupPanel);
            }
        });

        // Client Sign Up
        clientSignUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                signupPanel = new SignupPanel(mainWindow, "client");
                loginPanel.setVisible(false);
                add(signupPanel);
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
