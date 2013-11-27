package net.baremodels.runner;

import net.baremodels.intent.Intent;
import net.baremodels.model.Model;

final class SimpleModelAnalyzer
    implements ModelAnalyzer
{
    public Intent generateIntent(Model model) {
        Object result = invokeOperation(model);
        return (Intent) result;
    }

    private Object invokeOperation(Model model) {
        return model.operations().values().iterator().next().invoke();
    }

    public boolean generatesSingleIntent(Model model) {
        return model.operations().size()==1 && (invokeOperation(model) instanceof Intent);
    }

}
