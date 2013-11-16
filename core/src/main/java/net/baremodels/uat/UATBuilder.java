package net.baremodels.uat;

import net.baremodels.models.ModelFactory;
import net.baremodels.runner.Runner;

public final class UATBuilder {

    public AssertionListener listener = (failure)-> {throw new AssertionError(failure.message);};
    public ModelFactory modelFactory = ModelFactory.DEFAULT;

    /**
     * Return a UAT that fails using the given listener.
     */
    public UATBuilder withListener(AssertionListener listener) {
        this.listener = listener;
        return this;
    }

    /**
     * Return a UAT that displays failures using the given Runner.
     */
    public static UAT withFailureRunner(Runner runner) {
        return new UATBuilder().withListener(
                (failure) -> {runner.setModel(ModelFactory.DEFAULT.of(failure), x -> true ); })
                .build();
    }

    public UAT build() {
        return new UAT(listener,modelFactory);
    }
}
