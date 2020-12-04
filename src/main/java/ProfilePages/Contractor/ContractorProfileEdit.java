package ProfilePages.Contractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContractorProfileEdit extends JPanel{
    private JButton saveChanges;
    private JLabel EditHeader;
    private JLabel name;
    private JPanel contractorProfileEdit;
    private JLabel businessName;
    private JLabel contactNumber;
    private JTextField nameBox;
    private JTextField businessNameBox;
    private JTextField contactNumberBox;

    public ContractorProfileEdit() {


        //this.setLayout(new GridLayout());
        this.setLayout(new CardLayout());

        this.add(contractorProfileEdit, "contractorProfileEdit");
        saveChanges.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
