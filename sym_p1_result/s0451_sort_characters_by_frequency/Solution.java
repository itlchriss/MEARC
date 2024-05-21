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
//@ requires(*Given a string param_s, sort it in decreasing order based on the frequency of the characters.*);
//@ requires(*The frequency of a character is the number of times it appears in the string.*);
//@ requires(*Return the sorted string.*);
//@ requires(*Example 1:*);
//@ requires(*Input: s = "tree"*);
//@ requires(*Output: "eert"*);
//@ requires(*Explanation: 'e' appears twice while 'r' and 't' both appear once.*);
//@ requires(*So 'e' must appear before both 'r' and 't'.*);
//@ requires(*Therefore "eetr" is also a valid answer.*);
//@ requires(*Example 2:*);
//@ requires(*Input: s = "cccaaa"*);
//@ requires(*Output: "aaaccc"*);
//@ requires(*Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.*);
//@ requires(*Note that "cacaca" is incorrect, as the same characters must be together.*);
//@ requires(*Example 3:*);
//@ requires(*Input: s = "Aabb"*);
//@ requires(*Output: "bbAa"*);
//@ requires(*Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.*);
//@ requires(*Note that 'A' and 'a' are treated as two different characters.*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= s.length <= 5  10<sup>5</sup></code>*);
//@ requires(*param_s consists of uppercase and lowercase English letters and digits.*);
//@ ensures(*If there are multiple answers, the result is any of them.*);
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