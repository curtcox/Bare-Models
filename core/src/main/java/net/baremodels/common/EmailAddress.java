package net.baremodels.common;

import net.baremodels.intents.EmailIntent;

public final class EmailAddress
{
    public String value;
    public User user;

    public EmailIntent sendEmail(String subject, String body) {
        return EmailIntent.emailSubjectBodyTo(subject, body, user);
    }
}
