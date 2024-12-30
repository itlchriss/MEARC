package g0401_0500.s0451_sort_characters_by_frequency;

// #Medium #String #Hash_Table #Sorting #Heap_Priority_Queue #Counting #Bucket_Sort
// #Data_Structure_II_Day_21_Heap_Priority_Queue
// #2022_07_18_Time_13_ms_(89.63%)_Space_43.3_MB_(87.60%)

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
//@ requires(*The length of the string parameter `s` is less than or equal to 500000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` consists of only uppercase and lowercase English letters and digits.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 1455\. Check If a Word Occurs As a Prefix of Any Word in a Sentence*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*A **sentence** is a list of words that are separated by a single space with no leading or trailing spaces.*);
//@ requires(**);
//@ requires(**   For example, `"Hello World"`, `"HELLO"`, and `"hello world hello world "` are all sentences.*);
//@ requires(**);
//@ requires(*A **word** is a sequence of characters such as `&#34;abcDefG123&#34;` or `&#34;Leet123code&#34;`.*);
//@ requires(**);
//@ requires(*A sentence **s** is **good** if **every** word in `s` starts with a **capital** letter, where:*);
//@ requires(**);
//@ requires(**   The first word in `s` **must** start with a **capital** letter.*);
//@ requires(**   Any word in the **middle** of `s` must **not** start with a **capital** letter.*);
//@ requires(**   The last word in `s` **must** start with a **capital** letter.*);
//@ requires(**);
//@ requires(*Given a string `sentence` that represents a sentence, return `true` _if_ `sentence` _is **good**_. Otherwise, return `false`.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** sentence =  "Hello world  "*);
//@ requires(***Output:** false*);
//@ requires(***Explanation:** The first word is  "Hello " but it does not start with a capital letter.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** sentence =  "I am Tony  "*);
//@ requires(***Output:** false*);
//@ requires(***Explanation:** The first word is  "I " but it does not start with a capital letter.*);
//@ requires(*The second word is  "am " but it starts with a lowercase letter.*);
//@ requires(*The third word is  "Tony " but it does not start with a capital letter.*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** sentence =  "Let us go "*);
//@ requires(***Output:** false*);
//@ requires(***Explanation:** The first word is  "Let " but it does not start with a capital letter.*);
//@ requires(*The second word is  "us " but it starts with a lowercase letter.*);
//@ requires(*The third word is  "go " but it does not start with a capital letter.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= sentence.length <= 100`*);
//@ requires(**   `sentence` consists of words separated by a single space.*);
//@ requires(**   `sentence` does not have leading or trailing spaces.*);
//@ requires(**   All the words in `sentence` are composed of uppercase and lowercase English letters and digits.*);
//@ requires(**);
//@ requires(***Follow up:** Could you solve this problem in `O(n)` time complexity and `O(1)` space complexity?*);
//@ ensures(*The string result is a valid string that contains the same characters as the input string `s`, but in a different order.*);
//@ ensures(*The string result has the same frequency of each character as the input string `s`.*);
//@ ensures(*If there are multiple valid results, return any of them.*);
//@ ensures(*If the string parameter `s` is equal to "tree", the string result is equal to "eert".*);
//@ ensures(*If the string parameter `s` is equal to "cccaaa", the string result is equal to "aaaccc".*);
//@ ensures(*If the string parameter `s` is equal to "Aabb", the string result is equal to "bbAa".*);
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        TreeMap<Integer, List<Character>> reverseMap = new TreeMap<>(Collections.reverseOrder());
        for (Map.Entry<Character, Integer> c : map.entrySet()) {
            int freq = map.get(c.getKey());
            reverseMap.computeIfAbsent(freq, k -> new ArrayList<>());
            reverseMap.get(freq).add(c.getKey());
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, List<Character>> freq : reverseMap.entrySet()) {
            List<Character> list = reverseMap.get(freq.getKey());
            for (char c : list) {
                for (int i = 0; i < freq.getKey(); i++) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}