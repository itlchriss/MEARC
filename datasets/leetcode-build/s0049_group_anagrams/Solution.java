package g0001_0100.s0049_group_anagrams;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #String #Hash_Table #Sorting
// #Data_Structure_II_Day_8_String #Programming_Skills_II_Day_11 #Udemy_Strings
// #Big_O_Time_O(n*k_log_k)_Space_O(n) #2023_08_11_Time_6_ms_(92.28%)_Space_46.4_MB_(98.50%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	//@ requires(*1. The input array `strs` is not null.*);
	//@ requires(*2. The input array `strs` is not empty.*);
	//@ requires(*3. Each element in the input array `strs` is not null.*);
	//@ requires(*4. Each element in the input array `strs` is not empty.*);
	//@ requires(*5. Each element in the input array `strs` consists only of lowercase English letters.*);
	//@ ensures(*1. The returned list is not null.*);
	//@ ensures(*2. The returned list contains sublists.*);
	//@ ensures(*3. Each sublist in the returned list contains anagrams.*);
	//@ ensures(*4. The order of the sublists in the returned list can be any order.*);
	//@ ensures(*5. The order of the anagrams within each sublist in the returned list can be any order.*);
	//@ ensures(*6. The returned list contains all the anagrams from the input array `strs`.*);
	//@ ensures(*7. The returned list does not contain any non-anagram strings.*);
	//@ ensures(*8. The returned list does not contain any duplicate anagrams.*);
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> hm = new HashMap<>();
        for (String s : strs) {
            char[] ch = s.toCharArray();
            Arrays.sort(ch);
            String temp = new String(ch);
            hm.computeIfAbsent(temp, k -> new ArrayList<>());
            hm.get(temp).add(s);
        }
        return (new ArrayList<>(hm.values()));
    }
}