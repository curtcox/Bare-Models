package net.baremodels.uat;

import net.baremodels.awt.AwtRunner;
import net.baremodels.models.ModelFactory;
import net.baremodels.runner.Runner;
import net.baremodels.swing.SwingRunner;

public final class UATBuilder {
    /**
     * Return a UAT that fails assertions the same way JUnit does.
     */
    public static UAT of() {
        return new UAT((failure)-> {throw new AssertionError(failure.message);}, ModelFactory.DEFAULT );
    }

    /**
     * Return a UAT that fails using the given listener.
     */
    public static UAT withListener(AssertionListener listener) {
        return new UAT(listener,ModelFactory.DEFAULT);
    }

    /**
     * Return a UAT that displays failures using the given Runner.
     */
    public static UAT withFailureRunner(Runner runner) {
        ModelFactory modelFactory = ModelFactory.DEFAULT;
        return new UAT(
                (failure) -> {runner.setModel(modelFactory.of(failure), x -> true ); },
                modelFactory
        );
    }

    /**
     * Return a UAT that displays failures using Awt.
     */
    public static UAT withAwt() {
        return withFailureRunner(new AwtRunner());
    }

    /**
     * Return a UAT that displays failures using Swing.
     */
    public static UAT withSwing() {
        return withFailureRunner(new SwingRunner());
    }

}
