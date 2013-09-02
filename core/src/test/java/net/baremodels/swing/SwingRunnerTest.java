package net.baremodels.swing;

import net.baremodels.apps.Nucleus;
import net.baremodels.model.Model;
import net.baremodels.models.ObjectModel;
import net.baremodels.runner.ModelRenderer;
import net.baremodels.runner.SimpleModelRenderer;
import net.baremodels.runner.SimpleRunner;
import net.baremodels.swing.SwingDevice;

public class SwingRunnerTest {

    private static Model newNucleus() {
        Nucleus nucleus = new Nucleus();
        return ObjectModel.of(nucleus);
    }

    public static void main(String[] args) {
        SwingDevice device = SwingDevice.newInstance();
        ModelRenderer renderer = new SimpleModelRenderer();
        SimpleRunner runner = new SimpleRunner(renderer,device);
        runner.setModel(newNucleus());
    }

}
