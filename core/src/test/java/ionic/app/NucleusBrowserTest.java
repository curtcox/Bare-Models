package ionic.app;

import net.baremodels.runner.Browser;
import net.baremodels.runner.NextModelGenerator;
import net.baremodels.uat.UAT;
import org.junit.Before;
import org.junit.Test;

public class NucleusBrowserTest {

    Browser browser = NucleusTestFactory.newBrowser();
    Object home = browser.object;
    NextModelGenerator generator;
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
        uat.assertScreenContains("goHome", "goForward", "goBack", "breadcrumbs", "history");
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
    public void goHome_still_shows_navigation() {
        goHome();
        assertShowingNavigation();
    }

    @Test
    public void goHome_shows_home_page() {
        goHome();
        assertShowingHomePage();
    }

    private void goHome() {
        uat.select(home);
    }

    private void assertShowingHomePage() {
        uat.assertScreenContains("user", "users", "certificate", "star");
    }

}
