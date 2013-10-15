package net.baremodels.swing;

import ionic.app.NucleusTestFactory;
import net.baremodels.runner.Runner;

public class SwingRunnerTest {

    public static void main(String[] args) {
        Runner runner = new SwingRunner();
        runner.setModel(NucleusTestFactory.newNucleusModel(),x->true);
    }

}
