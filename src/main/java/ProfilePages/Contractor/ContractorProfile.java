package ProfilePages.Contractor;

import MainWindow.MainWindow;
import Server.UserCredentialsServer;

import javax.swing.*;
import java.awt.*;

public class ContractorProfile extends JPanel {
    private JPanel contractorProfile;
    private JPanel ProfilePicture;
    private JPanel Info;
    private JButton Book;
    private JTextField EnterRating;
    private JButton RateButton;
    private JLabel BusinessName;
    private JLabel ContactName;
    private JLabel Rating;
    private JPanel Portfolio;
    private JLabel ProfileHeader;
    private JLabel temp;
    private JLabel temp2;
    private JPanel ProfilePage;
    private JLabel Number;


    public ContractorProfile() {

        this.EnterRating.setVisible(true);
        this.Book.setVisible(true);
        this.RateButton.setVisible(true);


        //I set the box to enter
        if (MainWindow.myProfile) {
            myprofile();
        }
        else {
            this.EnterRating.setVisible(true);
            this.Book.setVisible(true);
            this.RateButton.setVisible(true);
        }
        BusinessName.setText("Name: " + UserCredentialsServer.currentUser.getBusinessName());
        ContactName.setText("Contact Person: " + UserCredentialsServer.currentUser.getName());
        Number.setText("Number: " + UserCredentialsServer.currentUser.getNumber());
        this.setLayout(new CardLayout());
        this.add(contractorProfile, "contractorProfile");
    }

    //(amon) If we're viewing our own profile, certain boxes and buttons will be hidden or made visible
    public void myprofile(){
        this.EnterRating.setVisible(false);
        this.Book.setVisible(false);
        this.RateButton.setVisible(false);
    }
}
