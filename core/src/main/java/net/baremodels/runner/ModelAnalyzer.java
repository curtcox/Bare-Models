package net.baremodels.runner;

import net.baremodels.intent.Intent;
import net.baremodels.model.Model;

interface ModelAnalyzer {

    Intent generateIntent(Model model);

    boolean generatesSingleIntent(Model model);

}
