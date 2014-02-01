package ionic.app;

import net.baremodels.runner.Browser;
import net.baremodels.runner.NextModelGenerator;
import net.baremodels.uat.UAT;
import org.junit.Before;
import org.junit.Test;

public class NucleusBrowserTest {

    Browser browser = NucleusTestFactory.newBrowser();
    Object home = browser.object;
    NextModelGenerator generator = (current,selected) -> current;
    UAT uat = new UAT(NucleusTestFactory.newAppContext(),generator);

    @Before
    public void init() {
        uat.show(browser);
    }

    @Test
    public void initially_shows_icons_for_navigation() {
        assertShowingNavigation();
    }

    private void assertShowingNavigation() {
        assertShowingNavigationButtons();
        assertShowingNavigationIcons();
    }

    private void assertShowingNavigationButtons() {
        uat.assertScreenContains("Home", "Forward", "Back");
    }

    private void assertShowingNavigationIcons() {
        uat.assertScreenContains("home", "arrow-right", "arrow-left");
    }

    @Test
    public void initially_shows_button_for_past_intents() {
        uat.assertScreenContains("actions");
    }

    @Test
    public void initially_shows_home_page() {
        assertShowingHomePage();
    }

    @Test
    public void go_home_still_shows_navigation() {
        goHome();
        assertShowingNavigation();
    }

    @Test
    public void go_home_shows_home_page() {
        goHome();
        assertShowingHomePage();
    }

    private void goHome() {
        uat.select(home);
    }

    private void assertShowingHomePage() {
        assertShowingHomePageButtons();
        assertShowingHomePageIcons();
    }

    private void assertShowingHomePageIcons() {
        uat.assertScreenContains("user", "users", "certificate", "star");
    }

    private void assertShowingHomePageButtons() {
        uat.assertScreenContains("Teams", "Users", "Badges", "Skills");
    }

}
