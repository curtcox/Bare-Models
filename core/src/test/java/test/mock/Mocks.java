package test.mock;

import java.lang.reflect.Field;
import java.util.*;
import static test.mock.Phase.*;

/**
 * Static methods for creating and interacting with test mocks.
 */
public final class Mocks {

    /**
     * For making mocks.
     */
    private static MockFactory factory = new MockFactory();

    /**
     * Initialize the instance variables in the given test.
     * Null interface fields will be assigned values.
     * Non-null values will be used as method return values.
     * Generally, this method will be used in a test as follows:
     * <pre>
             @Before
             public void init() {
                 Mocks.init(this);
             }
     * </pre>
     */
    public static void init(Object test) {
        factory = new MockFactory();
        current = when;
        try {
            _init(test);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static void _init(Object test) throws IllegalAccessException {
        Set values = getNonNullValues(test);
        for (Field field : test.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(test);
            Class type = field.getType();
            if (value==null && type.isInterface()) {
                String name = field.getName();
                Object mock = mock(name,type,values.toArray());
                field.set(test,mock);
            }
        }
    }

    /**
     * Return a mock of the given description.
     * This method is used internally by init.  It can be used directly.
     */
    static <T> T mock(String name, Class<T> face, Object... addedValues) {
        current = when;
        Map<Class,Object> values = new HashMap();
        values.putAll(defaultValues);
        for (Object value : addedValues) {
            for (Class key : keysFor(value)) {
                values.put(key,value);
            }
        }
        return factory.mock(face, name, values);
    }

    /**
     * The base set of default values.
     */
    private static Map<Class,Object> defaultValues = new HashMap() {{
        put(boolean.class,false);
    }};

    /**
     * Specify that the given latest will return the given result.
     */
    public static <T> void when(T condition, T result) {
        current = when;
        factory.when(condition, result);
    }

    /**
     * Start verifying invocations.
     */
    public static void verify() {
        current = verify;
    }

    /**
     * Start verifying things didn't happen.
     */
    public static void no() {
        current = no;
    }

    /**
     * Return the non-null field values.
     */
    private static Set getNonNullValues(Object test) throws IllegalAccessException {
        Set values = new HashSet();
        for (Field field : test.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(test);
            if (value!=null) {
                values.add(value);
            }
        }
        return values;
    }

    /**
     * Return the class keys that will be used to lookup the given value.
     */
    private static List<Class> keysFor(Object value) {
        List<Class> list = new ArrayList<>();
        Class c = value.getClass();
        list.add(c);
        list.add(c.getSuperclass());
        list.addAll(Arrays.asList(c.getInterfaces()));
        return list;
    }

}
