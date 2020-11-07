package SignupPanel;

import LoginPanel.LoginPanel;
import MainWindow.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.management.ManagementFactory;

public class SignupPanel extends JPanel{
    private JLabel signUpLabel;
    private JTextField selectUsernameTextField;
    private JTextField enterNameTextField;
    private JLabel selectUsernameLabel;
    private JLabel enterNameLabel;
    private JLabel enterPasswordLabel;
    private JLabel confirmPasswordLabel;
    private JPasswordField enterPasswordField;
    private JPasswordField confirmPasswordField;
    private JButton signUpButton;
    private JLabel signUpErrorLabel;
    private JButton goBackToLoginButton;
    private JLabel haveAnAccountLabel;

    public SignupPanel(final MainWindow mainWindow, final String userType) {
        signUpErrorLabel.setVisible(false);

        // Set labels according to user type
        signUpLabel.setText("Welcome to the " + userType + " sign up");
        if(userType.equals("contractor")) {
            enterNameLabel.setText("Enter business name:");
        }
        else if(userType.equals("client")) {
            enterNameLabel.setText("Enter full name:");
        }

        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Check that all fields are filled in
                if(selectUsernameTextField.getText().equals("") || enterNameTextField.getText().equals("") || enterPasswordField.getPassword() == null || confirmPasswordField.getPassword() == null){
                    signUpErrorLabel.setText("Error: Please fill all fields.");
                    signUpErrorLabel.setVisible(true);
                    return;
                }

                // Check that passwords match
                if(enterPasswordField.getPassword() != confirmPasswordField.getPassword())
                {
                    signUpErrorLabel.setText("Error: Passwords do not match.");
                    signUpErrorLabel.setVisible(true);
                    return;
                }

                // Sign up successful
                mainWindow.successfulSignUp(userType);
            }
        });

        goBackToLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainWindow.backToLogin();
            }
        });
    }
}
