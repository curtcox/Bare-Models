package net.baremodels.runner;

import net.baremodels.intent.Intent;
import net.baremodels.model.Inspectable;

/**
 * For analyzing a model.
 */
interface ModelAnalyzer {

    /**
     * Return true if this model generates a single intent.
     */
    boolean generatesSingleIntent(Inspectable inspectable);

    /**
     * Generate the single intent that this model generates.
     */
    Intent generateIntent(Inspectable inspectable);

}
