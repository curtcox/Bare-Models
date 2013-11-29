package test.mock;

import java.util.*;

public final class Mocks {

    enum Phase {
        when, test, verify, no
    }

    static Phase phase;
    private static Mock mock = new Mock();

    private static Map<Class,Object> defaultValues = new HashMap() {{
        put(Void.class,null);
        put(void.class,null);
        put(Boolean.class,false);
        put(boolean.class,false);
    }};

    public static <T> T mock(Class<T> clazz, Object... values) {
        return mock("???" , clazz, values);
    }

    public static <T> T mock(String name, Class<T> clazz, Object... addedValues) {
        phase = Phase.when;
        Map<Class,Object> values = new HashMap();
        values.putAll(defaultValues);
        for (Object value : addedValues) {
            for (Class key : keysFor(value)) {
                values.put(key,value);
            }
        }
        return mock.mock(clazz, name, values);
    }

    private static List<Class> keysFor(Object value) {
        List<Class> list = new ArrayList<>();
        Class c = value.getClass();
        list.add(c);
        list.add(c.getSuperclass());
        list.addAll(Arrays.asList(c.getInterfaces()));
        return list;
    }

    public static void verify() {
        phase = Phase.verify;
    }

    public static void no() {
        phase = Phase.no;
    }

    public static <T> void when(T condition, T result) {
        phase = Phase.when;
        mock.when(condition,result);
    }
}
