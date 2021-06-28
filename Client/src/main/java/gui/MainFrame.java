package gui;

import utilities.Process;
import utilities.Response;
import utilities.commands.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;

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
        JScrollPane scrollPane = new JScrollPane(mapPanel);

        tabbedPane.addTab("Table", tablePanel);
        tabbedPane.addTab("Map", scrollPane);

        JPanel buttons = new JPanel(new GridLayout(0, 1, 5, 5));
        buttons.setBorder(BorderFactory.createEmptyBorder(20, 5, 5, 5));

        JButton addBtn = new JButton("Add");
        JButton removeBtn = new JButton("Remove");
        JButton infoBtn = new JButton("Info");
        JButton updateBtn = new JButton("Update");
        JButton clearBtn = new JButton("Clear");
        JButton scriptBtn = new JButton("Execute script");
        JButton historyBtn = new JButton("History");
        JButton helpBtn = new JButton("Help");

        addBtn.addActionListener(e -> {
            InputDragonFrame dragonInput = new InputDragonFrame(process, DataActions.ADD);
            dragonInput.init();
            tabbedPane.updateUI();
        });

        // FIXME: Update executes two times
        updateBtn.addActionListener(e -> {
            InputDragonFrame dragonInput = new InputDragonFrame(process, DataActions.UPDATE);
            dragonInput.init();
            tabbedPane.updateUI();
        });

        removeBtn.addActionListener(e -> {
            RemovalFrame removalFrame = new RemovalFrame(process);
            removalFrame.init();
            tabbedPane.updateUI();
        });

        infoBtn.addActionListener(e -> {
            Command infoCom = new InfoCommand();
            process.sendAndExecute(infoCom);
        });

        clearBtn.addActionListener(e -> {
            process.sendAndExecute(new ClearCommand());
        });

        scriptBtn.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            int retVal = fc.showOpenDialog(this);
            if (retVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                Command executeScript = new ExecuteScriptCommand(process, file);
                executeScript.execute();
            }
        });

        historyBtn.addActionListener(e -> {
            Command historyCom = new HistoryCommand();
            process.sendAndExecute(historyCom);
        });

        helpBtn.addActionListener(e -> {
            Command helpCom = new HelpCommand(process.getCommands());
            process.sendAndExecute(helpCom);
        });

        JMenuItem exitItem = new JMenuItem("Exit");
        JMenu languagesMenu = new JMenu("Languages");
        JMenuItem rusLang = new JMenuItem("Russian");
        JMenuItem engLang = new JMenuItem("English");
        JMenuItem spanEng = new JMenuItem("Spanish");
        JMenu file = new JMenu("File");
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(file);
        file.add(languagesMenu);
        file.add(exitItem);
        languagesMenu.add(rusLang);
        languagesMenu.add(engLang);
        languagesMenu.add(spanEng);

        exitItem.addActionListener(e ->  {
            dispose();
            System.exit(0);
        });

        JLabel curUser = new JLabel("Current User: " + process.getLogin());

        buttons.add(addBtn);
        buttons.add(updateBtn);
        buttons.add(removeBtn);
        buttons.add(infoBtn);
        buttons.add(scriptBtn);
        buttons.add(clearBtn);
        buttons.add(historyBtn);
        buttons.add(helpBtn);
        buttons.add(curUser);

        add(menuBar, BorderLayout.NORTH);

        add(buttons, BorderLayout.EAST);

        add(tabbedPane, BorderLayout.CENTER);

        // FIXME: there's no any localized message, so something should bw done with it
        new Thread(() -> {
            while(true) {
                process.sendAndExecute(new ShowCommand());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    JOptionPane.showMessageDialog(null, "Interrupted", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }).start();

        setVisible(true);
    }

    public void informationReceived(Response response) {
        if (response.getToExit()) {
            System.exit(0);
        } else if (response.getCollection() != null) {
            tablePanel.getModel().setCollection(response.getCollection());
//            tablePanel.updateUI();
//            mapPanel.updateUI();
            tablePanel.getTable().updateUI();
            mapPanel.repaint();
        } else if (response.getAnswer() != null) {
            StringBuilder builder = new StringBuilder();
            for (String str: response.getAnswer()) {
                builder.append(str).append("\n");
            }
            JOptionPane.showMessageDialog(null, builder.toString());
        } else if (!response.getMessage().equals("") && !(response.getMessage() == null)) {
            JOptionPane.showMessageDialog(null, response.getMessage(),
                    "Server message", JOptionPane.ERROR_MESSAGE);
        }
    }



}
