package net.baremodels.runner;

import net.baremodels.model.Model;

import java.util.function.Predicate;

public interface Runner {

    void setModel(Model model, Predicate<Model> until);

}
