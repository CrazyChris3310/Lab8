package gui;

import utilities.ConnectionManager;

import javax.swing.*;
import java.awt.*;

public class AuthorizationFrame extends JFrame {

    JTextField loginField;
    JPasswordField passwordField;

    ConnectionManager connectionManager;

    public AuthorizationFrame(ConnectionManager cm) {
        connectionManager = cm;

        setTitle("Authorization Page");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 200);
        setResizable(false);
        setLocationRelativeTo(null);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        createDataInputFields();
        createButtons();

        setVisible(true);
    }

    private void createDataInputFields() {

        JPanel up = new JPanel(new GridLayout(2, 2));

        loginField = new JTextField();
        passwordField = new JPasswordField();

        loginField.setPreferredSize(new Dimension(100, 25));;
        passwordField.setPreferredSize(new Dimension(100, 25));

        JLabel loginLbl = new JLabel("Login: ");
        JLabel passwordLbl = new JLabel("Password: ");

        up.add(loginLbl);
        up.add(loginField);
        up.add(passwordLbl);
        up.add(passwordField);

        up.setBorder(BorderFactory.createEmptyBorder(30, 40, 40, 40));

        add(up);
    }

    private void createButtons() {
        JPanel buttons = new JPanel(new FlowLayout());

        JButton loginBtn = new JButton("Login");
        JButton signUpBtn = new JButton("SignUp");

        buttons.add(loginBtn);
        buttons.add(signUpBtn);

        add(buttons);
    }

}
