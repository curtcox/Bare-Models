package net.baremodels.intents;

import net.baremodels.common.TwitterHandle;
import net.baremodels.common.User;
import net.baremodels.intent.Intent;

public final class TweetIntent
    extends Intent
{
    public final String message;
    public final TwitterHandle handle;

    private TweetIntent(String message, TwitterHandle handle) {
        super(handle);
        this.message = message;
        this.handle = handle;
    }

    public static TweetIntent tweetTo(String message, User user) {
        return new TweetIntent(message,user.twitter);
    }
}
