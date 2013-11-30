package test.mock;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A method invocation with parameters.
 * Note that equality currently ignores arguments.
 */
final class Invocation {

    public final Object proxy;
    public final Method method;
    public final List<Object> args = new ArrayList<>();

    public Invocation(Object proxy, Method method, Object[] args) {
        this.proxy = proxy;
        this.method = method;
        if (args!=null) {
            this.args.addAll(Arrays.asList(args));
        }
    }

    @Override
    public boolean equals(Object o) {
        Invocation that = (Invocation) o;
        return proxy==that.proxy && method.equals(that.method);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public String toString() {
        return method.toString();
    }
}
