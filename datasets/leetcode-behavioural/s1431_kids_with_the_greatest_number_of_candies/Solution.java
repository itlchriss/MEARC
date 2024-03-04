package g1401_1500.s1431_kids_with_the_greatest_number_of_candies;

// #Easy #Array #2022_03_28_Time_1_ms_(84.43%)_Space_43.3_MB_(19.35%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `candies` is not null.*);
//@ ensures(*The length of the input array `candies` is greater than or equal to 2.*);
//@ ensures(*The length of the input array `candies` is less than or equal to 100.*);
//@ ensures(*Each element in the input array `candies` is greater than or equal to 1.*);
//@ ensures(*Each element in the input array `candies` is less than or equal to 100.*);
//@ ensures(*The input integer `extraCandies` is greater than or equal to 1.*);
//@ ensures(*The input integer `extraCandies` is less than or equal to 50.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array `result` is not null.*);
//@ ensures(*The length of the output array `result` is equal to the length of the input array `candies`.*);
//@ ensures(*Each element in the output array `result` is either `true` or `false`.*);
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = 0;
        for (int i : candies) {
            max = Math.max(max, i);
        }
        List<Boolean> result = new ArrayList<>();
        for (int candy : candies) {
            result.add(candy + extraCandies >= max);
        }
        return result;
    }
}