package ProfilePages.Customer;

import MainWindow.MainWindow;
import Users.Csv.CsvSearch;
import Users.User;
import Users.UserID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;

public class CustomerProfileContainerPanel extends JPanel{
    private MainWindow mainWindow;
    private JPanel customerContainer;
    private JPanel content;
    private JButton editButton;
    private JButton backButton;
    CardLayout cardLayout;

    private CustomerProfile customerProfile;

    private CustomerProfileEdit customerProfileEdit = new CustomerProfileEdit();

    public CustomerProfileContainerPanel(MainWindow mainWindow) {
        try {
            customerProfile = new CustomerProfile();
        }
        catch (IOException e) {
            System.out.println("General I/O exception: " + e.getMessage());
            e.printStackTrace();
            assert(true);
        }

        this.mainWindow = mainWindow;
        cardLayout = new CardLayout();
        content.setLayout(cardLayout);

        add(customerContainer);
        content.add(customerProfile, "customerProfile");
        content.add(customerProfileEdit, "customerProfileEdit");
        backButton.setVisible(false);
        editButton.setVisible(true);

        ((CardLayout) content.getLayout()).show(content, "customerProfile");

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                customerProfile.updateVales();
                customerProfileEdit.clearLabel();
                ((CardLayout) content.getLayout()).show(content, "customerProfile");
                backButton.setVisible(false);
                editButton.setVisible(true);

            }
        });
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) content.getLayout()).show(content, "customerProfileEdit");
                backButton.setVisible(true);
                editButton.setVisible(false);
            }
        });
    }
}
