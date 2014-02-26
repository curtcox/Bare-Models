package net.baremodels.util;

import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.function.Supplier;

/**
 * Properties that are taken from the supplier with every operation.
 */
public final class LivePropertiesFromSupplier
    implements Map<String, String>
{
    private final Supplier<Properties> supplier;

    public LivePropertiesFromSupplier(Supplier<Properties> supplier) {
        this.supplier = supplier;
    }

    private Properties properties() {
        return supplier.get();
    }

    @Override public     int                        size() { return properties().size(); }
    @Override public boolean                     isEmpty() { return properties().isEmpty(); }
    @Override public boolean       containsKey(Object key) { return properties().containsKey(key); }
    @Override public boolean   containsValue(Object value) { return properties().containsValue(value); }
    @Override public String                get(Object key) { return (String)     properties().get(key); }
    @Override public Set<String>                  keySet() { return (Set)        properties().keySet(); }
    @Override public Collection<String>           values() { return (Collection) properties().values(); }
    @Override public Set<Entry<String, String>> entrySet() { return (Set)        properties().entrySet(); }

    private String never() {
        throw new UnsupportedOperationException();
    }

    @Override public void putAll(Map<? extends String, ? extends String> m) { never(); }
    @Override public String put(String key, String value) { return never(); }
    @Override public String            remove(Object key) { return never(); }
    @Override public void                         clear() { never(); }

}
