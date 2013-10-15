package net.baremodels.awt;

import ionic.app.NucleusTestFactory;
import net.baremodels.runner.Runner;

public class AwtRunnerTest {

    public static void main(String[] args) {
        Runner runner = new AwtRunner();
        runner.setModel(NucleusTestFactory.newNucleusModel(),x -> true);
    }

}
