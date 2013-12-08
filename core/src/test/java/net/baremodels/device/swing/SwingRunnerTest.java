package net.baremodels.device.swing;

import ionic.app.NucleusTestFactory;
import net.baremodels.runner.SyncRunner;

public class SwingRunnerTest {

    public static void main(String[] args) {
        SyncRunner runner = new SwingRunner(model-> System.out.println(model));
        runner.setModel(NucleusTestFactory.newNucleusModel(),x->false);
    }

}
