package CustomerProfile;

import MainWindow.MainWindow;

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
    private CustomerProfileEdit customerProfileEdit = new CustomerProfileEdit();

    public CustomerProfile(){

        //I set the box to enter
        this.EnterRating.setVisible(true);
        this.Contact.setVisible(true);
        this.RateButton.setVisible(true);
        this.EditProfile.setVisible(false);
        this.setLayout(new CardLayout());

        this.add(customerProfile, "customerProfile");
        this.add(customerProfileEdit, "customerProfileEdit");

        EditProfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) CustomerProfile.this.getLayout()).show(CustomerProfile.this, "customerProfileEdit");
            }
        });
    }

    //(amon) If we're viewing our own profile, certain boxes and buttons will be hidden or made visible
public void myprofile(){
        this.EnterRating.setVisible(false);
        this.Contact.setVisible(false);
        this.RateButton.setVisible(false);
        this.EditProfile.setVisible(true);
}



}
