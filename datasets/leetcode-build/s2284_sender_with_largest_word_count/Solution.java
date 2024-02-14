package g2201_2300.s2284_sender_with_largest_word_count;

// #Medium #Array #String #Hash_Table #Counting
// #2022_06_18_Time_42_ms_(95.64%)_Space_49.8_MB_(94.99%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
	//@ requires(*The input arrays `messages` and `senders` are not null.*);
	//@ requires(*The lengths of `messages` and `senders` are equal.*);
	//@ requires(*The length of each element in `messages` is between 1 and 100.*);
	//@ requires(*The length of each element in `senders` is between 1 and 10.*);
	//@ requires(*Each element in `messages` consists of uppercase and lowercase English letters and a single space.*);
	//@ requires(*Each element in `messages` does not have leading or trailing spaces.*);
	//@ requires(*Each element in `senders` consists of uppercase and lowercase English letters only.*);
	//@ ensures(*The method returns a string representing the sender with the largest word count.*);
	//@ ensures(*If there is more than one sender with the largest word count, the method returns the sender with the lexicographically largest name.*);
    public String largestWordCount(String[] messages, String[] senders) {
        HashMap<String, Integer> x = new HashMap<>();
        for (int i = 0; i < messages.length; i++) {
            int words = messages[i].length() - messages[i].replace(" ", "").length() + 1;
            if (x.containsKey(senders[i])) {
                x.put(senders[i], x.get(senders[i]) + words);
            } else {
                x.put(senders[i], words);
            }
        }
        String result = "";
        int max = 0;
        for (Map.Entry<String, Integer> entry : x.entrySet()) {
            if (entry.getValue() > max
                    || entry.getValue() == max && result.compareTo(entry.getKey()) < 0) {
                max = entry.getValue();
                result = entry.getKey();
            }
        }
        return result;
    }
}