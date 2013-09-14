package ionic.app;

import net.baremodels.apps.Nucleus;
import net.baremodels.common.Skill;
import net.baremodels.common.Team;
import net.baremodels.model.Model;
import net.baremodels.models.ObjectModel;

public class NucleusTestFactory {

    public static Model newNucleus() {
        Skill java = new Skill();
        Skill javascript = new Skill();
        java.name = "java";
        javascript.name = "javascript";

        Nucleus nucleus = new Nucleus();
        nucleus.skills.add(java);
        nucleus.skills.add(javascript);

        nucleus.teams.add(new Team());
        nucleus.teams.add(new Team());
        nucleus.teams.add(new Team());
        nucleus.teams.add(new Team());

        return ObjectModel.of(nucleus);
    }
}
