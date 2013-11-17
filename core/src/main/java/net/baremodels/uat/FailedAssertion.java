package net.baremodels.uat;

import net.baremodels.device.text.TextUiState;

public final class FailedAssertion {
    public final String message;
    public final TextUiState state;

    public FailedAssertion(String message, TextUiState state) {
        this.message = message;
        this.state = state;
    }
}
