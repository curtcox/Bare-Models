package net.baremodels.device.swing;

import ionic.app.NucleusTestFactory;
import net.baremodels.runner.AppContext;
import net.baremodels.runner.SyncRunner;

public class SwingSyncBrowserRunnerMain {

    AppContext appContext = NucleusTestFactory.newAppContext();
    SyncRunner runner = SwingSyncRunner.newInstance(appContext, model-> System.out.println(model));

    public static void main(String[] args) {
        SwingSyncBrowserRunnerMain test = new SwingSyncBrowserRunnerMain();
        test.runner.setModel(NucleusTestFactory.newNucleusBrowserModel(),x->false);
    }

}
