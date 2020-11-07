package CustomerProfile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerProfileEdit extends JPanel{
    private JLabel EditHeader;
    private JPanel customerProfileEdit;
    private JButton backButton;
    private JButton saveChanges;


    public CustomerProfileEdit() {



        //this.setLayout(new GridLayout());
        this.setLayout(new CardLayout());
        this.add(customerProfileEdit, "customerProfileEdit");



    }
}
