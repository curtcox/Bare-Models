package net.baremodels.runner;

import net.baremodels.intent.Intent;
import net.baremodels.model.Model;

import java.util.List;

/**
 * The immutable context for a model at a particular instant.
 * This is used to provide navigational information.
 */
final class NavigationContext {
    public static final Model back = null;
    public static final Model forward = null;
    public static final Model home = null;
    public static final List<Model> breadcrumbs = null;
    public static final List<Model> history = null;
    public static final List<Intent> intents = null;
    public static final String location = null;
}
