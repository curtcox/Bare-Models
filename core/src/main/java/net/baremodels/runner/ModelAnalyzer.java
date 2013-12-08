package net.baremodels.runner;

import net.baremodels.intent.Intent;
import net.baremodels.model.Model;

interface ModelAnalyzer {


    boolean generatesSingleIntent(Model model);

    Intent generateIntent(Model model);

}
