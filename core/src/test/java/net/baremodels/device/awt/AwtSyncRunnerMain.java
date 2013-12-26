package net.baremodels.device.awt;

import ionic.app.NucleusTestFactory;
import net.baremodels.model.NavigationContext;
import net.baremodels.runner.AppContext;
import net.baremodels.runner.SimpleAppContext;
import net.baremodels.runner.SyncRunner;

public class AwtSyncRunnerMain {

    AppContext appContext = new SimpleAppContext();
    NavigationContext navigationContext = new NavigationContext();
    SyncRunner runner = AwtSyncRunner.newInstance(appContext, navigationContext, model -> System.out.println(model));

    public static void main(String[] args) {
        AwtSyncRunnerMain test = new AwtSyncRunnerMain();
        test.runner.setModel(NucleusTestFactory.newNucleusModel(),x->false);
    }

}
