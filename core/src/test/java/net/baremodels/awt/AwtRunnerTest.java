package net.baremodels.awt;

import ionic.app.NucleusTestFactory;
import net.baremodels.runner.ModelRenderer;
import net.baremodels.runner.SimpleModelRenderer;
import net.baremodels.runner.SimpleRunner;

public class AwtRunnerTest {

    public static void main(String[] args) {
        AwtDevice device = AwtDevice.newInstance();
        ModelRenderer renderer = new SimpleModelRenderer();
        SimpleRunner runner = new SimpleRunner(renderer,device);
        runner.setModel(NucleusTestFactory.newNucleus(),x -> true);
    }

}
