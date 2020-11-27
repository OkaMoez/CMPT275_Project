package ProfilePages.Contractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContractorProfileEdit extends JPanel{
    private JButton saveChanges;
    private JLabel EditHeader;
    private JPanel contractorProfileEdit;

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
