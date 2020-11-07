package ContractorProfile;

import javax.swing.*;
import java.awt.*;

public class ContractorProfileEdit extends JPanel{
    private JButton backButton;
    private JButton saveChanges;
    private JLabel EditHeader;
    private JPanel contractorProfileEdit;

    public ContractorProfileEdit() {


        //this.setLayout(new GridLayout());
        this.setLayout(new CardLayout());

        this.add(contractorProfileEdit, "customerProfileEdit");
    }
}
