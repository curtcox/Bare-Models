package net.baremodels.intents;

import net.baremodels.common.LinkedInAccount;
import net.baremodels.common.User;
import net.baremodels.intent.Intent;

public class LinkedInMessageIntent
    extends Intent
{
    public final String subject;
    public final String body;
    public final LinkedInAccount account;

    private LinkedInMessageIntent(String subject, String body, LinkedInAccount account) {
        super(account);
        this.subject = subject;
        this.body = body;
        this.account = account;
    }

    public static LinkedInMessageIntent sendSubjectBodyTo(
            String subject, String body, User user) {
        return new LinkedInMessageIntent(subject,body,user.linkedIn);
    }
}
