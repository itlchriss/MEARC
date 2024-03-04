package g2101_2200.s2103_rings_and_rods;

// #Easy #String #Hash_Table #2022_05_31_Time_2_ms_(46.84%)_Space_42.2_MB_(29.77%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `rings` must have a length of `2n`.*);
//@ ensures(*The value of `n` must be between 1 and 100 (inclusive).*);
//@ ensures(*The characters at even indices in `rings` (0, 2, 4, ...) must be either 'R', 'G', or 'B'.*);
//@ ensures(*The characters at odd indices in `rings` (1, 3, 5, ...) must be digits from '0' to '9'.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return an integer representing the number of rods that have all three colors of rings on them.*);
    public int countPoints(String rings) {
        Map<Integer, Integer> redHashMap = new HashMap<>();
        Map<Integer, Integer> greenHashMap = new HashMap<>();
        Map<Integer, Integer> blueHashMap = new HashMap<>();
        for (int i = 0; i <= rings.length() - 2; i = i + 2) {
            char charOne = rings.charAt(i);
            char charTwo = rings.charAt(i + 1);

            if (charOne == 'R') {
                redHashMap.put(Character.getNumericValue(charTwo), 123);
            } else if (charOne == 'G') {
                greenHashMap.put(Character.getNumericValue(charTwo), 123);
            } else {
                blueHashMap.put(Character.getNumericValue(charTwo), 123);
            }
        }
        int result = 0;
        for (int i = 0; i <= 9; i++) {
            if (redHashMap.containsKey(i)
                    && greenHashMap.containsKey(i)
                    && blueHashMap.containsKey(i)) {
                result++;
            }
        }
        return result;
    }
}