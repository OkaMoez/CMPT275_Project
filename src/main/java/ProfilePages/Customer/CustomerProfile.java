package ProfilePages.Customer;

import MainWindow.MainWindow;
import Server.UserCredentialsServer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
    private JPanel Content;
    private JPanel BlankSpace;
    private JLabel ProfileHeader;
    private JPanel profilePage;
    private JLabel Number;
    private JButton backButton;
    public CustomerProfile() throws IOException {

        BufferedImage myPicture = ImageIO.read(new File("src/main/resources/profile.JPG"));

        //Use following line for JAR file
        //BufferedImage myPicture = ImageIO.read(new File("resources/profile.JPG"));

        Image scaledInstance = myPicture.getScaledInstance(150,150,Image.SCALE_DEFAULT);
        JLabel picLabel = new JLabel(new ImageIcon(scaledInstance));

        ProfilePicture.setLayout(new CardLayout());
        ProfilePicture.add(picLabel);


        this.Contact.setVisible(false);
        Name.setText("Name: " + UserCredentialsServer.currentUser.getName());
        Address.setText("Address: " + UserCredentialsServer.currentUser.getAddress());
        Number.setText("Number: " + UserCredentialsServer.currentUser.getNumber());
        this.setLayout(new CardLayout());
        this.add(customerProfile, "customerProfile");
    }
    public void updateVales(){
        Name.setText("Name: " + UserCredentialsServer.currentUser.getName());
        Address.setText("Address: " + UserCredentialsServer.currentUser.getAddress());
        Number.setText("Number: " + UserCredentialsServer.currentUser.getNumber());
    }


}
