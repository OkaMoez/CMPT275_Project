package ContractorProfile;

import javax.swing.*;
import java.awt.*;

public class ContractorProfile extends JPanel {
    private JPanel contractorProfile;
    private JPanel ProfilePicture;
    private JPanel Info;
    private JButton Book;
    private JButton EditProfile;
    private JTextField EnterRating;
    private JButton RateButton;
    private JLabel BusinessName;
    private JLabel ContactName;
    private JLabel Rating;
    private JPanel Portfolio;
    private JLabel ProfileHeader;
    private JLabel temp;
    private JLabel temp2;


    public ContractorProfile() {

        this.EnterRating.setVisible(true);
        this.Book.setVisible(true);
        this.RateButton.setVisible(true);
        this.EditProfile.setVisible(false);

        this.setLayout(new GridLayout());
        this.add(contractorProfile);
    }

    //(amon) If we're viewing our own profile, certain boxes and buttons will be hidden or made visible
    public void myprofile(){
        this.EnterRating.setVisible(false);
        this.Book.setVisible(false);
        this.RateButton.setVisible(false);
        this.EditProfile.setVisible(true);
    }
}
