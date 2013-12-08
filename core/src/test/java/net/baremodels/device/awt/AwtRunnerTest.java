package net.baremodels.device.awt;

import ionic.app.NucleusTestFactory;
import net.baremodels.runner.SyncRunner;

public class AwtRunnerTest {

    public static void main(String[] args) {
        SyncRunner runner = new AwtRunner(model-> System.out.println(model));
        runner.setModel(NucleusTestFactory.newNucleusModel(),x -> false);
    }

}
