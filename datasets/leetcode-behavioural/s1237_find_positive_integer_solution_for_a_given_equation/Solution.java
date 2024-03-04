package g1201_1300.s1237_find_positive_integer_solution_for_a_given_equation;

// #Medium #Math #Binary_Search #Two_Pointers #Interactive
// #2022_03_12_Time_2_ms_(79.60%)_Space_41.7_MB_(45.96%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    // This is the custom function interface.
    // You should not implement it, or speculate about its implementation
    public interface CustomFunction {
        // Returns f(x, y) for any given positive integers x and y.
        // Note that f(x, y) is increasing with respect to both x and y.
        // i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
        int f(int x, int y);
    }
//@ ensures(*The behavioural requirements (preconditions and postconditions) for the Java method `public List<List<Integer>> findSolution(CustomFunction customfunction, int z)` are as follows:*);
//@ ensures(**);
//@ ensures(*Preconditions:*);
//@ ensures(*1. The `customfunction` parameter must not be null.*);
//@ ensures(*2. The `z` parameter must be a positive integer.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*1. The method must return a list of lists of integers, representing all positive integer pairs `(x, y)` where `f(x, y) == z`.*);
//@ ensures(*2. The order of the pairs in the returned list does not matter.*);
//@ ensures(*3. The returned list must not contain any duplicate pairs.*);
//@ ensures(*4. Each pair `(x, y)` in the returned list must be a positive integer pair, i.e., both `x` and `y` must be positive integers.*);
//@ ensures(*5. The returned list must contain all valid pairs for the given `z` value, according to the hidden formula of the `customfunction`.*);
//@ ensures(*6. The returned list must not contain any invalid pairs, i.e., pairs `(x, y)` for which `f(x, y) != z`.*);
//@ ensures(*7. The returned list must contain all possible valid pairs for the given `z` value, according to the hidden formula of the `customfunction`.*);
//@ ensures(*8. The method must not modify the state of the `customfunction` object.*);
//@ ensures(*9. The method must not modify the value of the `z` parameter.*);
//@ ensures(*10. The method must terminate and return a result in a reasonable amount of time, even for large input values.*);

    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> result = new ArrayList<>();
        int x = 1;
        int y = 1000;
        while (x < 1001 && y > 0) {
            int functionResult = customfunction.f(x, y);
            if (functionResult < z) {
                x++;
            } else if (functionResult > z) {
                y--;
            } else {
                result.add(Arrays.asList(x++, y--));
            }
        }
        return result;
    }
}