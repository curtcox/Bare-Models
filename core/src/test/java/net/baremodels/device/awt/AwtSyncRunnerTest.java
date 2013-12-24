package net.baremodels.device.awt;

import ionic.app.NucleusTestFactory;
import net.baremodels.model.NavigationContext;
import net.baremodels.runner.AppContext;
import net.baremodels.runner.SimpleAppContext;
import net.baremodels.runner.SyncRunner;

public class AwtSyncRunnerTest {

    public static void main(String[] args) {
        AppContext appContext = new SimpleAppContext();
        NavigationContext navigationContext = new NavigationContext();
        SyncRunner runner = new AwtSyncRunner(appContext,navigationContext,model-> System.out.println(model));
        runner.setModel(NucleusTestFactory.newNucleusModel(),x -> false);
    }

}
