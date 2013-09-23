package net.baremodels.swing;

import ionic.app.NucleusTestFactory;
import net.baremodels.runner.ModelRenderer;
import net.baremodels.runner.SimpleModelRenderer;
import net.baremodels.runner.SimpleRunner;

public class SwingRunnerTest {

    public static void main(String[] args) {
        SwingDevice device = SwingDevice.newInstance();
        ModelRenderer renderer = new SimpleModelRenderer();
        SimpleRunner runner = new SimpleRunner(renderer,device);
        runner.setModel(NucleusTestFactory.newNucleusModel(),x->true);
    }

}
