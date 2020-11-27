package ContractorProfile;

import CustomerProfile.CustomerProfile;
import MainWindow.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
