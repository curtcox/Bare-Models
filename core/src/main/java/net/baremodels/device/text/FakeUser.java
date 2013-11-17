package net.baremodels.device.text;

import net.baremodels.model.Model;

/**
 * Used to simulate a user in testing.
 */
public interface FakeUser {

    /**
     * Given everything about what is being presented to the user right now,
     * pick the next model.
     */
    Model pickModelFrom(TextUiState state);

}
