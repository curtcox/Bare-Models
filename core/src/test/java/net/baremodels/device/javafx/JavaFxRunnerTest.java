package net.baremodels.device.javafx;

import net.baremodels.apps.Nucleus;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.runner.ModelRenderer;
import net.baremodels.runner.SimpleModelRenderer;
import net.baremodels.runner.SimpleSyncRunner;

public class JavaFxRunnerTest {

    private static Model newNucleus() {
        Nucleus nucleus = new Nucleus();
        return ModelFactory.DEFAULT.of(nucleus);
    }

    public static void main(String[] args) {
        JavaFxDevice device = JavaFxDevice.newInstance();
        ModelRenderer renderer = new SimpleModelRenderer();
        SimpleSyncRunner runner = new SimpleSyncRunner(renderer,device,model-> System.out.println(model));
        runner.setModel(newNucleus(),x->true);
    }
}

