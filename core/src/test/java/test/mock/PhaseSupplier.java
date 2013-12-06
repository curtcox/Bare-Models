package test.mock;

import java.util.function.Supplier;

/**
 * TODO
 * FIXME
 * This tries to work by inspecting the stack.
 * I considered explicitly adding a test() in addition to when(), verify(), and no().
 * That would have been problematic.  How do you produce a decent message when people
 * can forget the test()?
 * It could be made to work, but that's now what I'm going to do.
 * It is being committed right now, in its broken state, to revert to if needed.
 *
 * Going forward, I am simply going to specify the return value before the method
 * invocation.  That should greatly simplify the state logic and completely remove
 * the need for stack inspection.  It makes when easier, too.
 */
final class PhaseSupplier
    implements Supplier<Phase>
{
    final Class test;

    public PhaseSupplier(Object test) {
        this.test = test.getClass();
    }

    @Override
    public Phase get() {
        Phase current = Phase.current;
        if (current==Phase.invoke_or_when) {
            return inMethodUnderTest() ? Phase.invoke : Phase.when;
        }
        return current;
    }

    private boolean inMethodUnderTest() {
        return !isTestMethod(directCallerOfMock());
    }

    private boolean isTestMethod(StackTraceElement stackTraceElement) {
        return stackTraceElement.getMethodName().contains("_");
    }

    private StackTraceElement directCallerOfMock() {
        throw new UnsupportedOperationException("This should only be used in a mock");
    }
}
