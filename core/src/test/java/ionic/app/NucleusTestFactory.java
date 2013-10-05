package ionic.app;

import net.baremodels.apps.Nucleus;
import net.baremodels.common.Skill;
import net.baremodels.common.Team;
import net.baremodels.common.User;
import net.baremodels.model.Model;
import net.baremodels.models.ObjectModel;

import java.util.Arrays;
import java.util.List;

public class NucleusTestFactory {

    public static Model newNucleusModel() {
        return ObjectModel.of(newNucleus());
    }

    public static Nucleus newNucleus() {
        Skill java = newSkill("Java");
        Skill javascript = newSkill("Javascript");
        Skill objectiveC = newSkill("Objective C");
        Skill charm = newSkill("Charm");

        Nucleus nucleus = new Nucleus();
        List<Skill> skills = nucleus.skills;
        skills.add(java);
        skills.add(javascript);

        User tinker = newUser("Tinker");
        User evars = newUser("Evars");
        User chance = newUser("Chance");
        User hannibal = newUser("Hannibal");
        User face = newUser("Face",charm);
        User ba = newUser("B.A.");
        User murdoch = newUser("Murdoch");
        User adam = newUser("Adam",java,objectiveC);
        User sam = newUser("Sam");

        List<Team> teams = nucleus.teams;
        teams.add(newTeam("A", hannibal, face, ba, murdoch));
        teams.add(newTeam("Cubs", tinker, evars, chance));
        teams.add(newTeam("Development", adam));
        teams.add(newTeam("Axe", adam, sam));
        return nucleus;
    }

    private static Team newTeam(String name, User... users) {
        Team team = new Team();
        team.name = name;
        team.users = Arrays.asList(users);
        return team;
    }

    private static User newUser(String name, Skill... skills) {
        User user = new User();
        user.firstName = name;
        user.skills = Arrays.asList(skills);
        return user;
    }

    private static Skill newSkill(String name) {
        Skill skill = new Skill();
        skill.name = name;
        return skill;
    }
}
