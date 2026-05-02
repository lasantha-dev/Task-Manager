package UserInterface;

import DBM.DatabaseManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;

public class SettingWindow {

    private JPanel sPanel;

    public JPanel getsPanel() {
        return sPanel;
    }

    private JTextField txtReceiverGroup, txtReceiverName, txtTitle, txtAssignedGroup, txtAssignedName;
    private JTable table;
    private DefaultTableModel model;

    private JButton btnSave,btnUpdate,btnDelete;

    private int selectedId = -1;

    public SettingWindow() {

        sPanel =new JPanel();
        sPanel.setLayout(null);

        JLabel lblReceiverGroup = new JLabel("Receiver Group");
        lblReceiverGroup.setBounds(20, 40, 100, 25);
        sPanel.add(lblReceiverGroup);

        JLabel lblReceiverName = new JLabel("Receiver Name");
        lblReceiverName.setBounds(20, 80, 100, 25);
        sPanel.add(lblReceiverName);

        JLabel lblTitle = new JLabel("Document Title");
        lblTitle.setBounds(20, 120, 100, 25);
        sPanel.add(lblTitle);

        JLabel lblAssignedGroup = new JLabel("Assigned Group");
        lblAssignedGroup.setBounds(20, 160, 100, 25);
        sPanel.add(lblAssignedGroup);

        JLabel lblAssignedPerson = new JLabel("Assigned Person");
        lblAssignedPerson.setBounds(20, 200, 100, 25);
        sPanel.add(lblAssignedPerson);

        // -------- TextFields --------
        txtReceiverGroup = new JTextField();
        txtReceiverGroup.setBounds(120, 40, 150, 25);
        sPanel.add(txtReceiverGroup);


        txtReceiverName= new JTextField();
        txtReceiverName.setBounds(120, 80, 150, 25);
        sPanel.add(txtReceiverName);

        txtTitle = new JTextField();
        txtTitle.setBounds(120, 120, 150, 25);
        sPanel.add(txtTitle);

        txtAssignedGroup = new JTextField();
        txtAssignedGroup.setBounds(120, 160, 150, 25);
        sPanel.add(txtAssignedGroup);

        txtAssignedName = new JTextField();
        txtAssignedName.setBounds(120, 200, 150, 25);
        sPanel.add(txtAssignedName);

        // -------- Buttons --------
        btnSave = new JButton("Save");
        btnSave.setBounds(20, 240, 80, 30);
        sPanel.add(btnSave);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(110, 240, 80, 30);
        sPanel.add(btnUpdate);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(200, 240, 80, 30);
        sPanel.add(btnDelete);

        //=====send button action to existing window======//
        ButtonAction buttonAction= new ButtonAction();
        buttonAction.saveData(this);

        buttonAction.updateData(this);
        buttonAction.deleteData(this);



        // -------- Table --------
       // DatabaseManager databaseManager = new DatabaseManager();
        //databaseManager.getDetailsTable();

        String[] cols = {"ID","RGroup","RName","Title","AGroup","APerson"};
        model = new DefaultTableModel(cols,0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(300, 40, 400, 150);
        sPanel.add(scrollPane);

        loadTableData(this);

        // -------- Table Click → Fill TextFields --------
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();

            if (row != -1) {
                selectedId = Integer.parseInt(model.getValueAt(row, 0).toString());

                txtReceiverGroup.setText(model.getValueAt(row, 1).toString());
                txtReceiverName.setText(model.getValueAt(row, 2).toString());
                txtTitle.setText(model.getValueAt(row, 3).toString());
                txtAssignedGroup.setText(model.getValueAt(row, 4).toString());
                txtAssignedName.setText(model.getValueAt(row, 5).toString());


            }
        });
    }

    //=================load data into Jtable==============//
    public void loadTableData(SettingWindow settingWindow) {

        model.setRowCount(0);
        DatabaseManager databaseManager =new DatabaseManager();

        try {
            ResultSet rs = databaseManager.getDetailsTable();

            while (rs.next()) {
                model.addRow(new Object[]{

                        rs.getInt("id"),
                        rs.getString("receiver_group"),
                        rs.getString("receiver_name"),
                        rs.getString("document_title"),
                        rs.getString("assigned_group"),
                        rs.getString("assigned_person"),

                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadDetailstable(){

        //model.setRowCount(0);
        DatabaseManager dm =new DatabaseManager();
         try{
            ResultSet rs = dm.getDetailsTable();
             while (rs.next()){

                model.addRow(new Object[]{

                        rs.getInt("id"),
                        rs.getString("receiver_group"),
                        rs.getString("receiver_name"),
                        rs.getString("document_title"),
                        rs.getString("assigned_group"),
                        rs.getString("assigned_person")


                });
             }

         } catch (Exception e) {
             e.printStackTrace();
         }


    }


    public JButton getBtnSave() {
        return btnSave;
    }

    public String getNameGroup() {
        return txtReceiverGroup.getText();
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public JTextField getTxtReceiverGroup() {
        return txtReceiverGroup;
    }

    public JTextField getTxtReceiverName() {
        return txtReceiverName;
    }

    public JTextField getTxtTitle() {
        return txtTitle;
    }

    public JTextField getTxtAssignedGroup() {
        return txtAssignedGroup;
    }

    public JTextField getTxtAssignedName() {
        return txtAssignedName;
    }

    public int getSelectedId() {
        return selectedId;
    }

    public JButton getBtnUpdate() {
        return btnUpdate;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public JTable getTable() {
        return table;
    }
}
