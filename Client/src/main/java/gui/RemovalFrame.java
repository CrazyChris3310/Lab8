package gui;

import javax.swing.*;
import java.awt.*;

public class RemovalFrame extends JFrame {

    JRadioButton rmById;
    JTextField idField;
    JRadioButton rmByKiller;
    JRadioButton removeGreater;
    JButton removeBtn;

    public RemovalFrame() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(550, 200);
        setSize(300, 260);

    }

    public void init() {

        setLayout(null);
        setResizable(false);

        createFields();
        setVisible(true);


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


    }

}
