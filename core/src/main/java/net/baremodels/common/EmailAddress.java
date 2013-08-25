package net.baremodels.common;

import net.baremodels.intent.Intent;
import net.baremodels.intents.EmailIntent;
import net.baremodels.model.Property;
import net.baremodels.models.ObjectModel;

public final class EmailAddress
{
    public String value;
    public User user;

    public EmailIntent sendEmail(String subject, String body) {
        return EmailIntent.emailSubjectBodyTo(subject, body, user);
    }
}
