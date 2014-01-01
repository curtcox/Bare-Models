package net.baremodels.common;

import net.baremodels.intents.EmailIntent;

/**
 * An email address.
 * This implementation deliberately avoids referencing the JavaMail API, to minimize dependencies.
 */
public final class EmailAddress
{
    public String value;
    public User user;

    public EmailIntent sendEmailTo() {
        return EmailIntent.emailTo(user);
    }
}
