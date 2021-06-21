package gui;

import dragon.Dragon;
import dragon.Person;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class DragonTableModel extends AbstractTableModel {

    private ArrayList<String[]> collection;
    private String[] columnNames;

    public DragonTableModel() {
        columnNames = new String[]{"id", "Dragon name", "creation date", "X coordinate", "YCoordinate", "Age",
                "Description", "Wingspan", "Dragon type", "Killer", "Killer's birthday", "Eye color", "Hair color",
                "Nationality", "X Location", "Y Location", "Z Location", "Owner"};
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return collection.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return collection.get(rowIndex)[columnIndex];

    }

    public ArrayList<String[]> getCollection() {
        return collection;
    }

    public void setCollection(ArrayList<String[]> collection) {
        this.collection = collection;
    }
}
