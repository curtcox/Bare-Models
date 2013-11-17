package net.baremodels.uat;

import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.runner.Runner;

import java.util.function.Predicate;

public final class UATBuilder {

    public ModelFactory modelFactory = ModelFactory.DEFAULT;
    public AssertionListener failureListener = (failure)-> {throw new AssertionError(failure.message);};
    public Predicate<Model> until = new Times(10);

    /**
     * Return a UAT that fails using the given failureListener.
     */
    public UATBuilder withFailureListener(AssertionListener listener) {
        this.failureListener = listener;
        return this;
    }

    /**
     * Return a UAT that displays failures using the given Runner.
     */
    public UAT withFailureRunner(Runner runner) {
        return new UATBuilder().withFailureListener(
                (failure) -> {
                    runner.setModel(ModelFactory.DEFAULT.of(failure), until);
                })
                .build();
    }

    public UAT build() {
        return new UAT(failureListener,modelFactory);
    }
}
