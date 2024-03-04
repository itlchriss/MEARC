package g2701_2800.s2744_find_maximum_number_of_string_pairs;

// #Easy #Array #String #Hash_Table #Simulation
// #2023_09_23_Time_1_ms_(100.00%)_Space_40.8_MB_(93.18%)

public class Solution {
    private boolean func(String a, String b) {
        int n = a.length();
        int m = b.length();
        if (n != m) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            if (a.charAt(i) != b.charAt(n - 1 - i)) {
                return false;
            }
        }
        return true;
    }
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `words` is not null.*);
//@ ensures(*The length of the input array `words` is at least 1 and at most 50.*);
//@ ensures(*Each string in the input array `words` has a length of 2.*);
//@ ensures(*The strings in the input array `words` are distinct.*);
//@ ensures(*Each string in the input array `words` contains only lowercase English letters.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the maximum number of pairs that can be formed from the input array `words`.*);

    public int maximumNumberOfStringPairs(String[] words) {
        int ans = 0;
        int n = words.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (func(words[i], words[j])) {
                    ans++;
                }
            }
        }
        return ans;
    }
}