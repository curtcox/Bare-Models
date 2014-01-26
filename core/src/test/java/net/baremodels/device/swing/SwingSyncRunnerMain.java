package net.baremodels.device.swing;

import ionic.app.NucleusTestFactory;
import net.baremodels.runner.*;
import net.baremodels.runner.modelrenderer.SimpleModelContainerRenderer;

public class SwingSyncRunnerMain {

    AppContext appContext = NucleusTestFactory.newAppContext();
    NextModelGenerator generator = new SelectedNextModelGenerator();
    ModelContainerRenderer renderer = new SimpleModelContainerRenderer();
    SyncRunner runner = SwingSyncRunner.newInstance(appContext, renderer, generator, model-> System.out.println(model));

    public static void main(String[] args) {
        SwingSyncRunnerMain test = new SwingSyncRunnerMain();
        test.runner.setModel(NucleusTestFactory.newNucleusModel(),x->false);
    }

}
