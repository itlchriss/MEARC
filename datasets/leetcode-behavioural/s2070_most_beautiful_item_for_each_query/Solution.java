package g2001_2100.s2070_most_beautiful_item_for_each_query;

// #Medium #Array #Sorting #Binary_Search #2022_05_30_Time_53_ms_(96.04%)_Space_119.9_MB_(48.51%)

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `items` is not null.*);
//@ ensures(*The input array `queries` is not null.*);
//@ ensures(*The length of `items` is greater than or equal to 1.*);
//@ ensures(*The length of `queries` is greater than or equal to 1.*);
//@ ensures(*Each element in `items` is an array of length 2.*);
//@ ensures(*The price and beauty values in `items` are positive integers.*);
//@ ensures(*The price and beauty values in `items` are less than or equal to 10^9.*);
//@ ensures(*The values in `queries` are positive integers.*);
//@ ensures(*The values in `queries` are less than or equal to 10^9.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned array `answer` is not null.*);
//@ ensures(*The length of `answer` is equal to the length of `queries`.*);
//@ ensures(*Each element in `answer` is a non-negative integer.*);
//@ ensures(*The maximum beauty value in `answer` is less than or equal to 10^9.*);
//@ ensures(*The maximum beauty value in `answer` is the maximum beauty of an item whose price is less than or equal to the corresponding query value in `queries`.*);
    public int[] maximumBeauty(int[][] items, int[] queries) {
        int[] res = new int[queries.length];
        Arrays.sort(items, Comparator.comparingInt(a -> a[1]));
        for (int i = 0; i < res.length; i++) {
            res[i] = maxBeauty(items, queries[i]);
        }
        return res;
    }

    private int maxBeauty(int[][] items, int query) {
        for (int i = items.length - 1; i >= 0; i--) {
            int price = items[i][0];
            int beauty = items[i][1];
            if (price <= query) {
                return beauty;
            }
        }
        return 0;
    }
}