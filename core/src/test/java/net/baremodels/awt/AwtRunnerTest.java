package net.baremodels.awt;

import net.baremodels.apps.Nucleus;
import net.baremodels.common.Skill;
import net.baremodels.common.Team;
import net.baremodels.model.Model;
import net.baremodels.models.ObjectModel;
import net.baremodels.runner.ModelRenderer;
import net.baremodels.runner.SimpleModelRenderer;
import net.baremodels.runner.SimpleRunner;

public class AwtRunnerTest {

    private static Model newNucleus() {
        Nucleus nucleus = new Nucleus();
        nucleus.skills.add(new Skill("Java"));
        nucleus.skills.add(new Skill("Javascript"));

        nucleus.teams.add(new Team());
        nucleus.teams.add(new Team());
        nucleus.teams.add(new Team());
        nucleus.teams.add(new Team());

        return ObjectModel.of(nucleus);
    }

    public static void main(String[] args) {
        AwtDevice device = AwtDevice.newInstance();
        ModelRenderer renderer = new SimpleModelRenderer();
        SimpleRunner runner = new SimpleRunner(renderer,device);
        runner.setModel(newNucleus());
    }

}
