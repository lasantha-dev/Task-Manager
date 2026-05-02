package UserInterface;

import javax.swing.*;
        import java.awt.*;

public class ActionTakenWindow {

    private JPanel panel;

    private JTextField txtSearchRef;
    private JLabel lblRecNameValue;
    private JLabel lblRefValue;
    private JTextField txtActionTaken;

    private JButton btnSearch;
    private JButton btnSubmit;

    public ActionTakenWindow() {

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createTitledBorder("Action Taken"));

        Font labelFont = new Font("Segoe UI", Font.BOLD, 13);

        // ===== SEARCH SECTION =====
        JLabel lblSearch = new JLabel("Reference No:");
        lblSearch.setBounds(20, 30, 120, 25);
        lblSearch.setFont(labelFont);
        panel.add(lblSearch);

        txtSearchRef = new JTextField();
        txtSearchRef.setBounds(140, 30, 150, 25);
        panel.add(txtSearchRef);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(300, 30, 100, 25);
        panel.add(btnSearch);

        // ===== DISPLAY DETAILS =====
        JLabel lblRecName = new JLabel("Receiver Name:");
        lblRecName.setBounds(20, 80, 120, 25);
        lblRecName.setFont(labelFont);
        panel.add(lblRecName);

        lblRecNameValue = new JLabel("----");
        lblRecNameValue.setBounds(150, 80, 200, 25);
        panel.add(lblRecNameValue);

        JLabel lblRef = new JLabel("Reference No:");
        lblRef.setBounds(20, 110, 120, 25);
        lblRef.setFont(labelFont);
        panel.add(lblRef);

        lblRefValue = new JLabel("----");
        lblRefValue.setBounds(150, 110, 200, 25);
        panel.add(lblRefValue);

        // ===== ACTION TAKEN =====
        JLabel lblAction = new JLabel("Action Taken:");
        lblAction.setBounds(20, 150, 120, 25);
        lblAction.setFont(labelFont);
        panel.add(lblAction);

        txtActionTaken = new JTextField();
        txtActionTaken.setBounds(150, 150, 250, 25);
        panel.add(txtActionTaken);

        // ===== SUBMIT BUTTON =====
        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(160, 200, 120, 35);
        panel.add(btnSubmit);

        ButtonAction ba = new ButtonAction();
        ba.actionWindowSearchButton(this);
        ba.actionWindowSubmitButton(this);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JTextField getTxtSearchRef() {
        return txtSearchRef;
    }

    public JLabel getLblRecNameValue() {
        return lblRecNameValue;
    }

    public JLabel getLblRefValue() {
        return lblRefValue;
    }

    public JTextField getTxtActionTaken() {
        return txtActionTaken;
    }

    public JButton getBtnSearch() {
        return btnSearch;
    }

    public JButton getBtnSubmit() {
        return btnSubmit;
    }
}
