package gui;

import javax.swing.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocation(200, 200);

        JTabbedPane tabbedPane = new JTabbedPane();

    //---------------------------------------------------------

        ArrayList<String[]> data = new ArrayList<>();

        DragonTableModel model = new DragonTableModel();
        model.setCollection(data);
        JTable table = new JTable();

    //-------------------------------------------------------


        setVisible(true);
    }
}
