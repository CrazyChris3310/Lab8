package gui;

import dragon.DragonType;

import javax.swing.*;
import java.awt.*;

public class InputDragonFrame extends JFrame {

    JTextField nameField;
    JTextField xCordField;
    JTextField yCordField;
    JTextField descField;
    JTextField ageField;
    JTextField wingspanField;
    JComboBox<DragonType> DragonTypeBox ;
    JRadioButton hasKiller;
    JRadioButton noKiller;

    public InputDragonFrame() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(550, 200);
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
        xCordField = new JFormattedTextField();
        yCordField = new JFormattedTextField();
        descField = new JTextField();
        ageField = new JTextField();
        wingspanField = new JTextField();
        DragonTypeBox = new JComboBox<>();
        hasKiller = new JRadioButton("Yes");
        noKiller = new JRadioButton("No");
        ButtonGroup group = new ButtonGroup();
        group.add(hasKiller);
        group.add(noKiller);

        nameField.setPreferredSize(new Dimension(200, 25));
        xCordField.setPreferredSize(new Dimension(70, 25));
        yCordField.setPreferredSize(new Dimension(70, 25));
        descField.setPreferredSize(new Dimension(200, 25));
        ageField.setPreferredSize(new Dimension(70, 25));
        wingspanField.setPreferredSize(new Dimension(70, 25));
        DragonTypeBox.setPreferredSize(new Dimension(200, 25));
        hasKiller.setPreferredSize(new Dimension(70, 25));
        noKiller.setPreferredSize(new Dimension(70, 25));

        DragonTypeBox.addItem(DragonType.AIR);
        DragonTypeBox.addItem(DragonType.FIRE);
        DragonTypeBox.addItem(DragonType.UNDERGROUND);
        DragonTypeBox.addItem(DragonType.WATER);

        JPanel namePan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel cordsPan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel descPan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel numPan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel typePan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel killerPan = new JPanel(new FlowLayout(FlowLayout.LEFT));

        namePan.add(new JLabel("Dragon Name:"));
        namePan.add(nameField);

        cordsPan.add(new JLabel("Coordinates:"));
        cordsPan.add(new JLabel("X:"));
        cordsPan.add(xCordField);
        cordsPan.add(new JLabel("Y:"));
        cordsPan.add(yCordField);
        descPan.add(new JLabel("Description:"));
        descPan.add(descField);
        numPan.add(new JLabel("Age:"));
        numPan.add(ageField);
        numPan.add(new JLabel("Wingspan:"));
        numPan.add(wingspanField);
        typePan.add(new JLabel("Dragon Type:"));
        typePan.add(DragonTypeBox);
        killerPan.add(new JLabel("Killer:"));
        killerPan.add(hasKiller);
        killerPan.add(noKiller);

        add(namePan);
        add(cordsPan);
        add(descPan);
        add(numPan);
        add(typePan);
        add(killerPan);
        add(okBtn);

    }

}
