package net.baremodels.device.swing.icon;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

    interface PanelFactory {
        JPanel createPanel() throws Exception;
    }

    public static void main(String[] args) {
        showFrameLater(() -> {
            JPanel panel = new JPanel();
            panel.add(new JLabel("Demo"));
            return panel;
        });
    }

    public static void showFrameLater(PanelFactory panelSupplier) {
        EventQueue.invokeLater(() -> {
            try {
                showFrame(panelSupplier);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void showFrame(PanelFactory panelSupplier) throws Exception {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panelSupplier.createPanel());
        frame.pack();
        frame.validate();
        frame.setVisible(true);
    }

}
