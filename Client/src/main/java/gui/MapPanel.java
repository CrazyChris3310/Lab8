package gui;

import utilities.Process;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {

    Process process;

    public MapPanel(Process process) {
        this.process = process;
        setLayout(new BorderLayout());
    }
}
