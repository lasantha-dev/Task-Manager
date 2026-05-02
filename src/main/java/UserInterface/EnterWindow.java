
package UserInterface;

import DBM.LoadComboBox;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EnterWindow {



    private JPanel ePanel;

    public JPanel getePanel() {
        return ePanel;
    }
    //============create frame object===============//
    //private MainFrame frame;

    //public MainFrame getFrame() {
   //     return frame;
   // }
//
    //===========ComboBox in enter Window=====================//
    private JComboBox<String> comboRecGroup;
    private JComboBox<String> comboRecName;
    private JComboBox<String> comboTTitle;
    private JComboBox<String> comboAssignGroup;
    private JComboBox<String> comboAssignedName;

    //===============Text field in enter window
    private JTextField txtDesc,txtReceivedDetails;

    //=====================Buttons in enter window===================//
    private JButton btnSave;

    // ===================== Date =====================
    private LocalDate now = LocalDate.now();
    DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");



    //===============Create TabbedPane=====================


    public  EnterWindow() {


            ePanel = new JPanel();
            ePanel.setLayout(null);

            Font labelFont = new Font("Segoe UI", Font.BOLD, 13);

            // -------- Date --------
            JLabel lblDate = new JLabel(now.format(dateTimeFormatter) + " (TODAY)");
            lblDate.setBounds(20, 10, 500, 30);
            lblDate.setFont(new Font("Segoe UI", Font.BOLD, 20));
            ePanel.add(lblDate);

            // ===== LEFT SIDE =====

            // Receiver Group
            JLabel lblRecGroup = new JLabel("Receiver Group:");
            lblRecGroup.setBounds(20, 60, 130, 25);            lblRecGroup.setFont(labelFont);
            ePanel.add(lblRecGroup);

            comboRecGroup = new JComboBox<>();
            comboRecGroup.setBounds(160, 60, 200, 25);            ePanel.add(comboRecGroup);

            // Receiver Name
            JLabel lblRecName = new JLabel("Receiver Name:");
            lblRecName.setBounds(20, 100, 130, 25);            lblRecName.setFont(labelFont);
            ePanel.add(lblRecName);

            comboRecName = new JComboBox<>();
            comboRecName.setBounds(160, 100, 200, 25);            ePanel.add(comboRecName);

            // Received Details
            JLabel lblReceivedDetails = new JLabel("Received Details:");
            lblReceivedDetails.setBounds(20, 140, 130, 25);            lblReceivedDetails.setFont(labelFont);
            ePanel.add(lblReceivedDetails);

            txtReceivedDetails = new JTextField();
            txtReceivedDetails.setBounds(160, 140, 200, 25);            ePanel.add(txtReceivedDetails);

            // ===== RIGHT SIDE =====

            // Title
            JLabel lblTitle = new JLabel("Title:");
            lblTitle.setBounds(520, 60, 130, 25);            lblTitle.setFont(labelFont);
            ePanel.add(lblTitle);

            comboTTitle = new JComboBox<>();
            comboTTitle.setBounds(660, 60, 200, 25);
            ePanel.add(comboTTitle);

            // Description
            JLabel lblDesc = new JLabel("Description:");
            lblDesc.setBounds(520, 100, 130, 25);            lblDesc.setFont(labelFont);
            ePanel.add(lblDesc);

            txtDesc = new JTextField();
            txtDesc.setBounds(660, 100, 200, 25);
            ePanel.add(txtDesc);

            // Assigned Group
            JLabel lblAssignGroup = new JLabel("Assigned Group:");
            lblAssignGroup.setBounds(520, 140, 130, 25);            lblAssignGroup.setFont(labelFont);
            ePanel.add(lblAssignGroup);

            comboAssignGroup = new JComboBox<>();
            comboAssignGroup.setBounds(660, 140, 200, 25);
            ePanel.add(comboAssignGroup);

            // Assigned Person
            JLabel lblAssignPerson = new JLabel("Assigned Person:");
            lblAssignPerson.setBounds(520, 180, 130, 25);            lblAssignPerson.setFont(labelFont);
            ePanel.add(lblAssignPerson);

            comboAssignedName = new JComboBox<>();
            comboAssignedName.setBounds(660, 180, 200, 25);
            ePanel.add(comboAssignedName);


            // ===== RESULT LABEL =====
            JLabel rlabel = new JLabel();
            rlabel.setBounds(20, 220, 500, 25);            rlabel.setForeground(Color.RED);
            ePanel.add(rlabel);

            // ===== BUTTON =====
            btnSave = new JButton("Save");
            btnSave.setBounds(440, 260, 120, 35);            btnSave.setFont(new Font("Segoe UI", Font.BOLD, 14));
            ePanel.add(btnSave);


            // -------- Load Data --------
            LoadComboBox lc = new LoadComboBox();


            lc.loadComboBox(comboRecGroup, "receiver_group");
            lc.loadComboBox(comboRecName, "receiver_name");
            lc.loadComboBox(comboTTitle, "document_title");
            lc.loadComboBox(comboAssignGroup, "assigned_group");
            lc.loadComboBox(comboAssignedName, "assigned_person");


        comboRecGroup.setSelectedIndex(-1);
        comboRecName.setSelectedIndex(-1);
        comboTTitle.setSelectedIndex(-1);
        comboAssignGroup.setSelectedIndex(-1);
        comboAssignedName.setSelectedIndex(-1);


            ButtonAction ba = new ButtonAction();
            ba.saveData(this, new ViewWindow());

    }

    public void clearFields() {

        // Clear text fields
        txtReceivedDetails.setText("");
        txtDesc.setText("");

        // Reset combo boxes
        comboRecGroup.setSelectedIndex(-1);
        comboRecName.setSelectedIndex(-1);
        comboTTitle.setSelectedIndex(-1);
        comboAssignGroup.setSelectedIndex(-1);
        comboAssignedName.setSelectedIndex(-1);

        // Optional: clear result label
        // rlabel.setText("");

        // Focus first field
        comboRecGroup.requestFocus();
    }

    public JComboBox<String> getComboRecGroup() {
        return comboRecGroup;
    }

    public JButton getBtnSave() {
        return btnSave;
    }



    public JComboBox<String> getComboRecName() {
        return comboRecName;
    }

    public JComboBox<String> getComboTTitle() {
        return comboTTitle;
    }

    public JComboBox<String> getComboAssignGroup() {
        return comboAssignGroup;
    }

    public JComboBox<String> getComboAssignedName() {
        return comboAssignedName;
    }

    public JTextField getTxtDesc() {
        return txtDesc;
    }

    public String getNow() {
        return now.toString();
    }

    public JTextField getTxtReceivedDetails() {
        return txtReceivedDetails;
    }


}
