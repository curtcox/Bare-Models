package net.baremodels.util;

/**
 * Because we don't want to force a JRE version with java.util.Objects, just
 * for a few methods.
 * @author dev1
 */
public final class Objects {

    public static boolean equals(Object a, Object b) {
        if (a==null && b==null) {
            return true;
        }
        if ((a==null) != (b==null)) {
            return false;
        }
        return a.equals(b);
    }
    
    public static int hash(Object... values) {
        int hash = 0;
        for (Object value : values) {
            if (value!=null) {
                hash ^= value.hashCode();
            }
        }
        return hash;
    }
    
    public static <T> T requireNonNull(T t) {
        if (t==null) {
            throw new NullPointerException();
        }
        return t;
    }
}
