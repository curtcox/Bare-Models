package net.baremodels.device.swing;

import ionic.app.NucleusTestFactory;
import net.baremodels.model.NavigationContext;
import net.baremodels.runner.AppContext;
import net.baremodels.runner.SimpleAppContext;
import net.baremodels.runner.SyncRunner;

public class SwingSyncRunnerMain {

    AppContext appContext = new SimpleAppContext();
    NavigationContext navigationContext = new NavigationContext();
    SyncRunner runner = SwingSyncRunner.newInstance(appContext,navigationContext, model-> System.out.println(model));

    public static void main(String[] args) {
        SwingSyncRunnerMain test = new SwingSyncRunnerMain();
        test.runner.setModel(NucleusTestFactory.newNucleusModel(),x->false);
    }

}