package net.baremodels.lang;

import java.util.ArrayList;
import java.util.List;

/**
 * These are methods that would be on String.
 * @author curt
 */
public final class Strings {
    
    public static String format(String template, Object... args) {
        int at = 0;
        int arg = 0;
        StringBuilder out = new StringBuilder();
        while (template.indexOf("%s",at)>=0) {
            int begin = template.indexOf("%s",at);
            out.append(template.substring(at,begin));
            out.append(first90(args[arg++]));
            at = begin + 2;
        }
        out.append(template.substring(at));
        return out.toString();
    }

    private static String first90(Object o) {
        String string = "" + o;
        return string.length() > 90
            ? string.substring(0,90) + "..."
            : string;
    }
    
    public static String format(String template, int x, int y, Object o) {
        return format(template,new Object[]{x,y,o});
    }

    public static String[] split(String string, String separator) {
        List<String> parts = new ArrayList<String>();
        int start = 0;
        for (int i=0; i<string.length(); i++) {
            String sub = string.substring(i, i+1);
            if (sub.equals(separator)) {
                parts.add(string.substring(start,i));
                start = i + 1;
            }
        }
        parts.add(string.substring(start));
        return parts.toArray(new String[0]);
    }

}
