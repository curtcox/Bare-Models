package net.baremodels.intents;

import net.baremodels.common.EmailAddress;
import net.baremodels.common.User;
import net.baremodels.intent.Intent;

public final class EmailIntent
    extends Intent
{
    public final String subject;
    public final String body;
    public final EmailAddress address;

    private EmailIntent(String subject, String body, EmailAddress address) {
        this.subject = subject;
        this.body = body;
        this.address = address;
    }

    public static EmailIntent emailSubjectBodyTo(
            String subject, String body, User user) {
        return new EmailIntent(subject,body,user.email);
    }
}
