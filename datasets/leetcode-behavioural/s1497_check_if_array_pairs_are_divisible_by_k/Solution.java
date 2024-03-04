package g1401_1500.s1497_check_if_array_pairs_are_divisible_by_k;

// #Medium #Array #Hash_Table #Counting #2022_03_24_Time_7_ms_(97.44%)_Space_74.1_MB_(77.18%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `arr` is not null.*);
//@ ensures(*The length of the input array `arr` is even.*);
//@ ensures(*The input integer `k` is greater than or equal to 1.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns `true` if it is possible to divide the array into `n / 2` pairs such that the sum of each pair is divisible by `k`.*);
//@ ensures(*The method returns `false` if it is not possible to divide the array into `n / 2` pairs such that the sum of each pair is divisible by `k`.*);
    public boolean canArrange(int[] arr, int k) {
        int[] freq = new int[k];
        for (int num : arr) {
            freq[Math.abs((num % k) + k) % k]++;
        }
        if (freq[0] % 2 != 0) {
            return false;
        }
        for (int i = 1; i <= k / 2; i++) {
            if (i == k - i && freq[i] % 2 != 0) {
                return false;
            }
            if (freq[i] != freq[k - i]) {
                return false;
            }
        }
        return true;
    }
}