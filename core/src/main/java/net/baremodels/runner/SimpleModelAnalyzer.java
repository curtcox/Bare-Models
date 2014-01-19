package net.baremodels.runner;

import net.baremodels.intent.Intent;
import net.baremodels.model.Inspectable;
import net.baremodels.model.Model;

/**
 * A simple implementation of ModelAnalyzer.
 */
final class SimpleModelAnalyzer
    implements ModelAnalyzer
{
    public Intent generateIntent(Inspectable inspectable) {
        Model model = (Model) inspectable;
        return (Intent) model.operations().values().iterator().next().invoke();
    }

    public boolean generatesSingleIntent(Inspectable inspectable) {
        if (inspectable instanceof Model) {
            Model model = (Model) inspectable;
            return model.operations().size()==1 &&
                   model.operations().values().iterator().next().hasIntent();
        }
        return false;
    }

}
