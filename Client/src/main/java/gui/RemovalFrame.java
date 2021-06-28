package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import dragon.Dragon;
import dragon.Person;
import exceptions.NotEnoughDataException;
import utilities.Process;
import utilities.commands.Command;
import utilities.commands.RemoveAnyByKillerCommand;
import utilities.commands.RemoveByIdCommand;
import utilities.commands.RemoveGreaterCommand;

public class RemovalFrame extends JFrame implements Parent {

    JRadioButton rmById;
    JTextField idField;
    JRadioButton rmByKiller;
    JRadioButton removeGreater;
    JButton removeBtn;

    InputKillerFrame killerFrame;
    InputDragonFrame dragonFrame;

    Process process;

    public RemovalFrame(Process process) {
        this.process = process;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(550, 200);
        setSize(300, 260);

    }

    public void init() {

        setLayout(null);
        setResizable(false);

        createFields();
        setVisible(true);

        rmByKiller.addActionListener(e -> {
            killerFrame = new InputKillerFrame(this, process);
            killerFrame.init();
            if (dragonFrame != null) {
                dragonFrame.dispose();
            }
        });

        removeGreater.addActionListener(e -> {
            dragonFrame = new InputDragonFrame(process, DataActions.REMOVE);
            dragonFrame.init();
            if (killerFrame != null) {
                killerFrame.dispose();
            }
        });

        rmById.addActionListener(e -> {
            if (killerFrame != null) {
                killerFrame.dispose();
            } if (dragonFrame != null) {
                dragonFrame.dispose();
            }
        });

    }

    private void createFields() {
        rmById = new JRadioButton("Remove by id: ");
        rmByKiller = new JRadioButton("Remove by killer");
        removeGreater = new JRadioButton("Remove greater");
        removeBtn = new JButton("Remove");

        idField = new JTextField();

        idField.setPreferredSize(new Dimension(50, 25));

//        GroupLayout layout = new GroupLayout(getContentPane());
//        layout.setAutoCreateGaps(true);
//        layout.setAutoCreateContainerGaps(true);
//
//        layout.setHorizontalGroup(layout.createSequentialGroup()
//                .addGroup(layout.createParallelGroup()
//                    .addComponent(rmById)
//                    .addComponent(rmByKiller)
//                    .addComponent(removeGreater))
//                .addComponent(idField));
//
//        layout.setVerticalGroup(layout.createSequentialGroup()
//                .addGroup(layout.createParallelGroup()
//                        .addComponent(rmById)
//                        .addComponent(idField))
//                .addComponent(rmByKiller)
//                .addComponent(removeGreater)
//                .addComponent(removeBtn));

        ButtonGroup group = new ButtonGroup();
        group.add(rmById);
        group.add(rmByKiller);
        group.add(removeGreater);

        rmById.setBounds(8, 10, 120, 25);
        idField.setBounds(130, 10, 140, 25);
        rmByKiller.setBounds(8, 50, 326, 46);
        removeGreater.setBounds(8, 101, 326, 46);
        removeBtn.setBounds(50, 153, 188, 41);


        add(rmByKiller);
        add(idField);
        add(rmById);
        add(removeGreater);
        add(removeBtn);

        removeBtn.addActionListener(this::mainButtonAction);

    }

    // FIXME: Seems like removeById is not working
    @Override
    public void mainButtonAction(ActionEvent e) {
        Command command;
        if (rmById.isSelected()) {
            Long id = Long.parseLong(idField.getText());
            command = new RemoveByIdCommand(id);
        } else if (rmByKiller.isSelected()) {
            try {
                Person killer = killerFrame.buildKiller();
                command = new RemoveAnyByKillerCommand(killer);
            } catch (NotEnoughDataException exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage(),
                        "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } else  {
            dragonFrame.mainButtonAction(e);
            dispose();
            return;
//            Dragon dragon = dragonFrame.buildDragon();
//            command = new RemoveGreaterCommand(dragon);
        }
        process.sendAndExecute(command);
        dispose();
    }
}
