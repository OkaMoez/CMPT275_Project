package Login.SignupContentPanel;

import javax.swing.*;

public class SignupContentPanel extends JPanel{
    private JPanel mainPanel;
    private JLabel signUpLabel;
    private JTextField selectUsernameTextField;
    private JTextField numberField;
    private JLabel selectUsernameLabel;
    private JLabel enterNumberLabel;
    private JLabel enterPasswordLabel;
    private JLabel confirmPasswordLabel;
    private JPasswordField enterPasswordField;
    private JPasswordField confirmPasswordField;
    private JTextField nameField;
    private JTextField addressOrBusinessNameField;
    private JLabel enterNameOrContact;
    private JLabel enterAddressOrBusinessName;
    private JComboBox comboBox1;
    private JTextField subUserTypeField;
    private JLabel subUserType;
    private String[] tempUser = new String[7];

    public SignupContentPanel(final String userType) {
        add(mainPanel);
        // Set labels according to user type
        signUpLabel.setText("Welcome to the " + userType + " sign up");
        if(userType.equals("contractor")) {
            enterAddressOrBusinessName.setText("Business name:");
            enterNameOrContact.setText("Contact Person:");
            comboBox1.setVisible(true);
            subUserType.setVisible(true);
        }
        else if(userType.equals("client")) {
            enterNameOrContact.setText("Full name:");
            enterAddressOrBusinessName.setText("Address:");
            comboBox1.setVisible(false);
            subUserType.setVisible(false);
        }
    }



    public String getUsername() {
        return (selectUsernameTextField.getText());
    }
    public String getName() {
        return (nameField.getText());
    }
    public String getPassword() {
        String pass1 =new String(enterPasswordField.getPassword());
        String pass2= new String(confirmPasswordField.getPassword());
        if(pass1.equals(pass2)){
            return pass1;
        }
        else{
            return "badPassword";
        }
    }
    public String getAddressOrBusiness(){return (addressOrBusinessNameField.getText());}
    public String getNumber(){return (numberField.getText());}
    public String getSubUserType(){return (String)comboBox1.getSelectedItem();}

    public void setBlank(){
        nameField.setText("");
        selectUsernameTextField.setText("");
        enterPasswordField.setText("");
        addressOrBusinessNameField.setText("");
        numberField.setText("");
        confirmPasswordField.setText("");

    }
}
