package ProfilePages.Customer;

import MainWindow.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CustomerProfileContainer extends JPanel{
    private JPanel customerContainer;
    private JPanel content;
    private JButton editButton;
    private JButton backButton;
    CardLayout cardLayout = new CardLayout();

    private CustomerProfile customerProfile = new CustomerProfile();

    private CustomerProfileEdit customerProfileEdit = new CustomerProfileEdit();

    public  CustomerProfileContainer() throws IOException {
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
