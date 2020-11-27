package Login.ContentPanel;

import javax.swing.*;

public class LoginContentPanel extends JPanel{
    private JPanel mainPanel;
    private JTextField contractorUsernameTextField;
    private JTextField clientUsernameTextField;
    private JPasswordField contractorPasswordField;
    private JPasswordField clientPasswordField;
    private JLabel welcomeLabel;
    private JLabel contractorLoginLabel;
    private JLabel clientLoginLabel;
    private JLabel contractorUsernameLabel;
    private JLabel clientUsernameLabel;
    private JLabel contractorPasswordLabel;
    private JLabel clientPasswordLabel;

    public LoginContentPanel(){
        add(mainPanel);
    }

    public String getContractorUsername(){ return contractorUsernameTextField.getText();}

    public String getContractorPassword(){ return new String(contractorPasswordField.getPassword());}

    public String getClientUsername(){ return clientUsernameTextField.getText();}

    public String getClientPassword(){ return new String(clientPasswordField.getPassword());}

}
