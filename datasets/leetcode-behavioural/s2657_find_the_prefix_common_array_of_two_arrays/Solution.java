package g2601_2700.s2657_find_the_prefix_common_array_of_two_arrays;

// #Medium #Array #Hash_Table #2023_09_07_Time_2_ms_(99.04%)_Space_44.2_MB_(94.23%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input arrays `A` and `B` are not null.*);
//@ ensures(*The length of arrays `A` and `B` is equal.*);
//@ ensures(*The length of arrays `A` and `B` is at least 1.*);
//@ ensures(*The elements in arrays `A` and `B` are integers.*);
//@ ensures(*The elements in arrays `A` and `B` are positive integers.*);
//@ ensures(*The elements in arrays `A` and `B` are unique.*);
//@ ensures(*The elements in arrays `A` and `B` are in the range from 1 to `n`, where `n` is the length of the arrays.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned array `C` is not null.*);
//@ ensures(*The length of the returned array `C` is equal to the length of arrays `A` and `B`.*);
//@ ensures(*The elements in the returned array `C` are integers.*);
//@ ensures(*The elements in the returned array `C` are non-negative.*);
//@ ensures(*The elements in the returned array `C` are in non-decreasing order.*);
//@ ensures(*For each index `i` in the returned array `C`, `C[i]` is equal to the count of numbers that are present at or before the index `i` in both arrays `A` and `B`.*);
    public int[] findThePrefixCommonArray(int[] a, int[] b) {
        int[] result = new int[51];
        int counter = 0;
        for (int i = 0; i < a.length; i++) {
            result[a[i]]++;
            if (result[a[i]] == 2) {
                counter++;
            }
            result[b[i]]++;
            if (result[b[i]] == 2) {
                counter++;
            }
            a[i] = counter;
        }
        return a;
    }
}