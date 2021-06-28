package gui;

import com.toedter.calendar.JDateChooser;
import dragon.*;
import dragon.Color;
import exceptions.NotEnoughDataException;
import utilities.Process;


import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class InputKillerFrame extends JFrame{

    Process process;
    Parent parent;

    JTextField nameField;
    JSpinner dateChooser;
    JComboBox<Color> eyeColorBox;
    JComboBox<Color> hairColorBox;
    JComboBox<Country> nationBox;
    JTextField xLocField;
    JTextField yLocField;
    JTextField zLocField;

    Border defaultBorder;


    public InputKillerFrame(Parent parent, Process process) {
        this.parent = parent;
        this.process = process;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        setLocation(550, 200);
//        setLocationRelativeTo(dragonFrame);
        setSize(320, 400);
    }

    public void init() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        createFields();
        setVisible(true);

    }

    private void createFields() {

        JButton okBtn = new JButton("OK");

        nameField = new JTextField();
        xLocField = new JFormattedTextField();
        yLocField = new JTextField();
        zLocField = new JTextField();
        eyeColorBox = new JComboBox<>(new Color[]{Color.BLACK, Color.GREEN, Color.ORANGE, Color.RED, Color.WHITE, Color.YELLOW});
        hairColorBox = new JComboBox<>(new Color[]{Color.BLACK, Color.GREEN, Color.ORANGE, Color.RED, Color.WHITE, Color.YELLOW});
        nationBox = new JComboBox<>(new Country[]{Country.USA, Country.INDIA, Country.ITALY, Country.VATICAN, Country.NORTH_KOREA});

        dateChooser = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateChooser, "dd-MM-yyyy kk:mm:ss");
        dateChooser.setEditor(dateEditor);
        dateChooser.setValue(new Date());

        defaultBorder = nameField.getBorder();

        nameField.setPreferredSize(new Dimension(200, 25));
        eyeColorBox.setPreferredSize(new Dimension(200, 25));
        dateChooser.setPreferredSize(new Dimension(200, 25));
        hairColorBox.setPreferredSize(new Dimension(200, 25));
        nationBox.setPreferredSize(new Dimension(200, 25));
        xLocField.setPreferredSize(new Dimension(50, 25));
        yLocField.setPreferredSize(new Dimension(50, 25));
        zLocField.setPreferredSize(new Dimension(50, 25));

        setActions();

        // TODO: need to make different versions of this behavior depending on which frame opened it
        okBtn.addActionListener(this::executeAction);


        JPanel namePan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel birthPan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel eyeColorPan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel hairColorPan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel nationPan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel locationPan = new JPanel(new FlowLayout(FlowLayout.LEFT));

        namePan.add(new JLabel("Killer Name:"));
        namePan.add(nameField);


        birthPan.add(new JLabel("Birthday: "));
        birthPan.add(dateChooser);
//        birthPan.add(timeSpinner);

        eyeColorPan.add(new JLabel("Eye Color:"));
        eyeColorPan.add(eyeColorBox);

        hairColorPan.add(new JLabel("Hair Color: "));
        hairColorPan.add(hairColorBox);

        nationPan.add(new JLabel("Nationality: "));
        nationPan.add(nationBox);

        locationPan.add(new JLabel("Location:"));
        locationPan.add(new JLabel("X: "));
        locationPan.add(xLocField);
        locationPan.add(new JLabel("Y: "));
        locationPan.add(yLocField);
        locationPan.add(new JLabel("Z: "));
        locationPan.add(zLocField);

        add(namePan);
        add(birthPan);
        add(eyeColorPan);
        add(hairColorPan);
        add(nationPan);
        add(locationPan);
        add(okBtn);

    }

    public void setActions() {
        xLocField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                integerKeyListener(e);
            }
        });
        yLocField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                integerKeyListener(e);
            }
        });
        zLocField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                integerKeyListener(e);
            }
        });

        xLocField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                ((JTextField)e.getSource()).setEditable(true);
            }
        });
        yLocField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                ((JTextField)e.getSource()).setEditable(true);
            }
        });
        zLocField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                ((JTextField)e.getSource()).setEditable(true);
            }
        });
    }

    private void integerKeyListener(KeyEvent e) {
        char c = e.getKeyChar();
        JTextField field = (JTextField) e.getSource();
        if (Character.isDigit(c) || e.getKeyCode() == KeyEvent.VK_BACK_SPACE || c == '-') {
            field.setEditable(true);
        } else {
            field.setEditable(false);
        }
    }

    public Person buildKiller() throws NotEnoughDataException {

        checkIfAllFieldsAreValid();

        Person killer = new Person();
        killer.setName(nameField.getText());
        killer.setBirthday(((Date) dateChooser.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        killer.setEyeColor((Color) eyeColorBox.getSelectedItem());
        killer.setHairColor(hairColorBox.getItemAt(hairColorBox.getSelectedIndex()));
        killer.setNationality(nationBox.getItemAt(nationBox.getSelectedIndex()));
        killer.setLocation(buildLocation());

        return killer;
    }

    private void checkIfAllFieldsAreValid() throws NotEnoughDataException {
        String errorMessage = null;
        if (nameField.getText().equals("")) {
            errorMessage = "Killer's name is mandatory";
        } else if (xLocField.getText().equals("") || yLocField.getText().equals("") || zLocField.getText().equals("")) {
            errorMessage = "Killer's Location should be specified in all 3 coordinates";
        }

        if (errorMessage != null) {
            throw new NotEnoughDataException(errorMessage);
        }
    }

    public Location buildLocation() {
        int x = Integer.parseInt(xLocField.getText());
        Long y = Long.parseLong(yLocField.getText());
        long z = Long.parseLong(zLocField.getText());

        return new Location(x, y, z);
    }

    private void executeAction(ActionEvent e) {
        parent.mainButtonAction(e);
    }

}
