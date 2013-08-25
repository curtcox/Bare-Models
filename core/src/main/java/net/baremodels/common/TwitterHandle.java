package net.baremodels.common;

import net.baremodels.intents.TweetIntent;

public class TwitterHandle {
    public String value;
    public User user;

    public TweetIntent tweetTo(String message) {
        return TweetIntent.tweetTo(message,user);
    }
}
