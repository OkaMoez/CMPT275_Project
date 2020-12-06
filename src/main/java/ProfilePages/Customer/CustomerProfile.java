package ProfilePages.Customer;

import MainWindow.MainWindow;
import Server.UserCredentialsServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerProfile extends JPanel{

    private JPanel customerProfile;
    private JPanel ProfilePicture;
    private JPanel Info;
    private JButton Contact;
    private JButton EditProfile;
    private JTextField EnterRating;
    private JButton RateButton;
    private JLabel Name;
    private JLabel Address;
    private JLabel Rating;
    private JPanel Content;
    private JPanel BlankSpace;
    private JLabel ProfileHeader;
    private JLabel temp;
    private JPanel profilePage;
    private JLabel Number;
    private JButton backButton;
    public CustomerProfile(){

        //I set the box to enter
        if (MainWindow.myProfile) {
            myprofile();
        }
        else {
            this.EnterRating.setVisible(true);
            this.Contact.setVisible(true);
            this.RateButton.setVisible(true);
        }


        Name.setText("Name: " + UserCredentialsServer.currentUser.getName());
        Address.setText("Address: " + UserCredentialsServer.currentUser.getAddress());
        Number.setText("Number: " + UserCredentialsServer.currentUser.getNumber());
        this.setLayout(new CardLayout());
        this.add(customerProfile, "customerProfile");
    }

    //(amon) If we're viewing our own profile, certain boxes and buttons will be hidden or made visible
public void myprofile(){
        this.EnterRating.setVisible(false);
        this.Contact.setVisible(false);
        this.RateButton.setVisible(false);
}

}
