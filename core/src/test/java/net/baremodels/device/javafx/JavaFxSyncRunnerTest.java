package net.baremodels.device.javafx;

import net.baremodels.apps.Nucleus;
import net.baremodels.model.Model;
import net.baremodels.runner.NavigationContext;
import net.baremodels.models.ModelFactory;
import net.baremodels.runner.*;

public class JavaFxSyncRunnerTest {

    private static Model newNucleus() {
        Nucleus nucleus = new Nucleus();
        return ModelFactory.DEFAULT.of(nucleus);
    }

    public static void main(String[] args) {
        JavaFxSyncDevice device = JavaFxSyncDevice.newInstance();
        AppContext appContext = new SimpleAppContext();
        NavigationContext navigationContext = new NavigationContext();

        SimpleSyncRunner runner = new SimpleSyncRunner(appContext,navigationContext,device,model-> System.out.println(model));
        runner.setModel(newNucleus(),x->true);
    }
}

