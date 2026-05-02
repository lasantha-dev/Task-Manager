
package UserInterface;

import DBM.DatabaseManager;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;

public class ViewWindow {

    private JPanel vPanel;

    private JXDatePicker datePickerView;

    public JPanel getvPanel() {
        return vPanel;
    }

    //==============table in view window===========//
    private DefaultTableModel model;

    //============Buttons in view window===============//
    private JButton btnSearch;


    //==========label===============//
    private JLabel lblCurrentDate;




    public ViewWindow() {

        vPanel = new JPanel();
        vPanel.setLayout(null);


        btnSearch = new JButton("Search");
        btnSearch.setBounds(180, 20, 100, 30);
        vPanel.add(btnSearch);



        // -------- Table --------

        // ===================== Table =====================
        String[] columns = {
                "Date",
                "<html>Reference<br>Number</html>",
                "<html>Received<br>Details</html>",
                "<html>Receiver<br>Group</html>",
                "<html>Receiver<br>Name</html>",
                "<html>Document<br>Title</html>",
                "<html>Assigned<br>Group</html>",
                "<html>Assigned<br>Person</html>",
                "Description",
                "<html>Forwarded<br>Details</html>"
        };

        model = new DefaultTableModel(columns,0);
        JTable table = new JTable(model);
        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(),40));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 60, 950, 400);
        vPanel.add(scrollPane);


        // -------- Date Controls --------
        datePickerView = new JXDatePicker();
        datePickerView.setBounds(20, 20, 150, 30);
        datePickerView.setDate(Date.valueOf(LocalDate.now()));
        vPanel.add(datePickerView);

        ButtonAction ba = new ButtonAction();
        ba.saveData(this);
        ba.filterData(this);
        //ba.saveData(this);
        loadTableData();


    }

    //=================load data into Jtable task==============//
    public void loadTableData() {

        model.setRowCount(0);
        DatabaseManager databaseManager =new DatabaseManager();

        try {
            ResultSet rs = databaseManager.getDataFromTaskTable();

            while (rs.next()) {
                model.addRow(new Object[]{

                        rs.getString("created_date"),      // Date
                        rs.getString("reference_number"),// Reference No.
                        rs.getString("receiver_details"),
                        rs.getString("receiver_group"),    // Receiver Group
                        rs.getString("receiver_name"),     // Receiver Name
                        rs.getString("document_title"),    // Document Title
                        rs.getString("assigned_group"),    // Assigned Group
                        rs.getString("assigned_person"),   // Assigned Person
                        rs.getString("description"),        // Description
                        rs.getString("forwarded_details")


                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void filterByDate(String date) {

        model.setRowCount(0);

        DatabaseManager dm = new DatabaseManager();

        String sql = "SELECT * FROM Tasks WHERE created_date = '" + date + "'";

        try {
            ResultSet rs = dm.getDetailsToLoadCombo(sql);

            while (rs.next()) {
                model.addRow(new Object[]{

                        rs.getString("created_date"),
                        rs.getString("reference_number"),
                        rs.getString("receiver_details"),
                        rs.getString("receiver_group"),
                        rs.getString("receiver_name"),
                        rs.getString("document_title"),
                        rs.getString("assigned_group"),
                        rs.getString("assigned_person"),
                        rs.getString("description"),
                        rs.getString("forwarded_details")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JXDatePicker getDatePickerView() {
        return datePickerView;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public JButton getBtnSearch() {
        return btnSearch;
    }


}
