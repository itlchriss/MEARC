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
// @requires strs != null && (\forall int i; 0 <= i && i < strs.length; strs[i] != null);
// @requires (\forall int i, j; 0 <= i && i < strs.length && 0 <= j && j < strs.length; i != j ==> !strs[i].equals(strs[j]));
// @requires strs.length >= 1 && strs.length <= 10000;
// @requires (\forall int i; 0 <= i && i < strs.length; strs[i].length() >= 0 && strs[i].length() <= 100);
// @requires (\forall int i; 0 <= i && i < strs.length; (\forall int j; 0 <= j && j < strs.length; i != j ==> strs[i].length() == strs[j].length()));
// @ensures \result != null;
// @ensures (\forall int i; 0 <= i && i < \result.size(); \result.get(i) != null);
// @ensures (\forall int i; 0 <= i && i < \result.size(); (\forall int j; 0 <= j && j < \result.size(); i != j ==> !\result.get(i).equals(\result.get(j))));
// @ensures (\forall int i; 0 <= i && i < \result.size(); (\forall int j; 0 <= j && j < \result.get(i).size(); \result.get(i).get(j) != null));
// @ensures (\forall int i; 0 <= i && i < \result.size(); (\forall int j; 0 <= j && j < \result.get(i).size(); \result.get(i).get(j).length() >= 0 && \result.get(i).get(j).length() <= 100));
// @ensures (\forall int i; 0 <= i && i < \result.size(); (\forall int j; 0 <= j && j < \result.get(i).size(); (\forall int k; 0 <= k && k < \result.get(i).size(); j != k ==> \result.get(i).get(j).length() == \result.get(i).get(k).length()));
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