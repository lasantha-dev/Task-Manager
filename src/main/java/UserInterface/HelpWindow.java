package UserInterface;

import javax.swing.*;
import java.awt.*;

public class HelpWindow {

    private JPanel panel;

    public JPanel getPanel() {
        return panel;
    }

    public HelpWindow() {

        panel = new JPanel();
        panel.setLayout(null);

        JTextArea helpText = new JTextArea();

        helpText.setText(
                "📌 RUBBER TASK MANAGER - HELP GUIDE\n\n" +

                        "🗄️ DATABASE INFORMATION\n" +
                        "- Database Type: SQLite\n" +
                        "- File Name: rubber.db\n" +
                        "- Location: Project root folder\n\n" +

                        "⚙️ AUTO TABLE CREATION\n" +
                        "The system automatically creates tables:\n" +
                        "1. details_table\n" +
                        "2. Tasks\n\n" +

                        "📋 MANUAL SETUP (IF NEEDED)\n" +
                        "Use SQLite Browser and create database:\n\n" +

                        "Database Name: rubber.db\n\n" +

                        "Table: details_table\n" +
                        "- id (INTEGER PRIMARY KEY AUTOINCREMENT)\n" +
                        "- receiver_group TEXT NOT NULL\n" +
                        "- receiver_name TEXT NOT NULL\n" +
                        "- document_title TEXT NOT NULL\n" +
                        "- assigned_group TEXT NOT NULL\n" +
                        "- assigned_person TEXT NOT NULL\n\n" +

                        "Table: Tasks\n" +
                        "- id INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                        "- created_date TEXT NOT NULL\n" +
                        "- reference_number TEXT\n" +
                        "- receiver_group TEXT\n" +
                        "- receiver_name TEXT\n" +
                        "- document_title TEXT\n" +
                        "- assigned_group TEXT\n" +
                        "- assigned_person TEXT\n" +
                        "- description TEXT\n\n" +

                        "📌 NOTE\n" +
                        "- Keep rubber.db in same project folder\n" +
                        "- Do NOT change table names\n" +
                        "- Use DB Browser for SQLite for manual editing\n\n" +

                        "📧 CONTACT DEVELOPER\n" +
                        "Email: lranjith1989@gmail.com\n" +
                        "For support or bug fixing, contact above email.\n"
        );

        helpText.setEditable(false);
        helpText.setFont(new Font("Arial", Font.PLAIN, 14));
        helpText.setBounds(10, 10, 750, 500);

        JScrollPane scrollPane = new JScrollPane(helpText);
        scrollPane.setBounds(10, 10, 760, 520);

        panel.add(scrollPane);
    }
}