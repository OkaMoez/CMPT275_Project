package ProfilePages.Contractor;

import MainWindow.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ContractorProfileContainer extends JPanel {
    private JPanel contractorContainer;
    private JButton editButton;
    private JButton backButton;
    private JPanel content;
    CardLayout cardLayout = new CardLayout();

    private ContractorProfile contractorProfile = new ContractorProfile();

    private ContractorProfileEdit contractorProfileEdit = new ContractorProfileEdit();

    public ContractorProfileContainer() throws IOException {
        content.setLayout(cardLayout);

        add(contractorContainer);
        content.add(contractorProfile, "contractorProfile");
        content.add(contractorProfileEdit, "contractorProfileEdit");
        backButton.setVisible(false);
        if (MainWindow.myProfile) {
            editButton.setVisible(true);
        }
        ((CardLayout) content.getLayout()).show(content, "contractorProfile");

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contractorProfile.updateVales();
                contractorProfileEdit.clearLabel();
                ((CardLayout) content.getLayout()).show(content, "contractorProfile");
                backButton.setVisible(false);
                if (MainWindow.myProfile) {
                    editButton.setVisible(true);
                }
            }
        });
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) content.getLayout()).show(content, "contractorProfileEdit");
                backButton.setVisible(true);
                editButton.setVisible(false);
            }
        });
    }
}
