package ionic.app;

import net.baremodels.runner.Browser;
import net.baremodels.uat.UAT;
import org.junit.Before;
import org.junit.Test;

public class NucleusBrowserTest {

    Browser browser = NucleusTestFactory.newBrowser();
    UAT uat = new UAT(NucleusTestFactory.newAppContext());

    @Before
    public void init() {
        uat.show(browser);
    }

    @Test
    public void initially_shows_icons_for_navigation() {
        assertShowingNavigation();
    }

    private void assertShowingNavigation() {
        uat.assertScreenContains("location", "home", "forward", "back", "breadcrumbs", "history");
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
    public void home_still_shows_navigation() {
        home();
        assertShowingNavigation();
    }

    @Test
    public void home_shows_home_page() {
        home();
        assertShowingHomePage();
    }

    private void home() {
        uat.select(browser.home);
    }

    private void assertShowingHomePage() {
        uat.assertScreenContains("user", "users", "certificate", "star");
    }

}
