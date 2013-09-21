package net.baremodels.text;

import net.baremodels.model.Model;

/**
 * Used to simulate a user in testing.
 */
public interface FakeUser {

    /**
     * Given the following text, return the model that the user would select.
     */
    Model set(String text);

}
