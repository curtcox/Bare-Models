package net.baremodels.intent;

/**
 * An intent to take a particular action.
 * @author curt
 */
public abstract class Intent<T> {

    public final T target;

    protected Intent() {
        target = null;
    }

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
