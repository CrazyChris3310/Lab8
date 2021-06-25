package gui;

import dragon.Dragon;
import utilities.Process;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TablePanel extends JPanel {
    Process process;
    JTable table;
    DragonTableModel model;

    public TablePanel(Process process) {
        this.process = process;

        setLayout(new BorderLayout());

        model = new DragonTableModel();
        table = new JTable(model);

//        String[] typeNames = new String[] {"AIR", "FIRE", "WATER", "UNDERGROUND"};
//        JComboBox<String> boxForDragonType = new JComboBox<>(typeNames);
//
//        String[] colorNames = new String[] {"AIR", "FIRE", "WATER", "UNDERGROUND"};
//        JComboBox<String> boxForColors = new JComboBox<>(colorNames);
//
//        String[] countryNames = new String[] {"AIR", "FIRE", "WATER", "UNDERGROUND"};
//        JComboBox<String> boxForCounties = new JComboBox<>(countryNames);
//
//        table.getColumnModel().getColumn(8).setCellEditor(new DefaultCellEditor(boxForDragonType));
//        table.getColumnModel().getColumn(12).setCellEditor(new DefaultCellEditor(boxForColors));
//        table.getColumnModel().getColumn(13).setCellEditor(new DefaultCellEditor(boxForColors));
//        table.getColumnModel().getColumn(14).setCellEditor(new DefaultCellEditor(boxForCounties));
//
//        JFormattedTextField field = new JFormattedTextField();
//
//        table.getColumnModel().getColumn(14).setCellEditor(new DefaultCellEditor(field));

//        Timer timer = new Timer(1000, e-> {
//            if (!table.isEditing()) {
//                model.setCollection(dragons);
//                table.updateUI();
//            }
//        });
//        timer.start();

        JScrollPane paneWithTable = new JScrollPane(table);

        add(paneWithTable, BorderLayout.CENTER);

//        new Thread(() -> {
//            while(true) {
//                process.sendAndExecute("show");
//                model.setCollection(process.getCollection());
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    JOptionPane.showMessageDialog(null, "Interrupted", "Error", JOptionPane.ERROR_MESSAGE);
//                }
//            }
//        }).start();

        SwingWorker<ArrayList<Dragon>, Void> worker = new SwingWorker<ArrayList<Dragon>, Void>() {
            @Override
            protected ArrayList<Dragon> doInBackground() throws Exception {
                process.sendAndExecute("show");
                return process.getCollection();
            }

            @Override
            protected void done() {
                try {
                    model.setCollection(get());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Exception", "error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        Timer timer = new Timer(5000, (e)-> {
            worker.execute();
        });
        timer.start();


    }

    public DragonTableModel getModel() {
        return model;
    }

    public void setTableCollection(ArrayList<Dragon> collection) {
        model.setCollection(collection);
    }
}
