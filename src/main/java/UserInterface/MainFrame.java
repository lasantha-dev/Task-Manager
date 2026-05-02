package UserInterface;

import javax.swing.*;

public class MainFrame extends JFrame {

    //===============Create TabbedPane=====================
    private JTabbedPane tabbedPane;

    public MainFrame() {

        setTitle("Rubber Task Manager");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("src/main/resources/logo.png");
        setIconImage(icon.getImage());

        tabbedPane =new JTabbedPane();

        EnterWindow enterWindow =new EnterWindow();
        ViewWindow viewWindow =new ViewWindow();
        SettingWindow settingWindow = new SettingWindow();
        HelpWindow helpWindow = new HelpWindow();
        ActionTakenWindow actionTakenWindow = new ActionTakenWindow();



        tabbedPane.addTab("Task Entry",enterWindow.getePanel());
        tabbedPane.addTab("View Entry",viewWindow.getvPanel());
        tabbedPane.addTab("Action Taken",actionTakenWindow.getPanel());
        tabbedPane.addTab("Setting",settingWindow.getsPanel());

        tabbedPane.addTab("Help",helpWindow.getPanel());


        add(tabbedPane);

    }


    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }
}
