package net.baremodels.uat;

import ionic.app.NucleusTestFactory;
import net.baremodels.apps.Nucleus;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ObjectTypesTest {

    UAT testObject = UAT.of();

    @Test
    public void screenContains_list_element_text_from_all_elements() {
        List<String> list = Arrays.asList("Tinker","Evars","Chance");
        testObject.show(list);
        for (String text : list) {
            testObject.assertScreenContains(text);
        }
    }

    @Test
    public void screenContains_model_name() {
        testObject.show(new Nucleus());
        testObject.assertScreenContains("Nucleus");
    }

    @Test
    public void screenContains_model_part_names() {
        testObject.show(new Nucleus());
        testObject.assertScreenContains("Teams");
        testObject.assertScreenContains("Users");
        testObject.assertScreenContains("Badges");
        testObject.assertScreenContains("Skills");
    }

    @Test
    public void screenContains_team_list_element_names() {
        Nucleus nucleus = NucleusTestFactory.newNucleus();
        testObject.show(nucleus.teams);
        for (String text : nucleus.teams.stream().map(t->t.name).toArray(x->new String[x])) {
            testObject.assertScreenContains(text);
        }
    }

    @Test
    public void screenContains_user_list_element_names() {
        Nucleus nucleus = NucleusTestFactory.newNucleus();
        testObject.show(nucleus.users);
        for (String text : nucleus.users.stream().map(t->t.name()).toArray(x->new String[x])) {
            testObject.assertScreenContains(text);
        }
    }

    @Test
    public void screenContains_skill_list_element_names() {
        Nucleus nucleus = NucleusTestFactory.newNucleus();
        testObject.show(nucleus.skills);
        for (String text : nucleus.skills.stream().map(t->t.name).toArray(x->new String[x])) {
            testObject.assertScreenContains(text);
        }
    }

}
