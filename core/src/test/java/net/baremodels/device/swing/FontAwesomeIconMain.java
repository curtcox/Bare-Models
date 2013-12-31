package net.baremodels.device.swing;

import javax.swing.*;
import java.awt.*;

public class FontAwesomeIconMain {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> main());
    }

    private static void main() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Icon icon = new FontAwesomeIcon('\uf0e0', 160);
        frame.setContentPane(new JLabel(icon));
        frame.pack();
        frame.validate();
        frame.setVisible(true);
    }
}
