package net.baremodels.device.awt;

import ionic.app.NucleusTestFactory;
import net.baremodels.runner.*;

public class AwtSyncRunnerMain {

    AppContext appContext = new SimpleAppContext();
    NextModelGenerator generator = new SelectedNextModelGenerator();
    SyncRunner runner = AwtSyncRunner.newInstance(appContext, generator, model -> System.out.println(model));

    public static void main(String[] args) {
        AwtSyncRunnerMain test = new AwtSyncRunnerMain();
        test.runner.setModel(NucleusTestFactory.newNucleusModel(),x->false);
    }

}
