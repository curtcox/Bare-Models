package net.baremodels.common;

import net.baremodels.intents.FacebookMessageIntent;

public class FacebookAccount {
    public String value;
    public User user;

    public FacebookMessageIntent sendMessage(String subject, String body) {
        return FacebookMessageIntent.sendSubjectBodyTo(subject,body,user);
    }
}
