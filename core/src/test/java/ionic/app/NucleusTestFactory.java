package ionic.app;

import net.baremodels.apps.Nucleus;
import net.baremodels.common.*;
import net.baremodels.model.Model;
import net.baremodels.model.Property;
import net.baremodels.models.ModelFactory;
import net.baremodels.runner.AppContext;
import net.baremodels.runner.Browser;
import net.baremodels.runner.SimpleAppContext;
import net.baremodels.ui.UIGlyph;

import java.util.*;

public class NucleusTestFactory {

    public static Model newNucleusModel() {
        return ModelFactory.DEFAULT.of(newNucleus());
    }

    public static Model newNucleusBrowserModel() {
        return ModelFactory.DEFAULT.of(newBrowser());
    }

    public static Browser newBrowser() {
        Browser browser = new Browser();
        Nucleus nucleus = newNucleus();
        browser.home = nucleus;
        browser.current = nucleus;
        browser.history = new ArrayList<>();
        browser.breadcrumbs = new ArrayList<>();
        browser.intents = new ArrayList<>();
        browser.location = nucleus.toString();
        return browser;
    }

    static class PropertyNameMatcher implements Property.Matcher {

        final String name;

        public PropertyNameMatcher(String name) {
            this.name = name;
        }

        @Override
        public boolean matches(Property property) {
            return property.name().equalsIgnoreCase(name);
        }
    }

    public static AppContext newAppContext() {
        Map<Property.Matcher, UIGlyph> propertyGlyphs = new HashMap<>();

        propertyGlyphs.put(new PropertyNameMatcher("teams"),UIGlyph.users);
        propertyGlyphs.put(new PropertyNameMatcher("users"),UIGlyph.user);
        propertyGlyphs.put(new PropertyNameMatcher("badges"),UIGlyph.certificate);
        propertyGlyphs.put(new PropertyNameMatcher("skills"),UIGlyph.star);

        propertyGlyphs.put(new PropertyNameMatcher("email"),UIGlyph.envelope);
        propertyGlyphs.put(new PropertyNameMatcher("twitter"),UIGlyph.twitter);
        propertyGlyphs.put(new PropertyNameMatcher("linkedin"),UIGlyph.linkedin);
        propertyGlyphs.put(new PropertyNameMatcher("facebook"),UIGlyph.facebook);

        return new SimpleAppContext(propertyGlyphs);
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

        User sam = newUser("Sam","Axe");
        User adam = newUser("Adam","Axe",java,objectiveC);
        User tinker = newUser("Joe","Tinker");
        User evars = newUser("Johnny","Evars");
        User chance = newUser("Frank","Chance");
        User hannibal = newUser("John","Smith");
        User face = newUser("Templeton","Peck",charm);
        User ba = newUser("Bosco","Baracas");
        User murdoch = newUser("H.M.","Murdoch");

        nucleus.users.addAll(Arrays.asList(sam,adam,tinker,evars,chance,hannibal,face,ba,murdoch));
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

    private static User newUser(String firstName, String lastName, Skill... skills) {
        User user = new User();
        user.firstName = firstName;
        user.lastName = lastName;
        user.userName = String.format("%s.%s",firstName,lastName);

        user.streetAddress = new StreetAddress();
        user.streetAddress.line1 = "17 " + lastName + " Lane";
        user.streetAddress.city = firstName + "ville";
        user.streetAddress.state = "IL";
        user.streetAddress.zip = "62666";

        user.cellPhone = new PhoneNumber();
        user.cellPhone.value = "555-1212";

        user.homePhone = new PhoneNumber();
        user.homePhone.value = "666-1212";

        user.email = new EmailAddress();
        user.email.value = String.format("%s.%s@example.com",firstName,lastName);
        user.email.user = user;

        user.facebook = new FacebookAccount();
        user.facebook.value = String.format("/%s.%s",firstName,lastName);
        user.facebook.user = user;

        user.twitter = new TwitterHandle();
        user.twitter.value = String.format("@%s.%s",firstName,lastName);
        user.twitter.user = user;

        user.linkedIn = new LinkedInAccount();
        user.linkedIn.value = String.format("%s.%s@linkedIn",firstName,lastName);
        user.linkedIn.user = user;

        user.skills = Arrays.asList(skills);
        return user;
    }

    private static Skill newSkill(String name) {
        Skill skill = new Skill();
        skill.name = name;
        return skill;
    }
}
