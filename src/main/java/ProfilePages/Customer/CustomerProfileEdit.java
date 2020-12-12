package ProfilePages.Customer;

import Server.UserCredentialsServer;
import Users.Csv.CsvSearch;
import com.opencsv.exceptions.CsvException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CustomerProfileEdit extends JPanel{
    private JLabel EditHeader;
    private JPanel customerProfileEdit;
    private JButton backButton;
    private JButton saveChanges;
    private JLabel name;
    private JLabel address;
    private JTextField contactnumberBox;
    private JTextField nameBox;
    private JTextField addressBox;
    private JLabel editWarningLabel;
    private String newName;
    private String newAddress;
    private String newNumber;
    private String[] updatedUser = new String[9];

    CsvSearch csvSearch = new CsvSearch();

    public CustomerProfileEdit() throws IOException {
        editWarningLabel.setText("");
        this.setLayout(new CardLayout());
        this.add(customerProfileEdit, "customerProfileEdit");

        saveChanges.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(!addressBox.getText().isEmpty()){
                    newAddress = addressBox.getText();
                    UserCredentialsServer.currentUser.setAddress(newAddress);
                }
                if(!nameBox.getText().isEmpty()){
                    newName = nameBox.getText();
                    UserCredentialsServer.currentUser.setName(newName);
                }
                if(!contactnumberBox.getText().isEmpty()){
                    newNumber = contactnumberBox.getText();
                    UserCredentialsServer.currentUser.setNumber(newNumber);
                }
                addressBox.setText("");
                nameBox.setText("");
                contactnumberBox.setText("");
                UserCredentialsServer.placeholderUserMap.replace(UserCredentialsServer.currentUser.getUserID(),UserCredentialsServer.currentUser);
                editWarningLabel.setText("Entered Values were updated");

                updatedUser[0] = String.valueOf(UserCredentialsServer.currentUser.getUserID());
                updatedUser[1] ="client";
                updatedUser[2] =UserCredentialsServer.currentUser.getPassword();
                updatedUser[3] =UserCredentialsServer.currentUser.getName();
                updatedUser[4] =UserCredentialsServer.currentUser.getAddress();
                updatedUser[5] =UserCredentialsServer.currentUser.getNumber();
                updatedUser[6] ="null";
                updatedUser[7] ="null";
                updatedUser[8] ="null";
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
