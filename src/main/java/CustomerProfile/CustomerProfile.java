package CustomerProfile;

import javax.swing.*;
import java.awt.*;

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

    public CustomerProfile() {

        //I set the box to enter
        this.EnterRating.setVisible(true);
        this.Contact.setVisible(true);
        this.RateButton.setVisible(true);
        this.EditProfile.setVisible(false);

        this.setLayout(new GridLayout());
        this.add(customerProfile);


    }

    //(amon) If we're viewing our own profile, certain boxes and buttons will be hidden or made visible
public void myprofile(){
        this.EnterRating.setVisible(false);
        this.Contact.setVisible(false);
        this.RateButton.setVisible(false);
        this.EditProfile.setVisible(true);
}



}
