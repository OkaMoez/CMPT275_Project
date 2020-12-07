package ProfilePages.Contractor;

import Server.UserCredentialsServer;
import Users.CsvSearch;
import Users.UserID;
import com.opencsv.exceptions.CsvException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ContractorProfileEdit extends JPanel{
    private JButton saveChanges;
    private JLabel EditHeader;
    private JLabel name;
    private JPanel contractorProfileEdit;
    private JLabel businessName;
    private JLabel contactNumber;
    private JTextField nameBox;
    private JTextField businessNameBox;
    private JTextField contactNumberBox;
    private JLabel editWarningLabel;
    private JComboBox comboBox1;
    private JLabel trade;
    private String newName;
    private String newBusinessName;
    private String newNumber;
    private String[] updatedUser = new String[9];

    CsvSearch csvSearch = new CsvSearch();

    public ContractorProfileEdit() throws IOException {
        editWarningLabel.setText("");

        //this.setLayout(new GridLayout());
        this.setLayout(new CardLayout());

        this.add(contractorProfileEdit, "contractorProfileEdit");
        saveChanges.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!businessNameBox.getText().isEmpty()){
                    newBusinessName = businessNameBox.getText();
                    UserCredentialsServer.currentUser.setAddress(newBusinessName);
                }
                if(!nameBox.getText().isEmpty()){
                    newName = nameBox.getText();
                    UserCredentialsServer.currentUser.setName(newName);
                }
                if(!contactNumberBox.getText().isEmpty()){
                    newNumber = contactNumberBox.getText();
                    UserCredentialsServer.currentUser.setNumber(newNumber);
                }
                String subUserType= (String) comboBox1.getSelectedItem();
                if(!subUserType.equals("Please select one")){
                    UserCredentialsServer.currentUser.setSubUserType(subUserType);
                }

                businessNameBox.setText("");
                nameBox.setText("");
                contactNumberBox.setText("");
                UserCredentialsServer.placeholderUserMap.replace(UserCredentialsServer.currentUser.getUserID(),UserCredentialsServer.currentUser);
                editWarningLabel.setText("Entered Values were updated");


                updatedUser[0] = String.valueOf(UserCredentialsServer.currentUser.getUserID());
                updatedUser[1] ="contractor";
                updatedUser[2] =UserCredentialsServer.currentUser.getPassword();
                updatedUser[3] =UserCredentialsServer.currentUser.getName();
                updatedUser[4] ="null";
                updatedUser[5] =UserCredentialsServer.currentUser.getNumber();
                updatedUser[6] =UserCredentialsServer.currentUser.getBusinessName();
                updatedUser[7] = UserCredentialsServer.currentUser.getRating();
                updatedUser[8] =UserCredentialsServer.currentUser.getSubUserType();
                for(int i=0;i<8;i++){
                    if(updatedUser[i].contains(",")){
                        editWarningLabel.setText("Please enter values without including commas");
                        return;
                    }
                }
                try {
                    csvSearch.editUser(updatedUser);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (CsvException csvException) {
                    csvException.printStackTrace();
                }
            }
        });
    }
    public void clearLabel(){
        editWarningLabel.setText("");
    }
}
