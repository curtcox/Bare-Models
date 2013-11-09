package net.baremodels.intents;

import net.baremodels.common.PhoneNumber;
import net.baremodels.intent.Intent;

public final class CallPhoneIntent
    extends Intent
{
    public final PhoneNumber number;

    private CallPhoneIntent(PhoneNumber number) {
        super(number);
        this.number = number;
    }

    public static CallPhoneIntent of(PhoneNumber number) {
        return new CallPhoneIntent(number);
    }
}
