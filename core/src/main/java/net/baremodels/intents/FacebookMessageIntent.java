package net.baremodels.intents;

import net.baremodels.common.FacebookAccount;
import net.baremodels.common.User;
import net.baremodels.intent.Intent;

public class FacebookMessageIntent
    extends Intent
{
    public final String subject;
    public final String body;
    public final FacebookAccount account;

    private FacebookMessageIntent(String subject, String body, FacebookAccount account) {
        this.subject = subject;
        this.body = body;
        this.account = account;
    }

    public static FacebookMessageIntent sendSubjectBodyTo(
            String subject, String body, User user) {
        return new FacebookMessageIntent(subject,body,user.facebook);
    }
}
