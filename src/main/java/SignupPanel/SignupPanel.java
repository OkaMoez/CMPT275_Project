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

    public SignupPanel(final String userType) {
        // Set labels according to user type
        signUpLabel.setText("Welcome to the " + userType + " sign up");
        if(userType.equals("contractor")) {
            enterNameLabel.setText("Enter business name:");
        }
        else if(userType.equals("client")) {
            enterNameLabel.setText("Enter full name:");
        }
    }

    public String getUsername() {
        return (selectUsernameTextField.getText());
    }

    public String getName() {
        return (enterNameTextField.getText());
    }

    public String getPassword() {
        return (enterPasswordField.getPassword().toString());
    }
}
