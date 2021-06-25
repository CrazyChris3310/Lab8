package gui;

import dragon.Color;
import dragon.Country;
import dragon.DragonType;

import javax.swing.*;
import java.awt.*;

public class InputKillerFrame extends JFrame{

    JTextField nameField;
    JTextField birthField;
    JComboBox<Color> eyeColorBox;
    JComboBox<Color> hairColorBox;
    JComboBox<Country> nationBox;
    JTextField xLocField;
    JTextField yLocField;
    JTextField zLocField;


    public InputKillerFrame() {
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
        birthField = new JFormattedTextField();
        xLocField = new JFormattedTextField();
        yLocField = new JTextField();
        zLocField = new JTextField();
        eyeColorBox = new JComboBox<>(new Color[]{Color.BLACK, Color.GREEN, Color.ORANGE, Color.RED, Color.WHITE, Color.YELLOW});
        hairColorBox = new JComboBox<>(new Color[]{Color.BLACK, Color.GREEN, Color.ORANGE, Color.RED, Color.WHITE, Color.YELLOW});
        nationBox = new JComboBox<>(new Country[]{Country.USA, Country.INDIA, Country.ITALY, Country.VATICAN, Country.NORTH_KOREA});

        nameField.setPreferredSize(new Dimension(200, 25));
        birthField.setPreferredSize(new Dimension(200, 25));
        eyeColorBox.setPreferredSize(new Dimension(200, 25));
        hairColorBox.setPreferredSize(new Dimension(200, 25));
        nationBox.setPreferredSize(new Dimension(200, 25));
        xLocField.setPreferredSize(new Dimension(50, 25));
        yLocField.setPreferredSize(new Dimension(50, 25));
        zLocField.setPreferredSize(new Dimension(50, 25));


        JPanel namePan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel birthPan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel eyeColorPan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel hairColorPan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel nationPan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel locationPan = new JPanel(new FlowLayout(FlowLayout.LEFT));

        namePan.add(new JLabel("Killer Name:"));
        namePan.add(nameField);

        birthPan.add(new JLabel("Birthday: "));
        birthPan.add(birthField);

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

}
