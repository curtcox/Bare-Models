package net.baremodels.common;

import net.baremodels.intent.Intent;
import net.baremodels.intents.CallPhoneIntent;
import net.baremodels.models.ObjectModel;

public final class PhoneNumber
{
    public String value;
    public User user;

    public CallPhoneIntent call() {
        return CallPhoneIntent.of(this);
    }
}
