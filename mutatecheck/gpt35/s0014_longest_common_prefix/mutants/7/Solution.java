package g0001_0100.s0014_longest_common_prefix;

// #Easy #Top_Interview_Questions #String #Level_2_Day_2_String #Udemy_Strings
// #2023_08_09_Time_0_ms_(100.00%)_Space_40.2_MB_(83.48%)

public class Solution {
//@ ensures((\forall int i; 0 <= i && i < strs.length; \result.startsWith(strs[i])));
//@ ensures((\forall int i, j; 0 <= i && i < strs.length && 0 <= j && j < strs.length; strs[i].startsWith(\result) && strs[j].startsWith(\result)));
//@ ensures(\result != null);
//@ requires(strs != null && (\forall int i; 0 <= i && i < strs.length; strs[i] != null));
//@ ensures((\forall int i, j; 0 <= i && i < strs.length && 0 <= j && j < strs.length; strs[i].startsWith(\result) && strs[j].startsWith(\result) && (\forall int k; 0 <= k && k < \result.length(); strs[i].charAt(k) == strs[j].charAt(k))) ==> (\forall int i; 0 <= i && i < strs.length; \result.startsWith(strs[i])));
//@ ensures((\forall int i, j; 0 <= i && i < strs.length && 0 <= j && j < strs.length; strs[i].startsWith(\result) && strs[j].startsWith(\result) && (\forall int k; 0 <= k && k < \result.length(); strs[i].charAt(k) == strs[j].charAt(k))) ==> (\forall int i, j; 0 <= i && i < strs.length && 0 <= j && j < strs.length; strs[i].startsWith(\result) && strs[j].startsWith(\result) && (\forall int k; 0 <= k && k < \result.length(); strs[i].charAt(k) == strs[j].charAt(k))));
//@ ensures((\forall int i, j; 0 <= i && i < strs.length && 0 <= j && j < strs.length; strs[i].startsWith(\result) && strs[j].startsWith(\result) && (\forall int k; 0 <= k && k < \result.length(); strs[i].charAt(k) == strs[j].charAt(k))));
//@ ensures((\forall int i, j; 0 <= i && i < strs.length && 0 <= j && j < strs.length; strs[i].startsWith(\result) && strs[j].startsWith(\result) && (\forall int k; 0 <= k && k < \result.length(); strs[i].charAt(k) == strs[j].charAt(k))) ==> (\forall int i, j; 0 <= i && i < strs.length && 0 <= j && j < strs.length; strs[i].startsWith(\result) && strs[j].startsWith(\result)));
    public String longestCommonPrefix(String[] strs) {
        if (strs.length < 1) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String temp = strs[0];
        int i = 1;
        String cur;
        while (temp.length() >= 0 && i < strs.length) {
            if (temp.length() > strs[i].length()) {
                temp = temp.substring(0, strs[i].length());
            }
            cur = strs[i].substring(0, temp.length());
            if (!cur.equals(temp)) {
                temp = temp.substring(0, temp.length() - 1);
            } else {
                i++;
            }
        }
        return temp;
    }
}
