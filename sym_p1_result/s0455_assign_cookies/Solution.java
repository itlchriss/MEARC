package g0401_0500.s0455_assign_cookies;

// #Easy #Array #Sorting #Greedy #2022_07_18_Time_12_ms_(41.00%)_Space_52.6_MB_(78.45%)

import java.util.Arrays;

public class Solution {
//@ requires(*Assume you are an awesome parent and want to give your children some cookies.*);
//@ requires(*But, you should give each child at most one cookie.*);
//@ requires(*Each child `i` has a greed factor `g[i]`, which is the minimum size of a cookie that the child will be content with; and each cookie `j` has a size `s[j]`.*);
//@ requires(*If `s[j] >= g[i]`, we can assign the cookie `j` to the child `i`, and the child `i` will be content.*);
//@ requires(*Your goal is to maximize the number of your content children and output the maximum number.*);
//@ requires(*Example 1:*);
//@ requires(*Input: g = [1,2,3], s = [1,1]*);
//@ requires(*Output: 1*);
//@ requires(*Explanation:*);
//@ requires(*You have 3 children and 2 cookies.*);
//@ requires(*The greed factors of 3 children are 1, 2, 3.*);
//@ requires(*And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content.*);
//@ requires(*You need to output 1.*);
//@ requires(*Example 2:*);
//@ requires(*Input: g = [1,2], s = [1,2,3]*);
//@ requires(*Output: 2*);
//@ requires(*Explanation:*);
//@ requires(*You have 2 children and 3 cookies.*);
//@ requires(*The greed factors of 2 children are 1, 2.*);
//@ requires(*You have 3 cookies and their sizes are big enough to gratify all of the children,*);
//@ requires(*You need to output 2.*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= g.length <= 3  10<sup>4</sup></code>*);
//@ requires(*<code>0 <= s.length <= 3  10<sup>4</sup></code>*);
//@ requires(*<code>1 <= g[i], s[j] <= 2<sup>31</sup> - 1</code>*);
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int result = 0;
        int i = 0;
        int j = 0;
        //@ maintaining 0 <= i <= g.length;
        //@ maintaining 0 <= j <= s.length;
        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) {
                result++;
                i++;
            }
            j++;
        }
        return result;
    }
}