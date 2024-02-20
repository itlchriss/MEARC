package g2301_2400.s2370_longest_ideal_subsequence;

// #Medium #String #Hash_Table #Dynamic_Programming
// #2022_08_15_Time_28_ms_(85.71%)_Space_48.6_MB_(85.71%)

@SuppressWarnings("java:S135")
public class Solution {
	//@ requires(*1. The input string `s` is not null.*);
	//@ requires(*2. The input string `s` is not empty.*);
	//@ requires(*3. The input integer `k` is non-negative.*);
	//@ requires(*4. The input integer `k` is less than or equal to 25.*);
	//@ ensures(*1. The output is an integer representing the length of the longest ideal string.*);
	//@ ensures(*2. The output is non-negative.*);
    public int longestIdealString(String s, int k) {
        int ans = 1;
        int[] array = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int curr = s.charAt(i) - 'a';
            int currans = 1;
            int temp = k;
            array[curr] += 1;
            int j = curr - 1;
            while (temp > 0) {
                if (j == -1) {
                    break;
                }
                currans = Math.max(currans, array[j] + 1);
                temp--;
                if (j == 0) {
                    break;
                }
                j--;
            }
            temp = k;
            j = curr + 1;
            while (temp > 0) {
                if (j == 26) {
                    break;
                }
                currans = Math.max(currans, array[j] + 1);
                temp--;
                if (j == 25) {
                    break;
                }
                j++;
            }
            array[curr] = Math.max(currans, array[curr]);
            ans = Math.max(ans, array[curr]);
        }
        return ans;
    }
}