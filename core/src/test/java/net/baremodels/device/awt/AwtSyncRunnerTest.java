package net.baremodels.device.awt;

import ionic.app.NucleusTestFactory;
import net.baremodels.runner.AppContext;
import net.baremodels.runner.SyncRunner;

public class AwtSyncRunnerTest {

    public static void main(String[] args) {
        AppContext appContext = null;
        SyncRunner runner = new AwtSyncRunner(appContext,model-> System.out.println(model));
        runner.setModel(NucleusTestFactory.newNucleusModel(),x -> false);
    }

}
