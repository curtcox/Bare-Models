package net.baremodels.swing;

import ionic.app.NucleusTestFactory;
import net.baremodels.runner.Runner;

public class SwingRunnerTest {

    public static void main(String[] args) {
        Runner runner = new SwingRunner(model-> System.out.println(model));
        runner.setModel(NucleusTestFactory.newNucleusModel(),x->false);
    }

}
