package net.baremodels.runner;

import net.baremodels.model.ListModel;
import net.baremodels.model.Property;

import java.util.*;

/**
 * A simple implementation of PropertyNameMapper.
 * It turns camelCase and snake_case into Title Case.
 */
public final class SimplePropertyNameMapper
    implements PropertyNameMapper
{
    private static final Map<String,String> mappings = generateMappings();

    private static Map<String, String> generateMappings() {
        Map<String,String> map = new LinkedHashMap<>();
        map.put("_"," ");
        List<String> letters = letters();
        for (String lower : letters) {
            String upper = lower.toUpperCase();
            map.put(lower.toUpperCase(), " " + upper);
            map.put(" " + lower, " " + upper);
        }
        return map;
    }

    private static List<String> letters() {
        List<String> list = new ArrayList<>();
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        for (int i=0; i<alpha.length(); i++) {
            list.add("" + alpha.charAt(i));
        }
        return list;
    }

    @Override
    public String getName(Property property) {
        return (isList(property))
            ? capitalize(replaceCharacters(property))
            : capitalize(replaceCharacters(property)) + ": " + property.model();
    }

    private boolean isList(Property property) {
        return property.model() instanceof ListModel;
    }

    private String replaceCharacters(Property property) {
        String value = property.name();
        for (String source : mappings.keySet()) {
            value = value.replaceAll(source,mappings.get(source));
        }
        return value;
    }

    private String capitalize(String name) {
        return name.toUpperCase().substring(0,1) + name.substring(1);
    }
}
