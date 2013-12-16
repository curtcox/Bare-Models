package net.baremodels.device.swing;

import ionic.app.NucleusTestFactory;
import net.baremodels.runner.AppContext;
import net.baremodels.runner.SimpleAppContext;
import net.baremodels.runner.SyncRunner;

public class SwingSyncRunnerTest {

    public static void main(String[] args) {
        AppContext appContext = new SimpleAppContext();
        SyncRunner runner = new SwingSyncRunner(appContext,model-> System.out.println(model));
        runner.setModel(NucleusTestFactory.newNucleusModel(),x->false);
    }

}
