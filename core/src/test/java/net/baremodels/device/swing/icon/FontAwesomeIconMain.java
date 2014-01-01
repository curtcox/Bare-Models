package net.baremodels.device.swing.icon;

import net.baremodels.device.swing.icon.FontAwesomeIcon;
import net.baremodels.device.swing.icon.FontAwesomeIcon.Glyph;

import javax.swing.*;
import java.awt.*;

public class FontAwesomeIconMain {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> main());
    }

    private static void main() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(74,10));
        for (Glyph glyph : Glyph.values()) {
            panel.add(new JLabel(glyph.name()));
            panel.add(new JLabel(new FontAwesomeIcon(glyph, 13)));
        }
        frame.setContentPane(panel);
        frame.pack();
        frame.validate();
        frame.setVisible(true);
    }
}
