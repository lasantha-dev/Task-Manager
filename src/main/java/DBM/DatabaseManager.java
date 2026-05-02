



package DBM;

import UserInterface.ButtonAction;
import UserInterface.EnterWindow;
import UserInterface.SettingWindow;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    public static Connection getConnection() throws SQLException {
        String oneDrivePath = System.getenv("OneDrive");

        // fallback if OneDrive not found
        if (oneDrivePath == null) {
            oneDrivePath = System.getProperty("user.home");
        }

        String folderPath = oneDrivePath + "\\RubberTaskManager";

        java.io.File folder = new java.io.File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String dbPath = folderPath + "\\rubber.db";

        return DriverManager.getConnection("jdbc:sqlite:" + dbPath);

            //return DriverManager.getConnection("jdbc:sqlite:rubber.db");
    }

    public void createTable() {

        String sql = "CREATE TABLE IF NOT EXISTS details_table (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "receiver_group TEXT NOT NULL,"+ "receiver_name TEXT NOT NULL,"+ "document_title TEXT NOT NULL,"+
                "assigned_group TEXT NOT NULL,"+"assigned_person TEXT NOT NULL)";

        String sql1 = "CREATE TABLE IF NOT EXISTS Tasks (" + "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "created_date TEXT NOT NULL," + "reference_number TEXT," +"receiver_details,"+
                "receiver_group TEXT," + "receiver_name TEXT," + "document_title TEXT," + "assigned_group TEXT," +
                "assigned_person TEXT," + "description TEXT," +"forwarded_details" + ")";

        try (
                Connection conn =getConnection();
                Statement stmt =conn.createStatement()
        ) {
            stmt.execute(sql);
            System.out.println("Details_Table created success");

            stmt.execute(sql1);
            System.out.println("Task Table created success");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //===============method to get data from details table=================//
    public ResultSet getDetailsTable(){

        String sql = "SELECT * FROM details_table";

        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //===============method to get data from task table=================//
    public ResultSet  getDataFromTaskTable(){

        String sql = "SELECT * FROM Tasks";

        try {
            Connection conn =getConnection();
            Statement stmt = conn.createStatement();
            return  stmt.executeQuery(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
public void insertDetailsToDetails(String rg,String rn,String t,String ag,String an){

    String sql = "INSERT INTO details_table(receiver_group,receiver_name,document_title,assigned_group,assigned_person) VALUES (?,?,?,?,?)";

    try (Connection conn =getConnection();
    PreparedStatement pst =conn.prepareStatement(sql)){
        pst.setString(1, rg);
        pst.setString(2, rn);
        pst.setString(3, t);
        pst.setString(4, ag);
        pst.setString(5, an);

        pst.executeUpdate();

    } catch (Exception e) {
        e.printStackTrace();
    }

    }

    public int insertDetailsToTasks(String date,String rd, String rg, String rn, String t, String ag, String ap, String d) {

        String sql = "INSERT INTO Tasks(created_date,receiver_details, receiver_group,receiver_name,document_title,assigned_group,assigned_person,description,forwarded_details) VALUES (?,?,?,?,?,?,?,?,?)";

        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, date);
            pst.setString(2, rd);
            pst.setString(3, rg);
            pst.setString(4, rn);
            pst.setString(5, t);
            pst.setString(6, ag);
            pst.setString(7, ap);
            pst.setString(8, d);

            int affectedRows = pst.executeUpdate();

            if (affectedRows > 0) {
                ResultSet rs = pst.getGeneratedKeys();

                if (rs.next()) {
                    return rs.getInt(1); // ✅ return generated ID
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1; // ❌ failed insert
    }
    public void updateReferenceNumber(int id, String refNo){

        String sql = "UPDATE Tasks SET reference_number=? WHERE id=?";

        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, refNo);
            pst.setInt(2, id);

            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        public ResultSet getDetailsToLoadCombo(String sql) {

        try {
           Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public void loadComboBoxGroups(EnterWindow enterWindow) {
        String sql = "SELECT receiver_group FROM Tasks";

        try {

            ResultSet rs = getDetailsToLoadCombo(sql); // create this method

            enterWindow.getComboRecGroup().removeAllItems(); // clear old items

            while (rs.next()) {
                enterWindow.getComboRecGroup().addItem(rs.getString("receiver_group"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateDetails(int id, String rg, String rn, String t, String ag, String ap) {

        String sql = "UPDATE details_table SET receiver_group=?, receiver_name=?, document_title=?, assigned_group=?, assigned_person=? WHERE id=?";

        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, rg);
            pst.setString(2, rn);
            pst.setString(3, t);
            pst.setString(4, ag);
            pst.setString(5, ap);
            pst.setInt(6, id);

            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteDetails(int id) {

        String sql = "DELETE FROM details_table WHERE id=?";

        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] searchByReference(String ref) {

        String[] data = null;

        String sql = "SELECT receiver_name, reference_number FROM Tasks WHERE reference_number=?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, ref);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    data = new String[]{
                            rs.getString("receiver_name"),
                            rs.getString("reference_number")
                    };
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public int updateForwardedDetails(String ref, String action) {

        int result = 0;

        try {
            Connection con = getConnection();
            String sql = "UPDATE Tasks SET forwarded_details=? WHERE reference_number=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, action);
            ps.setString(2, ref);

            result = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}


