package net.baremodels.common;

public final class Environment {

    public final User user;
    public final long now;

    Environment(User user, long now) {
        this.user = user;
        this.now = now;
    }
}
