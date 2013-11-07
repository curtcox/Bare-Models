package net.baremodels.common;

import net.baremodels.intents.EmailIntent;

public final class EmailAddress
{
    public String value;
    public User user;

    public EmailIntent sendEmailTo() {
        return EmailIntent.emailTo(user);
    }
}
