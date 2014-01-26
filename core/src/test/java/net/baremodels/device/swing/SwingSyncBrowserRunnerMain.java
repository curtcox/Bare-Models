package net.baremodels.device.swing;

import ionic.app.NucleusTestFactory;
import net.baremodels.runner.*;
import net.baremodels.runner.modelrenderer.BrowserModelContainerRenderer;
import net.baremodels.runner.modelrenderer.SimpleModelContainerRenderer;

public class SwingSyncBrowserRunnerMain {

    AppContext appContext = NucleusTestFactory.newAppContext();
    NextModelGenerator generator = new SelectedNextModelGenerator();
    ModelContainerRenderer renderer = new BrowserModelContainerRenderer(new SimpleModelContainerRenderer(appContext));
    SyncRunner runner = SwingSyncRunner.newInstance(appContext, renderer, generator, model-> System.out.println(model));

    public static void main(String[] args) {
        SwingSyncBrowserRunnerMain test = new SwingSyncBrowserRunnerMain();
        test.runner.setModel(NucleusTestFactory.newNucleusBrowserModel(),x->false);
    }

}
