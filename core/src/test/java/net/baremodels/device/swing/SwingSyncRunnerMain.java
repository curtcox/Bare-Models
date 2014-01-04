package net.baremodels.device.swing;

import ionic.app.NucleusTestFactory;
import net.baremodels.runner.AppContext;
import net.baremodels.runner.SyncRunner;

public class SwingSyncRunnerMain {

    AppContext appContext = NucleusTestFactory.newAppContext();
    SyncRunner runner = SwingSyncRunner.newInstance(appContext, model-> System.out.println(model));

    public static void main(String[] args) {
        SwingSyncRunnerMain test = new SwingSyncRunnerMain();
        test.runner.setModel(NucleusTestFactory.newNucleusModel(),x->false);
    }

}
