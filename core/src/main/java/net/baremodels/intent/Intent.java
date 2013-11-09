package net.baremodels.intent;

/**
 * An intent to take a particular action.
 * @author curt
 */
public abstract class Intent<T> {

    /**
     * Intents always have a target, although that target which might be null.
     * Specific intents may have a more strongly typed method of accessing
     * the target.
     */
    public final T target;

    protected Intent(T target) {
        this.target = target;
    }

    /**
     * Something that listens for an Intent.
     * @author curt
     */
    public interface Listener {
        void onIntent(Intent intent);
    }

}
