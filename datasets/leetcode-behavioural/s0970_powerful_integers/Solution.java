package g0901_1000.s0970_powerful_integers;

// #Medium #Hash_Table #Math #2022_03_31_Time_1_ms_(100.00%)_Space_42.3_MB_(27.54%)

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The values of x and y must be integers greater than or equal to 1 and less than or equal to 100.*);
//@ ensures(*The value of bound must be an integer greater than or equal to 0 and less than or equal to 10^6.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a List<Integer> containing all the powerful integers that have a value less than or equal to the bound.*);
//@ ensures(*Each value in the returned list occurs at most once.*);
//@ ensures(*The order of the values in the returned list can be in any order.*);
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        int iBound = (x == 1 ? 1 : (int) (Math.log10(bound) / Math.log10(x)));
        int jBound = (y == 1 ? 1 : (int) (Math.log10(bound) / Math.log10(y)));
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i <= iBound; i++) {
            for (int j = 0; j <= jBound; j++) {
                int number = (int) (Math.pow(x, i) + Math.pow(y, j));
                if (number <= bound) {
                    set.add(number);
                }
            }
        }
        return new ArrayList<>(set);
    }
}