package g2701_2800.s2761_prime_pairs_with_target_sum;

// #Medium #Array #Math #Enumeration #Number_Theory
// #2023_09_25_Time_57_ms_(99.24%)_Space_56.4_MB_(10.58%)

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Solution {
    private static final HashSet<Integer> PRIMES = new HashSet<>();
    private static final List<Integer> LIST = new ArrayList<>();
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` is a positive integer greater than or equal to 1.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is a 2D sorted list of prime number pairs.*);
//@ ensures(*Each pair `[x, y]` in the output satisfies the following conditions:*);
//@ ensures(*  - `1 <= x <= y <= n`*);
//@ ensures(*  - `x + y == n`*);
//@ ensures(*  - `x` and `y` are prime numbers.*);
//@ ensures(*The list is sorted in increasing order of `x`.*);
//@ ensures(*If there are no prime number pairs that satisfy the conditions, the output is an empty array.*);

    public List<List<Integer>> findPrimePairs(int n) {
        List<List<Integer>> answer = new ArrayList<>();
        for (int a : LIST) {
            int other = n - a;
            if (other < n / 2 || a > n / 2) {
                break;
            }
            if (PRIMES.contains(other)) {
                answer.add(List.of(a, other));
            }
        }
        return answer;
    }

    static {
        int m = 1000001;
        boolean[] visited = new boolean[m];
        for (int i = 2; i < m; i++) {
            if (!visited[i]) {
                PRIMES.add(i);
                LIST.add(i);
                int j = i;
                while (j < m) {
                    visited[j] = true;
                    j += i;
                }
            }
        }
    }
}