package SignupContentPanel;

import javax.swing.*;

public class SignupContentPanel extends JPanel{
    private JPanel mainPanel;
    private JLabel signUpLabel;
    private JTextField selectUsernameTextField;
    private JTextField enterNameTextField;
    private JLabel selectUsernameLabel;
    private JLabel enterNameLabel;
    private JLabel enterPasswordLabel;
    private JLabel confirmPasswordLabel;
    private JPasswordField enterPasswordField;
    private JPasswordField confirmPasswordField;

    public SignupContentPanel(final String userType) {
        add(mainPanel);
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
        return ( new String(enterPasswordField.getPassword()));
    }
}
