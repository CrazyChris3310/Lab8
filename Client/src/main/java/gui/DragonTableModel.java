package gui;
import dragon.*;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

public class DragonTableModel extends AbstractTableModel {

    private ArrayList<Dragon> collection;
    private String[] columnNames;

    public DragonTableModel() {
        collection = new ArrayList<>();
        columnNames = new String[]{"id", "Dragon name", "creation date", "X coordinate", "YCoordinate", "Age",
                "Description", "Wingspan", "Dragon type", "Killer", "Name" ,"Birthday", "Eye color", "Hair color",
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
        return defineField(rowIndex, columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Object obj = defineField(0, columnIndex);
        if (obj == null)
            return Object.class;
        else
            return obj.getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    // FIXME: make something with errorCode
    // FIXME: aValue is always a string
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        int errorCode = setField(aValue, rowIndex, columnIndex);
        if (errorCode == 1) {
            System.out.println("ERRRRPR");
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public ArrayList<Dragon> getCollection() {
        return collection;
    }

    public void setCollection(ArrayList<Dragon> collection) {
        this.collection = collection;
    }

    private Object defineField(int row, int col) {
        Dragon dragon = collection.get(row);
        Person killer = dragon.getKiller();

        switch (col) {
            case 0: return dragon.getId();
            case 1: return dragon.getName();
            case 2: return dragon.getCreationDate();
            case 3: return dragon.getCoordinates().getX();
            case 4: return dragon.getCoordinates().getY();
            case 5: return dragon.getAge();
            case 6: return dragon.getDescription();
            case 7: return dragon.getWingspan();
            case 8: return dragon.getType();
            case 9: return killer != null;
            case 18: return dragon.getOwner();
        }

        if (killer == null) {
            return null;
        }

        switch (col) {
            case 10: return killer.getName();
            case 11: return killer.getBirthday();
            case 12: return killer.getEyeColor();
            case 13: return killer.getHairColor();
            case 14: return killer.getNationality();
            case 15: return killer.getLocation().getX();
            case 16: return killer.getLocation().getY();
            case 17: return killer.getLocation().getZ();
        }

        return null;
    }

    // TODO: check for data types, now it is only String and Number
    private int setField(Object val, int row, int col) {

        String value;
        if (val instanceof Number)
            value = String.valueOf(val);
        if (val instanceof LocalDateTime)
            value = ((LocalDateTime) val).format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
        if (val instanceof ZonedDateTime)
            value = ((ZonedDateTime) val).format(DateTimeFormatter.ofPattern("dd-MM-yy hh:mm:ss xxxxx"));
        if (val instanceof Boolean)
            value = String.valueOf(val);
        else
            value = (String) val;

        Dragon dragon = collection.get(row);
        Person killer = dragon.getKiller();

        switch (col) {
            case 1: dragon.setName(value); return 0;
            case 3: dragon.getCoordinates().setX(Long.parseLong(value)); return 0;
            case 4: dragon.getCoordinates().setY(Float.parseFloat(value)); return 0;
            case 5: dragon.setAge(Integer.parseInt(value)); return 0;
            case 6: dragon.setDescription(value); return 0;
            case 7: dragon.setWingspan(Long.parseLong(value)); return 0;
            case 8: dragon.setType(DragonType.valueOf(value)); return 0;
        }

        if (killer == null) {
            if (value == null)
                return 0;
            else
                killer = new Person();
        }

        if (value == null) {
            dragon.setKiller(null);
            return 0;
        }

        switch (col) {
            case 10: killer.setName(value); break;
            case 11: killer.setBirthday(LocalDateTime.parse(value)); break;
            case 12: killer.setEyeColor(Color.valueOf(value)); break;
            case 13: killer.setHairColor(Color.valueOf(value)); break;
            case 14: killer.setNationality(Country.valueOf(value)); break;
            case 15: killer.getLocation().setX(Integer.parseInt(value)); break;
            case 16: killer.getLocation().setY(Long.parseLong(value)); break;
            case 17: killer.getLocation().setZ(Long.parseLong(value)); break;
        }

        dragon.setKiller(killer);
        return 0;

    }

}
