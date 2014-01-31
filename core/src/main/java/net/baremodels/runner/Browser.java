package net.baremodels.runner;

/**
 * An object browser.
 * This is like a web browser, but it looks at objects, rather than web pages.
 */
public final class Browser {

    public Object object;

    public Browser(Object home) {
        this.object = home;
    }

    public Object home() {
        return null;
    }

    public Object back(){
        return null;
    }

    public Object forward() {
        return null;
    }

    public void goTo(Object object) {

    }


}
