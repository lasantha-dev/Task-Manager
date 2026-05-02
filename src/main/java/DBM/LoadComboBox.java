package DBM;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;

public class LoadComboBox {
        public void loadComboBox(JComboBox<String> comboBox, String columnName) {
            try {

                Connection conn= DatabaseManager.getConnection();
                ResultSet rs = conn
                        .createStatement()
                        .executeQuery("SELECT DISTINCT " + columnName + " FROM details_table");

                comboBox.removeAllItems(); // clear old items
                while (rs.next()) {
                    String value = rs.getString(columnName);
                    comboBox.addItem(value);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

}
