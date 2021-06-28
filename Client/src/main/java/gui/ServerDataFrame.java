package gui;

import exceptions.ServerUnavailableException;
import utilities.ConnectionManager;

import javax.swing.*;
import java.awt.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;


public class ServerDataFrame extends JFrame {

    private JTextField hostNameField;
    private JTextField portField;

    ConnectionManager connectionManager;


    public ServerDataFrame(ConnectionManager connectionManager) {

        this.connectionManager = connectionManager;

        setTitle("Connection Page");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 200);
        setResizable(false);
        setLocationRelativeTo(null);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        createInputFields();
        createButtons();

        setVisible(true);
    }

    private void createInputFields() {
        JPanel up = new JPanel(new FlowLayout());
        JLabel hostNameLabel = new JLabel("Ip: ");
        JLabel portLabel = new JLabel("Port: ");

        up.setBorder(BorderFactory.createEmptyBorder(30, 10, 20, 10));

        hostNameField = new JTextField();
        portField = new JTextField();

        hostNameField.setPreferredSize(new Dimension(100, 25));
        portField.setPreferredSize(new Dimension(70, 25));

        up.add(hostNameLabel);
        up.add(hostNameField);
        up.add(portLabel);
        up.add(portField);

        add(up);
    }

    private void createButtons() {
        JPanel down = new JPanel(new FlowLayout());
        JButton connectBtn = new JButton("Connect");
        JButton cancelBtn = new JButton("Cancel");

        cancelBtn.addActionListener(e -> dispose());
        connectBtn.addActionListener(e ->  {
            if (validatePort()) {
                try {
                    connectionManager.connect();
                    new AuthorizationFrame(connectionManager);
                    dispose();
                } catch (ServerUnavailableException exception) {
                    JOptionPane.showMessageDialog(null, "Server is temporarily unavailable",
                            "Connection Error", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Wrong port format", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        down.add(connectBtn);
        down.add(cancelBtn);


        add(down);
    }

    private boolean validatePort() {
        try {
            int port = Integer.parseInt(portField.getText());
            if (port >=0 && port <= 99999) {
                SocketAddress adr = new InetSocketAddress(hostNameField.getText(), port);
                connectionManager.setAdr(adr);
                return true;
            }
            else
                return false;
        } catch (NumberFormatException e) {
            return false;
        }

    }

}
