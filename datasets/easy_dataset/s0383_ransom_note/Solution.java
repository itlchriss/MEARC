package g0301_0400.s0383_ransom_note;

// #Easy #String #Hash_Table #Counting #Data_Structure_I_Day_6_String
// #2022_07_13_Time_1_ms_(99.97%)_Space_46_MB_(62.86%)

public class Solution {
//@ ensures(*If the string parameter `ransomNote` can be constructed from the string parameter `magazine`, the boolean result is true.*);
//@ ensures(*Each letter in the string parameter `magazine` can only be used once in the string parameter `ransomNote`.*);
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] a = new int[26];
        int n = ransomNote.length();
        for (int i = 0; i < n; i++) {
            a[ransomNote.charAt(i) - 97]++;
        }
        for (int i = 0; i < magazine.length() && n != 0; i++) {
            if (a[magazine.charAt(i) - 97] > 0) {
                n--;
                a[magazine.charAt(i) - 97]--;
            }
        }
        return n == 0;
    }
}