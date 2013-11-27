package test.mock;

import java.util.*;

public final class Mocks {

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
        mock.verify();
    }

    public static <T> T no() {
        return mock.no();
    }

    public static void when(Object condition, Object result) {
        mock.when(condition,result);
    }
}
