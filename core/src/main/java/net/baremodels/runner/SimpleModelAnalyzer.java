package net.baremodels.runner;

import net.baremodels.intent.Intent;
import net.baremodels.model.Model;

/**
 * A simple implementation of ModelAnalyzer.
 */
final class SimpleModelAnalyzer
    implements ModelAnalyzer
{
    public Intent generateIntent(Model model) {
        return (Intent) model.operations().values().iterator().next().invoke();
    }

    public boolean generatesSingleIntent(Model model) {
        return model.operations().size()==1 &&
              (model.operations().values().iterator().next().invoke() instanceof Intent);
    }

}
