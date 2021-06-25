package gui;

import dragon.Dragon;
import utilities.Process;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainFrame extends JFrame {

    Process process;

    TablePanel tablePanel;
    MapPanel mapPanel;


    public MainFrame(Process process) {
        this.process = process;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocation(200, 200);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        tablePanel = new TablePanel(process);
        mapPanel = new MapPanel(process);

        tabbedPane.addTab("Table", tablePanel);
        tabbedPane.addTab("Map", mapPanel);

        JPanel buttons = new JPanel(new GridLayout(0, 1, 5, 5));
        buttons.setBorder(BorderFactory.createEmptyBorder(20, 5, 5, 5));

        JButton addBtn = new JButton("Add");
        JButton removeBtn = new JButton("Remove");
        JButton infoBtn = new JButton("Info");
        JButton sortBtn = new JButton("Sort");
        JButton updateBtn = new JButton("Update");
        JButton clearBtn = new JButton("Clear");
        JButton scriptBtn = new JButton("Execute script");

        addBtn.addActionListener(e -> {
            InputDragonFrame dragonInput = new InputDragonFrame();
            dragonInput.init();
                }
        );

        buttons.add(addBtn);
        buttons.add(updateBtn);
        buttons.add(removeBtn);
        buttons.add(infoBtn);
        buttons.add(sortBtn);
        buttons.add(scriptBtn);
        buttons.add(clearBtn);

        add(buttons, BorderLayout.EAST);

        add(tabbedPane, BorderLayout.CENTER);

        setVisible(true);
    }

}
