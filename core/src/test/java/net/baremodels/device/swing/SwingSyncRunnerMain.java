package net.baremodels.device.swing;

import ionic.app.NucleusTestFactory;
import net.baremodels.runner.AppContext;
import net.baremodels.runner.NextModelGenerator;
import net.baremodels.runner.SelectedNextModelGenerator;
import net.baremodels.runner.SyncRunner;

public class SwingSyncRunnerMain {

    AppContext appContext = NucleusTestFactory.newAppContext();
    NextModelGenerator generator = new SelectedNextModelGenerator();
    SyncRunner runner = SwingSyncRunner.newInstance(appContext, generator, model-> System.out.println(model));

    public static void main(String[] args) {
        SwingSyncRunnerMain test = new SwingSyncRunnerMain();
        test.runner.setModel(NucleusTestFactory.newNucleusModel(),x->false);
    }

}
