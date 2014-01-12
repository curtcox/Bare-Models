package net.baremodels.runner;

import net.baremodels.intent.Intent;

import java.util.ArrayList;
import java.util.List;

/**
 * An object browser.
 * This is like a web browser, but it looks at objects, rather than web pages.
 */
public final class Browser {

    public Object object;
    public final List<Object> breadcrumbs = new ArrayList<>();
    public final List<Object> history = new ArrayList<>();
    public final List<Intent> intents = new ArrayList<>();

    public Browser(Object home) {
        this.object = home;
    }

    public Object goHome() {
        return null;
    }

    public Object goBack(){
        return null;
    }

    public Object goForward() {
        return null;
    }

    public void goToObject(Object object) {

    }


}
