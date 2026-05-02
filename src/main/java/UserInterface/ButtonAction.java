package UserInterface;

import DBM.DatabaseManager;

import javax.swing.*;
import java.sql.ResultSet;


public class ButtonAction {

    String a;

    public void saveData(SettingWindow settingWindow){



        settingWindow.getBtnSave().addActionListener(e->{

            DatabaseManager dm = new DatabaseManager();

            if (settingWindow.getTxtReceiverGroup().getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter a name");
                return;
            }

            dm.insertDetailsToDetails(

                    settingWindow.getTxtReceiverGroup().getText(),
                    settingWindow.getTxtReceiverName().getText(),
                    settingWindow.getTxtTitle().getText(),
                    settingWindow.getTxtAssignedGroup().getText(),
                    settingWindow.getTxtAssignedName().getText()

            );
            settingWindow.loadDetailstable();

                    settingWindow.getTxtReceiverGroup().setText("");
                    settingWindow.getTxtAssignedName().setText("");
                    settingWindow.getTxtAssignedGroup().setText("");
                    settingWindow.getTxtTitle().setText("");
                    settingWindow.getTxtReceiverName().setText("");

            int confirm = JOptionPane.showConfirmDialog(null, "Save this record?");
            if (confirm == JOptionPane.YES_OPTION) {

                settingWindow.loadTableData(settingWindow);
            }



        });
    }

    public void saveData(EnterWindow enterWindow,ViewWindow viewWindow){

        enterWindow.getBtnSave().addActionListener(e->{

            DatabaseManager dm = new DatabaseManager();

            String recGroup = "";
            if (enterWindow.getComboRecGroup().getSelectedItem() != null) {
                recGroup = enterWindow.getComboRecGroup().getSelectedItem().toString();
            }

            String recName = "";
            if (enterWindow.getComboRecName().getSelectedItem() != null) {
                recName = enterWindow.getComboRecName().getSelectedItem().toString();
            }

            String title = "";
            if (enterWindow.getComboTTitle().getSelectedItem() != null) {
                title = enterWindow.getComboTTitle().getSelectedItem().toString();
            }

            String assignGroup = "";
            if (enterWindow.getComboAssignGroup().getSelectedItem() != null) {
                assignGroup = enterWindow.getComboAssignGroup().getSelectedItem().toString();
            }

            String assignName = "";
            if (enterWindow.getComboAssignedName().getSelectedItem() != null) {
                assignName = enterWindow.getComboAssignedName().getSelectedItem().toString();
            }


            int id = dm.insertDetailsToTasks(

                    enterWindow.getNow(),
                    enterWindow.getTxtReceivedDetails().getText().toString(),
                    recGroup,
                    recName,
                    title,
                    assignGroup,
                    assignName,
                    enterWindow.getTxtDesc().getText().toString()


                    );


            // STEP 2: generate reference number
            if (id != -1) {
                String year = String.valueOf(java.time.LocalDate.now().getYear());
                String refNo = year + id;

                // STEP 3: update reference number
                dm.updateReferenceNumber(id, refNo);



                JOptionPane.showMessageDialog(null,
                        "Saved Successfully!\nReference Number: " + refNo);

                enterWindow.clearFields();

                // ✅ REFRESH TABLE HERE
                viewWindow.loadTableData();
            }


        });
    }

    public void updateData(SettingWindow settingWindow){

        settingWindow.getBtnUpdate().addActionListener(e -> {

            int row = settingWindow.getTable().getSelectedRow();

            int id = Integer.parseInt(settingWindow.getModel().getValueAt(row, 0).toString());

            if (id == -1) {
                JOptionPane.showMessageDialog(null, "Select a row first!");
                return;
            }


            DatabaseManager dm = new DatabaseManager();

            dm.updateDetails(
                    id,
                    settingWindow.getTxtReceiverGroup().getText(),
                    settingWindow.getTxtReceiverName().getText(),
                    settingWindow.getTxtTitle().getText(),
                    settingWindow.getTxtAssignedGroup().getText(),
                    settingWindow.getTxtAssignedName().getText()
            );

            int confirm = JOptionPane.showConfirmDialog(null, "Update this record?");
            if (confirm == JOptionPane.YES_OPTION) {

                dm.updateDetails(id, settingWindow.getTxtReceiverGroup().getText(),settingWindow.getTxtReceiverName().getText(),settingWindow.getTxtTitle().getText(),
                        settingWindow.getTxtAssignedGroup().getText(),settingWindow.getTxtAssignedName().getText());

            settingWindow.loadTableData(settingWindow);
            }
        });
    }
    public void deleteData(SettingWindow settingWindow){

        settingWindow.getBtnDelete().addActionListener(e -> {

            int row = settingWindow.getTable().getSelectedRow();

            int id = Integer.parseInt(settingWindow.getModel().getValueAt(row, 0).toString());

            if (id == -1) {
                JOptionPane.showMessageDialog(null, "Select a row first!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null, "Delete this record?");

            if (confirm == JOptionPane.YES_OPTION) {

                DatabaseManager dm = new DatabaseManager();
                dm.deleteDetails(id);

                settingWindow.loadTableData(settingWindow);
            }
        });
    }

    public void filterData(ViewWindow viewWindow){
        viewWindow.getBtnSearch().addActionListener(e -> {

            java.util.Date selectedDate = viewWindow.getDatePickerView().getDate();

            if (selectedDate == null) {
                JOptionPane.showMessageDialog(null, "Select a date!");
                return;
            }

            java.sql.Date sqlDate = new java.sql.Date(selectedDate.getTime());

            viewWindow.filterByDate(sqlDate.toString());
        });
    }

    public void actionWindowSearchButton(ActionTakenWindow actionTakenWindow){

        String action = actionTakenWindow.getTxtActionTaken().getText().trim();
       actionTakenWindow.getBtnSearch().addActionListener(e -> {

            String ref = actionTakenWindow.getTxtSearchRef().getText().trim();

            if (ref.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter Reference Number");
                return;
            }
           DatabaseManager dm = new DatabaseManager();

           String[] data = dm.searchByReference(ref);

           if (data != null) {
               actionTakenWindow.getLblRecNameValue().setText(data[0]);
               actionTakenWindow.getLblRefValue().setText(data[1]);
           } else {
               JOptionPane.showMessageDialog(null, "No Record Found");
           }

        });
    }

    public void actionWindowSubmitButton(ActionTakenWindow actionTakenWindow){

        actionTakenWindow.getBtnSubmit().addActionListener(e -> {

            String action = actionTakenWindow.getTxtActionTaken().getText().trim();
            String ref = actionTakenWindow.getLblRefValue().getText();

            if (ref.equals("----")) {
                JOptionPane.showMessageDialog(null, "Search record first");
                return;
            }

            if (action.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter Action Taken");
                return;
            }

            DatabaseManager dm = new DatabaseManager();
            // Update DB
            int result = dm.updateForwardedDetails(ref, action);

            if (result > 0) {
                JOptionPane.showMessageDialog(null, "Saved Successfully!");

                // Clear fields
                actionTakenWindow.getTxtSearchRef().setText("");
                actionTakenWindow.getTxtActionTaken().setText("");
                actionTakenWindow.getLblRecNameValue().setText("----");
                actionTakenWindow.getLblRefValue().setText("----");
            }
        });
    }

    public void saveData(ViewWindow viewWindow){


    }

}
