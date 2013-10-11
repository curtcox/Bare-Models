package net.baremodels.common;

import net.baremodels.intents.CallPhoneIntent;

public final class PhoneNumber
{
    public String value;
    public User user;

    public CallPhoneIntent call() {
        return CallPhoneIntent.of(this);
    }
}
