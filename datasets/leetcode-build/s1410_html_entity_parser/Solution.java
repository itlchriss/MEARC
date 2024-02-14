package g1401_1500.s1410_html_entity_parser;

// #Medium #String #Hash_Table #2022_03_27_Time_19_ms_(98.92%)_Space_42.6_MB_(100.00%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
	//@ requires(*The input `text` string is not null.*);
	//@ requires(*The input `text` string has a length greater than or equal to 1 and less than or equal to 10^*);
	//@ ensures(*The returned string is the same as the input `text` string, but with all HTML entities replaced by their corresponding special characters.*);
	//@ ensures(*The returned string does not contain any HTML entities.*);
    public String entityParser(String text) {
        Map<String, String> map = new HashMap<>();
        map.put("&quot;", "\"");
        map.put("&apos;", "'");
        map.put("&amp;", "&");
        map.put("&gt;", ">");
        map.put("&lt;", "<");
        map.put("&frasl;", "/");
        int n = text.length();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < n) {
            char c = text.charAt(i);
            if (c == '&') {
                int index = text.indexOf(";", i);
                if (index >= 0) {
                    String pattern = text.substring(i, index + 1);
                    if (map.containsKey(pattern)) {
                        sb.append(map.get(pattern));
                        i += pattern.length();
                        continue;
                    }
                }
            }
            sb.append(c);
            i++;
        }
        return sb.toString();
    }
}