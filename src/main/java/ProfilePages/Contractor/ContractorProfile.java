package ProfilePages.Contractor;

import MainWindow.MainWindow;
import Server.UserCredentialsServer;
import Users.Contractor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContractorProfile extends JPanel {
    private JPanel contractorProfile;
    private JPanel ProfilePicture = new JPanel();
    private JPanel Info;
    private JButton Book;
    private JTextField EnterRating;
    private JButton RateButton;
    private JLabel BusinessName;
    private JLabel ContactName;
    private JLabel Rating;
    private JPanel Portfolio;
    private JLabel ProfileHeader;
    private JPanel ProfilePage;
    private JLabel Number;
    private JLabel subUserType;
    private JButton messagingLinkButton;
    MainWindow mainWindow;

    public ContractorProfile(MainWindow mainWindow) {
        this.mainWindow = mainWindow;

        try {
            BufferedImage myPicture = ImageIO.read(new File("src/main/java/ProfilePages/profile.JPG"));
            Image scaledInstance = myPicture.getScaledInstance(150,150,Image.SCALE_DEFAULT);
            JLabel picLabel = new JLabel(new ImageIcon(scaledInstance));
            ProfilePicture.setLayout(new CardLayout());
            ProfilePicture.add(picLabel);
        }
        catch (IOException e) {
            System.out.println("General I/O exception: " + e.getMessage());
            e.printStackTrace();
            assert(true);
        }

        this.EnterRating.setVisible(false);
        this.Book.setVisible(false);
        this.RateButton.setVisible(false);
        this.messagingLinkButton.setVisible(false);

        BusinessName.setText("Business Name: " + UserCredentialsServer.currentUser.getBusinessName());
        ContactName.setText("Contact Person: " + UserCredentialsServer.currentUser.getName());
        Number.setText("Number: " + UserCredentialsServer.currentUser.getNumber());
        subUserType.setText("Trade: "+ UserCredentialsServer.currentUser.getSubUserType());
        Rating.setText("Rating: "+UserCredentialsServer.currentUser.getRating());

        this.setLayout(new CardLayout());

        this.add(contractorProfile, "contractorProfile");
    }

    public ContractorProfile(MainWindow mainWindow, Contractor contractor) {
        this.mainWindow = mainWindow;

        try {
            BufferedImage myPicture = ImageIO.read(new File("src/main/resources/profile.JPG"));
            Image scaledInstance = myPicture.getScaledInstance(150,150,Image.SCALE_DEFAULT);
            JLabel picLabel = new JLabel(new ImageIcon(scaledInstance));
            ProfilePicture.setLayout(new CardLayout());
            ProfilePicture.add(picLabel);
        }
        catch (IOException e) {
            System.out.println("General I/O exception: " + e.getMessage());
            e.printStackTrace();
            assert(true);
        }


        this.EnterRating.setVisible(true);
        this.Book.setVisible(true);
        this.RateButton.setVisible(true);
        this.messagingLinkButton.setVisible(true);

        BusinessName.setText("Business Name: " + contractor.getBusinessName());
        ContactName.setText("Contact Person: " + contractor.getName());
        Number.setText("Number: " + contractor.getNumber());
        subUserType.setText("Trade: "+ contractor.getSubUserType());
        Rating.setText("Rating: "+ contractor.getRating());

        this.setLayout(new CardLayout());
        this.add(contractorProfile, "contractorProfile");

        messagingLinkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.changePageToMessaging(contractor.getUserID());
                return;
            }
        });
    }

    public void updateVales(){
        ContactName.setText("Contact Person: " + UserCredentialsServer.currentUser.getName());
        BusinessName.setText("Business Name: " + UserCredentialsServer.currentUser.getBusinessName());
        Number.setText("Number: " + UserCredentialsServer.currentUser.getNumber());
        subUserType.setText("Trade: "+ UserCredentialsServer.currentUser.getSubUserType());
        Rating.setText("Rating: "+UserCredentialsServer.currentUser.getRating());
    }

}
