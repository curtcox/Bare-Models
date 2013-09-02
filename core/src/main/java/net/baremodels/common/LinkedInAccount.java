package net.baremodels.common;

import net.baremodels.intents.LinkedInMessageIntent;

public class LinkedInAccount {
    public String value;
    public User user;

    public LinkedInMessageIntent sendMessage(String subject, String body) {
        return LinkedInMessageIntent.sendSubjectBodyTo(subject,body,user);
    }
}
