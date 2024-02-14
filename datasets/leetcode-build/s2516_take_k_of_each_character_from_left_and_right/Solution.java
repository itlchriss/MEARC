package g2501_2600.s2516_take_k_of_each_character_from_left_and_right;

// #Medium #String #Hash_Table #Sliding_Window #2023_03_30_Time_6_ms_(94.24%)_Space_43.5_MB_(46.60%)

public class Solution {
	//@ requires(*The input string `s` must not be null.*);
	//@ requires(*The input string `s` must consist of only the letters 'a', 'b', and 'c'.*);
	//@ requires(*The input integer `k` must be non-negative.*);
	//@ requires(*The length of the input string `s` must be between 1 and 10^5 (inclusive).*);
	//@ requires(*The value of `k` must be between 0 and the length of `s` (inclusive).*);
	//@ ensures(*The method should return an integer representing the minimum number of minutes needed to take at least `k` of each character in the string `s`.*);
	//@ ensures(*If it is not possible to take `k` of each character, the method should return -1.*);
    public int takeCharacters(String s, int k) {
        if (s.length() < 3 * k) {
            return -1;
        }
        int[] cnt = new int[3];
        char[] arr = s.toCharArray();
        for (char ch : arr) {
            cnt[ch - 'a']++;
        }
        if (cnt[0] < k || cnt[1] < k || cnt[2] < k) {
            return -1;
        }
        int ans = arr.length;
        int i = 0;
        int j = 0;
        while (j < arr.length) {
            cnt[arr[j] - 'a']--;
            if (cnt[0] >= k && cnt[1] >= k && cnt[2] >= k) {
                ans = Math.min(ans, arr.length - (j - i + 1));
                j++;
            } else {
                cnt[arr[i] - 'a']++;
                i++;
                j++;
            }
        }
        return ans;
    }
}