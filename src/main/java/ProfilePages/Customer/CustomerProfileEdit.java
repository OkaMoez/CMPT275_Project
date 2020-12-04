package ProfilePages.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerProfileEdit extends JPanel{
    private JLabel EditHeader;
    private JPanel customerProfileEdit;
    private JButton backButton;
    private JButton saveChanges;
    private JLabel name;
    private JLabel address;
    private JTextField contactnumberBox;
    private JTextField nameBox;
    private JTextField addressBox;


    public CustomerProfileEdit() {

        this.setLayout(new CardLayout());
        this.add(customerProfileEdit, "customerProfileEdit");
        saveChanges.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
