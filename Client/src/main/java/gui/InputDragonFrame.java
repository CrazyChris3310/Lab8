package gui;

import dragon.Coordinates;
import dragon.Dragon;
import dragon.DragonType;
import dragon.Person;
import exceptions.NotEnoughDataException;
import utilities.Process;
import utilities.commands.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.function.Predicate;

public class InputDragonFrame extends JFrame implements Parent {

    Process process;
    InputKillerFrame inputKillerFrame;

    JLabel idLabel;
    JTextField idField;

    JTextField nameField;
    JTextField xCordField;
    JTextField yCordField;
    JTextField descField;
    JTextField ageField;
    JTextField wingspanField;
    JComboBox<DragonType> DragonTypeBox ;
    JRadioButton hasKiller;
    JRadioButton noKiller;

    final DataActions action;

    JButton okBtn;

    // TODO: add different labels on button depending which command was chosen
    public InputDragonFrame(Process process, DataActions action) {
        inputKillerFrame = null;
        this.process = process;
        this.action = action;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setLocation(550, 200);
        setSize(320, 400);
        okBtn = new JButton("OK");
    }

    public void init() {
        createFields();
        setVisible(true);
    }

    private void createFields() {

        nameField = new JTextField();
        xCordField = new JFormattedTextField();
        yCordField = new JFormattedTextField();
        descField = new JTextField();
        ageField = new JTextField();
        wingspanField = new JTextField();
        DragonTypeBox = new JComboBox<>();
        hasKiller = new JRadioButton("Yes");
        noKiller = new JRadioButton("no");
        ButtonGroup group = new ButtonGroup();
        group.add(hasKiller);
        group.add(noKiller);

        hasKiller.addActionListener(e -> {
            if (hasKiller.isSelected()) {
                inputKillerFrame = new InputKillerFrame(this, process);
                inputKillerFrame.init();
            }
        });

        noKiller.addActionListener(e -> {
            if (inputKillerFrame != null) {
                inputKillerFrame.dispose();
                inputKillerFrame = null;
            }
        });

        okBtn.addActionListener(this::mainButtonAction);

        nameField.setPreferredSize(new Dimension(200, 25));
        xCordField.setPreferredSize(new Dimension(70, 25));
        yCordField.setPreferredSize(new Dimension(70, 25));
        descField.setPreferredSize(new Dimension(200, 25));
        ageField.setPreferredSize(new Dimension(70, 25));
        wingspanField.setPreferredSize(new Dimension(70, 25));
        DragonTypeBox.setPreferredSize(new Dimension(200, 25));
        hasKiller.setPreferredSize(new Dimension(70, 25));
        noKiller.setPreferredSize(new Dimension(70, 25));

        DragonTypeBox.addItem(null);
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

        if (action == DataActions.UPDATE) {
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            okBtn.setText("Update");
            idLabel = new JLabel("Id: ");
            idField = new JTextField();
            idField.setPreferredSize(new Dimension(250, 25));
            panel.add(idLabel);
            panel.add(idField);
            add(panel);
        } else if (action == DataActions.ADD) {
            okBtn.setText("Add");
        } else if (action == DataActions.REMOVE) {
            okBtn.setText("Remove");
        }

        setActions();

        add(namePan);
        add(cordsPan);
        add(descPan);
        add(numPan);
        add(typePan);
        add(killerPan);
        add(okBtn);
    }

    public Dragon buildDragon() throws NotEnoughDataException {

        checkIfAllFieldsAreValid();

        Dragon dragon = new Dragon();
        dragon.setName(nameField.getText());
        dragon.setWingspan(readWingSpan());
        dragon.setType(DragonTypeBox.getItemAt(DragonTypeBox.getSelectedIndex()));
        dragon.setDescription(descField.getText());
        dragon.setAge(Integer.parseInt(ageField.getText()));
        dragon.setOwner(process.getLogin());
        dragon.setCoordinates(buildCoordinates());

        Person killer;
        // FIXME: If I set No killer it still remains requires data for killer
        if (inputKillerFrame == null) {
            dragon.setKiller(null);
        } else {
            killer = inputKillerFrame.buildKiller();
            dragon.setKiller(killer);
            inputKillerFrame.dispose();
            inputKillerFrame = null;
        }

        return dragon;
    }

    private void checkIfAllFieldsAreValid() throws NotEnoughDataException {
        String errorMsg = null;
        if (nameField.getText().equals(""))
            errorMsg = "Name is mandatory";
        else if (ageField.getText().equals(""))
            errorMsg = "Age is mandatory";
        else if (xCordField.getText().equals("") || yCordField.getText().equals(""))
            errorMsg = "All coordinates should be specified";
        if (action == DataActions.UPDATE && idField.getText().equals(""))
            errorMsg = "Id should be specified";
        if (errorMsg != null) {
            throw new NotEnoughDataException(errorMsg);
        }
    }

    private Long readWingSpan() {
        if (wingspanField.getText().equals("")) {
            return null;
        } else
            return Long.parseLong(wingspanField.getText());
    }

    private Coordinates buildCoordinates() {
        Long x = Long.parseLong(xCordField.getText());
        float y = Float.parseFloat(yCordField.getText());
        return new Coordinates(x, y);
    }

    private void setActions() {

        xCordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                xCordField.setEditable(true);
                if (xCordField.getText().equals(""))
                    return;
                if (Integer.parseInt(xCordField.getText()) > 302) {
                    xCordField.setText("302");
                }
            }
        });
        yCordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                yCordField.setEditable(true);
            }
        });
        ageField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                ageField.setEditable(true);
            }
        });
        wingspanField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                wingspanField.setEditable(true);
            }
        });

        xCordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                checker(e, (c) -> Character.isDigit(c) || e.getKeyCode() == KeyEvent.VK_BACK_SPACE || c == '-');
            }
        });
        yCordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                checker(e,(c) ->Character.isDigit(c) || e.getKeyCode() == KeyEvent.VK_BACK_SPACE
                        || c == '-' || c == '.' || c == ',');
            }
        });
        ageField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                checker(e, (c) -> Character.isDigit(c) || e.getKeyCode() == KeyEvent.VK_BACK_SPACE);
            }
        });
        wingspanField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                checker(e, (c) -> Character.isDigit(c) || e.getKeyCode() == KeyEvent.VK_BACK_SPACE);
            }
        });

        if (action == DataActions.UPDATE) {
            idField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    checker(e, (c) -> Character.isDigit(c) || e.getKeyCode() == KeyEvent.VK_BACK_SPACE); }
            });
            idField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    yCordField.setEditable(true);
                }
            });
        }


    }

    private void checker(KeyEvent e, Predicate<Character> predicate) {
        char c = e.getKeyChar();
        JTextField field = (JTextField) e.getSource();
        field.setEditable(predicate.test(c));

    }

    @Override
    public void mainButtonAction(ActionEvent e) {
        Dragon dragon;
        try {
            dragon = buildDragon();
        } catch (NotEnoughDataException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage(),
                    "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Command command;
        if (action == DataActions.ADD) {
            command = new AddCommand(dragon);
        } else if (action == DataActions.UPDATE) {
            Long id = Long.parseLong(idField.getText());
            command = new UpdateIdCommand(id, dragon);
        } else {
            command = new RemoveGreaterCommand(dragon);
        }
        process.sendAndExecute(command);
        process.sendAndExecute(new ShowCommand());

        if (inputKillerFrame != null) {
            inputKillerFrame.dispose();
        }
        dispose();
    }
}
